package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.FilmActor;
import com.github.jinahya.mysql.sakila.persistence.FilmActorId;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link FilmActorRepository}.
 */
@AddBeanClasses(FilmActorRepository_.class)
class FilmActorRepository_IT
        extends _Repository_IT<FilmActorRepository, FilmActor, FilmActorId> {

    FilmActorRepository_IT() {
        super(FilmActorRepository.class, FilmActor.class, FilmActorId.class);
    }
}
