#!/bin/sh
# Runs the unit tests: compile, hibernate-processor generating the repository implementations, and the tests
# against H2 in MySQL mode (the `sakila-test` unit of src/test/resources/META-INF/persistence.xml).
#
# Needs no database.
#
# `clean` is deliberate. The processor writes a <Repository>_ class per @Repository interface, and a renamed or
# removed interface leaves its stale implementation under target/generated-sources, which the next compile picks
# up and fails on with "cannot find symbol" -- a failure silent about its cause.
#
# One provider only: EclipseLink 5.0 has no Jakarta Data, so unlike upstream (mysql-sakila-persistence) there is no
# provider profile to name here.
cd "$(dirname "$0")" || exit 1

exec ./mvnw clean test "$@"
