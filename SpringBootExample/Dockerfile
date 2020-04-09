##build project
FROM gradle:6.3.0-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon 

##Create Image.
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /home/app/target/spring-boot-example.jar
WORKDIR  /home/app/target/
RUN chmod 777 /home/app/target/*.jar
EXPOSE 5000
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /home/app/target/spring-boot-example.jar" ]