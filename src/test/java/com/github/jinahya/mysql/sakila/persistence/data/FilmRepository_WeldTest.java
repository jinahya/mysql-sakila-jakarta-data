package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link FilmRepository} in a CDI container.
 */
class FilmRepository_WeldTest
        extends _Repository_WeldTest<FilmRepository> {

    FilmRepository_WeldTest() {
        super(FilmRepository.class);
    }
}
