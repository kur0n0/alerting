FROM eclipse-temurin:17-jdk-alpine
COPY target/*.jar alerting.jar
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom", "-jar", "/alerting.jar"]