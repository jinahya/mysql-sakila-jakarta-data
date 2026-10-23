# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this project is

Jakarta Data 1.0 repositories over the Jakarta Persistence entities of
[`mysql-sakila-persistence`](https://github.com/jinahya/mysql-sakila-persistence), which maps the
[MySQL Sakila Sample Database](https://dev.mysql.com/doc/sakila/en/). A single, parent-less jar. The sibling
`../mysql-sakila-spring-data` does the same in Spring Data JPA, but this module does **not** follow its shape or
rules; design for Jakarta Data on its own terms. The author's background is Java EE 5/6, so plain DAO-style
explanations and container-free code are preferred.

- Main package: `com.github.jinahya.mysql.sakila.persistence.data` (`@NullMarked`, JSpecify; a lookup that may find
  nothing returns `Optional`).
- One repository per upstream entity, `<Entity>Repository`, annotated `@Repository`. They are empty so far. Each
  extends **both** `_Repository<Entity, Id>` (the unannotated shared base; Jakarta Data needs no `@NoRepositoryBean`)
  **and** `CrudRepository<Entity, Id>` directly. Drop the direct `CrudRepository` and `hibernate-processor` fails with
  "repository does not have a well-defined primary entity type for inherited member 'deleteById'". A query method in
  `_Repository` is implemented per repository with the concrete types, and makes the processor emit a harmless, empty
  `_Repository_` metamodel class.
- Upstream entities and their static metamodel (`Actor_`, `Film_`, ...): `com.github.jinahya.mysql.sakila.persistence`
  in `io.github.jinahya:mysql-sakila-persistence`, one package up.
- **One provider, no provider profiles.** Hibernate Data Repositories is the only Jakarta Data provider for Java SE:
  `hibernate-processor` implements every `@Repository` interface at compile time as a `<Name>_` class under
  `target/generated-sources/annotations`, which runs on an `org.hibernate.StatelessSession`. EclipseLink 5.0 has no
  Jakarta Data, so unlike upstream there is no EclipseLink axis here.
- No repository names a `dataStore`; the unit is the caller's choice. The generated `<Name>_` has a `public`
  constructor taking a `StatelessSession` (container-free use) and an `@Inject` no-arg constructor that opens its
  session from a `@PersistenceUnit` (CDI use).
- The published jar carries **repositories only**. `jakarta.data-api`, `jakarta.persistence-api`,
  `jakarta.validation-api`, `jakarta.inject-api`, `cdi-api`, `hibernate-core`, `jspecify` and Lombok are all
  `provided`; the consumer brings its own Jakarta Data, provider and (to enforce constraints on repository
  parameters) a validation provider. With `jakarta.inject` / CDI on the processor's classpath, the generated
  `<Name>_` is a `@Dependent` CDI bean with an `@Inject` constructor.
- `-parameters` is on (`maven.compiler.parameters`), as upstream: Jakarta Data binds query parameters by name.

## Upstream

The sibling checkout `../mysql-sakila-persistence` publishes, at `0.0.1-SNAPSHOT`, the entity jar and a second jar of
the same artifact classified `test-companion` (`*_Randomizer` / `*_Persister` classes). Both exist **only in the local
`~/.m2`**: after any change upstream, run `./mvnw -DskipTests install` there before building here. Its
`persistence.xml` is test-only, so this project supplies its own.

- Do not copy upstream's randomizers or persisters into `src/test`; the `test-companion` jar is on the test
  classpath. Podam and `jinahya-persistence-test-utils` are declared here because that jar brings no dependencies.
- The version properties in `pom.xml` (`version.jinahya-persistence`, `version.uk.co.jemos.podam`, Hibernate ORM
  `7.4.12.Final`, H2, mysql-connector-j, ...) deliberately mirror upstream's, so what compiles here against the
  test-companion jar is what that jar was compiled against, and the generated code runs on the ORM that generated
  upstream's metamodel. Change them in step with upstream, not independently.
- Upstream's `../mysql-sakila-persistence/EXERCISES.asciidoc` / `EXERCISES.yaml` is the catalogue of query exercises the sibling projects answer
  one method per exercise, exercise id named in the method's javadoc.

## Build

Maven wrapper (`./mvnw`); the build JDK must be **25** (enforced). Main and test sources both compile at release 25.
Every script below runs `clean` on purpose: a renamed or removed `@Repository` interface leaves a stale generated
`<Name>_` class that the next incremental compile fails on with an unexplained "cannot find symbol".

```sh
./_mvn_test.sh                         # unit tests on H2 in MySQL mode (unit `sakila-test`); no database needed
./_mvn_verify.sh                       # + integration tests (*IT) against the MySQL below (unit `sakila-it`)
./_mvn_verify_notests.sh               # full lifecycle, tests compiled but not run; no database needed
./_mvn_test.sh -Dtest=ActorRepository_Test             # one test class
./_mvn_test.sh -Dtest=ActorRepository_Test#method      # one test method
./_mvn_verify.sh -DskipTests           # the ITs alone
./_mvn_verify.sh -DskipITs             # the unit tests alone
./_mvn_verify.sh -Dit.test=ActorRepository_IT          # one IT class
```

Failsafe is bound only by the `failsafe` profile (`-Pfailsafe`), picking up `**/*IT.java`; a plain `./mvnw verify`
never runs an IT. As of surefire/failsafe 3.6.0, `-DskipTests` reaches surefire only, so `-DskipITs` is the flag
that stops the ITs. Both plugins stop after the first failing class (`skipAfterFailureCount=1`). The enforcer fails
the build on dependency non-convergence, a JDK below 25, and a `-P` naming a profile that does not exist.

**The integration tests need the Sakila container upstream owns**: run `../mysql-sakila-persistence/_docker_compose_up.sh`
(Ctrl-C takes it down with its data). It runs `mysql:9.7` on `localhost:3306`, database `sakila`, `root` /
`password`, loaded with the Sakila schema and data. The `sakila-it` unit connects there with `hbm2ddl.auto=none`:
the sample data is the fixture the ITs read, and nothing may touch the schema or leave rows behind.

## Tests

A Weld SE container per test method (`@EnableAutoWeld` on `__Repository_TestBase<R, T, K>`). The container creates the
generated `<R>_` through its `@Inject` constructor; the concrete test adds the class and names its types:

```java
@AddBeanClasses(ActorRepository_.class)
class ActorRepository_Test
        extends _Repository_Test<ActorRepository, Actor, Integer> {

    ActorRepository_Test() {
        super(ActorRepository.class, Actor.class, Integer.class);
    }
}
```

`getRepositoryInstance()` returns the repository, one `@Dependent` instance (and `StatelessSession`) per test.
`_Repository_Test` (unit `sakila-test`, empty H2 per test, insert what you query) and `_Repository_IT` (unit
`sakila-it`, read the sample data, roll back any write) each add, with an inherited `@AddBeanClasses`, a nested
`EntityManagerFactoryProducer` for their unit. A write needs the repository's own session and its transaction
(`session(getRepositoryInstance()).inTransaction(...)`). Every repository has an empty `<R>_Test` and `<R>_IT`, no
test methods yet. Three things are load-bearing:

- `<R>_` takes the factory from a `@PersistenceUnit` field, resource injection that no producer reaches and Weld SE
  does not fill. `__WeldJpaInjectionServices`, a test-only Weld `JpaInjectionServices` registered in
  `src/test/resources/META-INF/services/org.jboss.weld.bootstrap.api.Service`, hands it the produced factory. Without
  it the field stays `null` (NPE in the generated `@PostConstruct`).
- The producer is `@Singleton`, not `@ApplicationScoped`: `<R>_`'s `unwrap(SessionFactory.class)` on a client proxy
  throws `ClassCastException`.
- The base does not `@Inject R`: an injection point typed by a type variable is a CDI definition error
  (WELD-001407), even when the subclass binds it. It injects `Instance<Object>` and selects `repositoryClass`.

- `META-INF/persistence.xml`: units `sakila-test` (H2 `MODE=MySQL`, `create-drop`) and `sakila-it` (MySQL, `none`),
  mirroring upstream's; keep the two files in step. Both list every upstream class explicitly, since
  `exclude-unlisted-classes` is on.
- `logback-test.xml`: root DEBUG, Hibernate at WARN except `org.hibernate.SQL` (DEBUG) and `org.hibernate.orm.jdbc.bind`
  (TRACE) for statements and bind values.

The test classpath also has Hibernate Validator + Expressly (to enforce the repositories' parameter constraints),
JUnit 5, AssertJ, Mockito, and the upstream `test-companion` randomizers/persisters.
