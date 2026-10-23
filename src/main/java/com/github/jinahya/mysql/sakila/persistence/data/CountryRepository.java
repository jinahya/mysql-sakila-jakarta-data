package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Country;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface CountryRepository
        extends _Repository<Country, Integer>,
                CrudRepository<Country, Integer> {

}
