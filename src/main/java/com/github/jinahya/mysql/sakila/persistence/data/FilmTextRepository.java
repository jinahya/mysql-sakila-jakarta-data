package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.FilmText;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface FilmTextRepository
        extends _Repository<FilmText, Integer>,
                CrudRepository<FilmText, Integer> {

}
