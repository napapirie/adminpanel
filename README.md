# Corporate Admin Panel

Учебный проект административной панели корпоративного сервиса на **Java 17 + Spring Boot**.

## Возможности

- авторизация через Spring Security;
- роли `ADMIN`, `MANAGER`, `USER`;
- доступ к `/admin` и `/api/admin/**` только для ADMIN;
- создание, просмотр, редактирование, блокировка и удаление пользователей;
- BCrypt-хеширование паролей;
- REST API + Swagger/OpenAPI;
- SOAP endpoint с XSD-контрактом и автоматически публикуемым WSDL;
- JPA/Hibernate, H2 по умолчанию и PostgreSQL driver;
- аудит действий через Observer;
- интеграционные тесты прав доступа.

## Использованные паттерны

**Factory** — `UserFactory` централизует создание пользователя и кодирование пароля.

**Observer** — `AuditObserver` и `LoggingAuditObserver` получают события создания, изменения и удаления пользователей.

Также архитектура использует **Repository** (`UserRepository`) и **Service Layer** (`UserService`).

## Запуск

Требуется JDK 17+ и Maven 3.9+.

```bash
mvn clean test
mvn spring-boot:run
```

Открыть: `http://localhost:8080/admin`

Демонстрационная учётная запись создаётся автоматически:

- login: `admin`
- password: `admin123`

> Для реального проекта пароль администратора нельзя хранить в README/исходниках; здесь он оставлен только для учебной демонстрации.

## REST API

Swagger UI: `http://localhost:8080/swagger-ui.html`

Основные операции:

- `GET /api/admin/users`
- `GET /api/admin/users/{id}`
- `POST /api/admin/users`
- `PUT /api/admin/users/{id}`
- `DELETE /api/admin/users/{id}`

Пример JSON для создания:

```json
{
  "username": "manager1",
  "password": "password123",
  "fullName": "Иван Иванов",
  "roles": ["MANAGER"],
  "enabled": true
}
```

## SOAP

WSDL после запуска: `http://localhost:8080/services/users.wsdl`

Операция `getUserCount` возвращает количество пользователей.

Пример SOAP-запроса:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:usr="http://example.com/corporate/users">
  <soapenv:Header/>
  <soapenv:Body>
    <usr:getUserCountRequest/>
  </soapenv:Body>
</soapenv:Envelope>
```

## База данных

По умолчанию используется файловая H2: `jdbc:h2:file:./data/corporate`.

Для PostgreSQL можно заменить настройки datasource, например через переменные окружения или `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/corporate
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

## Структура

```text
controller/   MVC и REST-контроллеры
service/      бизнес-логика
repository/   доступ к данным
entity/       JPA-сущности и роли
dto/          REST-модели
config/       Security, SOAP, начальные данные
soap/         SOAP endpoint
pattern/      Factory и Observer
exception/    обработка ошибок
resources/    HTML, application.properties, XSD
```

## Тестирование

`SecurityIntegrationTest` проверяет, что:

- неавторизованный пользователь перенаправляется на страницу входа;
- роль USER не получает доступ к админке;
- роль ADMIN получает доступ.

Запуск: `mvn test`.

## Публикация на GitHub

```bash
git init
git add .
git commit -m "Implement corporate admin panel"
git branch -M main
git remote add origin https://github.com/USERNAME/corporate-admin-panel.git
git push -u origin main
```

Перед публикацией замените `USERNAME` на имя своего GitHub-аккаунта.


## Работа с админ-панелью

После запуска откройте `http://localhost:8080/admin` и войдите под `admin / admin123`. В блоке «Создать пользователя» задайте логин, имя, пароль и одну или несколько ролей (`USER`, `MANAGER`, `ADMIN`). В таблице пользователей можно изменить имя, набор ролей, заблокировать/разблокировать учётную запись флажком «Активен», сохранить изменения или удалить пользователя.
