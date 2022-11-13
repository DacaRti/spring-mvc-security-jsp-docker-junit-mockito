FROM maven:3.8.6-openjdk-11 AS mvn-build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean install

FROM tomcat:9.0.65-jre11
COPY --from=mvn-build /app/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]