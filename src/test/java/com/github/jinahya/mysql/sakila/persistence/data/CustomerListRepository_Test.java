package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.CustomerList;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link CustomerListRepository}.
 */
@AddBeanClasses(CustomerListRepository_.class)
class CustomerListRepository_Test
        extends _Repository_Test<CustomerListRepository, CustomerList, Integer> {

    CustomerListRepository_Test() {
        super(CustomerListRepository.class, CustomerList.class, Integer.class);
    }
}
