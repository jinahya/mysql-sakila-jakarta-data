package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link CategoryRepository}.
 */
class CategoryRepository_IT
        extends _Repository_IT<CategoryRepository> {

    CategoryRepository_IT() {
        super(CategoryRepository.class);
    }
}
