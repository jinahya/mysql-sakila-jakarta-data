package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link CustomerRepository} in a CDI container.
 */
class CustomerRepository_WeldIT
        extends _Repository_WeldIT<CustomerRepository> {

    CustomerRepository_WeldIT() {
        super(CustomerRepository.class);
    }
}
