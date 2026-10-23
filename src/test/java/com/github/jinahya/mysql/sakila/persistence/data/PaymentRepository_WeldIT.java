package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link PaymentRepository} in a CDI container.
 */
class PaymentRepository_WeldIT
        extends _Repository_WeldIT<PaymentRepository> {

    PaymentRepository_WeldIT() {
        super(PaymentRepository.class);
    }
}
