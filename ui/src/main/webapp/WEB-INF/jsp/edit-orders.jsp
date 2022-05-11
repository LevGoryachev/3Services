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
            <h3>Receiving time (from TimeService): </h3>
            <h3>Время загрузки данных о заказах (из TimeService):</h3>
            <%--<h2>${attributes.timeService.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))}</h2>--%>
        </div>
        <div class="cblock">
            <form:form
                    method="POST"
                    action="${pageContext.request.contextPath}/orders/edit/update" modelAttribute="order">
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
                            <td><form:input path="customerName" /></td>
                            <td><form:input path="address" /></td>
                            <td><form:input path="summ"/></td>
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
                                                <td><form:input path="orderDetails[${counter.index}].itemNumber" /></td>
                                                <td><form:input path="orderDetails[${counter.index}].serialNumber" /></td>
                                                <td><form:input path="orderDetails[${counter.index}].productName" /></td>
                                                <td><form:input path="orderDetails[${counter.index}].qty" /></td>
                                                <td><button onclick="removeLine(this)">Удалить</button></td>
                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            <td colspan="3"><button onclick="addLine()">+ Добавить позицию</button></td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Submit new"/></td>
                        </tr>
                    </tbody>
                </table>
            </form:form>
        </div>
</body>
</html>