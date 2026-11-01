package com.github.jinahya.mysql.sakila.persistence.data;

import com.github.jinahya.mysql.sakila.persistence.FilmList;
import jakarta.data.repository.Repository;

/**
 * A repository for the {@code film_list} view of the Sakila database.
 * <p>
 * Upstream maps the view by {@link FilmList}, without {@code @Entity}: no column of it identifies a row. So this
 * repository has no primary entity: it extends neither {@link _Repository} nor
 * {@link jakarta.data.repository.CrudRepository}, and each method it gets will run a native query, through Hibernate's
 * {@link org.hibernate.annotations.processing.SQL @SQL}, as {@link SalesByStoreRepository}'s do. It declares none yet.
 */
@Repository
public interface FilmListRepository {

}
