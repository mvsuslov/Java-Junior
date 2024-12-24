# Используем образ OpenJDK
FROM openjdk:17-jdk-slim

# Устанавливаем Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем проект в контейнер
COPY . .

# Собираем проект с Maven
RUN mvn clean install

# Указываем команду для запуска
CMD ["java", "-cp", "target/my-jpa-project-1.0-SNAPSHOT.jar", "com.example.JpaExample"]
