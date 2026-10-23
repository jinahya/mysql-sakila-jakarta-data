package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Customer;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link CustomerRepository}.
 */
@AddBeanClasses(CustomerRepository_.class)
class CustomerRepository_IT
        extends _Repository_IT<CustomerRepository, Customer, Integer> {

    CustomerRepository_IT() {
        super(CustomerRepository.class, Customer.class, Integer.class);
    }
}
