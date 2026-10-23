package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link FilmCategoryRepository} in a CDI container.
 */
class FilmCategoryRepository_WeldIT
        extends _Repository_WeldIT<FilmCategoryRepository> {

    FilmCategoryRepository_WeldIT() {
        super(FilmCategoryRepository.class);
    }
}
