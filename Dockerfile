FROM azul/zulu-openjdk-alpine:11

RUN apk add bash curl nss tree \
        && rm -rf /var/cache/apk/*

ENV APP_NAME inventory-service-app-bootable
ENV APP_HOME /opt/applications/${APP_NAME}
ENV APP_FILE ${APP_NAME}.jar

WORKDIR ${APP_HOME}

COPY target/${APP_FILE} ${APP_HOME}

EXPOSE 8080 8787 9990

ADD container-scripts/run.sh /run.sh

RUN chmod a+x /run.sh

CMD [ "/run.sh" ]
