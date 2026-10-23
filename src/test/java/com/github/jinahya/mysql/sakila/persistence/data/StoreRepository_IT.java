package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Store;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link StoreRepository}.
 */
@AddBeanClasses(StoreRepository_.class)
class StoreRepository_IT
        extends _Repository_IT<StoreRepository, Store, Integer> {

    StoreRepository_IT() {
        super(StoreRepository.class, Store.class, Integer.class);
    }
}
