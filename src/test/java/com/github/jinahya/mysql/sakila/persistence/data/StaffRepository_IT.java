package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Staff;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link StaffRepository}.
 */
@AddBeanClasses(StaffRepository_.class)
class StaffRepository_IT
        extends _Repository_IT<StaffRepository, Staff, Integer> {

    StaffRepository_IT() {
        super(StaffRepository.class, Staff.class, Integer.class);
    }
}
