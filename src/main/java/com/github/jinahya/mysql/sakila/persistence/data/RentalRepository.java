package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Rental;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface RentalRepository
        extends _Repository<Rental, Integer>,
                CrudRepository<Rental, Integer> {

}
