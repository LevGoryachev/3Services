<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Orders</title>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/app.js"></script>
    </head>
    <body>
        <div class="cblock">
            <h2>Welcome to Order Service Web Interface</h2>
            <h2>Добро пожаловать в веб интерфейс сервиса заказов!</h2>
            <h3>Edit page</h3>
            <h3>Страница редактирования</h3>
        </div>
        <div class="cblock">
            <form:form id="orderForm"
                    method="POST"
                    action="${pageContext.request.contextPath}/orders/save" modelAttribute="order">
                <table>
                    <thead>
                        <tr>
                            <th>ID Заказа</th>
                            <th>Имя заказчика</th>
                            <th>Адрес заказчика</th>
                            <th>Общая сумма заказа</th>
                            <th>Дата создания</th>
                            <th>Детали заказа</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><form:input path="id" readonly="true"/></td>
                            <td><form:input path="customerName" pattern="^[^\s]*$" /></td>
                            <td><form:input path="address" /></td>
                            <td><form:input path="summ" pattern="^\\d+(\\.\\d{2})$"/></td>
                            <td><form:input path="createdDate" readonly="true"/></td>
                            <td style="min-width: 520px">
                                <div class="tabs">
                                    <table id="lineTable" border="1" cellpadding="5" cellspacing="0">
                                        <thead>
                                        <tr>
                                            <th>ID Строки</th>
                                            <th>Серийный номер товара</th>
                                            <th>Название товара</th>
                                            <th>Количество</th>
                                            <th>Действие</th>
                                        </tr>
                                        </thead>

                                        <c:forEach items="${order.orderDetails}" var="orderDetails" varStatus="counter">
                                            <tr>
                                                <td><form:input path="orderDetails[${counter.index}].itemNumber" pattern="^[ 0-9]+$" /></td>
                                                <td><form:input path="orderDetails[${counter.index}].serialNumber" pattern="^[^\s]*$" /></td>
                                                <td><form:input path="orderDetails[${counter.index}].productName" /></td>
                                                <td><form:input path="orderDetails[${counter.index}].qty" pattern="^[ 0-9]+$" /></td>
                                                <td><button type="button" onclick="removeLine(this)">Удалить</button></td>
                                            </tr>
                                        </c:forEach>

                                        <tr>
                                            <td colspan="5"><button type="button" onclick="addLine()">+ Добавить позицию</button></td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form:form>
            <div class="cbutton">
                <button onclick="submitForm()">Сохранить</button>
                <form:form id="orderDelete"
                            method="DELETE"
                            action="${pageContext.request.contextPath}/orders/edit/delete/${order.id}"
                            onsubmit="return confirm('Удалить этот заказ?');">
                    <button type="submit">Удалить заказ?</button>
                </form:form>
                <form:form id="goBack"
                            method="GET"
                            action="${pageContext.request.contextPath}/orders"
                            onsubmit="return confirm('Вернуться без сохранения?');">
                    <button type="submit">Отмена/назад</button>
                </form:form>
            </div>
        </div>
</body>
</html>