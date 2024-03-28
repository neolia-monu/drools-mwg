#!/bin/sh


DATABASE_NAME="drools-db"
USERNAME="username"
PASSWORD="password"
CONTAINER_NAME="postgres_drools"

docker run -d \
    --name $CONTAINER_NAME \
    -p 5431:5432 \
    -e POSTGRES_DB=$DATABASE_NAME \
    -e POSTGRES_USER=$USERNAME \
    -e POSTGRES_PASSWORD=$PASSWORD \
    -v postgres_data:/var/lib/postgresql/data \
    postgres:latest

echo "PostgreSQL container '$CONTAINER_NAME' is running."

docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' postgres_drools