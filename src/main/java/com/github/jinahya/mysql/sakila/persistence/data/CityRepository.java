package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.City;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface CityRepository
        extends _Repository<City, Integer>,
                CrudRepository<City, Integer> {

}
