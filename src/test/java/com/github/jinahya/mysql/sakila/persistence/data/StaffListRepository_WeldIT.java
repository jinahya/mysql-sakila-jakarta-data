package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link StaffListRepository} in a CDI container.
 */
class StaffListRepository_WeldIT
        extends _Repository_WeldIT<StaffListRepository> {

    StaffListRepository_WeldIT() {
        super(StaffListRepository.class);
    }
}
