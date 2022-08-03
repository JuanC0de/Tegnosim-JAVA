/**
 * Autor: Juan Andrés Bolaño Ospina.
 * Date: 9/06/2021
 * Version: 4.0
    Descripción: Interfaz encargada de coordinar las ventas, los inventarios, las garantías y los cáculos en estos.
 */

package tegnosim;

import java.awt.Graphics;
import java.awt.Image;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


public class Inventarios extends javax.swing.JFrame {
    //Modelos de las tablas
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel segundoModelo = new DefaultTableModel();
    DefaultTableModel modeloGarantia = new DefaultTableModel();
    //Objeto de la clase DatosInventario para tener un ArrayList con las bodegas
    DatosInventario agregar= new DatosInventario();
    ArrayList <Tegnosim> datosInventario= agregar.getDatosInventario();
    int total=0;
    //Objeto de la clase AgregarVentas para tener un ArrayList de las ventas
    AgregarVentas nuevaVenta= new AgregarVentas();
    ArrayList <DatosVentas> ventasExistentes= nuevaVenta.devolverVentas();
    //Objeto de la clase AgregarGarantía para tener un ArrayList con las garantías.
    AgregarGarantía garantia= new AgregarGarantía();
    ArrayList <DatosGarantia> garantiasActuales= garantia.devolverGarantia();
    /**
     * Constructor encargado de insertar los títulos en las tablas y de no permitir la edición de ciertos componentes.
     */
    public Inventarios() {
        initComponents();
        this.setLocationRelativeTo(null); 
        this.titulos();
        this.titulosVentas();
        this.titulosGarantias();
        this.txtDineroglobal.setEnabled(false);
        this.txtTotalEquipos.setEnabled(false);;
        this.recibosTraslados.setEnabled(false);
        this.mostrarVentas.setEnabled(false);
        this.mostrarInfoGarantia.setEnabled(false);
    }
    // Métodos encargados de insertar los títulos en las tablas.
    
    /**
     * Establece los Títulos que tendrá la tabla de inventario.
     */
    public void titulos(){
        ArrayList <Object> titulosColumnas= new ArrayList <Object>();
        titulosColumnas.add("Fecha/Hora");
        titulosColumnas.add("Zona");
        titulosColumnas.add("Bodega");
        titulosColumnas.add("Encargado");
        titulosColumnas.add("A10S");
        titulosColumnas.add("A21s");
        titulosColumnas.add("A51");
        titulosColumnas.add("A71");
        titulosColumnas.add("Note 10");
        titulosColumnas.add("Total");
        for(Object columnas: titulosColumnas){
            modelo.addColumn(columnas);
        }
        this.tablaInventarios.setModel(modelo);
    }
    /**
     * Establece los títulos de la tabla de ventas.
     */
    public void titulosVentas(){
        ArrayList <Object> titulosVentas= new ArrayList <Object>();
        titulosVentas.add("Fecha");
        titulosVentas.add("Cliente");
        titulosVentas.add("Cédula");
        titulosVentas.add("Equipo");
        titulosVentas.add("Cantidad");
        titulosVentas.add("Bodega");
        titulosVentas.add("ID");
        titulosVentas.add("Vendedor");
        for (Object recorrerTítulos : titulosVentas) {
            segundoModelo.addColumn(recorrerTítulos);
        }
        this.tablaVentas.setModel(segundoModelo);
    }
    /**
     * Establece los títulos de la tabla de garantías
     */
    public void titulosGarantias(){
        ArrayList <Object> titulosGarantias= new ArrayList <Object>();
        titulosGarantias.add("Cliente");
        titulosGarantias.add("Cedula");
        titulosGarantias.add("Equipo");
        titulosGarantias.add("Encargado");
        titulosGarantias.add("Hora");
        for (Object recorrerTitulos : titulosGarantias) {
            modeloGarantia.addColumn(recorrerTitulos);
        }
        this.tablaGarantias.setModel(modeloGarantia);
    }
    //Fin métodos que establecen los títulos.
    
    /**
     * Verifica si los campos de Texto están todos llenos.
     * @return 
     */
    public boolean verificarCampos(){
        if(txtA10s.getText().equals("") || txtA21s.getText().equals("") || txtA51.getText().equals("") || txtA71.getText().equals("") || txtNote.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Por favor verifique que todas las cantidades están llenas.\n¡Recuerde que sino tiene ninguno debe colocar 0!","No ha puesto todos los datos",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
            return true;
        }
    }
    /**
     * Verifica si los campos en la sección de traslados están llenos.
     * @return 
     */
    public boolean verificarCamposTraslado(){
        if(txtTrasladoA10s.getText().equals("") || txtTrasladoA21s.getText().equals("") || txtTrasladoA51.getText().equals("") || txtTrasladoA71.getText().equals("") || txtTrasladoNote.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Por favor verifique que todas las cantidades están llenas.\n¡Recuerde que sino tiene ninguno debe colocar 0!","No ha puesto todos los datos",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
            return true;
        }
    }
    /**
     * Verifica si los campos en la sección de ventas están llenos.
     * @return 
     */
    public boolean verificarCamposVentas(){
        if(txtCantidadVender.getText().equals("") || txtCedula.getText().equals("") || txtNombreCliente.getText().equals("") || cbBodegaVenta.getSelectedItem().equals("") || cbEquipo.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null,"Por favor verifique que todos los espacios tengan la información solicitada.\n¡Recuerde que no debe dejar información incompleta!","Espacios vacíos.",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
            return true;
        }
    }
    //Corregir para verificar tipo de datos.
    public boolean verificarTipoCampos(){
        return true;
    }
    /**
     * Recorre el ArrayList de inventarios y verifica si eixiste la bodega solicita por parámetros.(true encontrada, false no encontrada)
     * @param bodegaEncontrar
     * @return 
     */
    public boolean verficarExistencia(String bodegaEncontrar){
        for(int i=0;i<datosInventario.size();i++){
            if(datosInventario.get(i).getBodega().equals(bodegaEncontrar)){
                return true;
            }
        }
        return false;
    }
    /**
     * Verifica si los campos de Garantía están llenos.
     * @return 
     */
    public boolean verificarCamposGarantia(){
        if(txtNombreClienteGarantia.getText().equals("") || txtCedulaGarantia.getText().equals("") || txtDescripcionGarantia.getText().equals("") || equiposGarantia.getSelectedIndex()==0 || txtEncargadoGarantia.getText().equals("") || txtIdGarantia.getText().equals("") || telefonoCliente.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Por favor verifique que todos los espacios tengan la información solicitada.\n¡Recuerde que no debe dejar información incompleta!","Espacios vacíos.",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
        return true;        
        }        
    }
    //Fin metodos de verificación.
    
