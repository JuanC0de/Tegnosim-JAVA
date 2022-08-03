/**
 * Autor: Juan Andrés Bolaño Ospina.
 * Date: 14/06/2021
 * Version: 3.0
    Descripción: Se encarga de almacenar los objetos de las ventas que se realicen.
 */
package tegnosim;

import java.util.ArrayList;


public class AgregarVentas {
     private static ArrayList <DatosVentas> arrayVenta= new ArrayList<DatosVentas>();
     DatosVentas venta= new DatosVentas();
     private static int reciboVenta=0;
     
    public AgregarVentas(){
        
    } 
    /**
     * Se le agregan los datos de los parámetros al objeto y posteriormente se agrega al ArrayList.
     * @param nombreCliente
     * @param cedula
     * @param fechaCompra
     * @param equipo
     * @param cantidad
     * @param bodegaVenta 
     */
    public void agregarDatosventa(String nombreCliente,int cedula,String fechaCompra,String equipo,int cantidad,String bodegaVenta){
        reciboVenta++;
        venta.asignarDatos(nombreCliente, cedula, fechaCompra, equipo, cantidad, bodegaVenta,reciboVenta);
        arrayVenta.add(venta);
    }
    /**
     * Se encarga de recorrer el array y por cada objeto sumar la cantidad de equipos totales.Se retorna la sumatoria de los totales.
     * @return 
     */
    public int calcularCantidades(){
        int total=0;
        for (int i = 0; i < arrayVenta.size(); i++) {
            int cantidadIndividual=arrayVenta.get(i).getCantidad();
            total+=cantidadIndividual;
        }
        return total;
    }

    public static ArrayList<DatosVentas> devolverVentas(){
        return arrayVenta;
    }
    public static int devolverRecibo(){
        return reciboVenta;
    }
}
