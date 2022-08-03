/**
 * Autor: Juan Andrés Bolaño Ospina.
 * Date: 14/06/2021
 * Version: 1.0
    Descripción: Se encarga de darle forma a los objetos de las ventas.
 */
package tegnosim;

public class DatosVentas {

    public void asignarDatos(String nombreCliente,int cedula,String fechaCompra,String equipo,int cantidad,String bodegaVenta,int id) {
    this.nombreCliente=nombreCliente;
    this.cedula=cedula;
    this.equipo=equipo;
    this.fechaCompra=fechaCompra;
    this.cantidad=cantidad;
    this.bodegaVenta=bodegaVenta;
    this.id=id;
    }
    public DatosVentas(){
        
    }

    private String nombreCliente="";
    private int cedula=0;
    private String fechaCompra="";
    private String equipo="";
    private int cantidad=0;
    private String bodegaVenta="";
    private int id=0;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getBodegaVenta() {
        return bodegaVenta;
    }

    public void setBodegaVenta(String bodegaVenta) {
        this.bodegaVenta = bodegaVenta;
    }
    
    
    
}
