FROM ubuntu
COPY oauthserver-0.0.2-SNAPSHOT.jar .
RUN apt-get update && apt-get upgrade -y
RUN apt-get install default-jre -y

ARG key_value
ENV ENCRYPT_KEY=$key_value

EXPOSE 8901
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "oauthserver-0.0.2-SNAPSHOT.jar"]
