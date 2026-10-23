package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.CustomerList;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link CustomerListRepository}.
 */
@AddBeanClasses(CustomerListRepository_.class)
class CustomerListRepository_IT
        extends _Repository_IT<CustomerListRepository, CustomerList, Integer> {

    CustomerListRepository_IT() {
        super(CustomerListRepository.class, CustomerList.class, Integer.class);
    }
}
