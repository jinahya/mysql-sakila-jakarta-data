package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link ActorRepository} in a CDI container.
 */
class ActorRepository_WeldIT
        extends _Repository_WeldIT<ActorRepository> {

    ActorRepository_WeldIT() {
        super(ActorRepository.class);
    }
}
