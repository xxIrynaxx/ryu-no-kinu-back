# --- Первый этап: Сборка приложения (Build Stage) ---
FROM openjdk:17-jdk-slim-bullseye AS builder # Изменено здесь

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем pom.xml первыми для лучшего использования кеширования Docker
COPY pom.xml .

# Копируем остальной исходный код
COPY src ./src

# Выполняем сборку Maven. Это создаст папку 'target' с JAR-файлом.
RUN mvn clean install -DskipTests

# --- Второй этап: Запуск приложения (Runtime Stage) ---
# Используем более легкий образ OpenJDK для конечного рантайма
FROM openjdk:17-jre-slim-bullseye # Изменено здесь

# Устанавливаем рабочую директорию для приложения
WORKDIR /app

# Копируем скомпилированный JAR-файл из первого этапа (builder)
COPY --from=builder /app/target/ryu-no-kinu-back-0.0.1-SNAPSHOT.jar app.jar

# Указываем порт, который слушает ваше Spring Boot приложение (по умолчанию 8888)
EXPOSE 8888

# Команда для запуска приложения при старте контейнера
ENTRYPOINT ["java", "-jar", "/app.jar"]