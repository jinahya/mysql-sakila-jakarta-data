package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.StaffList;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link StaffListRepository}.
 */
@AddBeanClasses(StaffListRepository_.class)
class StaffListRepository_Test
        extends _Repository_Test<StaffListRepository, StaffList, Integer> {

    StaffListRepository_Test() {
        super(StaffListRepository.class, StaffList.class, Integer.class);
    }
}
