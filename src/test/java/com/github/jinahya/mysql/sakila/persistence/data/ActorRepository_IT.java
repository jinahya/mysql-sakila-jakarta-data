package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Actor;
import com.github.jinahya.mysql.sakila.persistence.Actor_;
import jakarta.data.Order;
import jakarta.data.Sort;
import jakarta.data.page.PageRequest;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;

/**
 * The integration tests of {@link ActorRepository}.
 * <p>
 * The expected rows are the ones upstream's {@code actor_exercises_0N_*.sql} and
 * {@code Actor_Exercises_0N_*_PersistenceIT} give.
 */
@AddBeanClasses(ActorRepository_.class)
class ActorRepository_IT
        extends _Repository_IT<ActorRepository, Actor, Integer> {

    ActorRepository_IT() {
        super(ActorRepository.class, Actor.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @DisplayName("SAKILA_ACTOR_B_01: listByLastName(lastName)")
    @Nested
    class ListByLastNameTest {

        @Test
        void __returnsTheGuinesses() {
            final var list = getRepositoryInstance().listByLastName("GUINESS");
            assertThat(list).extracting(Actor::getActorId, Actor::getFirstName)
                    .containsExactlyInAnyOrder(tuple(1, "PENELOPE"), tuple(90, "SEAN"),
                                               tuple(179, "ED"));
        }

        @Test
        void __orderedByActorId() {
            final var list = getRepositoryInstance().listByLastName("GUINESS");
            assertThat(list).extracting(Actor::getActorId).containsExactly(1, 90, 179);
        }

        @Test
        void __nullLastName_npe() {
            assertThatThrownBy(() -> getRepositoryInstance().listByLastName(null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @DisplayName("SAKILA_ACTOR_B_03: streamByLastNameStartingWith(prefix)")
    @Nested
    class StreamByLastNameStartingWithTest {

        @Test
        void __returnsActorsOfWa() {
            try (var stream = getRepositoryInstance().streamByLastNameStartingWith("WA")) {
                assertThat(stream).extracting(Actor::getActorId)
                        .containsExactlyInAnyOrder(2, 95, 196, 29);
            }
        }

        @Test
        void __orderedByLastNameThenActorId() {
            try (var stream = getRepositoryInstance().streamByLastNameStartingWith("WA")) {
                assertThat(stream).extracting(Actor::getActorId, Actor::getLastName)
                        .containsExactly(tuple(2, "WAHLBERG"), tuple(95, "WAHLBERG"),
                                         tuple(196, "WALKEN"), tuple(29, "WAYNE"));
            }
        }

        @Test
        void __nullPrefix_matchesNothing() {
            try (var stream = getRepositoryInstance().streamByLastNameStartingWith(null)) {
                assertThat(stream).isEmpty();
            }
        }
    }

    @DisplayName("SAKILA_ACTOR_I_02: selectFilmCounts(pageRequest)")
    @Nested
    class SelectFilmCountsTest {

        @Test
        void __returnsTheFiveActorsInTheMostFilms() {
            final var page = getRepositoryInstance().selectFilmCounts(PageRequest.ofSize(5));
            assertThat(page.content())
                    .extracting(ActorRepository.ActorFilmCount::actorId,
                                ActorRepository.ActorFilmCount::firstName,
                                ActorRepository.ActorFilmCount::lastName,
                                ActorRepository.ActorFilmCount::films)
                    .containsExactlyInAnyOrder(
                            tuple(107, "GINA", "DEGENERES", 42L),
                            tuple(102, "WALTER", "TORN", 41L),
                            tuple(198, "MARY", "KEITEL", 40L),
                            tuple(181, "MATTHEW", "CARREY", 39L),
                            tuple(23, "SANDRA", "KILMER", 37L));
        }

        @Test
        void __orderedByFilmsDescending() {
            final var page = getRepositoryInstance().selectFilmCounts(PageRequest.ofSize(5));
            assertThat(page.content()).extracting(ActorRepository.ActorFilmCount::actorId)
                    .containsExactly(107, 102, 198, 181, 23);
        }

        @Test
        void __paged() {
            final var page = getRepositoryInstance().selectFilmCounts(PageRequest.ofPage(2, 5, true));
            assertThat(page.content()).hasSize(5).extracting(ActorRepository.ActorFilmCount::films)
                    .allMatch(f -> f <= 37L);
            assertThat(page.totalElements()).isEqualTo(200L);
        }

        @Test
        void __nullPageRequest_npe() {
            assertThatThrownBy(() -> getRepositoryInstance().selectFilmCounts(null))
                    .isInstanceOf(NullPointerException.class);
        }
    }

    @DisplayName("SAKILA_ACTOR_I_03: listWithoutFilms(pageRequest, order)")
    @Nested
    class ListWithoutFilmsTest {

        // no rows
        @Test
        void __returnsNoActor() {
            final var page = getRepositoryInstance().listWithoutFilms(
                    PageRequest.ofSize(10), Order.by(Sort.asc(Actor_.ACTOR_ID)));
            assertThat(page.content()).isEmpty();
        }

        @Test
        void __paged() {
            final var page = getRepositoryInstance().listWithoutFilms(
                    PageRequest.ofSize(10), Order.by(Sort.asc(Actor_.ACTOR_ID)));
            assertThat(page.totalElements()).isZero();
            assertThat(page.hasNext()).isFalse();
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

    @DisplayName("SAKILA_ACTOR_A_01: listInFilmsOfEveryRating(pageRequest, order)")
    @Nested
    class ListInFilmsOfEveryRatingTest {

        @Test
        void __returnsTheFirstFive() {
            final var page = getRepositoryInstance().listInFilmsOfEveryRating(
                    PageRequest.ofSize(5), Order.by(Sort.asc(Actor_.ACTOR_ID)));
            assertThat(page.content()).extracting(Actor::getActorId)
                    .containsExactlyInAnyOrder(1, 2, 3, 4, 5);
        }

        @Test
        void __orderedByActorId() {
            final var page = getRepositoryInstance().listInFilmsOfEveryRating(
                    PageRequest.ofSize(5), Order.by(Sort.asc(Actor_.ACTOR_ID)));
            assertThat(page.content()).extracting(Actor::getActorId)
                    .containsExactly(1, 2, 3, 4, 5);
        }

        @Test
        void __paged() {
            final var first = getRepositoryInstance().listInFilmsOfEveryRating(
                    PageRequest.ofSize(5), Order.by(Sort.asc(Actor_.ACTOR_ID)));
            final var second = getRepositoryInstance().listInFilmsOfEveryRating(
                    first.nextPageRequest(), Order.by(Sort.asc(Actor_.ACTOR_ID)));
            assertThat(second.content()).extracting(Actor::getActorId)
                    .allMatch(id -> id > 5).isSorted();
            assertThat(second.totalElements()).isEqualTo(first.totalElements());
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

    @DisplayName("SAKILA_ACTOR_A_02: selectFilmCountRanksAtMost(filmRank, pageRequest)")
    @Nested
    class SelectFilmCountRanksAtMostTest {

        @Test
        void __returnsTheTopSevenWithTies() {
            final var page = getRepositoryInstance().selectFilmCountRanksAtMost(
                    7L, PageRequest.ofSize(20));
            assertThat(page.content())
                    .extracting(ActorRepository.ActorFilmCountRank::actorId,
                                ActorRepository.ActorFilmCountRank::firstName,
                                ActorRepository.ActorFilmCountRank::lastName,
                                ActorRepository.ActorFilmCountRank::films,
                                ActorRepository.ActorFilmCountRank::filmRank)
                    .containsExactlyInAnyOrder(
                            tuple(107, "GINA", "DEGENERES", 42, 1L),
                            tuple(102, "WALTER", "TORN", 41, 2L),
                            tuple(198, "MARY", "KEITEL", 40, 3L),
                            tuple(181, "MATTHEW", "CARREY", 39, 4L),
                            tuple(23, "SANDRA", "KILMER", 37, 5L),
                            tuple(81, "SCARLETT", "DAMON", 36, 6L),
                            tuple(13, "UMA", "WOOD", 35, 7L),
                            tuple(37, "VAL", "BOLGER", 35, 7L),
                            tuple(60, "HENRY", "BERRY", 35, 7L),
                            tuple(106, "GROUCHO", "DUNST", 35, 7L),
                            tuple(144, "ANGELA", "WITHERSPOON", 35, 7L),
                            tuple(158, "VIVIEN", "BASINGER", 35, 7L));
        }

        @Test
        void __orderedByFilmRankThenActorId() {
            final var page = getRepositoryInstance().selectFilmCountRanksAtMost(
                    7L, PageRequest.ofSize(20));
            assertThat(page.content()).extracting(ActorRepository.ActorFilmCountRank::actorId)
                    .containsExactly(107, 102, 198, 181, 23, 81, 13, 37, 60, 106, 144, 158);
        }

        @Test
        void __paged() {
            final var page = getRepositoryInstance().selectFilmCountRanksAtMost(
                    7L, PageRequest.ofPage(2, 5, true));
            assertThat(page.content()).extracting(ActorRepository.ActorFilmCountRank::actorId)
                    .containsExactly(81, 13, 37, 60, 106);
            assertThat(page.totalElements()).isEqualTo(12L);
        }

        @Test
        void __nullPageRequest_npe() {
            assertThatThrownBy(() -> getRepositoryInstance().selectFilmCountRanksAtMost(7L, null))
                    .isInstanceOf(NullPointerException.class);
        }
    }
}
