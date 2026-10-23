package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link StaffListRepository} in a CDI container.
 */
class StaffListRepository_WeldTest
        extends _Repository_WeldTest<StaffListRepository> {

    StaffListRepository_WeldTest() {
        super(StaffListRepository.class);
    }
}
