/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrocompra;

import java.util.ArrayList;

/**
 *
 * @author Gonzalo
 */
public class CarroCompra {
    
    private ArrayList<ProductoCarro> productos = new ArrayList<>();

    public ArrayList<ProductoCarro> getProductos() {
        return productos;
    }
    
    public boolean agregarProducto(ProductoCarro producto){
        this.productos.add(producto);
        return true;
    }
    
    public boolean eliminarProducto(int id){
        for (ProductoCarro producto : productos) {
            if (producto.getId() == id) {
                this.productos.remove(producto);
                return true;
            }
        }
        return false;
    }
    
    public int getTotalFinal(){
        int monto = 0;
        for (ProductoCarro producto : productos) {
            monto+=producto.getSubTotal();
        }
        return monto;
    }
}
