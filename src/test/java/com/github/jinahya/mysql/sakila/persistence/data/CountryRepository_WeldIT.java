package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link CountryRepository} in a CDI container.
 */
class CountryRepository_WeldIT
        extends _Repository_WeldIT<CountryRepository> {

    CountryRepository_WeldIT() {
        super(CountryRepository.class);
    }
}
