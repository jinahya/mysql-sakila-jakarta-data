package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Inventory;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface InventoryRepository
        extends _Repository<Inventory, Integer>,
                CrudRepository<Inventory, Integer> {

}
