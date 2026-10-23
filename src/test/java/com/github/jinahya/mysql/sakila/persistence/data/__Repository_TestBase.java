package com.github.jinahya.mysql.sakila.persistence.data;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * The root of the repository tests, with no container: the factory, the session and the repository
 * are created by hand, as a Java EE 5 application outside a server would.
 * <p>
 * One {@link EntityManagerFactory} per test class, created from the persistence unit the subclass
 * names, and one {@link StatelessSession} per test method, from which the generated
 * {@code <Repository>_} class is instantiated through its {@code public} constructor. A stateless
 * session has no persistence context: each repository call goes to the database, an entity it
 * returns is detached, and a write needs a transaction of the session's own
 * ({@code session().inTransaction(s -> ...)}).
 *
 * @param <R> the type of the repository under test.
 * @see _Repository_Test
 * @see _Repository_IT
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class __Repository_TestBase<R extends _Repository<?, ?>> {

    /**
     * Returns the implementation that {@code hibernate-processor} generated for the specified
     * repository, the {@code <Repository>_} class beside it.
     *
     * @param repositoryClass the repository interface.
     * @param <R>             the type of the repository.
     * @return the generated implementation class.
     */
    static <R extends _Repository<?, ?>> Class<? extends R> implementationClass(
            final Class<R> repositoryClass) {
        Objects.requireNonNull(repositoryClass, "repositoryClass is null");
        final var implementationName = repositoryClass.getName() + '_';
        try {
            return Class.forName(implementationName, true, repositoryClass.getClassLoader())
                    .asSubclass(repositoryClass);
        } catch (final ClassNotFoundException e) {
            throw new RuntimeException("failed to load " + implementationName, e);
        }
    }

    /**
     * Instantiates the implementation that {@code hibernate-processor} generated for the specified
     * repository, the {@code <Repository>_} class beside it, over the specified session.
     *
     * @param repositoryClass the repository interface.
     * @param session         the session the implementation runs on.
     * @param <R>             the type of the repository.
     * @return a new instance of the generated implementation.
     */
    static <R extends _Repository<?, ?>> R newRepositoryInstance(final Class<R> repositoryClass,
                                                                 final StatelessSession session) {
        Objects.requireNonNull(session, "session is null");
        final var implementationClass = implementationClass(repositoryClass);
        try {
            return implementationClass.getConstructor(StatelessSession.class).newInstance(session);
        } catch (final NoSuchMethodException | InstantiationException | IllegalAccessException
                       | InvocationTargetException e) {
            throw new RuntimeException("failed to instantiate " + implementationClass, e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance for the specified persistence unit and repository.
     *
     * @param persistenceUnitName the name of the persistence unit to test against.
     * @param repositoryClass     the repository under test.
     */
    __Repository_TestBase(final String persistenceUnitName, final Class<R> repositoryClass) {
        super();
        this.persistenceUnitName =
                Objects.requireNonNull(persistenceUnitName, "persistenceUnitName is null");
        this.repositoryClass = Objects.requireNonNull(repositoryClass, "repositoryClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------

    @BeforeAll
    void createEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    @AfterAll
    void closeEntityManagerFactory() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @BeforeEach
    void openSession() {
        session = entityManagerFactory.unwrap(SessionFactory.class).openStatelessSession();
        repository = newRepositoryInstance(repositoryClass, session);
    }

    @AfterEach
    void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the factory of the persistence unit, open for the whole test class.
     *
     * @return the factory of the persistence unit.
     */
    EntityManagerFactory entityManagerFactory() {
        return entityManagerFactory;
    }

    /**
     * Returns the session of the current test, which the {@link #repository()} runs on.
     *
     * @return the session of the current test.
     */
    StatelessSession session() {
        return session;
    }

    /**
     * Returns the repository under test, over the {@link #session()} of the current test.
     *
     * @return the repository under test.
     */
    R repository() {
        return repository;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the persistence unit, in {@code META-INF/persistence.xml}.
     */
    final String persistenceUnitName;

    /**
     * The repository interface under test.
     */
    final Class<R> repositoryClass;

    private EntityManagerFactory entityManagerFactory;

    private StatelessSession session;

    private R repository;
}
