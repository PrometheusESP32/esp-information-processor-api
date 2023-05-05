FROM eclipse-temurin:17-jdk-jammy AS builder
ARG JAR_NAME=prometheus-esp-information-processor-api-0.0.1-SNAPSHOT.jar
RUN mkdir -p /home
WORKDIR /home
COPY . .
RUN /bin/sh -c "chmod +x ./mvnw"
RUN /bin/sh -c "./mvnw dependency:resolve"
RUN /bin/sh -c "./mvnw clean package"

FROM eclipse-temurin:17-jdk-jammy AS runner
ARG JAR_NAME=prometheus-esp-information-processor-api-0.0.1-SNAPSHOT.jar

RUN mkdir -p /home
COPY --from=builder /home/target/${JAR_NAME} /home/app.jar

COPY docker/Bangkok /etc/localtime
RUN echo Asia/Bangkok > /etc/timezone

WORKDIR /home

EXPOSE 8080

ENTRYPOINT exec java $JAVA_OPTS $TIME_ZONE -jar /home/app.jar $JAVA_CONFIGS