package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.Language;
import org.jboss.weld.junit5.auto.AddBeanClasses;

/**
 * The unit tests of {@link LanguageRepository}.
 */
@AddBeanClasses(LanguageRepository_.class)
class LanguageRepository_Test
        extends _Repository_Test<LanguageRepository, Language, Integer> {

    LanguageRepository_Test() {
        super(LanguageRepository.class, Language.class, Integer.class);
    }
}
