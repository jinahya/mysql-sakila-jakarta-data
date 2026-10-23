package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link ActorInfoRepository} in a CDI container.
 */
class ActorInfoRepository_WeldIT
        extends _Repository_WeldIT<ActorInfoRepository> {

    ActorInfoRepository_WeldIT() {
        super(ActorInfoRepository.class);
    }
}
