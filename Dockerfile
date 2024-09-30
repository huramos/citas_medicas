FROM eclipse-temurin:22-jdk AS buildstage

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src
COPY Wallet_U2XJ61GE26MMMU70 /app/wallet

ENV TNS_ADMIN=/app/wallet

RUN mvn clean package

FROM eclipse-temurin:22-jdk

COPY --from=buildstage /app/target/citas_medicas-0.0.1-SNAPSHOT.jar /app/citas_medicas.jar

COPY Wallet_U2XJ61GE26MMMU70 /app/wallet
ENTRYPOINT [ "java", "-jar", "/app/citas_medicas.jar" ]