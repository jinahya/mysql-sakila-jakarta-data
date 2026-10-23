package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link CustomerListRepository} in a CDI container.
 */
class CustomerListRepository_WeldTest
        extends _Repository_WeldTest<CustomerListRepository> {

    CustomerListRepository_WeldTest() {
        super(CustomerListRepository.class);
    }
}
