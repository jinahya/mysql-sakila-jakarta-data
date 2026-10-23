package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link ActorInfoRepository} in a CDI container.
 */
class ActorInfoRepository_WeldTest
        extends _Repository_WeldTest<ActorInfoRepository> {

    ActorInfoRepository_WeldTest() {
        super(ActorInfoRepository.class);
    }
}
