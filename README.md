# 3Services
<p><b>TimeService</b> - WEB сервис, предоставляющий текущее время.</p>
<p><b>OrderService</b> - API с логикой для работы с заказами (работает с PostgreSQL).</p>
<p><b>UI</b> - WEB интерфейс на JSP (+ использованы JS, стили CSS), отображает заказы и их содержимое из OrderService,
 а также подгружает текущую дату/время из TimeService по REST. В том числе, в UI предусмотрена возможность
 редактирования, добавления, удаления заказов с позициями,
 поля ввода имеют предварительную валидацию перед отправкой данных на backend.</p>

<p>Java 11, Spring-Boot, Spring Data JPA, Sping Web Flux, Swagger, JSP...</p>
<p>Сборка - Gradle.</p>

<p><b>Для развертывания:</b></p>
<ul>
<li><b>Собранные WAR архивы</b> <a href="https://github.com/LevGoryachev/3Services/releases" target="_blank">(releases)</a></li>
<li><b>Java 11< версии</b></li>
<li><b>TomCat (тестил на версии 8.5)</b></li>
<li><b>БД PostgreSQL (тестировалось на 13 вер.)</b></li>
</ul>

<h3>Описание работы веб-сервисов</h3>
<p><b>Сервис UI:</b></p>
<p>Слой контроллеров ru.goryachev.ui.controller вызывает соответсвующие методы слоя сервисов ru.goryachev.ui.service,
а service - использует вебклиенты ru.goryachev.ui.webclient, передавая в методы url ресурса, id и т.д.
 На каждый микросервис написан отдельный клиент с предустановлеными url, timeouts из пропертей (application.yml). 
 </p>
<p>При get запросе на /orders (просмотр заказов в браузере) UiService делает get запрос на сервис работы с заказами
 (OrderService), и в случае успеха делает второй запрос на сервис получения времени (TimeService).
 Данные из ответов собираюся в HashMap и передаются в атрибуты модели (Model) для вывода на страницу.</p>
 <p>Страница просмотра заказов выглядит следующим образом:</p>
 
 
 ![UI_orders_view](https://user-images.githubusercontent.com/61917893/167987851-ffde60b8-f214-4542-8156-c676ce85d400.jpg)
 
 
 <p>В случае, если TimeService не отвечает,
 данные в Model всё же передаются и заказы отображаются без отметки "времени". 
 В логах выводится предупреждение об отсутствии ответа от соответствующего сервиса.
 <p>В случае, если OrderService не отвечает (исключение WebClientа обрабатывается в try/catch)
 в лог выводится сообщение, пробрасывается MicroserviceNotAnswerException
 которое отлавливается хендлером (ControllerAdvisor) в общем порядке и в тело ответа помещаюся сообщения.
 В этом случае обращение к сервису времени TimeService не происходит за ненадобностью.</p>
<p>Данное поведение можно проверить, поочередно отключая TimeService, OrderService при работающем UI,
 обновляя страницу.</p>
<p>Для редактирования или добавления нового заказа предусмотрена отдельная JSP страница, где возможно
отредактировать заказ с позициями. Заказ с позициями формируется на странице, при нажатии "сохранить"
заносится в атрибут модели как одна сущность (one-to-many) и передаётся в метод сервис-слоя
 для последующей обработки. Далее, при помощи webclientа (ru.goryachev.ui.webclient) делается post/put
  запрос с телом на API сервиса заказов OrderService.</p>
 <p>Delete кнопка удаления удаляет заказ и все позиции к нему из БД.</p>
<p>Страница редактирования заказов выглядит следующим образом:</p>

![UI_orders_edit](https://user-images.githubusercontent.com/61917893/167987869-75322237-8bc9-4ce8-a52b-73b194a4fb2d.jpg)

   <p><b>Сервисы  TimeService и OrderService:</b></p>
   <p>Представляют собой REST сервисы (end-поинты - описаны swagger-ui,
    кратко: TimeService - только GET, OrderService - CRUD операции).</p>
    
<h3>Подготовка к работе сервисов (например на localhost):</h3>

<p><b>1. Cоздать структуру БД</b></p>
<p>Можно создать структуру таблиц в БД, скопировав в psql консоль:</p>
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
<p>Также, структура БД в файле доступна в проекте:<br> OrderService\src\main\resources\db.migration\V1_0__OrderService_DDL.sql</p>
<p>и файл с тестовыми данными:<br> OrderService\src\main\resources\db.migration\testData.sql<br>
</p>

<p>При создании структуры другим способом проверить
имя БД на соответствие "orderservicedb",
при необходимости изменить в application.properties (модуль OrderService) spring.datasource.url
или spring datasource переменные (url, user, password)</p>

<p><b>2. Развернуть вебсервисы:</b></p>
<p> скопировать war-файлы (3шт OrderService.war, TimeService.war, ui-1.0.war) в работающий Tomcat
(в папку \apache-tomcat\webapps или через ui Tomcat). 
Готовые сборки можно взять из релизов <a href="https://github.com/LevGoryachev/3Services/releases" target="_blank">(releases)</a>
</p> 
<p>При необходимости можно также самому собрать модули (предусмотрены отдельные build.gradle
 для каждого сервиса), используя GradleWrapper следующим образом:</p>
<p>В консоли из папки проекта (3Services):</p>
<p>выполнить <b>gradlew build</b> (соберутся все 3 сборки);</p>
<p>Собрать по отдельности:</p>
<p><b>gradlew build -p OrderService</b></p>
<p><b>gradlew build -p TimeService</b></p>
<p><b>gradlew build -p ui</b></p>
<p>war архивы будут положены в build/libs соответствующего модуля</p>
<p>Проверить url развернутых двух сервисов (OrderService и TimeService)
на соответствие настроек urlscheme в UI модуле ui\src\main\resources\<b>application.yml</b>, чтобы с ними мог корректно работать UI сервис.
При необходимости изменить в <b>application.yml</b> модуля urlscheme (параметр domain), apiversion и subdomain оставить без изменения.</p>

<h3>Описание структуры проекта, назначение пакетов (packages)</h3>
<p>Каждый сервис (микросервис) выделен в отдельный модуль. Итого 3 модуля: UI, OrderService, TimeService
с возможностью отдельной сборки каждого.</p>
<ul>
<li>api (или controllers) - классы контроллеров, REST-контроллеров</li>
<li>app - классы инициализации/запуска, конфиги Swagger</li>
<li>model - классы сущностей Entity, классы композитных ключей</li>
<li>repository - расширение интерфейсов JPA</li>
<li>service - классы для логики</li>
<li>exception - содержит кастомные исключения, а также Advice контроллеры (отлавливают все исключения)</li>
<li>webclient - реализации клиентов для подключения к другим микросервисам</li>
<li>filter - классы фильтров (WebFilter)</li>
</ul>

<p>Lev Goryachev 2022</p>
