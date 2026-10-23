package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link RentalRepository} in a CDI container.
 */
class RentalRepository_WeldIT
        extends _Repository_WeldIT<RentalRepository> {

    RentalRepository_WeldIT() {
        super(RentalRepository.class);
    }
}
