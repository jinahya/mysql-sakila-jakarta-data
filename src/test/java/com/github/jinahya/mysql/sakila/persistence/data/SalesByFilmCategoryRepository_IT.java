package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.SalesByFilmCategory;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link SalesByFilmCategoryRepository}.
 */
@AddBeanClasses(SalesByFilmCategoryRepository_.class)
class SalesByFilmCategoryRepository_IT
        extends _Repository_IT<SalesByFilmCategoryRepository, SalesByFilmCategory, String> {

    SalesByFilmCategoryRepository_IT() {
        super(SalesByFilmCategoryRepository.class, SalesByFilmCategory.class, String.class);
    }
}
