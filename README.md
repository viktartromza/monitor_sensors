## О проекте
Приложение для мониторинга датчиков, контролирующих праметры микроклимата в помещениях

## Для запуска приложения
необходимо прописать значения для переменных окружения для основной базы данных DATASOURCE_URL, DATASOURCE_USERNAME, DATASOURCE_PASSWORD
и для базы данных статистики SM_DATASOURCE_URL, SM_DATASOURCE_USERNAME, SM_DATASOURCE_PASSWORD

## Пользователи

В системе 2 предустановленных пользователя:
### -admin (пароль admin) c ролью Administrator
### -user (пароль user) c ролью Viewer

## Swagger

Доступен по адресу http://localhost:8585/swagger-ui/index.html


## TO DO
- операции по добавлению, изменению статуса и роли пользователя
- покрытие кода юнит и интеграционными тестами
- доработка exception-handler
