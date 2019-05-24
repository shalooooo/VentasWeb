/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import carrocompra.CarroCompra;
import DAO.CategoriaDAO;
import DAO.ProductoDAO;
import DAO.ProductoVentaDAO;
import DAO.VentaDAO;
import carrocompra.ProductoCarro;
import entidades.Producto;
import entidades.Venta;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Gonzalo
 */
@Controller
public class VentaController {
    
    private ProductoVentaDAO pvDAO = new ProductoVentaDAO();
    private VentaDAO vDAO = new VentaDAO();
    private ProductoDAO pDAO = new ProductoDAO();
    private CategoriaDAO cDAO = new CategoriaDAO();
    
    private CarroCompra getCarro(HttpServletRequest request){
        CarroCompra carro = (CarroCompra) request.getSession().getAttribute("carroCompra");
        if (carro==null) {
            carro = new CarroCompra();
            request.getSession().setAttribute("carroCompra",carro);            
        }
        return carro;            
    }
    
    
    @RequestMapping(value = "/venta", method = RequestMethod.GET)
    public String venta(Model model){
        model.addAttribute("productos", pDAO.listar());
        return "venta";
    }
    
    @RequestMapping(value = "/agregar-producto",method = RequestMethod.POST)
    public String agregarProducto(Model model, RedirectAttributes ra,
            @RequestParam("txtCantidad") int cantidad,
            @RequestParam("cboProducto") int productoId,
            HttpServletRequest request){
        
        Producto producto = pDAO.buscarPorId(productoId);
            
        if (producto == null) {
            ra.addFlashAttribute("mensaje", "El producto no existe");
            return "redirect:venta";
        }
        
        ProductoCarro pCarro = new ProductoCarro();
        pCarro.setId(productoId);
        pCarro.setNombre(producto.getNombre());
        pCarro.setPrecio(producto.getPrecio());
        pCarro.setCantidad(cantidad);
        
        CarroCompra carro = this.getCarro(request);
        carro.agregarProducto(pCarro);
        
        return "redirect:venta";
    }
    
    @RequestMapping(value = "/eliminar-producto", method = RequestMethod.GET)
    public String eliminarProducto(Model model, RedirectAttributes rs, 
            HttpServletRequest request,
            @RequestParam("id") int id){
        CarroCompra carro = getCarro(request);
        carro.eliminarProducto(id);
        return "redirect:venta";
    }
    
    
    @RequestMapping(value = "finalizar-venta", method = RequestMethod.POST)
    public String finalizarVenta(Model model, RedirectAttributes ra,
            HttpServletRequest request,
            @RequestParam("medio_pago") int medioPago) {
        CarroCompra carro = getCarro(request);
        if (carro.getProductos().size() == 0) {
            ra.addFlashAttribute("mensaje", "No hay productos agregados en el carro.");
            return "redirect:venta";
        }
        Venta venta = vDAO.procesarVenta(carro.getProductos(), medioPago);
        if (venta==null) {
            ra.addFlashAttribute("mensaje","Ha ocurrido un error con la venta.");
            return "redirect:venta";
        }
        model.addAttribute("productos", carro.getProductos());
        model.addAttribute("total",carro.getTotalFinal());
        model.addAttribute("venta", venta);
        request.getSession().setAttribute("carroCompra", null);
        return "voucher";
    }
    
    
}
