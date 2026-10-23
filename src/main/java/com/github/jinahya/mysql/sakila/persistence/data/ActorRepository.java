package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Actor;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface ActorRepository
        extends _Repository<Actor, Integer>,
                CrudRepository<Actor, Integer> {

}
