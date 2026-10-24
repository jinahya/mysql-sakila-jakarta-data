package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Actor;
import com.github.jinahya.mysql.sakila.persistence.Actor_;
import jakarta.data.Order;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.By;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.OrderBy;
import jakarta.data.repository.Param;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;

import java.util.List;
import java.util.stream.Stream;

/**
 * A repository for {@link Actor}, the {@code actor} table of the Sakila database.
 * <p>
 * The exercise methods answer the exercises of upstream's {@code EXERCISES.asciidoc} on the
 * {@code actor} table, one method per exercise, each named in its javadoc by the exercise's id.
 */
@Repository
public interface ActorRepository
        extends _Repository<Actor, Integer>,
                CrudRepository<Actor, Integer> {

    // ----------------------------------------------------------------------------------- exercises

    /**
     * An actor, by id and names, and how many films the actor appears in; a row of
     * {@link #selectFilmCounts(PageRequest)}.
     *
     * @param actorId   the id of the actor.
     * @param firstName the first name of the actor.
     * @param lastName  the last name of the actor.
     * @param films     the number of films the actor appears in.
     */
    record ActorFilmCount(Integer actorId, String firstName, String lastName, long films) {
    }

    /**
     * An actor, by id and names, how many films the actor appears in, and the dense rank of that
     * number among every actor's; a row of {@link #selectFilmCountRanksAtMost(long, PageRequest)}.
     *
     * @param actorId   the id of the actor.
     * @param firstName the first name of the actor.
     * @param lastName  the last name of the actor.
     * @param films     the number of films the actor appears in, as JPQL's {@code SIZE} gives it.
     * @param filmRank  the dense rank of {@code films}, {@code 1} for the most.
     */
    record ActorFilmCountRank(Integer actorId, String firstName, String lastName, int films,
                              long filmRank) {
    }

    /**
     * Lists the actors whose {@link Actor_#LAST_NAME lastName} is equal to the specified value
     * ({@code SAKILA_ACTOR_B_01}, the book's pick, which orders by {@link Actor_#ACTOR_ID actorId}).
     *
     * @param lastName the last name to match.
     * @return a list of the actors whose last name is {@code lastName}, by their ids.
     */
    @Find
    @OrderBy(Actor_.ACTOR_ID)
    List<Actor> listByLastName(@By(Actor_.LAST_NAME) String lastName);

    /**
     * Streams the actors whose {@link Actor_#LAST_NAME lastName} starts with the specified prefix
     * ({@code SAKILA_ACTOR_B_03}, this module's own extra, which orders by
     * {@link Actor_#LAST_NAME lastName}, then by {@link Actor_#ACTOR_ID actorId}).
     * <p>
     * The trailing {@code %} is added in the query, so the caller passes the prefix alone. A
     * {@code @Query} parameter is bound unchecked, and a {@code null} prefix matches nothing. The
     * stream reads the rows as it is consumed; close it.
     *
     * @param prefix the prefix of the last name to match.
     * @return a stream of the actors whose last name starts with {@code prefix}, by their last names
     *         and ids.
     */
    @Query("""
            where lastName like :prefix || '%'
            order by lastName, actorId""")
    Stream<Actor> streamByLastNameStartingWith(@Param("prefix") String prefix);

    /**
     * Selects every actor who appears in a film, with the number of the films
     * ({@code SAKILA_ACTOR_I_02}, the book's pick, which orders by {@code films} descending, then by
     * {@code actorId}; the exercise's {@code LIMIT 5} is the first page of five).
     * <p>
     * The order is in the query: Hibernate takes an {@link Order} only for a method that returns the
     * entity, and this one returns a {@link ActorFilmCount} per group.
     *
     * @param pageRequest the page to return.
     * @return a page of the actors with their film counts, the most first.
     */
    @Query("""
            select a.actorId, a.firstName, a.lastName, count(fa)
            from Actor a
                     join a.filmActors fa
            group by a.actorId, a.firstName, a.lastName
            order by count(fa) desc, a.actorId""")
    Page<ActorFilmCount> selectFilmCounts(PageRequest pageRequest);

    /**
     * Lists the actors who appear in no film ({@code SAKILA_ACTOR_I_03}, this module's own extra,
     * which asks for no order; sort by {@link Actor_#ACTOR_ID actorId} for stable pages).
     *
     * @param pageRequest the page to return.
     * @param order       the order of the actors.
     * @return a page of the actors who appear in no film.
     */
    @Query("""
            select a
            from Actor a
            where not exists (select fa from FilmActor fa where fa.actor = a)""")
    Page<Actor> listWithoutFilms(PageRequest pageRequest, Order<Actor> order);

    /**
     * Lists the actors who appear in a film of every rating any film has ({@code SAKILA_ACTOR_A_01},
     * the book's pick, which orders by {@link Actor_#ACTOR_ID actorId}; the exercise's
     * {@code LIMIT 5} is the first page of five).
     *
     * @param pageRequest the page to return.
     * @param order       the order of the actors.
     * @return a page of the actors who appear in a film of every rating.
     */
    @Query("""
            select a
            from Actor a
            where (select count(distinct fa.film.rating) from FilmActor fa where fa.actor = a)
                  = (select count(distinct f.rating) from Film f)""")
    Page<Actor> listInFilmsOfEveryRating(PageRequest pageRequest, Order<Actor> order);

    /**
     * Selects the actors whose film counts rank at most the specified rank, ties included
     * ({@code SAKILA_ACTOR_A_02}, this module's own extra, which ranks densely, the most films
     * first, and orders by {@code filmRank}, then by {@code actorId}; the exercise's rank is
     * {@code 7}).
     * <p>
     * JPQL has no {@code DENSE_RANK()}; an actor's rank is the number of the distinct film counts at
     * or above the actor's, each counted once, by the first actor who has it. The order is in the
     * query, as in {@link #selectFilmCounts(PageRequest)}.
     *
     * @param filmRank    the largest rank to select.
     * @param pageRequest the page to return.
     * @return a page of the actors whose film counts rank at most {@code filmRank}.
     */
    @Query("""
            select a.actorId, a.firstName, a.lastName,
                   size(a.filmActors), count(b)
            from Actor a, Actor b
            where size(b.filmActors) >= size(a.filmActors)
              and not exists (select b2
                              from Actor b2
                              where size(b2.filmActors) = size(b.filmActors)
                                and b2.actorId < b.actorId)
            group by a.actorId, a.firstName, a.lastName
            having count(b) <= :filmRank
            order by count(b), a.actorId""")
    Page<ActorFilmCountRank> selectFilmCountRanksAtMost(@Param("filmRank") long filmRank,
                                                        PageRequest pageRequest);
}
