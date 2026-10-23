package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Store;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface StoreRepository
        extends _Repository<Store, Integer>,
                CrudRepository<Store, Integer> {

}
