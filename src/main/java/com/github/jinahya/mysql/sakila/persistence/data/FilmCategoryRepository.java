package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.FilmCategory;
import com.github.jinahya.mysql.sakila.persistence.FilmCategoryId;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface FilmCategoryRepository
        extends _Repository<FilmCategory, FilmCategoryId>,
                CrudRepository<FilmCategory, FilmCategoryId> {

}
