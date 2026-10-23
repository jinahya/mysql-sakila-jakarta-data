package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.FilmCategory;
import com.github.jinahya.mysql.sakila.persistence.FilmCategoryId;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link FilmCategoryRepository}.
 */
@AddBeanClasses(FilmCategoryRepository_.class)
class FilmCategoryRepository_IT
        extends _Repository_IT<FilmCategoryRepository, FilmCategory, FilmCategoryId> {

    FilmCategoryRepository_IT() {
        super(FilmCategoryRepository.class, FilmCategory.class, FilmCategoryId.class);
    }
}
