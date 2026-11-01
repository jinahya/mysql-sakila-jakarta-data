package com.github.jinahya.mysql.sakila.persistence.data;

import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link FilmListRepository}.
 */
@AddBeanClasses(FilmListRepository_.class)
class FilmListRepository_IT
        extends _NonEntityRepository_IT<FilmListRepository> {

    FilmListRepository_IT() {
        super(FilmListRepository.class);
    }
}
