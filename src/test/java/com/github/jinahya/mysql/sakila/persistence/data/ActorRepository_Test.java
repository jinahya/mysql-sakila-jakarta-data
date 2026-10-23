package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Actor;
import com.github.jinahya.mysql.sakila.persistence.Actor_;
import com.github.jinahya.mysql.sakila.persistence.Film;
import com.github.jinahya.mysql.sakila.persistence.FilmActor;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.data.Order;
import jakarta.data.Sort;
import jakarta.data.page.PageRequest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;

/**
 * The unit tests of {@link ActorRepository}.
 */
@AddBeanClasses(ActorRepository_.class)
class ActorRepository_Test
        extends _Repository_Test<ActorRepository, Actor, Integer> {

    ActorRepository_Test() {
        super(ActorRepository.class, Actor.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private Actor persistActor(final EntityManager em, final String firstName,
                               final String lastName) {
        final var actor = EntityPersisterUtils.newPersistedInstanceOf(em, Actor.class);
        actor.setFirstName(firstName);
        actor.setLastName(lastName);
        return actor;
    }

    private Film persistFilm(final EntityManager em, final Film.Rating rating) {
        final var film = EntityPersisterUtils.newPersistedInstanceOf(em, Film.class);
        film.setRating(rating);
        return film;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @DisplayName("SAKILA-ACTOR-B-01: listByLastName(lastName)")
    @Nested
    class ListByLastNameTest {

        @Test
        void __returnsActorsOfTheLastName() {
            final var ids = entityManagerFactory.callInTransaction(em -> new Integer[]{
                    persistActor(em, "PENELOPE", "GUINESS").getActorId(),
                    persistActor(em, "NICK", "WAHLBERG").getActorId(),
                    persistActor(em, "SEAN", "GUINESS").getActorId()
            });
            final var list = getRepositoryInstance().listByLastName("GUINESS");
            assertThat(list).extracting(Actor::getActorId).containsExactlyInAnyOrder(ids[0], ids[2]);
        }

        @Test
        void __orderedByActorId() {
            entityManagerFactory.runInTransaction(em -> {
                for (int i = 0; i < 5; i++) {
                    persistActor(em, "F" + i, "GUINESS");
                }
            });
            final var list = getRepositoryInstance().listByLastName("GUINESS");
            assertThat(list).hasSize(5).extracting(Actor::getActorId).isSorted();
        }

        @Test
        void __emptyWhenNoneMatches() {
            assertThat(getRepositoryInstance().listByLastName("GUINESS")).isEmpty();
        }

        @Test
        void __nullLastName_npe() {
            assertThatThrownBy(() -> getRepositoryInstance().listByLastName(null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @DisplayName("SAKILA-ACTOR-B-03: streamByLastNameStartingWith(prefix)")
    @Nested
    class StreamByLastNameStartingWithTest {

        @Test
        void __returnsActorsOfThePrefix() {
            entityManagerFactory.runInTransaction(em -> {
                persistActor(em, "NICK", "WAHLBERG");
                persistActor(em, "BELA", "WALKEN");
                persistActor(em, "PENELOPE", "GUINESS");
            });
            try (var stream = getRepositoryInstance().streamByLastNameStartingWith("WA")) {
                assertThat(stream).extracting(Actor::getLastName)
                        .containsExactlyInAnyOrder("WAHLBERG", "WALKEN");
            }
        }

        @Test
        void __orderedByLastNameThenActorId() {
            entityManagerFactory.runInTransaction(em -> {
                persistActor(em, "ALEC", "WAYNE");
                persistActor(em, "NICK", "WAHLBERG");
                persistActor(em, "BELA", "WALKEN");
                persistActor(em, "DARYL", "WAHLBERG");
            });
            try (var stream = getRepositoryInstance().streamByLastNameStartingWith("WA")) {
                final var list = stream.toList();
                assertThat(list).extracting(Actor::getLastName)
                        .containsExactly("WAHLBERG", "WAHLBERG", "WALKEN", "WAYNE");
                assertThat(list.get(0).getActorId()).isLessThan(list.get(1).getActorId());
            }
        }

        // a @Query parameter is bound as it is, unchecked; LIKE NULL matches nothing
        @Test
        void __nullPrefix_matchesNothing() {
            entityManagerFactory.runInTransaction(em -> persistActor(em, "NICK", "WAHLBERG"));
            try (var stream = getRepositoryInstance().streamByLastNameStartingWith(null)) {
                assertThat(stream).isEmpty();
            }
        }
    }

    @DisplayName("SAKILA-ACTOR-I-02: selectFilmCounts(pageRequest)")
    @Nested
    class SelectFilmCountsTest {

        // actors with 3, 1 and 2 films, and one with none
        private Integer[] persist() {
            return entityManagerFactory.callInTransaction(em -> {
                final var a = persistActor(em, "A", "A");
                final var b = persistActor(em, "B", "B");
                final var c = persistActor(em, "C", "C");
                persistActor(em, "D", "D");
                final var f1 = persistFilm(em, Film.Rating.G);
                final var f2 = persistFilm(em, Film.Rating.G);
                final var f3 = persistFilm(em, Film.Rating.G);
                em.persist(FilmActor.of(a, f1));
                em.persist(FilmActor.of(a, f2));
                em.persist(FilmActor.of(a, f3));
                em.persist(FilmActor.of(b, f1));
                em.persist(FilmActor.of(c, f1));
                em.persist(FilmActor.of(c, f2));
                return new Integer[]{a.getActorId(), b.getActorId(), c.getActorId()};
            });
        }

        @Test
        void __returnsActorsWithFilmCounts() {
            final var ids = persist();
            final var page = getRepositoryInstance().selectFilmCounts(PageRequest.ofSize(10));
            assertThat(page.content())
                    .extracting(ActorRepository.ActorFilmCount::actorId,
                                ActorRepository.ActorFilmCount::films)
                    .containsExactlyInAnyOrder(tuple(ids[0], 3L), tuple(ids[1], 1L),
                                               tuple(ids[2], 2L));
        }

        @Test
        void __orderedByFilmsDescending() {
            final var ids = persist();
            final var page = getRepositoryInstance().selectFilmCounts(PageRequest.ofSize(10));
            assertThat(page.content()).extracting(ActorRepository.ActorFilmCount::actorId)
                    .containsExactly(ids[0], ids[2], ids[1]);
        }

        @Test
        void __paged() {
            final var ids = persist();
            final var first = getRepositoryInstance().selectFilmCounts(PageRequest.ofSize(2));
            assertThat(first.content()).extracting(ActorRepository.ActorFilmCount::actorId)
                    .containsExactly(ids[0], ids[2]);
            assertThat(first.totalElements()).isEqualTo(3L);
            final var second = getRepositoryInstance().selectFilmCounts(first.nextPageRequest());
            assertThat(second.content()).extracting(ActorRepository.ActorFilmCount::actorId)
                    .containsExactly(ids[1]);
            assertThat(second.hasNext()).isFalse();
        }

        @Test
        void __nullPageRequest_npe() {
            assertThatThrownBy(() -> getRepositoryInstance().selectFilmCounts(null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @DisplayName("SAKILA-ACTOR-I-03: listWithoutFilms(pageRequest, order)")
    @Nested
    class ListWithoutFilmsTest {

        private Integer[] persist() {
            return entityManagerFactory.callInTransaction(em -> {
                final var a = persistActor(em, "A", "A");
                final var b = persistActor(em, "B", "B");
                final var c = persistActor(em, "C", "C");
                em.persist(FilmActor.of(b, persistFilm(em, Film.Rating.G)));
                return new Integer[]{a.getActorId(), b.getActorId(), c.getActorId()};
            });
        }

        @Test
        void __returnsActorsWithoutFilms() {
            final var ids = persist();
            final var page = getRepositoryInstance().listWithoutFilms(
                    PageRequest.ofSize(10), Order.by(Sort.asc(Actor_.ACTOR_ID)));
            assertThat(page.content()).extracting(Actor::getActorId)
                    .containsExactlyInAnyOrder(ids[0], ids[2]);
        }

        @Test
        void __ordered() {
            final var ids = persist();
            final var page = getRepositoryInstance().listWithoutFilms(
                    PageRequest.ofSize(10), Order.by(Sort.desc(Actor_.ACTOR_ID)));
            assertThat(page.content()).extracting(Actor::getActorId)
                    .containsExactly(Math.max(ids[0], ids[2]), Math.min(ids[0], ids[2]));
        }

        @Test
        void __paged() {
            persist();
            final var page = getRepositoryInstance().listWithoutFilms(
                    PageRequest.ofSize(1), Order.by(Sort.asc(Actor_.ACTOR_ID)));
            assertThat(page.content()).hasSize(1);
            assertThat(page.totalElements()).isEqualTo(2L);
            assertThat(page.hasNext()).isTrue();
        }

        @Test
        void __nullArguments_npe() {
            final var repository = getRepositoryInstance();
            assertThatThrownBy(() -> repository.listWithoutFilms(null, Order.by()))
                    .isInstanceOf(NullPointerException.class);
            assertThatThrownBy(() -> repository.listWithoutFilms(PageRequest.ofSize(1), null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @DisplayName("SAKILA-ACTOR-A-01: listInFilmsOfEveryRating(pageRequest, order)")
    @Nested
    class ListInFilmsOfEveryRatingTest {

        // films of G and PG; a in both, b in G only, c in both
        private Integer[] persist() {
            return entityManagerFactory.callInTransaction(em -> {
                final var a = persistActor(em, "A", "A");
                final var b = persistActor(em, "B", "B");
                final var c = persistActor(em, "C", "C");
                final var g = persistFilm(em, Film.Rating.G);
                final var pg = persistFilm(em, Film.Rating.PG);
                em.persist(FilmActor.of(a, g));
                em.persist(FilmActor.of(a, pg));
                em.persist(FilmActor.of(b, g));
                em.persist(FilmActor.of(c, g));
                em.persist(FilmActor.of(c, pg));
                return new Integer[]{a.getActorId(), b.getActorId(), c.getActorId()};
            });
        }

        @Test
        void __returnsActorsInFilmsOfEveryRating() {
            final var ids = persist();
            final var page = getRepositoryInstance().listInFilmsOfEveryRating(
                    PageRequest.ofSize(10), Order.by(Sort.asc(Actor_.ACTOR_ID)));
            assertThat(page.content()).extracting(Actor::getActorId)
                    .containsExactlyInAnyOrder(ids[0], ids[2]);
        }

        @Test
        void __ordered() {
            final var ids = persist();
            final var page = getRepositoryInstance().listInFilmsOfEveryRating(
                    PageRequest.ofSize(10), Order.by(Sort.desc(Actor_.ACTOR_ID)));
            assertThat(page.content()).extracting(Actor::getActorId)
                    .containsExactly(Math.max(ids[0], ids[2]), Math.min(ids[0], ids[2]));
        }

        @Test
        void __paged() {
            final var ids = persist();
            final var page = getRepositoryInstance().listInFilmsOfEveryRating(
                    PageRequest.ofSize(1), Order.by(Sort.asc(Actor_.ACTOR_ID)));
            assertThat(page.content()).extracting(Actor::getActorId)
                    .containsExactly(Math.min(ids[0], ids[2]));
            assertThat(page.totalElements()).isEqualTo(2L);
        }

        @Test
        void __nullArguments_npe() {
            final var repository = getRepositoryInstance();
            assertThatThrownBy(() -> repository.listInFilmsOfEveryRating(null, Order.by()))
                    .isInstanceOf(NullPointerException.class);
            assertThatThrownBy(
                    () -> repository.listInFilmsOfEveryRating(PageRequest.ofSize(1), null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @DisplayName("SAKILA-ACTOR-A-02: selectFilmCountRanksAtMost(filmRank, pageRequest)")
    @Nested
    class SelectFilmCountRanksAtMostTest {

        // a: 3 films (rank 1), b and c: 2 films (rank 2), d: 1 film (rank 3)
        private Integer[] persist() {
            return entityManagerFactory.callInTransaction(em -> {
                final var a = persistActor(em, "A", "A");
                final var b = persistActor(em, "B", "B");
                final var c = persistActor(em, "C", "C");
                final var d = persistActor(em, "D", "D");
                final var f1 = persistFilm(em, Film.Rating.G);
                final var f2 = persistFilm(em, Film.Rating.G);
                final var f3 = persistFilm(em, Film.Rating.G);
                em.persist(FilmActor.of(a, f1));
                em.persist(FilmActor.of(a, f2));
                em.persist(FilmActor.of(a, f3));
                em.persist(FilmActor.of(b, f1));
                em.persist(FilmActor.of(b, f2));
                em.persist(FilmActor.of(c, f1));
                em.persist(FilmActor.of(c, f2));
                em.persist(FilmActor.of(d, f1));
                return new Integer[]{a.getActorId(), b.getActorId(), c.getActorId(),
                        d.getActorId()};
            });
        }

        @Test
        void __returnsActorsRankedAtMost_tiesIncluded() {
            final var ids = persist();
            final var page = getRepositoryInstance().selectFilmCountRanksAtMost(
                    2L, PageRequest.ofSize(10));
            assertThat(page.content())
                    .extracting(ActorRepository.ActorFilmCountRank::actorId,
                                ActorRepository.ActorFilmCountRank::films,
                                ActorRepository.ActorFilmCountRank::filmRank)
                    .containsExactlyInAnyOrder(tuple(ids[0], 3, 1L), tuple(ids[1], 2, 2L),
                                               tuple(ids[2], 2, 2L));
        }

        @Test
        void __orderedByFilmRankThenActorId() {
            final var ids = persist();
            final var page = getRepositoryInstance().selectFilmCountRanksAtMost(
                    3L, PageRequest.ofSize(10));
            assertThat(page.content()).extracting(ActorRepository.ActorFilmCountRank::actorId)
                    .containsExactly(ids[0], ids[1], ids[2], ids[3]);
        }

        @Test
        void __paged() {
            final var ids = persist();
            final var first = getRepositoryInstance().selectFilmCountRanksAtMost(
                    3L, PageRequest.ofSize(3));
            assertThat(first.totalElements()).isEqualTo(4L);
            final var second = getRepositoryInstance().selectFilmCountRanksAtMost(
                    3L, first.nextPageRequest());
            assertThat(second.content()).extracting(ActorRepository.ActorFilmCountRank::actorId)
                    .containsExactly(ids[3]);
        }

        @Test
        void __nullPageRequest_npe() {
            assertThatThrownBy(() -> getRepositoryInstance().selectFilmCountRanksAtMost(1L, null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Inject
    private EntityManagerFactory entityManagerFactory;
}
