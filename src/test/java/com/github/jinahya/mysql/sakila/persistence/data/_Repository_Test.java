package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The base of the unit tests of a repository, on the {@code sakila-test} unit: an in-memory H2 in
 * MySQL mode, whose schema Hibernate generates from the entities and drops again when the factory
 * closes.
 * <p>
 * The database starts empty, so a test inserts what it queries.
 *
 * @param <R> the type of the repository under test.
 * @see _Repository_IT
 */
abstract class _Repository_Test<R extends _Repository<?, ?>>
        extends __Repository_TestBase<R> {

    static final String PERSISTENCE_UNIT_NAME = "sakila-test";

    _Repository_Test(final Class<R> repositoryClass) {
        super(PERSISTENCE_UNIT_NAME, repositoryClass);
    }
}
