package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Customer;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link CustomerRepository}.
 */
@AddBeanClasses(CustomerRepository_.class)
class CustomerRepository_Test
        extends _Repository_Test<CustomerRepository, Customer, Integer> {

    CustomerRepository_Test() {
        super(CustomerRepository.class, Customer.class, Integer.class);
    }
}
