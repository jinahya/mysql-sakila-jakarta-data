package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Payment;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface PaymentRepository
        extends _Repository<Payment, Integer>,
                CrudRepository<Payment, Integer> {

}
