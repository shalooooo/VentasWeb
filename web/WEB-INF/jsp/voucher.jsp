<%-- 
    Document   : voucher
    Created on : May 24, 2019, 1:07:54 AM
    Author     : Gonzalo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Compra finalizada con exito!</h1>
        <h2>Nro boleta: ${venta.getId()}</h2>
        <h2>Total: ${total}</h2>
        <h2>Medio de Pago: ${(venta.getMedioPago()==1)?'Tarjeta':'Efectivo'}</h2>
        <hr>
        <h3>Resumen de productos</h3>


        <table>
            <tr>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Subtotal</th>
            </tr>
            <c:forEach items="${productos}" var="p">
                <tr>
                    <td>${p.getNombre()}</td>
                    <td>${p.getPrecio()}</td>
                    <td>${p.getCantidad()}</td>
                    <td>${p.getSubTotal()}</td>
                </tr>
            </c:forEach>            
        </table>

    </body>
</html>
