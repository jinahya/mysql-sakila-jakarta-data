/**
 * Jakarta Data repositories for the entities of the MySQL Sakila Sample Database, which live one package up, in the
 * upstream {@code mysql-sakila-persistence} artifact.
 * <p>
 * Every repository here is an interface annotated {@link jakarta.data.repository.Repository}; its implementation, a
 * {@code <Name>_} class in this package, is generated at compile time by Hibernate Data Repositories and runs on a
 * {@link org.hibernate.StatelessSession}.
 * <p>
 * The package is {@link NullMarked}: every parameter and return type here is non-null unless marked {@code @Nullable}.
 * A lookup that may find nothing returns an {@link java.util.Optional} instead of a nullable entity.
 */
@NullMarked
package com.github.jinahya.mysql.sakila.persistence.data;

import org.jspecify.annotations.NullMarked;
