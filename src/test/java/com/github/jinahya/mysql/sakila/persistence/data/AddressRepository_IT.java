package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Address;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link AddressRepository}.
 */
@AddBeanClasses(AddressRepository_.class)
class AddressRepository_IT
        extends _Repository_IT<AddressRepository, Address, Integer> {

    AddressRepository_IT() {
        super(AddressRepository.class, Address.class, Integer.class);
    }
}
