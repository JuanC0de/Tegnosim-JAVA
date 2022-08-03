/**
 * Autor: Juan Andrés Bolaño Ospina.
 * Date: 7/06/2021
 * Version: 1.0
    Descripción: Dar forma a los objetos de tipo Bodega.
 */

package tegnosim;

public class Tegnosim {
    private String zona="";
    private String bodega="";
    private String encargado="";
    private int totalEquipos=0;
    private double valorTotal=0.0;
    private int cantA10s=0;
    private int cantA21s=0;
    private int cantA51=0;
    private int cantA71=0;
    private int cantNote10=0;
    private boolean repetir=false;
    
    public Tegnosim() {
    
    }
    /**
     * Asigna los valores a las variables.
     * @param zona
     * @param bodega
     * @param encargado
     * @param totalEquipos 
     */
    public void recibirNombres(String zona,String bodega, String encargado,int totalEquipos) {
    this.zona=zona;
    this.bodega=bodega;
    this.encargado=encargado;
    this.totalEquipos=totalEquipos;
    }
    /**
     * Método que recibe todas las cantidades de equipos, para hacer el trabajo de asignar las cantidades más rápido.
     * @param cantA10s
     * @param cantiA21s
     * @param cantA51
     * @param cantiA71
     * @param cantNote10 
     */
    public void recibirCanti(int cantA10s,int cantiA21s, int cantA51, int cantiA71, int cantNote10){
    this.cantA10s=cantA10s;
    this.cantA21s=cantiA21s;
    this.cantA51=cantA51;
    this.cantA71=cantiA71;
    this.cantNote10= cantNote10;
    }
    /**
     * Calcula cuanto dinero en total se tiene en bodega.
     * @return 
     */
    public double calcularDinero(){
    ValorEquipos ve= new ValorEquipos();
    //recoger precio de los equipos.
    double valorA10=ve.getValorA10s();
    double valorA21=ve.getValorA21s();
    double valorA51=ve.getValorA51();
    double valorA71=ve.getValorA71();
    double valorNote10=ve.getValorNote10();
    //multiplicar la cantidad por el precio para encontrar cuanto vale en total cada referencia.
    double precioA10= valorA10 * (double)this.getCantA10s();
    double precioA21= valorA21 * (double)this.getCantA21s();
    double precioA51= valorA51 * (double)this.getCantA51();
    double precioA71= valorA71 * (double)this.getCantA71();
    double precioNote= valorNote10 * (double)this.getCantNote10();
    //se suma todos los totales para encontrar cuanto dinero hay en bodega.
    double total=precioA10+precioA21+precioA51+precioA71+precioNote;
    setValorTotal(total);
    return total;
    }
    /**
     * Calcula la cantidad de equipos en bodega, retorna el total de equipos.
     * @return 
     */
    public int calcularCantidad(){
        int totalEquipos=this.getCantA10s()+this.getCantA21s()+this.getCantA51()+this.getCantA71()+this.getCantNote10();
        this.setTotalEquipos(totalEquipos);
        return totalEquipos;
    }
    
    public boolean isRepetir() {
        return repetir;
    }

    public void setRepetir(boolean repetir) {
        this.repetir = repetir;
    }
    public int getCantA10s() {
        return cantA10s;
    }

    public void setCantA10s(int cantA10s) {
        this.cantA10s = cantA10s;
    }

    public int getCantA21s() {
        return cantA21s;
    }

    public void setCantA21s(int cantA21s) {
        this.cantA21s = cantA21s;
    }

    public int getCantA51() {
        return cantA51;
    }

    public void setCantA51(int cantA51) {
        this.cantA51 = cantA51;
    }

    public int getCantA71() {
        return cantA71;
    }

    public void setCantA71(int cantA71) {
        this.cantA71 = cantA71;
    }

    public int getCantNote10() {
        return cantNote10;
    }

    public void setCantNote10(int cantNote10) {
        this.cantNote10 = cantNote10;
    }
    
    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public int getTotalEquipos() {
        return totalEquipos;
    }

    public void setTotalEquipos(int totalEquipos) {
        this.totalEquipos = totalEquipos;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotale) {
        this.valorTotal = valorTotale;
    }
    
    
    
}
