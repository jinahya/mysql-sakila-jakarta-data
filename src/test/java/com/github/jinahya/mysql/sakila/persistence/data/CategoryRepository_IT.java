package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Category;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link CategoryRepository}.
 */
@AddBeanClasses(CategoryRepository_.class)
class CategoryRepository_IT
        extends _Repository_IT<CategoryRepository, Category, Integer> {

    CategoryRepository_IT() {
        super(CategoryRepository.class, Category.class, Integer.class);
    }
}
