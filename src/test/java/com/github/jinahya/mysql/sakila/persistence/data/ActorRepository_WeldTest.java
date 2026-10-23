package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link ActorRepository} in a CDI container.
 */
class ActorRepository_WeldTest
        extends _Repository_WeldTest<ActorRepository> {

    ActorRepository_WeldTest() {
        super(ActorRepository.class);
    }
}
