package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.ActorInfo;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link ActorInfoRepository}.
 */
@AddBeanClasses(ActorInfoRepository_.class)
class ActorInfoRepository_IT
        extends _Repository_IT<ActorInfoRepository, ActorInfo, Integer> {

    ActorInfoRepository_IT() {
        super(ActorInfoRepository.class, ActorInfo.class, Integer.class);
    }
}
