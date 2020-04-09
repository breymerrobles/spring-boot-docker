##Create Image.
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY build/libs/*.jar  /home/app/target/app.jar
#ADD build/libs/*.jar  /home/app/target/app.jar
WORKDIR  /home/app/target/
RUN chmod 777 /home/app/target/*.jar
EXPOSE 9193
ENTRYPOINT exec  java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /home/app/target/app.jar
