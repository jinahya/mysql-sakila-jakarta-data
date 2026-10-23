package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.CustomerList;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface CustomerListRepository
        extends _Repository<CustomerList, Integer>,
                CrudRepository<CustomerList, Integer> {

}
