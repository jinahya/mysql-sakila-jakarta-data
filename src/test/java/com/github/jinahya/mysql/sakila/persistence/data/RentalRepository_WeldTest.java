package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link RentalRepository} in a CDI container.
 */
class RentalRepository_WeldTest
        extends _Repository_WeldTest<RentalRepository> {

    RentalRepository_WeldTest() {
        super(RentalRepository.class);
    }
}
