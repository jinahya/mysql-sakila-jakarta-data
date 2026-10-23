package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link InventoryRepository} in a CDI container.
 */
class InventoryRepository_WeldIT
        extends _Repository_WeldIT<InventoryRepository> {

    InventoryRepository_WeldIT() {
        super(InventoryRepository.class);
    }
}
