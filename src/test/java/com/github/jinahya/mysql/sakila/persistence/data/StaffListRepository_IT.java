package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.StaffList;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link StaffListRepository}.
 */
@AddBeanClasses(StaffListRepository_.class)
class StaffListRepository_IT
        extends _Repository_IT<StaffListRepository, StaffList, Integer> {

    StaffListRepository_IT() {
        super(StaffListRepository.class, StaffList.class, Integer.class);
    }
}
