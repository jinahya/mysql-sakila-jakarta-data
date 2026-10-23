package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.ActorInfo;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface ActorInfoRepository
        extends _Repository<ActorInfo, Integer>,
                CrudRepository<ActorInfo, Integer> {

}
