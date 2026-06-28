# Diplom_2
API-тесты для сервиса **Stellar Burgers**.

## 🎯 Задание

Протестированы эндпоинты API сервиса Stellar Burgers:

### Создание пользователя
- ✅ Создание уникального пользователя
- ✅ Создание пользователя, который уже зарегистрирован
- ✅ Создание пользователя без заполнения обязательных полей

### Логин пользователя
- ✅ Вход под существующим пользователем
- ✅ Вход с неверными логином и паролем

### Создание заказа
- ✅ С авторизацией
- ✅ Без авторизации
- ✅ С ингредиентами
- ✅ Без ингредиентов
- ✅ С неверным хешем ингредиентов

---

## 🛠️ Технологии

| Технология | Версия |
|------------|--------|
| Java | 11 |
| JUnit | 4.13.2 |
| REST Assured | 5.3.0 |
| Allure | 2.21.0 |
| Gson | 2.10.1 |

---

## 📁 Структура проекта
```bash

src/test/java/com/stellar/burgers/
├── client/
│ ├── UserClient.java
│ └── OrderClient.java
├── model/
│ ├── User.java
│ ├── Order.java
│ ├── Ingredient.java
│ └── DataGenerator.java
├── test/
│ ├── BaseTest.java
│ ├── UserTest.java
│ ├── LoginTest.java
│ └── OrderTest.java
└── utils/
  └── ResponseUtils.java
``` 

## 🧪 Запуск тестов

```bash

mvn clean test
``` 
## 📊 Allure-отчёт
```bash

mvn allure:report
``` 
## 👩‍💻 Автор
KateTesla13

## 📌 Статус
```bash

✅ Задание 2 выполнено

Создан отдельный репозиторий
Создан Maven-проект
Подключены JUnit 4, REST Assured, Allure
Написаны тесты
Сделан Allure-отчёт
``` 
