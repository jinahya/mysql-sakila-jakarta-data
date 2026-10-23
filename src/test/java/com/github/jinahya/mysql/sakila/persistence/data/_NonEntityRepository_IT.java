package com.github.jinahya.mysql.sakila.persistence.data;

import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;

import java.util.Objects;

/**
 * The base of the integration tests of a repository with no primary entity, one over a view
 * upstream maps without {@code @Entity}, on the {@code sakila-it} unit.
 * <p>
 * Such a repository extends no {@link _Repository}, so it does not fit
 * {@link __Repository_TestBase}; this base does the same for it otherwise: a Weld SE container per
 * test method, the factory of {@link _Repository_IT.EntityManagerFactoryProducer}, and the
 * generated {@code <Repository>_} class, which the concrete test adds, selected from an
 * {@link Instance}. The views do not exist on {@code sakila-test}, whose schema Hibernate
 * generates from the entities, so there is no unit-test counterpart.
 *
 * @param <R> the type of the repository under test.
 */
@EnableAutoWeld
@AddBeanClasses(_Repository_IT.EntityManagerFactoryProducer.class)
abstract class _NonEntityRepository_IT<R> {

    _NonEntityRepository_IT(final Class<R> repositoryClass) {
        super();
        this.repositoryClass = Objects.requireNonNull(repositoryClass, "repositoryClass is null");
    }

    /**
     * Returns the repository under test, a bean the container creates on the first call of the
     * current test.
     *
     * @return the repository under test.
     */
    R getRepositoryInstance() {
        if (repositoryInstance == null) {
            repositoryInstance = instance.select(repositoryClass).get();
        }
        return repositoryInstance;
    }

    /**
     * The repository interface under test.
     */
    final Class<R> repositoryClass;

    @Inject
    private Instance<Object> instance;

    private R repositoryInstance;
}
