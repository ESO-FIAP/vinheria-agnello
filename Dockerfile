# Build WAR dentro do container (Gradle), sem Maven
FROM gradle:8-jdk17 AS build
WORKDIR /app

COPY settings.gradle build.gradle ./
COPY src ./src

RUN gradle --no-daemon clean war

FROM tomcat:10.1-jre17-temurin
ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/build/libs/vinheria.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
