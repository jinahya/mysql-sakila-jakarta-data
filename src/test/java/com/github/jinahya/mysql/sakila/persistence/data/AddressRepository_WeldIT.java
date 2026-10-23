package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link AddressRepository} in a CDI container.
 */
class AddressRepository_WeldIT
        extends _Repository_WeldIT<AddressRepository> {

    AddressRepository_WeldIT() {
        super(AddressRepository.class);
    }
}
