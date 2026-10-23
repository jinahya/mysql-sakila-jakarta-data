package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link StaffRepository} in a CDI container.
 */
class StaffRepository_WeldIT
        extends _Repository_WeldIT<StaffRepository> {

    StaffRepository_WeldIT() {
        super(StaffRepository.class);
    }
}
