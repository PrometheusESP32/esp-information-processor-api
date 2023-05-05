FROM openjdk:21-ea-17-jdk-slim
ARG JAR_NAME=esp32-processor-api-0.0.1-SNAPSHOT.jar

RUN mkdir -p /home
ADD ../target/${JAR_NAME} /home/app.jar

COPY Bangkok /etc/localtime
RUN echo Asia/Bangkok > /etc/timezone

WORKDIR /home

EXPOSE 8080

ENTRYPOINT exec java $JAVA_OPTS $TIME_ZONE -jar /home/app.jar $JAVA_CONFIGS