    //Inicio métodos de recolección de Datos.
    
    /**
     * Recolecta la información ingresada por el usuario y crea un objeto de la Clase DatosInventario, luego se envía las variables para agregar este nuevo objeto al ArrayList en DatosInventario.
     */
    public void recolectarDatos(){
        try {
        String zona=(String)cbZonas.getSelectedItem();  
        String bodega=(String)cbBodegas.getSelectedItem(); 
        String encargado=(String)cbEncargados.getSelectedItem();
        int datosA10s= Integer.parseInt(txtA10s.getText());
        int datosA21s= Integer.parseInt(txtA21s.getText());
        int datosA51= Integer.parseInt(txtA51.getText());
        int datosA71= Integer.parseInt(txtA71.getText());
        int datosNote= Integer.parseInt(txtNote.getText());
        int totalBodega=0;
        totalBodega+=datosA10s+datosA21s+datosA51+datosA71+datosNote;
        
        DatosInventario datos= new DatosInventario();
        datos.agregarInventario(zona,bodega,encargado,datosA10s,datosA21s,datosA51,datosA71,datosNote,totalBodega);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"¡Se ha detectado un error!\n Para solucionar el error intente:\n Verifique que en los campos de cantidad no hay ni letras ni decimales.\n Si se vuelve a presentar el error por favor cierre el programa\n e intente ingresar de nuevo los datos.","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    /**
     * Recolecta los datos ingresados por el usuario.
     * Crea un objeto nuevo de la Clase Traslados.
     * Envía las variables para guardar el objeto en el ArrayList de Traslados.
     */
    public void recolectarDatosTraslado(){
        try{
        String bodegaOrigen=(String)cbBodegaOrigen.getSelectedItem();
        String bodegaDestino=(String)cbBodegaDestino.getSelectedItem();
        int trasladosA10s= Integer.parseInt(txtTrasladoA10s.getText());
        int trasladosA21s= Integer.parseInt(txtTrasladoA21s.getText());
        int trasladosA51= Integer.parseInt(txtTrasladoA51.getText());
        int trasladosA71= Integer.parseInt(txtTrasladoA71.getText());
        int trasladosNote= Integer.parseInt(txtTrasladoNote.getText());
        Traslados traslado= new Traslados();
        traslado.datosModificar(bodegaOrigen,bodegaDestino,trasladosA10s,trasladosA21s,trasladosA51,trasladosA71,trasladosNote);
        //agregar datos al recibo.
        if(traslado.isEncontrado() && traslado.isDestinoEncontrado()){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        String hora=date.format(LocalDateTime.now());
        //Mostrar los recibos.
        recibosTraslados.setText(recibosTraslados.getText()+"ID:"+traslado.sumarConteo()+"/"+""+bodegaOrigen+""+"le trasladó equipos a"+""+bodegaDestino+"\n"+"A10s: "+trasladosA10s+"\t"+"A21s: "+trasladosA21s+"\t"+"A51: "+trasladosA51+"\t"+"A71:"+trasladosA71+"\t"+"Note10: "+trasladosNote+"\n"+hora+"\n\n");
        //Mostrarle el recibo al usuario.
        JOptionPane.showMessageDialog(null, "ID:"+traslado.sumarConteo()+"\n"+"Bodega origen:"+bodegaOrigen+"\n"+"Bodega destino:"+bodegaDestino+"\n"+"Equipos trasladados:"+"\n"+"A10s:"+trasladosA10s+"\n"+"A21s"+trasladosA21s+"\n"+"A51:"+trasladosA51+"\n"+"A71:"+trasladosA71+"\n"+"Note10:"+trasladosNote,"Recibo", JOptionPane.INFORMATION_MESSAGE);
        this.agregarDatos();
        }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ocurrió un error al ingresar los datos. por favor vuelva a intentarlo","Error",JOptionPane.ERROR);
        }
    }
    /**
     * Recolecta los datos en la sección de garantía.
     * Se crea un objeto de la Clase AgregarGarantía.
     * Se envían las variables de este objeto para ser agregado al ArrayList de la clase AgregarGarantía.
     */
    public void RecolectarDatosGarantia(){
        try {
        String nombre=(String)txtNombreCliente.getText();
        int cedula=Integer.parseInt(txtCedulaGarantia.getText());
        String descripcion=(String)txtDescripcionGarantia.getText();
        String equipo=(String)equiposGarantia.getSelectedItem();
        String encargado=(String)txtEncargadoGarantia.getText();
        int idGarantia=Integer.parseInt(txtIdGarantia.getText());
        String numero=(String) telefonoCliente.getText();
        
        AgregarGarantía datoGarantia= new AgregarGarantía();
        datoGarantia.recibirInfo(nombre,cedula,equipo,descripcion,encargado,idGarantia,numero);
        this.agregarGarantias();
        } catch (Exception e) {
            System.out.println("ocurrio un error en la recolección de datos de garantía");
        }
    }
    //Fin métodos de recolección de Datos.
    
    /**
     * Metodos de limpieza de las Tablas.
     */
    public void limpiarTable(){
        for(int i= 0; i<modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i-=1;
        }
    }
    public void limpiarVentas(){
        for(int i=0;i<segundoModelo.getRowCount();i++){
            segundoModelo.removeRow(i);
            i-=1;
        }
    }
    public void limpiarGarantias(){
        for (int i = 0; i < modeloGarantia.getRowCount(); i++) {
            modeloGarantia.removeRow(i);
            i-=1;
        }
    }
    //Fin métodos de limpieza
    
    //Métodos para agregar Datos a las tablas
    
    /**
     * Recorre el ArrayList de inventarios y agrega a la tabla
     */
    public void agregarDatos(){
        this.limpiarTable();
        //variable date recibe hora y fecha
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        String hora=date.format(LocalDateTime.now());
        Object [] inventarios = new Object[10];
        for(int i=0; i< datosInventario.size(); i++){
            inventarios[0] = hora;
            inventarios[1] = datosInventario.get(i).getZona();
            inventarios[2] = datosInventario.get(i).getBodega();
            inventarios[3] = datosInventario.get(i).getEncargado();
            inventarios[4] = datosInventario.get(i).getCantA10s();
            inventarios[5] = datosInventario.get(i).getCantA21s();
            inventarios[6] = datosInventario.get(i).getCantA51();
            inventarios[7] = datosInventario.get(i).getCantA71();
            inventarios[8] = datosInventario.get(i).getCantNote10();
            inventarios[9] = datosInventario.get(i).calcularCantidad();
            modelo.addRow(inventarios);
        }
        tablaInventarios.setModel(modelo);
    }
    /**
     * Recorre el ArrayList de ventas y agrega a la tabla
     */
    public void agregarDatosVentas(){
        this.limpiarVentas();
        Object [] ventas = new Object[8];
        for (int i = 0; i < ventasExistentes.size(); i++) {
            ventas[0]=ventasExistentes.get(i).getFechaCompra();
            ventas[1]=ventasExistentes.get(i).getNombreCliente();
            ventas[2]=ventasExistentes.get(i).getCedula();
            ventas[3]=ventasExistentes.get(i).getEquipo();
            ventas[4]=ventasExistentes.get(i).getCantidad();
            ventas[5]=ventasExistentes.get(i).getBodegaVenta();
            ventas[6]=ventasExistentes.get(i).getId();
            ventas[7]=(String)txtNombreVendedor.getText();
            segundoModelo.addRow(ventas);
        }
        tablaVentas.setModel(segundoModelo);
    }
    /**
     * Recorre el ArrayList de garantías y agrega los datos a la tabla.
     */
    public void agregarGarantias(){
        this.limpiarGarantias();
        Object [] filasGarantias = new Object[5];
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        String hora=date.format(LocalDateTime.now());
        for (int i = 0; i < garantiasActuales.size(); i++) {
            filasGarantias[0]=garantiasActuales.get(i).getNombreCliente();
            filasGarantias[1]=garantiasActuales.get(i).getCedula();
            filasGarantias[2]=garantiasActuales.get(i).getEquipoGarantia();
            filasGarantias[3]=garantiasActuales.get(i).getVendedorEncargado();
            filasGarantias[4]=hora;
            modeloGarantia.addRow(filasGarantias);
        }
        tablaGarantias.setModel(modeloGarantia);
    }
    /**
     * Limpia todos los datos de inventario y borra tambien todos los objetos del ArrayList 
     */
    public void deleteDatos(){
        this.limpiarTable();
        datosInventario.clear();
    }
    //Fin de metodos de datos.
    
    /**
     * Método que mediante un indice que es la fila seleccionada por el usuario, muestra en la caja de texto
     * los datos de la garantía.
     * @param indice 
     */
    public void mostrarText(int indice){
        mostrarInfoGarantia.setText("");
        mostrarInfoGarantia.setText("Nombre:"+ garantiasActuales.get(indice).getNombreCliente()+"\n"+"Cédula: "+garantiasActuales.get(indice).getCedula()+"\n"+
                "Equipo: "+garantiasActuales.get(indice).getEquipoGarantia()+"\n"+"Asesor encargado: "+garantiasActuales.get(indice).getVendedorEncargado()+"\n"+
                "Teléfono: "+garantiasActuales.get(indice).getTelefono()+"\n"+"ID de venta: "+garantiasActuales.get(indice).getIdCompra()+"\n"+
                "Descripción del problema:"+"\n"+garantiasActuales.get(indice).getDescripcion());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new Fondo();
        indPages = new javax.swing.JTabbedPane();
        panelInventario = new javax.swing.JPanel();
        cbEncargados = new javax.swing.JComboBox<>();
        cbBodegas = new javax.swing.JComboBox<>();
        cbZonas = new javax.swing.JComboBox<>();
        txtNote = new javax.swing.JTextField();
        txtA71 = new javax.swing.JTextField();
        txtA51 = new javax.swing.JTextField();
        txtA21s = new javax.swing.JTextField();
        txtA10s = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pagTraslado = new javax.swing.JPanel();
        cbBodegaOrigen = new javax.swing.JComboBox<>();
        cbBodegaDestino = new javax.swing.JComboBox<>();
        txtTrasladoA10s = new javax.swing.JTextField();
        txtTrasladoA21s = new javax.swing.JTextField();
        txtTrasladoA51 = new javax.swing.JTextField();
        txtTrasladoA71 = new javax.swing.JTextField();
        txtTrasladoNote = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        recibosTraslados = new javax.swing.JTextArea();
        btnTrasladar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        ventanaVentas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        txtNombreCliente = new javax.swing.JTextField();
        txtCantidadVender = new javax.swing.JTextField();
        cbEquipo = new javax.swing.JComboBox<>();
        txtCedula = new javax.swing.JTextField();
        cbBodegaVenta = new javax.swing.JComboBox<>();
        btVenta = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        txtNombreVendedor = new javax.swing.JTextField();
        btnBorrarVentas = new javax.swing.JButton();
        mostrarVentas = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        limpiarVentas = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        txtNombreClienteGarantia = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        txtCedulaGarantia = new javax.swing.JTextField();
        equiposGarantia = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtDescripcionGarantia = new javax.swing.JTextArea();
        txtEncargadoGarantia = new javax.swing.JTextField();
        txtIdGarantia = new javax.swing.JTextField();
        finalizarGarantia = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        telefonoCliente = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaGarantias = new javax.swing.JTable();
        btnVerDetalle = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        mostrarInfoGarantia = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        panTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventarios = new javax.swing.JTable();
        deleteAll = new javax.swing.JButton();
        btnCalcular = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtDineroglobal = new javax.swing.JTextField();
        txtTotalEquipos = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        panelInventario.setBackground(new java.awt.Color(104, 149, 247));

        cbEncargados.setBackground(new java.awt.Color(204, 204, 204));
        cbEncargados.setForeground(new java.awt.Color(0, 0, 102));
        cbEncargados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Andrés Moreno", "Cristian Roncallo", "Elizabeth Mercado ", "Juan Hincapié", "Luis Medina", "Luis Saumeth", "Rubiela Gómez", "Samuel Bolaño", "Sara Cabrales", "Sonia Mogollón" }));

        cbBodegas.setBackground(new java.awt.Color(204, 204, 204));
        cbBodegas.setForeground(new java.awt.Color(0, 0, 102));
        cbBodegas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Plato", "Barrancas", "Cordoba ", "Fonseca Guajira", "Pueblo Bello", "Santa Marta", "Sincelejo 1", "Sincelejo 2", "Valledupar 1", "Valledupar 2" }));

        cbZonas.setBackground(new java.awt.Color(204, 204, 204));
        cbZonas.setForeground(new java.awt.Color(0, 0, 102));
        cbZonas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Cesar", "Córdoba ", "Guajira", "Magdalena", "Sucre" }));

        txtNote.setBackground(new java.awt.Color(204, 204, 204));

        txtA71.setBackground(new java.awt.Color(204, 204, 204));

        txtA51.setBackground(new java.awt.Color(204, 204, 204));

        txtA21s.setBackground(new java.awt.Color(204, 204, 204));

        txtA10s.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Samsung A10s");

        jLabel2.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Samsung A21s");

        jLabel3.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Samsung A51");

        jLabel4.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Samsung A71");

        jLabel5.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Xiaomi Note 10");

        jLabel6.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Bodega");

        jLabel7.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Encargado");

        jLabel8.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Zona");

        btnEditar.setBackground(new java.awt.Color(205, 206, 74));
        btnEditar.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(0, 153, 51));
        btnAgregar.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 153, 0));
        btnDelete.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Eliminar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setFont(new java.awt.Font("Lucida Bright", 0, 16)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(0, 0, 0));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInventarioLayout = new javax.swing.GroupLayout(panelInventario);
        panelInventario.setLayout(panelInventarioLayout);
        panelInventarioLayout.setHorizontalGroup(
            panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInventarioLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInventarioLayout.createSequentialGroup()
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbZonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelInventarioLayout.createSequentialGroup()
                        .addComponent(cbEncargados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7))
                    .addGroup(panelInventarioLayout.createSequentialGroup()
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInventarioLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelInventarioLayout.createSequentialGroup()
                                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNote, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtA71, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtA51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelInventarioLayout.createSequentialGroup()
                                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtA10s, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInventarioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInventarioLayout.createSequentialGroup()
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtA21s, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
        );
        panelInventarioLayout.setVerticalGroup(
            panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInventarioLayout.createSequentialGroup()
                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInventarioLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtA10s, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtA21s, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(panelInventarioLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbZonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInventarioLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbBodegas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(panelInventarioLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtA51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtA71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInventarioLayout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbEncargados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(75, 75, 75))
                    .addGroup(panelInventarioLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))))
        );

        indPages.addTab("Inventarios", panelInventario);

        pagTraslado.setBackground(new java.awt.Color(0, 153, 153));

        cbBodegaOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Plato", "Barrancas", "Cordoba ", "Fonseca Guajira", "Pueblo Bello", "Santa Marta", "Sincelejo 1", "Sincelejo 2", "Valledupar 1", "Valledupar 2" }));

        cbBodegaDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Plato", "Barrancas", "Cordoba ", "Fonseca Guajira", "Pueblo Bello", "Santa Marta", "Sincelejo 1", "Sincelejo 2", "Valledupar 1", "Valledupar 2" }));

        txtTrasladoA10s.setBackground(new java.awt.Color(255, 255, 255));

        txtTrasladoA21s.setBackground(new java.awt.Color(255, 255, 255));

        txtTrasladoA51.setBackground(new java.awt.Color(255, 255, 255));

        txtTrasladoA71.setBackground(new java.awt.Color(255, 255, 255));

        txtTrasladoNote.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        recibosTraslados.setBackground(new java.awt.Color(204, 204, 204));
        recibosTraslados.setColumns(20);
        recibosTraslados.setRows(5);
        jScrollPane2.setViewportView(recibosTraslados);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
        );

        btnTrasladar.setText("Trasladar");
        btnTrasladar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrasladarActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Origen");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Destino");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Cantidad A10s");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Cantidad A21s");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Cantidad A51");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Cantidad A71");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Cantidad Note 10");

        javax.swing.GroupLayout pagTrasladoLayout = new javax.swing.GroupLayout(pagTraslado);
        pagTraslado.setLayout(pagTrasladoLayout);
        pagTrasladoLayout.setHorizontalGroup(
            pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagTrasladoLayout.createSequentialGroup()
                .addGroup(pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagTrasladoLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(btnTrasladar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pagTrasladoLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagTrasladoLayout.createSequentialGroup()
                                .addComponent(cbBodegaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11))
                            .addGroup(pagTrasladoLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel13)
                                .addGap(44, 44, 44)
                                .addGroup(pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTrasladoA21s, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTrasladoA10s, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTrasladoA51, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTrasladoA71, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pagTrasladoLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(pagTrasladoLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(txtTrasladoNote, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))))
                .addGroup(pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagTrasladoLayout.createSequentialGroup()
                        .addGap(68, 122, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(31, 31, 31)
                        .addComponent(cbBodegaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(pagTrasladoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pagTrasladoLayout.setVerticalGroup(
            pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagTrasladoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBodegaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbBodegaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGroup(pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pagTrasladoLayout.createSequentialGroup()
                        .addGroup(pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagTrasladoLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(txtTrasladoA10s, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagTrasladoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)))
                        .addGroup(pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pagTrasladoLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addGroup(pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTrasladoA51, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTrasladoA71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)))
                            .addGroup(pagTrasladoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTrasladoA21s, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pagTrasladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtTrasladoNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTrasladar))
                    .addGroup(pagTrasladoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        indPages.addTab("Traslados", pagTraslado);

        ventanaVentas.setBackground(new java.awt.Color(0, 153, 204));

        jPanel2.setBackground(new java.awt.Color(0, 153, 204));

        txtNombreCliente.setBackground(new java.awt.Color(255, 255, 255));

        txtCantidadVender.setBackground(new java.awt.Color(255, 255, 255));

        cbEquipo.setBackground(new java.awt.Color(255, 255, 255));
        cbEquipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Samsung A10S ", "Samsung A21S ", "Samsung A51 ", "Samsung A71 ", "Xiaomi Note 10 " }));

        txtCedula.setBackground(new java.awt.Color(255, 255, 255));

        cbBodegaVenta.setBackground(new java.awt.Color(255, 255, 255));
        cbBodegaVenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Plato", "Barrancas", "Cordoba ", "Fonseca Guajira", "Pueblo Bello", "Santa Marta", "Sincelejo 1", "Sincelejo 2", "Valledupar 1", "Valledupar 2" }));

        btVenta.setBackground(new java.awt.Color(0, 153, 0));
        btVenta.setForeground(new java.awt.Color(255, 255, 255));
        btVenta.setText("Agregar Venta");
        btVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVentaActionPerformed(evt);
            }
        });

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tablaVentas);

        txtNombreVendedor.setBackground(new java.awt.Color(255, 255, 255));

        btnBorrarVentas.setBackground(new java.awt.Color(255, 0, 51));
        btnBorrarVentas.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrarVentas.setText("Borrar todo");
        btnBorrarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarVentasActionPerformed(evt);
            }
        });

        mostrarVentas.setBackground(new java.awt.Color(255, 255, 204));
        mostrarVentas.setForeground(new java.awt.Color(0, 0, 0));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Total Ventas:");

        limpiarVentas.setBackground(new java.awt.Color(204, 204, 204));
        limpiarVentas.setForeground(new java.awt.Color(51, 51, 51));
        limpiarVentas.setText("Limpiar");
        limpiarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarVentasActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Nombre del Cliente");

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Cédula del Cliente");

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Nombre del Vendedor");

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Equipo");

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Cantidad");

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Bodega de venta");

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(153, 0, 0));
        jLabel35.setText("Estimado asesor, por favor ingrese los datos para guardar la venta.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(limpiarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnBorrarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mostrarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel31)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtNombreVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel30))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel33)
                                                .addGap(66, 66, 66)
                                                .addComponent(txtCantidadVender, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel34)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbBodegaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel32)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2))))
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel35)
                .addGap(57, 57, 57))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadVender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel33))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBodegaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btVenta)
                    .addComponent(limpiarVentas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrarVentas)
                    .addComponent(mostrarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel2);

        javax.swing.GroupLayout ventanaVentasLayout = new javax.swing.GroupLayout(ventanaVentas);
        ventanaVentas.setLayout(ventanaVentasLayout);
        ventanaVentasLayout.setHorizontalGroup(
            ventanaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
        );
        ventanaVentasLayout.setVerticalGroup(
            ventanaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
        );

        indPages.addTab("Ventas", ventanaVentas);

        jPanel3.setBackground(new java.awt.Color(255, 153, 153));

        txtNombreClienteGarantia.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Dubai Light", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Estimado asesor, por favor llene los siguientes campos con el cliente que solicita garantía.");

        txtCedulaGarantia.setBackground(new java.awt.Color(255, 255, 255));

        equiposGarantia.setBackground(new java.awt.Color(255, 255, 255));
        equiposGarantia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Samsung A10S ", "Samsung A21S ", "Samsung A51 ", "Samsung A71 ", "Xiaomi Note 10 " }));

        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));

        txtDescripcionGarantia.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcionGarantia.setColumns(20);
        txtDescripcionGarantia.setForeground(new java.awt.Color(0, 0, 153));
        txtDescripcionGarantia.setRows(5);
        jScrollPane6.setViewportView(txtDescripcionGarantia);

        txtEncargadoGarantia.setBackground(new java.awt.Color(255, 255, 255));

        txtIdGarantia.setBackground(new java.awt.Color(255, 255, 255));

        finalizarGarantia.setBackground(new java.awt.Color(0, 102, 255));
        finalizarGarantia.setForeground(new java.awt.Color(255, 255, 255));
        finalizarGarantia.setText("Finalizar");
        finalizarGarantia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarGarantiaActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Descripción del problema");

        telefonoCliente.setBackground(new java.awt.Color(255, 255, 255));
        telefonoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoClienteActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Nombre del Cliente");

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Cédula");

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Equipo con problemas");

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("ID de la venta");

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Nombre del Asesor");

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Teléfono del Cliente");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCedulaGarantia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(equiposGarantia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEncargadoGarantia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdGarantia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombreClienteGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(telefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 65, Short.MAX_VALUE)))
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(finalizarGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(255, 255, 255))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(89, 89, 89))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreClienteGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCedulaGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(equiposGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEncargadoGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel26)
                    .addComponent(telefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(finalizarGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane5.setViewportView(jPanel3);

        indPages.addTab("Garantía", jScrollPane5);

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setForeground(new java.awt.Color(153, 153, 153));

        tablaGarantias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tablaGarantias);

        btnVerDetalle.setBackground(new java.awt.Color(204, 0, 204));
        btnVerDetalle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVerDetalle.setText("Ver Detalle");
        btnVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalleActionPerformed(evt);
            }
        });

        mostrarInfoGarantia.setColumns(20);
        mostrarInfoGarantia.setRows(5);
        jScrollPane9.setViewportView(mostrarInfoGarantia);

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Detalles");

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Garantías Recibidas.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(304, 304, 304))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addGap(126, 126, 126))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerDetalle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane7.setViewportView(jPanel4);

        indPages.addTab("Ver Garantías", jScrollPane7);

        panTable.setBackground(new java.awt.Color(183, 127, 28));
        panTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(191, 153, 38), new java.awt.Color(193, 155, 38), null, new java.awt.Color(191, 140, 38)));

        tablaInventarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaInventarios);

        deleteAll.setBackground(new java.awt.Color(204, 0, 0));
        deleteAll.setForeground(new java.awt.Color(255, 255, 255));
        deleteAll.setText("Eliminar Todo");
        deleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAllActionPerformed(evt);
            }
        });

        btnCalcular.setBackground(new java.awt.Color(204, 102, 0));
        btnCalcular.setForeground(new java.awt.Color(255, 255, 255));
        btnCalcular.setText("Calcular Totales");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("$");

        txtDineroglobal.setBackground(new java.awt.Color(255, 255, 255));

        txtTotalEquipos.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Total Equipos:");

        javax.swing.GroupLayout panTableLayout = new javax.swing.GroupLayout(panTable);
        panTable.setLayout(panTableLayout);
        panTableLayout.setHorizontalGroup(
            panTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(panTableLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(deleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDineroglobal, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panTableLayout.setVerticalGroup(
            panTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotalEquipos)
                        .addComponent(jLabel10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panTableLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDineroglobal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addGroup(panTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(btnCalcular)
                                .addComponent(deleteAll)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(indPages, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addComponent(indPages, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(panTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Botón agregar
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if(agregar.encontrarRepetidos((String)cbBodegas.getSelectedItem())){
                if(verificarCampos()){
                    this.recolectarDatos();
                    this.agregarDatos();
                }
        }
        else{
        JOptionPane.showMessageDialog(null, "La bodega ya ha sido agregada.\n Si desea realizar algún cambio presione el botón editar, no agregar.", "Bodega ya ingresada.", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed
    //Botón Editar.
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
            if (tablaInventarios.getSelectedRow() != -1 && verificarCampos()) {
            for(int i=0; i< datosInventario.size(); i++){
                 if(tablaInventarios.getSelectedRow()==i){
                    String zona=(String)cbZonas.getSelectedItem();  
                    String bodega=(String)cbBodegas.getSelectedItem(); 
                    String encargado=(String)cbEncargados.getSelectedItem();
                    int datosA10s= Integer.parseInt(txtA10s.getText());
                    int datosA21s= Integer.parseInt(txtA21s.getText());
                    int datosA51= Integer.parseInt(txtA51.getText());
                    int datosA71= Integer.parseInt(txtA71.getText());
                    int datosNote= Integer.parseInt(txtNote.getText());
                    int totalBodega=0;
                    totalBodega+=datosA10s+datosA21s+datosA51+datosA71+datosNote;
                    datosInventario.get(i).setZona(zona);
                    datosInventario.get(i).setBodega(bodega);
                    datosInventario.get(i).setEncargado(encargado);
                    datosInventario.get(i).setCantA10s(datosA10s);
                    datosInventario.get(i).setCantA21s(datosA21s);
                    datosInventario.get(i).setCantA51(datosA51);
                    datosInventario.get(i).setCantA71(datosA71);
                    datosInventario.get(i).setCantNote10(datosNote);
                    datosInventario.get(i).setTotalEquipos(totalBodega);
                    this.agregarDatos();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed
    //Botón eliminar Todo.
    private void deleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAllActionPerformed
       this.deleteDatos();
    }//GEN-LAST:event_deleteAllActionPerformed
    //Botón Calcular totales.
    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
            txtDineroglobal.setText(agregar.cantidadDinero()+"");
            txtTotalEquipos.setText(agregar.totalEquipos()+"");

    }//GEN-LAST:event_btnCalcularActionPerformed
    //Botón eliminar una fila.
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (tablaInventarios.getSelectedRow() != -1 && verificarCampos()){
            for(int i=0; i< datosInventario.size(); i++){
                 if(tablaInventarios.getSelectedRow()==i){
                     datosInventario.remove(i);
                     this.agregarDatos();
                 }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila para eliminar", "Error.", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    //Botón de traslado.
    private void btnTrasladarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrasladarActionPerformed
        if (verificarCamposTraslado() && verificarTipoCampos()) {
                this.recolectarDatosTraslado();
        }
    }//GEN-LAST:event_btnTrasladarActionPerformed
    //Botón de limpiar todo 
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtA10s.setText("");
        txtA21s.setText("");
        txtA51.setText("");
        txtA71.setText("");
        txtNote.setText("");
        cbBodegas.setSelectedIndex(0);
        cbEncargados.setSelectedIndex(0);
        cbZonas.setSelectedIndex(0);
    }//GEN-LAST:event_btnLimpiarActionPerformed
    //Botón de ventas.
    private void btVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVentaActionPerformed
        String bodegaVenta=(String)cbBodegaVenta.getSelectedItem();
        boolean verificador=true;
        if(verficarExistencia((String)cbBodegaVenta.getSelectedItem()) && verificarCamposVentas()){
            for (int i = 0; i < datosInventario.size(); i++) {
                if(datosInventario.get(i).getBodega().equals(bodegaVenta)){
                    int cantidad= Integer.parseInt(txtCantidadVender.getText());
                    if(cbEquipo.getSelectedIndex()==1 && cantidad<=datosInventario.get(i).getCantA10s()){
                        int equiposbodega=datosInventario.get(i).getCantA10s();
                        equiposbodega-=cantidad;
                        datosInventario.get(i).setCantA10s(equiposbodega);
                        break;
                    }
                    else if(cbEquipo.getSelectedIndex()==2 && cantidad<=datosInventario.get(i).getCantA21s()){
                        int equiposbodega=datosInventario.get(i).getCantA21s();
                        equiposbodega-=cantidad;
                        datosInventario.get(i).setCantA21s(equiposbodega);
                        break;
                    }
                    else if(cbEquipo.getSelectedIndex()==3 && cantidad<=datosInventario.get(i).getCantA51()){
                        int equiposbodega=datosInventario.get(i).getCantA51();
                        equiposbodega-=cantidad;
                        datosInventario.get(i).setCantA51(equiposbodega);
                        break;
                    }
                    else if(cbEquipo.getSelectedIndex()==4 && cantidad<=datosInventario.get(i).getCantA71()){
                        int equiposbodega=datosInventario.get(i).getCantA71();
                        equiposbodega-=cantidad;
                        datosInventario.get(i).setCantA71(equiposbodega);
                        break;
                    }
                    else if(cbEquipo.getSelectedIndex()==5 && cantidad<=datosInventario.get(i).getCantNote10()){
                        int equiposbodega=datosInventario.get(i).getCantNote10();
                        equiposbodega-=cantidad;
                        datosInventario.get(i).setCantNote10(equiposbodega);
                        break;
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "No se ha podido realizar la venta\n Posibles motivos:\n1.La cantidad de equipos a vender es superior a las existencias en bodega.\n 2.Posible inexistencia de la bodega seleccionada o del equipo.\n Para mayor seguridad, analice las bodegas disponibles en la tabla inferior\n y la cantidad de equipos que poseen.", "Error.", JOptionPane.ERROR_MESSAGE);
                        verificador=false;
                    }
                }
            }
            if(verificador){
            this.agregarDatos();
            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            String hora=date.format(LocalDateTime.now());
            //corregir datos para evitar repeticion
            AgregarVentas agregarNuevaVenta= new AgregarVentas();
            agregarNuevaVenta.agregarDatosventa((String)txtNombreCliente.getText(),Integer.parseInt(txtCedula.getText()),hora,(String)cbEquipo.getSelectedItem(),Integer.parseInt(txtCantidadVender.getText()),(String)cbBodegaVenta.getSelectedItem());           
            JOptionPane.showMessageDialog(this,"Informe de Venta:\n Nombre Cliente:"+(String)txtNombreCliente.getText()+"\n"+"Cédula:"+Integer.parseInt(txtCedula.getText())+"\n"+
                    "Fecha:"+hora+"\n"+"Equipo: "+(String)cbEquipo.getSelectedItem()+"\n"+"Cantidad: "+Integer.parseInt(txtCantidadVender.getText())+"\n"+"Bodega:"+(String)cbBodegaVenta.getSelectedItem()+"Id de venta"+agregarNuevaVenta.devolverRecibo(),"Recibo de Venta.",JOptionPane.INFORMATION_MESSAGE);
            this.agregarDatosVentas();
            mostrarVentas.setText(nuevaVenta.calcularCantidades()+"");
            }
        }
        else if(verificarCamposVentas()==false){
            JOptionPane.showMessageDialog(this,"Algunos campos se encuentran vacíos o sin seleccionar.\nPor favor vuelva a intentarlo.","Campos vacíos.",JOptionPane.ERROR_MESSAGE);
        }
        else if(verficarExistencia((String)cbBodegaVenta.getSelectedItem())== false){
            JOptionPane.showMessageDialog(this, "La bodega que se selecciona para la venta no está seleccionada o no está disponible por ahora.\n Si desea realizar la venta, seleccione otra bodega.", "Bodega Inválida", HEIGHT);
        }
        else{
            System.out.println("Ocurrió un error inesperado en la sección de ventas (línea 1354-1419)");
        }
    }//GEN-LAST:event_btVentaActionPerformed
    //Botón de borrar ventas
    private void btnBorrarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarVentasActionPerformed
        this.limpiarVentas();
        ventasExistentes.clear();
    }//GEN-LAST:event_btnBorrarVentasActionPerformed
    //Botón de agregar garantía
    private void finalizarGarantiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarGarantiaActionPerformed
        if(verificarCamposGarantia()){
            this.RecolectarDatosGarantia();
            txtNombreClienteGarantia.setText("");
            txtCedulaGarantia.setText("");
            equiposGarantia.setSelectedIndex(0);
            txtEncargadoGarantia.setText("");
            txtDescripcionGarantia.setText("");
            txtIdGarantia.setText("");
            telefonoCliente.setText("");
            this.agregarGarantias();
        }
    }//GEN-LAST:event_finalizarGarantiaActionPerformed

    private void telefonoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoClienteActionPerformed
    //Botón para visualizar la garantía seleccionada por el usuario
    private void btnVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleActionPerformed
        if(tablaGarantias.getSelectedRow()!=-1){
                mostrarText(tablaGarantias.getSelectedRow());
        }
        else{
            JOptionPane.showMessageDialog(this,"No se ha seleccionado ninguna fila.\n Por favor seleccione una fila para poder visualizar los datos.","Error, fila no seleccionada.",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerDetalleActionPerformed
    //Botón para limpiar todas las casillas de ventas.
    private void limpiarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarVentasActionPerformed
                txtNombreCliente.setText("");
                txtCedula.setText("");
                txtNombreVendedor.setText("");
                cbEquipo.setSelectedIndex(0);
                txtCantidadVender.setText("");
                cbBodegaVenta.setSelectedIndex(0);
                
    }//GEN-LAST:event_limpiarVentasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inventarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btVenta;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrarVentas;
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnTrasladar;
    private javax.swing.JButton btnVerDetalle;
    private javax.swing.JComboBox<String> cbBodegaDestino;
    private javax.swing.JComboBox<String> cbBodegaOrigen;
    private javax.swing.JComboBox<String> cbBodegaVenta;
    private javax.swing.JComboBox<String> cbBodegas;
    private javax.swing.JComboBox<String> cbEncargados;
    private javax.swing.JComboBox<String> cbEquipo;
    private javax.swing.JComboBox<String> cbZonas;
    private javax.swing.JButton deleteAll;
    private javax.swing.JComboBox<String> equiposGarantia;
    private javax.swing.JButton finalizarGarantia;
    private javax.swing.JTabbedPane indPages;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton limpiarVentas;
    private javax.swing.JTextArea mostrarInfoGarantia;
    private javax.swing.JTextField mostrarVentas;
    private javax.swing.JPanel pagTraslado;
    private javax.swing.JPanel panTable;
    private javax.swing.JPanel panelInventario;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTextArea recibosTraslados;
    private javax.swing.JTable tablaGarantias;
    private javax.swing.JTable tablaInventarios;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField telefonoCliente;
    private javax.swing.JTextField txtA10s;
    private javax.swing.JTextField txtA21s;
    private javax.swing.JTextField txtA51;
    private javax.swing.JTextField txtA71;
    private javax.swing.JTextField txtCantidadVender;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCedulaGarantia;
    private javax.swing.JTextArea txtDescripcionGarantia;
    private javax.swing.JTextField txtDineroglobal;
    private javax.swing.JTextField txtEncargadoGarantia;
    private javax.swing.JTextField txtIdGarantia;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreClienteGarantia;
    private javax.swing.JTextField txtNombreVendedor;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtTotalEquipos;
    private javax.swing.JTextField txtTrasladoA10s;
    private javax.swing.JTextField txtTrasladoA21s;
    private javax.swing.JTextField txtTrasladoA51;
    private javax.swing.JTextField txtTrasladoA71;
    private javax.swing.JTextField txtTrasladoNote;
    private javax.swing.JPanel ventanaVentas;
    // End of variables declaration//GEN-END:variables

    //Clase creada para darle el fondo al panel principal.
    class Fondo extends JPanel{
        private Image fondo;
        
        public void paint(Graphics g){
            fondo = new ImageIcon(getClass().getResource("/images/fondo.png")).getImage();
            
            g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
            
            setOpaque(false);
            super.paint(g);
        }
        
    }
}
