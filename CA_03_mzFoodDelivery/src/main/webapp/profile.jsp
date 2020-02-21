<%@ page import="MzFoodDelivery.User.User" %>
<%@ page import="MzFoodDelivery.MzFoodDelivery" %>
<%@ page import="MzFoodDelivery.User.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="MzFoodDelivery.User.CartItem" %><%--
  Created by IntelliJ IDEA.
  User: zhivar
  Date: 2/20/20
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>User</title>
    <style>
        li {
            padding: 5px
        }
    </style>
</head>

<body>

<%
    User user = MzFoodDelivery.getInstance().getUser();
%>

<ul>
    <li>id: 1</li>
    <li>full name: <%=user.getFullName()%></li>
    <li>phone number: <%=user.getPhoneNumber()%></li>
    <li>email: <%=user.getEmail()%>></li>
    <li>credit: <%=user.getCredit()%> Toman</li>
    <form action="/CA_03_mzFoodDelivery/chargeCredit" method="POST">
        <button type="submit">increase</button>
        <input type="number" name="credit" value="" />
    </form>
    <%
        Cart cartList = user.getUserCart();
    %>
    <%
        if (cartList.getSize() != 0) {
    %>
    <li>
        Orders :
        <div>
            <span>
                 restaurant name: <%=cartList.getRestaurant().getName()%>
            </span>
        </div>
        <ul>
            <%
                for (CartItem cartItem: cartList.getCartItems()) {
            %>
            <li>
                <%=cartItem.getFood().getName()%>
                ,
                <%=cartItem.getQuantity()%>
            </li>
            <%}%>
        </ul>
    </li>
    <%}%>
</ul>
</body>

</html>
