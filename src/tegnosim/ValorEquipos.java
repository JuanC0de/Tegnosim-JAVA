/**
 * Autor: Juan Andrés Bolaño Ospina.
 * Date: 7/06/2021
 * Version: 1.0
    Descripción: Clase encargada de asignar el precio de cada equipo
 */

package tegnosim;


public class ValorEquipos {
    //Las variables deben ser "final" porque el precio no puede ser editado, viene por defecto.
    private final double valorA10s=450000;
    private final double valorA21s=850000;
    private final double valorA51=1200000;
    private final double valorA71=1400000;
    private final double valorNote10=950000;
    
    public double getValorA10s() {
        return valorA10s;
    }

    public double getValorA21s() {
        return valorA21s;
    }

    public double getValorA51() {
        return valorA51;
    }

    public double getValorA71() {
        return valorA71;
    }

    public double getValorNote10() {
        return valorNote10;
    }
    
    /**
     * Se especifica el valor de equipo y al ser "final" no se permite editar el valor.
     * Se devuelve el valor de cada equipo.
     * @return 
     */
   
    
}
