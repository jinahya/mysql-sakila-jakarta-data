package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link StaffRepository}.
 */
class StaffRepository_IT
        extends _Repository_IT<StaffRepository> {

    StaffRepository_IT() {
        super(StaffRepository.class);
    }
}
