package com.github.jinahya.mysql.sakila.persistence.data;

import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * The base of the integration tests of a repository in a CDI container, on the {@code sakila-it}
 * unit, as {@link _Repository_IT} is without one.
 * <p>
 * Run with {@code -Pfailsafe}; see {@code _mvn_verify.sh}.
 *
 * @param <R> the type of the repository under test.
 * @see _Repository_WeldTest
 */
abstract class _Repository_WeldIT<R extends _Repository<?, ?>>
        extends __Repository_WeldTestBase<R> {

    /**
     * Produces the factory of the {@code sakila-it} unit, and closes it when the container shuts
     * down.
     *
     * @see _Repository_WeldTest.EntityManagerFactoryProducer
     */
    static class EntityManagerFactoryProducer {

        @Produces
        @Singleton
        EntityManagerFactory produceEntityManagerFactory() {
            return Persistence.createEntityManagerFactory(_Repository_IT.PERSISTENCE_UNIT_NAME);
        }

        void disposeEntityManagerFactory(@Disposes final EntityManagerFactory entityManagerFactory) {
            entityManagerFactory.close();
        }
    }

    _Repository_WeldIT(final Class<R> repositoryClass) {
        super(EntityManagerFactoryProducer.class, repositoryClass);
    }
}
