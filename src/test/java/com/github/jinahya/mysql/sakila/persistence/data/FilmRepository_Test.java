package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Film;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link FilmRepository}.
 */
@AddBeanClasses(FilmRepository_.class)
class FilmRepository_Test
        extends _Repository_Test<FilmRepository, Film, Integer> {

    FilmRepository_Test() {
        super(FilmRepository.class, Film.class, Integer.class);
    }
}
