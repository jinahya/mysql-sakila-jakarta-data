package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link AddressRepository} in a CDI container.
 */
class AddressRepository_WeldTest
        extends _Repository_WeldTest<AddressRepository> {

    AddressRepository_WeldTest() {
        super(AddressRepository.class);
    }
}
