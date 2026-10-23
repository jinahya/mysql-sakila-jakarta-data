package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link CustomerListRepository}.
 */
class CustomerListRepository_IT
        extends _Repository_IT<CustomerListRepository> {

    CustomerListRepository_IT() {
        super(CustomerListRepository.class);
    }
}
