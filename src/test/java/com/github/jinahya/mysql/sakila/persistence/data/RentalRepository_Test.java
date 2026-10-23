package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Rental;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link RentalRepository}.
 */
@AddBeanClasses(RentalRepository_.class)
class RentalRepository_Test
        extends _Repository_Test<RentalRepository, Rental, Integer> {

    RentalRepository_Test() {
        super(RentalRepository.class, Rental.class, Integer.class);
    }
}
