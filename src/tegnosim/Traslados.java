/**
 * Autor: Juan Andrés Bolaño Ospina.
 * Date: 10/06/2021
 * Version: 2.0
    Descripción: Esta clase se encarga de coordinar los traslados entre bodegas y  llevar un array con cada traslado que se realiza.
 */
package tegnosim;

import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Traslados {
    private static Integer reciboTraslado=2021000;
    private ArrayList <Integer> baseRecibos= new ArrayList<Integer>();
    DatosInventario datosBase= new DatosInventario();
    private boolean origenEncontrado=false;
    private boolean again=false;
    private boolean destinoEncontrado=false;
    
    public Traslados(){
        
    }
    /**
     * Este método se encarga de buscar si la bodega de origen y destino existen.
     * Verifica que la cantidad de equipos de cada referencia en inventario no es menor a la cantidad trasladada.
     * Posteriormente si los equipos son suficientes, los equipos se le restan a la bodega origen y se le suman a la bodega destino.
     * @param origen
     * @param destino
     * @param A10s
     * @param A21s
     * @param A51
     * @param A71
     * @param Note 
     */
    public void datosModificar(String origen, String destino,int A10s,int A21s, int A51,int A71,int Note){
        ArrayList <Tegnosim> bodegasInventario=datosBase.getDatosInventario();
        
        for (int i = 0; i < bodegasInventario.size(); i++) {
            if (bodegasInventario.get(i).getBodega() == origen) {
                //Se encontro la bodega
                this.setEncontrado(true);
                //Declaración de variables.
                int cantA10s=bodegasInventario.get(i).getCantA10s();
                int cantA21s=bodegasInventario.get(i).getCantA21s();
                int cantA51=bodegasInventario.get(i).getCantA51();
                int cantA71=bodegasInventario.get(i).getCantA71();
                int cantNote=bodegasInventario.get(i).getCantNote10();
                
                //Condicionales de cantidades.
                if(A10s>cantA10s){
                    JOptionPane.showMessageDialog(null, "La cantidad a enviar de A10s\n Es mayor a la que se tiene en inventario.\n Vuelva a Ingresar las cantidades.", "Cantidad a enviar Insuficiente", JOptionPane.ERROR_MESSAGE);    
                    again=true;
                    break;
                }
                else if (A21s>cantA21s) {
                    JOptionPane.showMessageDialog(null, "La cantidad a enviar de A21s\n Es mayor a la que se tiene en inventario.\n Vuelva a Ingresar las cantidades.", "Cantidad a enviar Insuficiente", JOptionPane.ERROR_MESSAGE);    
                    again=true;
                    break;
                }
                else if (A51>cantA51) {
                    JOptionPane.showMessageDialog(null, "La cantidad a enviar de A51\n Es mayor a la que se tiene en inventario.\n Vuelva a Ingresar las cantidades.", "Cantidad a enviar Insuficiente", JOptionPane.ERROR_MESSAGE);    
                    again=true;
                    break;
                }
                else if (A71>cantA71){
                    JOptionPane.showMessageDialog(null, "La cantidad a enviar de A71\n Es mayor a la que se tiene en inventario.\n Vuelva a Ingresar las cantidades.", "Cantidad a enviar Insuficiente", JOptionPane.ERROR_MESSAGE);    
                    again=true;
                    break;
                }
                else if (Note>cantNote) {
                    JOptionPane.showMessageDialog(null, "La cantidad a enviar de Note 10\n Es mayor a la que se tiene en inventario.\n Vuelva a Ingresar las cantidades.", "Cantidad a enviar Insuficiente", JOptionPane.ERROR_MESSAGE);    
                    again=true;
                    break;
                }
                //A10s
                cantA10s-=A10s;
                bodegasInventario.get(i).setCantA10s(cantA10s);
                //A21s
                cantA21s-=A21s;
                bodegasInventario.get(i).setCantA21s(cantA21s);
                //A51
                cantA51-=A51;
                bodegasInventario.get(i).setCantA51(cantA51);
                //A71
                cantA71-=A71;
                bodegasInventario.get(i).setCantA71(cantA71);
                //Note 10
                cantNote-=Note;
                bodegasInventario.get(i).setCantNote10(cantNote);
            }
            //Se verifica si la bodega de destino existe.
            else if(bodegasInventario.get(i).getBodega() == destino && again == false){
                this.setDestinoEncontrado(true);
                //A10s
                int cantA10s=bodegasInventario.get(i).getCantA10s();
                cantA10s+=A10s;
                bodegasInventario.get(i).setCantA10s(cantA10s);
                //A21
                int cantA21s=bodegasInventario.get(i).getCantA21s();
                cantA21s+=A21s;
                bodegasInventario.get(i).setCantA21s(cantA21s);
                //A51
                int cantA51=bodegasInventario.get(i).getCantA51();
                cantA51+=A51;
                bodegasInventario.get(i).setCantA51(cantA51);
                //A71
                int cantA71=bodegasInventario.get(i).getCantA71();
                cantA71+=A71;
                bodegasInventario.get(i).setCantA71(cantA71);
                //Note 10
                int cantNote=bodegasInventario.get(i).getCantNote10();
                cantNote+=Note;
                bodegasInventario.get(i).setCantNote10(cantNote);
            }
        }
        //Advertencias entregadas al usuario en caso de que no se encuentre alguna bodega
        if(again == true){}
        else if (origenEncontrado == false) {
           JOptionPane.showMessageDialog(null, "No se ha encontrado la Bodega de origen.\n Por favor cambie la Bodega que va a realizar el traslado.", "Bodega No encontrada.", JOptionPane.ERROR_MESSAGE);
        }
        else if(destinoEncontrado == false){
            JOptionPane.showMessageDialog(null, "No se ha encontrado la Bodega de destino.\n Por favor cambie la Bodega que va a realizar el traslado.", "Bodega No encontrada.", JOptionPane.ERROR_MESSAGE);
        }
        else{
        Integer agregar=sumarConteo();
        this.agregarRecibo(agregar);
        }
    }
    private void agregarRecibo(Integer num){
       baseRecibos.add(num);
    }
    
    public static Integer sumarConteo(){
        reciboTraslado++;
        return reciboTraslado;
    }
    public boolean isEncontrado() {
        return origenEncontrado;
    }

    public void setEncontrado(boolean encontrado) {
        this.origenEncontrado = encontrado;
    }
    
    public boolean isDestinoEncontrado() {
        return destinoEncontrado;
    }

    public void setDestinoEncontrado(boolean destinoEncontrado) {
        this.destinoEncontrado = destinoEncontrado;
    }
}
