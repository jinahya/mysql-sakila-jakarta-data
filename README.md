# mysql-sakila-jakarta-data

[Jakarta Data](https://jakarta.ee/specifications/data/1.0/) repositories for the
[MySQL Sakila Sample Database](https://dev.mysql.com/doc/sakila/en/), over the entities of
[mysql-sakila-persistence](https://github.com/jinahya/mysql-sakila-persistence).

The sibling [mysql-sakila-spring-data](https://github.com/jinahya/mysql-sakila-spring-data) is the same thing in
Spring Data JPA.

## The provider

One: [Hibernate Data Repositories](https://docs.hibernate.org/orm/7.4/repositories/html_single/Hibernate_Data_Repositories.html).
`hibernate-processor` implements every `@Repository` interface at compile time as a `<Name>_` class, which runs on
a `StatelessSession` of Hibernate ORM. EclipseLink 5.0 implements Jakarta Persistence 3.2 only, no Jakarta Data,
so unlike upstream this build has no provider profiles.

## The database

Upstream owns the compose file and the init scripts:

```shell
$ cd ../mysql-sakila-persistence && ./_docker-compose-up.sh
```

brings up `mysql:9.7` on `localhost:3306` as `root` / `password`, loaded with the Sakila schema and data; Ctrl-C takes
it down again. The `sakila-it` unit of [`persistence.xml`](src/test/resources/META-INF/persistence.xml) points there.

## Building

```shell
$ ./_mvn_test.sh              # unit tests, H2 in MySQL mode
$ ./_mvn_verify.sh            # + integration tests, against the database above
$ ./_mvn_verify_notests.sh    # no database needed
```

The upstream entity jar is a local `SNAPSHOT`: `./mvnw -DskipTests install` in `../mysql-sakila-persistence` first.
