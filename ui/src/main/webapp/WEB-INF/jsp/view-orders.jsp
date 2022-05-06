<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html>
<head>
    <title>Orders</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="cblock">
    <h2>Welcome to Order Service Web Interface</h2>
    <h3>Receiving time (from TimeService): </h3>
    <h2>${receivedTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))}</h2>
</div>

<div class="main">
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

    <c:forEach items="${orders}" var="orders">
        <tr>
            <td>${orders.id}</td>
            <td>${orders.customerName}</td>
            <td>${orders.address}</td>
            <td>${orders.summ}</td>
            <td>${orders.createdDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))}</td>
            <td>
                <div class="tabs">

                        <table border="1" cellpadding="5" cellspacing="0">
                            <thead>
                            <tr>
                                <th>ID Строки</th>
                                <th>Серийный номер товара</th>
                                <th>Название товара</th>
                                <th>Количество</th>
                            </tr>
                            </thead>
                            <c:forEach items="${orders.orderDetails}" var="orderDetails">
                                <tr>
                                    <td>${orderDetails.itemNumber}</td>
                                    <td>${orderDetails.serialNumber}</td>
                                    <td>${orderDetails.productName}</td>
                                    <td>${orderDetails.qty}</td>
                                </tr>
                            </c:forEach>
                        </table>
                </div>
                <button id="showContent">Показать/Скрыть</button>
                <div id=${orders.id} style="display:none;">Text Some Text Some</div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>
