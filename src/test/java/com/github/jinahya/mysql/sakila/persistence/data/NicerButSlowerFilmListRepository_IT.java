package com.github.jinahya.mysql.sakila.persistence.data;

import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link NicerButSlowerFilmListRepository}.
 */
@AddBeanClasses(NicerButSlowerFilmListRepository_.class)
class NicerButSlowerFilmListRepository_IT
        extends _NonEntityRepository_IT<NicerButSlowerFilmListRepository> {

    NicerButSlowerFilmListRepository_IT() {
        super(NicerButSlowerFilmListRepository.class);
    }
}
