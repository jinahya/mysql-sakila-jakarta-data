package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Language;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The integration tests of {@link LanguageRepository}.
 */
@AddBeanClasses(LanguageRepository_.class)
class LanguageRepository_IT
        extends _Repository_IT<LanguageRepository, Language, Integer> {

    LanguageRepository_IT() {
        super(LanguageRepository.class, Language.class, Integer.class);
    }
}
