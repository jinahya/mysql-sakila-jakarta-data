package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link CountryRepository} in a CDI container.
 */
class CountryRepository_WeldTest
        extends _Repository_WeldTest<CountryRepository> {

    CountryRepository_WeldTest() {
        super(CountryRepository.class);
    }
}
