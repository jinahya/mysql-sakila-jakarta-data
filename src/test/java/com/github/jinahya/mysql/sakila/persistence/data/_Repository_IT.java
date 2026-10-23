package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The base of the integration tests of a repository, on the {@code sakila-it} unit: the Sakila
 * database upstream's {@code _docker_compose_up.sh} brings up on {@code localhost:3306}.
 * <p>
 * The sample data is the fixture. A test reads the rows that are there, and anything it writes it
 * does inside a transaction it rolls back.
 * <p>
 * Run with {@code -Pfailsafe}; see {@code _mvn_verify.sh}.
 *
 * @param <R> the type of the repository under test.
 * @see _Repository_Test
 */
abstract class _Repository_IT<R extends _Repository<?, ?>>
        extends __Repository_TestBase<R> {

    static final String PERSISTENCE_UNIT_NAME = "sakila-it";

    _Repository_IT(final Class<R> repositoryClass) {
        super(PERSISTENCE_UNIT_NAME, repositoryClass);
    }
}
