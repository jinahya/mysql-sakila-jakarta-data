package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link CountryRepository}.
 */
class CountryRepository_IT
        extends _Repository_IT<CountryRepository> {

    CountryRepository_IT() {
        super(CountryRepository.class);
    }
}
