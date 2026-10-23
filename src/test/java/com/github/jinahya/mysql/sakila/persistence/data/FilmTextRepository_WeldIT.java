package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link FilmTextRepository} in a CDI container.
 */
class FilmTextRepository_WeldIT
        extends _Repository_WeldIT<FilmTextRepository> {

    FilmTextRepository_WeldIT() {
        super(FilmTextRepository.class);
    }
}
