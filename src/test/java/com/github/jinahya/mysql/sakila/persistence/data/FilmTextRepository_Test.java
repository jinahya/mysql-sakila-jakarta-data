package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.FilmText;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link FilmTextRepository}.
 */
@AddBeanClasses(FilmTextRepository_.class)
class FilmTextRepository_Test
        extends _Repository_Test<FilmTextRepository, FilmText, Integer> {

    FilmTextRepository_Test() {
        super(FilmTextRepository.class, FilmText.class, Integer.class);
    }
}
