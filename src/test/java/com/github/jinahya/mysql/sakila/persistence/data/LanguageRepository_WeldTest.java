package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The unit tests of {@link LanguageRepository} in a CDI container.
 */
class LanguageRepository_WeldTest
        extends _Repository_WeldTest<LanguageRepository> {

    LanguageRepository_WeldTest() {
        super(LanguageRepository.class);
    }
}
