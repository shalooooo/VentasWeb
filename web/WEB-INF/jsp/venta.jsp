<%-- 
    Document   : venta
    Created on : May 22, 2019, 11:52:14 PM
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
        <h1>Crear nueva venta</h1>
        <form action="agregar-producto" method="POST">

            <select name="cboProducto" id="cboProducto">
                <option value="">Seleccionar</option>
                <c:forEach items="${productos}" var="p">
                    <option value="${p.getId()}">${p.getNombre()}</option>
                </c:forEach>
            </select>

            <input type="number" name="txtCantidad" id="txtCantidad" placeholder="Cantidad">
            <input type="submit" value="Agregar">
        </form>
        <h4>${mensaje}</h4>

        <hr>

        <table>
            <tr>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Subtotal</th>
                <th>Opciones</th>            
            </tr>

            <c:forEach items="${sessionScope.carroCompra.getProductos()}" var="p">
                <tr>
                    <td>${p.getNombre()}</td>
                    <td>${p.getPrecio()}</td>
                    <td>${p.getCantidad()}</td>
                    <td>${p.getSubTotal()}</td>
                    <td>
                        <a href="eliminar-producto?id=${p.getId()}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>            
        </table>

        <h3>Total: ${sessionScope.carroCompra.getTotalFinal()}</h3>
        <form action="finalizar-venta" method="POST">
            <label for="">Medio de Pago</label>
            <label for="">Efectivo</label>
            <input type="radio" name="medio_pago" id="rbtEfectivo" value="0" checked="">
            <label for="">Tarjeta</label>
            <input type="radio" name="medio_pago" id="rbtTarjeta" value="1">
            <br><br>
            <input type="submit" value="Finalizar Venta">
        </form>
    </body>
</html>
