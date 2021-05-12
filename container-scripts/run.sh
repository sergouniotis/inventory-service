#!/bin/sh

# Enters a loop, until a connection is established to the provided URL.
while ! echo exit | nc ${POSTGRESQL_SERVICE_HOST} ${POSTGRESQL_SERVICE_PORT}; do sleep 10; done

cd $APP_HOME && java $JAVA_OPTS -jar $APP_FILE -b=0.0.0.0
