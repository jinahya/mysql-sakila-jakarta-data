package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Category;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface CategoryRepository
        extends _Repository<Category, Integer>,
                CrudRepository<Category, Integer> {

}
