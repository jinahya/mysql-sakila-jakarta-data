package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link PaymentRepository} in a CDI container.
 */
class PaymentRepository_WeldTest
        extends _Repository_WeldTest<PaymentRepository> {

    PaymentRepository_WeldTest() {
        super(PaymentRepository.class);
    }
}
