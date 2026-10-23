package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.FilmCategory;
import com.github.jinahya.mysql.sakila.persistence.FilmCategoryId;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link FilmCategoryRepository}.
 */
@AddBeanClasses(FilmCategoryRepository_.class)
class FilmCategoryRepository_Test
        extends _Repository_Test<FilmCategoryRepository, FilmCategory, FilmCategoryId> {

    FilmCategoryRepository_Test() {
        super(FilmCategoryRepository.class, FilmCategory.class, FilmCategoryId.class);
    }
}
