# Task Management System

Это проект системы управления задачами, разработанный на Spring Boot.

## Локальный запуск с помощью Docker Compose

Для запуска проекта локально с использованием Docker Compose выполните следующие шаги:

### Требования
- Установленный Docker и Docker Compose.
- Java 17 (для сборки проекта).
- Установленный Gradle (или используйте Gradle Wrapper).

### Шаги для запуска

1. **Сборка проекта**:
   Убедитесь, что проект собран в JAR-файл. Выполните команду:
   ```bash
   ./gradlew clean build

2. **Запуск Docker Compose**: Перейдите в корневую директорию проекта и выполните команду:

    ```bash
   docker-compose up --build
   
3. Доступ к приложению: После успешного запуска приложение будет доступно по адресу:
    
   ```bash
   http://localhost:8080/swagger-ui/index.html

4. Остановка контейнеров: Для остановки контейнеров выполните команду:

    ```bash
   docker-compose down

### Описание сервисов

* База данных: MySQL доступна на порту 3310.
* Приложение: Spring Boot приложение доступно на порту 8080.

 ### Переменные окружения
* SPRING_DATASOURCE_URL: URL для подключения к базе данных.
* SPRING_DATASOURCE_USERNAME: Имя пользователя базы данных.
* SPRING_DATASOURCE_PASSWORD: Пароль пользователя базы данных.
* SPRING_JPA_HIBERNATE_DDL_AUTO: Режим работы Hibernate (в данном случае validate).
* SPRING_FLYWAY_ENABLED: Включение Flyway для миграций.
* SPRING_FLYWAY_LOCATIONS: Путь к папке с миграциями.
* JWT_SECRET: Секретный ключ для JWT.
* JWT_LIFETIME: Время жизни JWT-токена.


