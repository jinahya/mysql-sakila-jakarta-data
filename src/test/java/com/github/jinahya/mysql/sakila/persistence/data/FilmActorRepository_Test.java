package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.FilmActor;
import com.github.jinahya.mysql.sakila.persistence.FilmActorId;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link FilmActorRepository}.
 */
@AddBeanClasses(FilmActorRepository_.class)
class FilmActorRepository_Test
        extends _Repository_Test<FilmActorRepository, FilmActor, FilmActorId> {

    FilmActorRepository_Test() {
        super(FilmActorRepository.class, FilmActor.class, FilmActorId.class);
    }
}
