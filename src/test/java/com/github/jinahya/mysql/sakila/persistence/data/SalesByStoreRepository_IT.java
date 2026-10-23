package com.github.jinahya.mysql.sakila.persistence.data;

import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * The integration tests of {@link SalesByStoreRepository}.
 * <p>
 * The expected rows are the ones upstream's {@code sales_by_store_exercises_0N_*.sql} and
 * {@code SalesByStore_Exercises_0N_*_PersistenceIT} give.
 */
@AddBeanClasses(SalesByStoreRepository_.class)
class SalesByStoreRepository_IT
        extends _NonEntityRepository_IT<SalesByStoreRepository> {

    SalesByStoreRepository_IT() {
        super(SalesByStoreRepository.class);
    }

    @DisplayName("SAKILA-SALES_BY_STORE-B-01: selectAll()")
    @Nested
    class SelectAllTest {

        @Test
        void __returnsEveryStore() {
            final var list = getRepositoryInstance().selectAll();
            assertThat(list)
                    .extracting(SalesByStoreRepository.StoreSales::store,
                                SalesByStoreRepository.StoreSales::manager,
                                SalesByStoreRepository.StoreSales::totalSales)
                    .containsExactlyInAnyOrder(
                            tuple("Woodridge,Australia", "Jon Stephens", new BigDecimal("33726.77")),
                            tuple("Lethbridge,Canada", "Mike Hillyer", new BigDecimal("33679.79")));
        }
    }

    @DisplayName("SAKILA-SALES_BY_STORE-B-02: selectDifference()")
    @Nested
    class SelectDifferenceTest {

        @Test
        void __returnsTheDifference() {
            assertThat(getRepositoryInstance().selectDifference())
                    .isEqualByComparingTo("46.98");
        }
    }

    @DisplayName("SAKILA-SALES_BY_STORE-I-01: selectSumsByStoreAndWithARental()")
    @Nested
    class SelectSumsByStoreAndWithARentalTest {

        @Test
        void __returnsTheSameSums() {
            final var sums = getRepositoryInstance().selectSumsByStoreAndWithARental();
            assertThat(sums.byStore()).isEqualByComparingTo("67406.56");
            assertThat(sums.withARental()).isEqualByComparingTo("67406.56");
        }
    }

    @DisplayName("SAKILA-SALES_BY_STORE-I-02: selectAllWithTakenByManager()")
    @Nested
    class SelectAllWithTakenByManagerTest {

        @Test
        void __returnsEveryStoreWithTakenByManager() {
            final var list = getRepositoryInstance().selectAllWithTakenByManager();
            assertThat(list)
                    .extracting(SalesByStoreRepository.StoreSalesTakenByManager::store,
                                SalesByStoreRepository.StoreSalesTakenByManager::manager,
                                SalesByStoreRepository.StoreSalesTakenByManager::totalSales,
                                SalesByStoreRepository.StoreSalesTakenByManager::takenByManager)
                    .containsExactlyInAnyOrder(
                            tuple("Lethbridge,Canada", "Mike Hillyer",
                                  new BigDecimal("33679.79"), new BigDecimal("33482.50")),
                            tuple("Woodridge,Australia", "Jon Stephens",
                                  new BigDecimal("33726.77"), new BigDecimal("33924.06")));
        }

        @Test
        void __orderedByStore() {
            final var list = getRepositoryInstance().selectAllWithTakenByManager();
            assertThat(list).extracting(SalesByStoreRepository.StoreSalesTakenByManager::store)
                    .containsExactly("Lethbridge,Canada", "Woodridge,Australia");
        }
    }
}
