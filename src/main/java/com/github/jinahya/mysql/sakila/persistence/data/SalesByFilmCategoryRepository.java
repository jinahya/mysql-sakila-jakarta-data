package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.SalesByFilmCategory;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface SalesByFilmCategoryRepository
        extends _Repository<SalesByFilmCategory, String>,
                CrudRepository<SalesByFilmCategory, String> {

}
