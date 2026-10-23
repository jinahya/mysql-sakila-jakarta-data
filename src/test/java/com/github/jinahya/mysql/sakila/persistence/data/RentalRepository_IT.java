package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Rental;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link RentalRepository}.
 */
@AddBeanClasses(RentalRepository_.class)
class RentalRepository_IT
        extends _Repository_IT<RentalRepository, Rental, Integer> {

    RentalRepository_IT() {
        super(RentalRepository.class, Rental.class, Integer.class);
    }
}
