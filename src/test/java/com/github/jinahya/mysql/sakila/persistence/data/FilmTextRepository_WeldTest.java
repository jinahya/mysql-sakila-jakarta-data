package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link FilmTextRepository} in a CDI container.
 */
class FilmTextRepository_WeldTest
        extends _Repository_WeldTest<FilmTextRepository> {

    FilmTextRepository_WeldTest() {
        super(FilmTextRepository.class);
    }
}
