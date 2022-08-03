/**
 * Autor: Juan Andrés Bolaño Ospina.
 * Date: 14/06/2021
 * Version: 1.0
Descripción: esta clase es la encargada de guardar los objetos que contienen los datos de las garantías.
 */
package tegnosim;

import java.util.ArrayList;


public class AgregarGarantía {
    private static ArrayList <DatosGarantia> infoGarantia= new ArrayList();
    DatosGarantia datos= new DatosGarantia();
    /**
     * Se reciben los siguientes parámetros:
     * @param nombreCliente
     * @param cedula
     * @param equipoGarantia
     * @param descripcion
     * @param vendedorEncargado
     * @param idCompra
     * @param telefono 
     * Este método se encarga de asignar esta información a un objeto "datos" de la clase DatosGarantia
     * que tiene todas las variables necesarias.
     */
    public void recibirInfo(String nombreCliente,int cedula,String equipoGarantia,String descripcion,String vendedorEncargado,int idCompra,String telefono){
        datos.asignarDatos(nombreCliente,equipoGarantia, descripcion, vendedorEncargado, telefono);
        datos.asignarCantidades(cedula,idCompra);
        this.agregarObjeto(datos);
    }
    /**
     * Se recibe un objeto como parametro y se agrega al ArrayList infoGarantía, que es la encargada de almacenar
     * los objetos con información de la garantía.
     * @param obj 
     */
    public static void agregarObjeto(DatosGarantia obj){
        infoGarantia.add(obj);
    }
    /**
     * Se retorna el ArrayList con los objetos de garantia
     * @return 
     */
    public static ArrayList<DatosGarantia> devolverGarantia(){
        return infoGarantia;
    }
}
