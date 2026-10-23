package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link CategoryRepository} in a CDI container.
 */
class CategoryRepository_WeldTest
        extends _Repository_WeldTest<CategoryRepository> {

    CategoryRepository_WeldTest() {
        super(CategoryRepository.class);
    }
}
