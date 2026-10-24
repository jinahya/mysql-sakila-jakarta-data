package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.SalesByStore;
import jakarta.data.repository.Repository;
import org.hibernate.annotations.processing.SQL;

import java.math.BigDecimal;
import java.util.List;

/**
 * A repository for the {@code sales_by_store} view of the Sakila database.
 * <p>
 * Upstream maps the view by {@link SalesByStore}, without {@code @Entity}: no column of it
 * identifies a row. So this repository has no primary entity: it extends neither
 * {@link _Repository} nor {@link jakarta.data.repository.CrudRepository}, and each of its methods
 * runs a native query, through Hibernate's {@link SQL @SQL}, whose rows the generated class maps to
 * a record by the record's constructor. The exercises of the view are all native-only, and each
 * method's {@code @SQL} is the SQL of upstream's {@code EXERCISES.asciidoc}, as it is.
 */
@Repository
public interface SalesByStoreRepository {

    // ------------------------------------------------------------------------------------------------------ exercises

    /**
     * A row of the view; a row of {@link #selectAll()}.
     *
     * @param store      the store, by its city and country.
     * @param manager    the full name of the store's manager.
     * @param totalSales the sum of the payments of the store.
     */
    record StoreSales(String store, String manager, BigDecimal totalSales) {
    }

    /**
     * The stores' sales summed, next to the sum of the payments with a rental; the row of
     * {@link #selectSumsByStoreAndWithARental()}.
     *
     * @param byStore     the sum of the {@code total_sales} of the view.
     * @param withARental the sum of the {@code amount} of the payments which have a rental.
     */
    record SalesSums(BigDecimal byStore, BigDecimal withARental) {
    }

    /**
     * A row of the view, with the sum of the payments its manager took; a row of
     * {@link #selectAllWithTakenByManager()}.
     *
     * @param store          the store, by its city and country.
     * @param manager        the full name of the store's manager.
     * @param totalSales     the sum of the payments of the store.
     * @param takenByManager the sum of the payments the manager took.
     */
    record StoreSalesTakenByManager(String store, String manager, BigDecimal totalSales,
                                    BigDecimal takenByManager) {
    }

    /**
     * Selects every row of the view ({@code SAKILA_SALES_BY_STORE_B_01}, native-only, which asks for
     * no order; the view's own is by country, then by city).
     *
     * @return a list of the rows of the view, one per store.
     */
    @SQL("""
            SELECT store, manager, total_sales
            FROM sales_by_store""")
    List<StoreSales> selectAll();

    /**
     * Selects how far apart the two stores' sales are ({@code SAKILA_SALES_BY_STORE_B_02},
     * native-only, a single value, so no sort).
     *
     * @return the difference between the largest and the smallest {@code total_sales}.
     */
    @SQL("""
            SELECT MAX(total_sales) - MIN(total_sales) AS difference
            FROM sales_by_store""")
    BigDecimal selectDifference();

    /**
     * Selects the stores' sales summed, next to every payment with a rental summed
     * ({@code SAKILA_SALES_BY_STORE_I_01}, native-only, a single row, so no sort).
     *
     * @return the two sums, which are the same.
     */
    @SQL("""
            SELECT (SELECT SUM(total_sales) FROM sales_by_store) AS by_store,
                   (SELECT SUM(amount) FROM payment WHERE rental_id IS NOT NULL) AS with_a_rental""")
    SalesSums selectSumsByStoreAndWithARental();

    /**
     * Selects each store's sales, next to what its manager took
     * ({@code SAKILA_SALES_BY_STORE_I_02}, native-only, which orders by {@code store}).
     *
     * @return a list of the rows of the view, with the sums their managers took, by store.
     */
    @SQL("""
            SELECT v.store, v.manager, v.total_sales, SUM(p.amount) AS taken_by_manager
            FROM sales_by_store v
                     JOIN staff s ON CONCAT(s.first_name, ' ', s.last_name) = v.manager
                     JOIN payment p ON p.staff_id = s.staff_id
            GROUP BY v.store, v.manager, v.total_sales
            ORDER BY v.store""")
    List<StoreSalesTakenByManager> selectAllWithTakenByManager();
}
