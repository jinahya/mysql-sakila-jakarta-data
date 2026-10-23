package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link CustomerListRepository} in a CDI container.
 */
class CustomerListRepository_WeldIT
        extends _Repository_WeldIT<CustomerListRepository> {

    CustomerListRepository_WeldIT() {
        super(CustomerListRepository.class);
    }
}
