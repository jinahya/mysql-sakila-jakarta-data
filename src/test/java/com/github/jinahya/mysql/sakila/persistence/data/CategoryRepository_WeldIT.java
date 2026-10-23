package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link CategoryRepository} in a CDI container.
 */
class CategoryRepository_WeldIT
        extends _Repository_WeldIT<CategoryRepository> {

    CategoryRepository_WeldIT() {
        super(CategoryRepository.class);
    }
}
