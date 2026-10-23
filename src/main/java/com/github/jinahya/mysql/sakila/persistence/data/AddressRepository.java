package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Address;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface AddressRepository
        extends _Repository<Address, Integer>,
                CrudRepository<Address, Integer> {

}
