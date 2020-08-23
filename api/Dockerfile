FROM adoptopenjdk/openjdk11:ubi

ADD target/*.jar /app.jar

RUN sh -c 'touch /app.jar'

VOLUME /tmp

CMD ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]

EXPOSE 8080