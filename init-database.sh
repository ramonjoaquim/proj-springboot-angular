#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER docker;
    CREATE DATABASE dcc;
    GRANT ALL PRIVILEGES ON DATABASE dcc TO docker;
    CREATE DATABASE my_project_test;
    GRANT ALL PRIVILEGES ON DATABASE dcc TO docker;
EOSQL
