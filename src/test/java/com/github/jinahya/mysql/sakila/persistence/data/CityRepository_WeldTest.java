package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link CityRepository} in a CDI container.
 */
class CityRepository_WeldTest
        extends _Repository_WeldTest<CityRepository> {

    CityRepository_WeldTest() {
        super(CityRepository.class);
    }
}
