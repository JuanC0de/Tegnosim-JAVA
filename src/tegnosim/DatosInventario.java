/**
 * Autor: Juan Andrés Bolaño Ospina.
 * Date: 9/06/2021
 * Version: 2.0
   Descripción: Se encarga de almacenar los objetos con bodegas de Inventario
 */
package tegnosim;

import java.util.ArrayList;

public class DatosInventario {

    private static ArrayList <Tegnosim> datosInventario = new ArrayList();
    Tegnosim d= new Tegnosim();
    
    /**
     * Se le asigna las variables al objeto d y posteriormente se agrega el objeto al ArrayList "datosInventario".
     * @param Zona
     * @param Bodega
     * @param Encargado
     * @param cantA10s
     * @param cantiA21s
     * @param cantA51
     * @param cantiA71
     * @param cantNote10
     * @param totalEquipos 
     */
    public void agregarInventario(String Zona,String Bodega, String Encargado, int cantA10s,int cantiA21s, int cantA51, int cantiA71, int cantNote10,int totalEquipos) {
        
        d.recibirNombres(Zona,Bodega,Encargado,totalEquipos);
        d.recibirCanti(cantA10s,cantiA21s,cantA51,cantiA71,cantNote10);
        d.calcularDinero();
        d.setRepetir(true);
        this.agregarObjeto(d);
    }
    public DatosInventario(){
        
    }
    /**
     * El método se encarga de recorrer el ArrayList y verificar si la bodega recibida como parametro ya existe.
     * @param bodegaC
     * @return 
     * Se retorna false si ya existe, true si no existe.
     */
    public boolean encontrarRepetidos(String bodegaC){
        for (int i = 0; i < datosInventario.size(); i++) {
            if(datosInventario.get(i).getBodega()== bodegaC){
                return false;
            }
        }
        return true;
    }
    /**
     * Recorre todo el array y suma la cantidad total de equipos, al final se envia la sumatoria del total de todos los equipos
     * @return 
     */
    public int totalEquipos(){
        int totalEquipos=0;
        for (int i = 0; i < datosInventario.size(); i++) {
            totalEquipos+=datosInventario.get(i).getTotalEquipos();
        }
        return totalEquipos;
    }
    /**
     * Recorre el array y calcula el valor total del dinero en equipos de todas las bodegas.
     * @return 
     */
    public double cantidadDinero(){
        double cantidad=0.0;
        for (int i = 0; i < datosInventario.size(); i++) {
            cantidad+=datosInventario.get(i).getValorTotal();
        }
        return cantidad;
    }
    public static void agregarObjeto(Tegnosim nuevoObjeto){
        datosInventario.add(nuevoObjeto);
    }
    public static ArrayList<Tegnosim> getDatosInventario() {
        return datosInventario;
    }
}
