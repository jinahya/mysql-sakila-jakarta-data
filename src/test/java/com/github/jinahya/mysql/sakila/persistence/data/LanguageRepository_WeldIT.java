package com.github.jinahya.mysql.sakila.persistence.data;

/**
 * The integration tests of {@link LanguageRepository} in a CDI container.
 */
class LanguageRepository_WeldIT
        extends _Repository_WeldIT<LanguageRepository> {

    LanguageRepository_WeldIT() {
        super(LanguageRepository.class);
    }
}
