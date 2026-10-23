package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Film;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link FilmRepository}.
 */
@AddBeanClasses(FilmRepository_.class)
class FilmRepository_IT
        extends _Repository_IT<FilmRepository, Film, Integer> {

    FilmRepository_IT() {
        super(FilmRepository.class, Film.class, Integer.class);
    }
}
