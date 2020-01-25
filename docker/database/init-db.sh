#!/bin/bash

createdb -U postgres -T template0 authdb
psql -U postgres authdb < authdb_dump.pgsql
