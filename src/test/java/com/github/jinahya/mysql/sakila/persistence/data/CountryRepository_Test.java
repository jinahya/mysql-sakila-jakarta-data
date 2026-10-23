package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Country;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link CountryRepository}.
 */
@AddBeanClasses(CountryRepository_.class)
class CountryRepository_Test
        extends _Repository_Test<CountryRepository, Country, Integer> {

    CountryRepository_Test() {
        super(CountryRepository.class, Country.class, Integer.class);
    }
}
