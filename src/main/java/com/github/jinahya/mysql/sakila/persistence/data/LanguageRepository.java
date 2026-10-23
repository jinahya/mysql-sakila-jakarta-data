package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Language;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface LanguageRepository
        extends _Repository<Language, Integer>,
                CrudRepository<Language, Integer> {

}
