package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.FilmActor;
import com.github.jinahya.mysql.sakila.persistence.FilmActorId;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface FilmActorRepository
        extends _Repository<FilmActor, FilmActorId>,
                CrudRepository<FilmActor, FilmActorId> {

}
