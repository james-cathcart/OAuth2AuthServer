FROM postgres

COPY authdb_dump.pgsql /

RUN mkdir -p /docker-entrypoint-initdb.d

COPY init-db.sh /docker-entrypoint-initdb.d
