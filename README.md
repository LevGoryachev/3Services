# 3Services
<p><b>TimeService</b> - WEB сервис, предоставляющий текущее время.</p>
<p><b>OrderService</b> - API с логикой для работы с заказами (работает с PostgreSQL).</p>
<p><b>UI</b> - WEB интерфейс (JSP), отображает заказы и их содержимое из OrderService,
 а также подгружает текущую дату/время из TimeService по REST.</p>
<p>Java 11, Spring-Boot, Spring Data JPA, Sping Web Flux, Swagger, JSP...</p>
<p><b>Для развертывания:</b></p>

<ul>
<li><b>Java 11< версии</b></li>
<li><b>TomCat (тестил на версии 8.5)</b></li>
<li><b>БД PostgreSQL (тестировалось на 13 вер.)</b></li>
</ul>

<p>Подготовка к работе сервисов (например на localhost):</p>
<ul>
<li><b>Создать структуру таблиц в БД</b>, скопировав в psql консоль:
<p>
CREATE DATABASE orderservicedb;<br>
\c orderservicedb<br>
CREATE TABLE "orders"(<br>
order_id BIGSERIAL PRIMARY KEY,<br>
customer_name VARCHAR(100) NOT NULL CHECK(customer_name !=''),<br>
address VARCHAR(100),<br>
summ DECIMAL(25,2) NOT NULL CHECK(summ > 0),<br>
created_date TIMESTAMP DEFAULT now()<br>
);<br>
CREATE TABLE "order_details"(<br>
item_number BIGINT NOT NULL,<br>
serial_number VARCHAR(100),<br>
product_name VARCHAR(100) NOT NULL CHECK(product_name !=''),<br>
qty BIGINT NOT NULL CHECK(qty > 0),<br>
order_id BIGINT REFERENCES "orders"(order_id) ON DELETE CASCADE,<br>
PRIMARY KEY (item_number, order_id)<br>
);<br>
</p>
<p>файл структуры: OrderService\src\main\resources\db.migration\V1_0__OrderService_DDL.sql<br>
и файл с тестовыми данными: OrderService\src\main\resources\db.migration\testData.sql</p>
<p>также имеются в <a href="https://github.com/LevGoryachev/3Services/releases" target="_blank">(Releases)</a></p>
<p>При создании структуры другим способом проверить
имя БД на соответствие "orderservicedb",
при необходимости изменить в application.properties (модуль OrderService) spring.datasource.url
или spring datasource переменные (url, user, password)</p>
</li>
<li><b>Развернуть вебсервисы:</b> скопировать war-файлы (3шт OrderService.war, TimeService.war, ui-1.0.war) в работающий Tomcat
(в папку \apache-tomcat\webapps или через ui Tomcat). 
Готовые сборки можно взять из релизов <a href="https://github.com/LevGoryachev/3Services/Releases" target="_blank">(Releases)</a>
 
<p>При необходимости можно также собрать модули (написаны отдельные build.gradle для каждого сервиса), используя GradleWrapper следующим образом:</p>
<p>В консоли из папки проекта (3Services): <b>gradlew build</b> (соберутся все модули)</p>
<p>По отдельности:</p>
<p><b>gradlew build -p OrderService</b></p>
<p><b>gradlew build -p TimeService</b></p>
<p><b>gradlew build -p ui</b></p>
<p>war архивы будут положены в build/libs соответствующего модуля</p>
</li>
<li><p>Проверить url развернутых двух сервисов (OrderService и TimeService)
на соответствие настроек urlscheme в UI модуле ui\src\main\resources\<b>application.yml</b>, чтобы с ними мог корректно работать UI сервис.
При необходимости изменить в <b>application.yml</b> модуля urlscheme.</p></li>
</ul>

<p>Описание структуры</p>
<ul>
<li>api - классы контроллеров</li>
<li>app - классы инициализации/запуска, конфиги Swagger</li>
<li>model - классы сущностей Entity, классы композитных ключей</li>
<li>repository - расширение интерфейсов JPA</li>
<li>service - классы для логики</li>
<li>exception - содержит кастомные исключения, а также Advice контроллеры (отлавливают все исключения)</li>
</ul>

<p>Описание работы веб-сервисов</p>
<p>При get запросе на /orders (UiService) делает get запрос на сервис работы с заказами,
 и в случае успеха делает второй запрос на сервис получения времени (TimeService).
 Данные из ответов собираюся в HashMap и передаются в Model для вывода на страницу.</p>
 <p>В случае, если TimeService не отвечает,
 данные в Model всё же передаются и заказы отображаются без отметки "времени". 
 В логах выводится предупреждение об отсутствии ответа.
 <p>В случае, если OrderService не отвечает, в лог выводится сообщение, пробрасывается
  MicroserviceNotAnswerException которое отлавливается Advice-контроллером(ControllerAdvisor)
   в общем порядке и в тело ответа помещаюся сообщения.</p>
   <p>Данное поведение можно проверить, поочередно отключая TimeService, OrderService</p>

<p>Lev Goryachev 2022</p>
