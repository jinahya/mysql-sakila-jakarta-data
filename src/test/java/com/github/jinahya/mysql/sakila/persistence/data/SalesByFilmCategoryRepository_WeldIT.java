package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link SalesByFilmCategoryRepository} in a CDI container.
 */
class SalesByFilmCategoryRepository_WeldIT
        extends _Repository_WeldIT<SalesByFilmCategoryRepository> {

    SalesByFilmCategoryRepository_WeldIT() {
        super(SalesByFilmCategoryRepository.class);
    }
}
