package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.SalesByFilmCategory;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link SalesByFilmCategoryRepository}.
 */
@AddBeanClasses(SalesByFilmCategoryRepository_.class)
class SalesByFilmCategoryRepository_Test
        extends _Repository_Test<SalesByFilmCategoryRepository, SalesByFilmCategory, String> {

    SalesByFilmCategoryRepository_Test() {
        super(SalesByFilmCategoryRepository.class, SalesByFilmCategory.class, String.class);
    }
}
