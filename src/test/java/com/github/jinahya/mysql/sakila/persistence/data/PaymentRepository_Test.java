package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Payment;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link PaymentRepository}.
 */
@AddBeanClasses(PaymentRepository_.class)
class PaymentRepository_Test
        extends _Repository_Test<PaymentRepository, Payment, Integer> {

    PaymentRepository_Test() {
        super(PaymentRepository.class, Payment.class, Integer.class);
    }
}
