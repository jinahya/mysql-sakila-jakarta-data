package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Inventory;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link InventoryRepository}.
 */
@AddBeanClasses(InventoryRepository_.class)
class InventoryRepository_Test
        extends _Repository_Test<InventoryRepository, Inventory, Integer> {

    InventoryRepository_Test() {
        super(InventoryRepository.class, Inventory.class, Integer.class);
    }
}
