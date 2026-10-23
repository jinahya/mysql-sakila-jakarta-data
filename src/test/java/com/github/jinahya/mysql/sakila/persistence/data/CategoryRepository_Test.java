package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Category;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link CategoryRepository}.
 */
@AddBeanClasses(CategoryRepository_.class)
class CategoryRepository_Test
        extends _Repository_Test<CategoryRepository, Category, Integer> {

    CategoryRepository_Test() {
        super(CategoryRepository.class, Category.class, Integer.class);
    }
}
