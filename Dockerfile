FROM eclipse-temurin:21
WORKDIR /app
COPY . /app 
RUN chmod +x mvnw &&  ./mvnw clean package -DskipTests
CMD ["./mvnw", "spring-boot:run"]