package com.github.jinahya.mysql.sakila.persistence.data;

import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.hibernate.StatelessSession;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * The root of the repository tests in a CDI container, Weld SE, as the generated repositories run
 * in an application server: the container creates the {@code <Repository>_} class through its
 * {@code @Inject} constructor, and the class opens its own session.
 * <p>
 * The container holds two bean classes: the generated implementation, and the producer of the
 * {@link EntityManagerFactory} that the subclass names. The generated class does not {@code @Inject}
 * the factory, though; it takes it from a {@link PersistenceUnit @PersistenceUnit} field, which is
 * resource injection, and a CDI producer never reaches it. Weld SE has no JPA of its own to fill
 * that field, so {@link WeldInitiator.Builder#setPersistenceUnitFactory(java.util.function.Function)}
 * hands it the produced factory. That is also why this is {@link EnableWeld @EnableWeld} with a
 * {@link WeldSetup @WeldSetup}, not {@code @EnableAutoWeld} with {@code @AddBeanClasses}: the
 * automagic mode has no way to set the factory, and the field stays {@code null}.
 * <p>
 * One container per test class, and so one factory, as {@link __Repository_TestBase} does. The
 * repository is a {@code @Dependent} bean: one instance, with its own {@link StatelessSession}, is
 * obtained for each test method and destroyed after it, which closes the session.
 *
 * @param <R> the type of the repository under test.
 * @see _Repository_WeldTest
 * @see _Repository_WeldIT
 * @see __Repository_TestBase
 */
@EnableWeld
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class __Repository_WeldTestBase<R extends _Repository<?, ?>> {

    /**
     * Creates a new instance with the specified producer and repository.
     *
     * @param producerClass   the bean class producing the {@link EntityManagerFactory} to test
     *                        against.
     * @param repositoryClass the repository under test.
     */
    __Repository_WeldTestBase(final Class<?> producerClass, final Class<R> repositoryClass) {
        super();
        Objects.requireNonNull(producerClass, "producerClass is null");
        this.repositoryClass = Objects.requireNonNull(repositoryClass, "repositoryClass is null");
        weld = WeldInitiator
                .from(__Repository_TestBase.implementationClass(repositoryClass), producerClass)
                .setPersistenceUnitFactory(
                        ip -> CDI.current().select(EntityManagerFactory.class).get())
                .build();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @BeforeEach
    void selectRepository() {
        repository = weld.select(repositoryClass).get();
    }

    @AfterEach
    void destroyRepository() {
        if (repository != null) {
            weld.destroy(repository);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the factory the container produced, open for the whole test class.
     *
     * @return the factory the container produced.
     */
    EntityManagerFactory entityManagerFactory() {
        return weld.select(EntityManagerFactory.class).get();
    }

    /**
     * Returns the session the {@link #repository()} of the current test opened, through the
     * {@code public session()} accessor every generated {@code <Repository>_} class has.
     *
     * @return the session of the current test.
     */
    StatelessSession session() {
        try {
            return (StatelessSession) repository.getClass().getMethod("session").invoke(repository);
        } catch (final NoSuchMethodException | IllegalAccessException
                       | InvocationTargetException e) {
            throw new RuntimeException("failed to get the session of " + repository, e);
        }
    }

    /**
     * Returns the repository under test, a bean the container created for the current test.
     *
     * @return the repository under test.
     */
    R repository() {
        return repository;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The repository interface under test.
     */
    final Class<R> repositoryClass;

    /**
     * The container, started before the first test of the class and shut down after the last.
     */
    @WeldSetup
    final WeldInitiator weld;

    private R repository;
}
