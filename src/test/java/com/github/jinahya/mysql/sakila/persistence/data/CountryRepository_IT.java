package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Country;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link CountryRepository}.
 */
@AddBeanClasses(CountryRepository_.class)
class CountryRepository_IT
        extends _Repository_IT<CountryRepository, Country, Integer> {

    CountryRepository_IT() {
        super(CountryRepository.class, Country.class, Integer.class);
    }
}
