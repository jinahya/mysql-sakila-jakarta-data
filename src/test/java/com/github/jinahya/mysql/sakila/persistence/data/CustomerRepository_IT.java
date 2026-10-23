package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link CustomerRepository}.
 */
class CustomerRepository_IT
        extends _Repository_IT<CustomerRepository> {

    CustomerRepository_IT() {
        super(CustomerRepository.class);
    }
}
