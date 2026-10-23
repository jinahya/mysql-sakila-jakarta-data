package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link FilmActorRepository} in a CDI container.
 */
class FilmActorRepository_WeldTest
        extends _Repository_WeldTest<FilmActorRepository> {

    FilmActorRepository_WeldTest() {
        super(FilmActorRepository.class);
    }
}
