package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Address;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link AddressRepository}.
 */
@AddBeanClasses(AddressRepository_.class)
class AddressRepository_Test
        extends _Repository_Test<AddressRepository, Address, Integer> {

    AddressRepository_Test() {
        super(AddressRepository.class, Address.class, Integer.class);
    }
}
