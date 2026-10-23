package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link FilmRepository}.
 */
class FilmRepository_IT
        extends _Repository_IT<FilmRepository> {

    FilmRepository_IT() {
        super(FilmRepository.class);
    }
}
