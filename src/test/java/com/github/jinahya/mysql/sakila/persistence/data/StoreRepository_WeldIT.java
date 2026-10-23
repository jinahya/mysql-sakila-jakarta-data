package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link StoreRepository} in a CDI container.
 */
class StoreRepository_WeldIT
        extends _Repository_WeldIT<StoreRepository> {

    StoreRepository_WeldIT() {
        super(StoreRepository.class);
    }
}
