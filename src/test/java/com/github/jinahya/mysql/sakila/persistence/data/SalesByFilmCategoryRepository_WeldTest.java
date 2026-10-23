package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link SalesByFilmCategoryRepository} in a CDI container.
 */
class SalesByFilmCategoryRepository_WeldTest
        extends _Repository_WeldTest<SalesByFilmCategoryRepository> {

    SalesByFilmCategoryRepository_WeldTest() {
        super(SalesByFilmCategoryRepository.class);
    }
}
