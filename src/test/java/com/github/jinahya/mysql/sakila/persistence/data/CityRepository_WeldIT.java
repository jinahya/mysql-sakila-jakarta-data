package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link CityRepository} in a CDI container.
 */
class CityRepository_WeldIT
        extends _Repository_WeldIT<CityRepository> {

    CityRepository_WeldIT() {
        super(CityRepository.class);
    }
}
