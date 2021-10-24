FROM java:8-jdk-alpine

COPY ./target/e-rickshaw-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch e-rickshaw-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","e-rickshaw-0.0.1-SNAPSHOT.jar"]