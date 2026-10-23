package com.github.jinahya.mysql.sakila.persistence.data;

import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * The base of the unit tests of a repository in a CDI container, on the {@code sakila-test} unit,
 * as {@link _Repository_Test} is without one.
 *
 * @param <R> the type of the repository under test.
 * @see _Repository_WeldIT
 */
abstract class _Repository_WeldTest<R extends _Repository<?, ?>>
        extends __Repository_WeldTestBase<R> {

    /**
     * Produces the factory of the {@code sakila-test} unit, and closes it when the container shuts
     * down.
     * <p>
     * The scope is {@link Singleton @Singleton}, a pseudo-scope, not
     * {@code @ApplicationScoped}: a normal scope injects a client proxy, and the generated
     * repository's {@code unwrap(SessionFactory.class)} on that proxy fails with a
     * {@link ClassCastException}.
     */
    static class EntityManagerFactoryProducer {

        @Produces
        @Singleton
        EntityManagerFactory produceEntityManagerFactory() {
            return Persistence.createEntityManagerFactory(_Repository_Test.PERSISTENCE_UNIT_NAME);
        }

        void disposeEntityManagerFactory(@Disposes final EntityManagerFactory entityManagerFactory) {
            entityManagerFactory.close();
        }
    }

    _Repository_WeldTest(final Class<R> repositoryClass) {
        super(EntityManagerFactoryProducer.class, repositoryClass);
    }
}
