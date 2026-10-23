package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.FilmText;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link FilmTextRepository}.
 */
@AddBeanClasses(FilmTextRepository_.class)
class FilmTextRepository_IT
        extends _Repository_IT<FilmTextRepository, FilmText, Integer> {

    FilmTextRepository_IT() {
        super(FilmTextRepository.class, FilmText.class, Integer.class);
    }
}
