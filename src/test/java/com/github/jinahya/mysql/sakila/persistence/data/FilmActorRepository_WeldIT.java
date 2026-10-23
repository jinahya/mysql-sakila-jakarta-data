package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link FilmActorRepository} in a CDI container.
 */
class FilmActorRepository_WeldIT
        extends _Repository_WeldIT<FilmActorRepository> {

    FilmActorRepository_WeldIT() {
        super(FilmActorRepository.class);
    }
}
