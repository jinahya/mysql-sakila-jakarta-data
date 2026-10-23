package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.ActorInfo;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link ActorInfoRepository}.
 */
@AddBeanClasses(ActorInfoRepository_.class)
class ActorInfoRepository_Test
        extends _Repository_Test<ActorInfoRepository, ActorInfo, Integer> {

    ActorInfoRepository_Test() {
        super(ActorInfoRepository.class, ActorInfo.class, Integer.class);
    }
}
