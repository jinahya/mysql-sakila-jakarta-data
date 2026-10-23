package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link CustomerRepository} in a CDI container.
 */
class CustomerRepository_WeldTest
        extends _Repository_WeldTest<CustomerRepository> {

    CustomerRepository_WeldTest() {
        super(CustomerRepository.class);
    }
}
