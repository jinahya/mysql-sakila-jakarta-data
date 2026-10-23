package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link StaffRepository} in a CDI container.
 */
class StaffRepository_WeldTest
        extends _Repository_WeldTest<StaffRepository> {

    StaffRepository_WeldTest() {
        super(StaffRepository.class);
    }
}
