package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link FilmCategoryRepository} in a CDI container.
 */
class FilmCategoryRepository_WeldTest
        extends _Repository_WeldTest<FilmCategoryRepository> {

    FilmCategoryRepository_WeldTest() {
        super(FilmCategoryRepository.class);
    }
}
