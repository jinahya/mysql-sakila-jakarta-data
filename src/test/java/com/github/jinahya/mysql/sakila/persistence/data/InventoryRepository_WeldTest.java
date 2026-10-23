package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link InventoryRepository} in a CDI container.
 */
class InventoryRepository_WeldTest
        extends _Repository_WeldTest<InventoryRepository> {

    InventoryRepository_WeldTest() {
        super(InventoryRepository.class);
    }
}
