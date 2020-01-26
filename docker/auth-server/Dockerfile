FROM ubuntu
COPY oauthdemo-0.0.2-SNAPSHOT.jar .
RUN apt-get update && apt-get upgrade -y
RUN apt-get install default-jre -y
EXPOSE 8901
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "oauthdemo-0.0.2-SNAPSHOT.jar"]
