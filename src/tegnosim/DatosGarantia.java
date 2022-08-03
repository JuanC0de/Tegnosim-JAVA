/**
 * Autor: Juan Andrés Bolaño Ospina.
 * Date: 14/06/2021
 * Version: 1.0
    Descripción: Esta clase se encarga de darle forma a los objetos de garantía.
 */
package tegnosim;

public class DatosGarantia {
    private String nombreCliente="";
    private int cedula=0;
    private String equipoGarantia="";
    private String descripcion="";
    private String vendedorEncargado="";
    private int idCompra=0;
    private String telefono="";
    
    /**
     * Asigna los String a sus respectivas variables.
     * @param nombreCliente
     * @param equipoGarantia
     * @param descripcion
     * @param vendedorEncargado
     * @param telefono 
     */
    public void asignarDatos(String nombreCliente,String equipoGarantia,String descripcion,String vendedorEncargado,String telefono){
        this.nombreCliente=nombreCliente;
        this.equipoGarantia=equipoGarantia;
        this.descripcion=descripcion;
        this.vendedorEncargado=vendedorEncargado;
        this.telefono=telefono;
    }
    /**
     * Asigna los int a sus respectivas variables.
     * @param cedula
     * @param idCompra 
     */
    public void asignarCantidades(int cedula,int idCompra){
        this.cedula=cedula;
         this.idCompra=idCompra;
    }
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getEquipoGarantia() {
        return equipoGarantia;
    }

    public void setEquipoGarantia(String equipoGarantia) {
        this.equipoGarantia = equipoGarantia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVendedorEncargado() {
        return vendedorEncargado;
    }

    public void setVendedorEncargado(String vendedorEncargado) {
        this.vendedorEncargado = vendedorEncargado;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }
    
}
