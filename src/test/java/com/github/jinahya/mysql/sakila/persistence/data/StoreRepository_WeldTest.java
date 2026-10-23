package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link StoreRepository} in a CDI container.
 */
class StoreRepository_WeldTest
        extends _Repository_WeldTest<StoreRepository> {

    StoreRepository_WeldTest() {
        super(StoreRepository.class);
    }
}
