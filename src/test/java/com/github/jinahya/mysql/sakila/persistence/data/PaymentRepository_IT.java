package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Payment;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link PaymentRepository}.
 */
@AddBeanClasses(PaymentRepository_.class)
class PaymentRepository_IT
        extends _Repository_IT<PaymentRepository, Payment, Integer> {

    PaymentRepository_IT() {
        super(PaymentRepository.class, Payment.class, Integer.class);
    }
}
