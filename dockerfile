FROM openjdk:12
ADD target/arch-intercessor-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]