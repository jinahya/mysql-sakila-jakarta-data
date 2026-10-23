package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Staff;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link StaffRepository}.
 */
@AddBeanClasses(StaffRepository_.class)
class StaffRepository_Test
        extends _Repository_Test<StaffRepository, Staff, Integer> {

    StaffRepository_Test() {
        super(StaffRepository.class, Staff.class, Integer.class);
    }
}
