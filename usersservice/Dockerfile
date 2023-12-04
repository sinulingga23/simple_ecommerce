FROM amazoncorretto:17-alpine-jdk
MAINTAINER Denny Rezky Sinulingga

COPY target/usersservice-0.0.1-SNAPSHOT.jar usersservice-0.0.1.jar
COPY target/classes/application.properties application.properties
COPY wait-for-it.sh wait-for-it.sh

CMD ["sh", "-c", "./wait-for-it.sh db:5432"]

ENTRYPOINT ["java","-jar","/usersservice-0.0.1.jar"]