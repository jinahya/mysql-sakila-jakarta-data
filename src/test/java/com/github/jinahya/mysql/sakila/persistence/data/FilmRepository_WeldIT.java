package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link FilmRepository} in a CDI container.
 */
class FilmRepository_WeldIT
        extends _Repository_WeldIT<FilmRepository> {

    FilmRepository_WeldIT() {
        super(FilmRepository.class);
    }
}
