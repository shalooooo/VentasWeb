/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import carrocompra.ProductoCarro;
import entidades.Producto;
import entidades.ProductoVenta;
import entidades.Venta;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Gonzalo
 */
public class VentaDAO extends Modelo<Venta>{

    ProductoVentaDAO pv = new ProductoVentaDAO();
     
    @Override
    public String getNombreModelo() {
        return "Venta";
    }
    
    public Venta procesarVenta(ArrayList<ProductoCarro> productos, int medioPago){
        Venta venta = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = sesion.beginTransaction();
            Venta ventaTmp = new Venta();
            ventaTmp.setFechaVenta(new Date());
            ventaTmp.setMontoTotal(0);
            ventaTmp.setMedioPago(medioPago);
            this.agregar(ventaTmp);
            int monto = 0;
            for (ProductoCarro producto : productos) {
                ProductoVenta productoVenta = new ProductoVenta();
                productoVenta.setCantidad(producto.getCantidad());
                productoVenta.setVenta(ventaTmp);
                productoVenta.setPrecio(producto.getPrecio());
                productoVenta.setProducto(new Producto(producto.getId()));
                monto+=producto.getSubTotal();
                pv.agregar(productoVenta);
            }
            ventaTmp.setMontoTotal(monto);
            this.modificar(ventaTmp);
            tx.commit();
            venta = ventaTmp;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sesion.close();
        }
        return venta;
    }
    
}
