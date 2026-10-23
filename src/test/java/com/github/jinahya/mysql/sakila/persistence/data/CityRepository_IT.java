package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.City;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link CityRepository}.
 */
@AddBeanClasses(CityRepository_.class)
class CityRepository_IT
        extends _Repository_IT<CityRepository, City, Integer> {

    CityRepository_IT() {
        super(CityRepository.class, City.class, Integer.class);
    }
}
