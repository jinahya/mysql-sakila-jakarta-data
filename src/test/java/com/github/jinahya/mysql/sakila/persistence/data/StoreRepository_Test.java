package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Store;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link StoreRepository}.
 */
@AddBeanClasses(StoreRepository_.class)
class StoreRepository_Test
        extends _Repository_Test<StoreRepository, Store, Integer> {

    StoreRepository_Test() {
        super(StoreRepository.class, Store.class, Integer.class);
    }
}
