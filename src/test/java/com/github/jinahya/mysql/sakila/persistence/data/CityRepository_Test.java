package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.City;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link CityRepository}.
 */
@AddBeanClasses(CityRepository_.class)
class CityRepository_Test
        extends _Repository_Test<CityRepository, City, Integer> {

    CityRepository_Test() {
        super(CityRepository.class, City.class, Integer.class);
    }
}
