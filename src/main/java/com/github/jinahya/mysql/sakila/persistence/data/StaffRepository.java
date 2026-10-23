package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Staff;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface StaffRepository
        extends _Repository<Staff, Integer>,
                CrudRepository<Staff, Integer> {

}
