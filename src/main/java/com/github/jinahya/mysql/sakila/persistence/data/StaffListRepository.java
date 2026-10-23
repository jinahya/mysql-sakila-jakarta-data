package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.StaffList;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface StaffListRepository
        extends _Repository<StaffList, Integer>,
                CrudRepository<StaffList, Integer> {

}
