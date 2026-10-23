package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Customer;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface CustomerRepository
        extends _Repository<Customer, Integer>,
                CrudRepository<Customer, Integer> {

}
