/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package appasesor;

import appadmin.*;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author jeanpi03
 */
public class RegistroFacturas extends javax.swing.JPanel {

    /**
     * Creates new form RegistroFacturas
     */
    public RegistroFacturas() {
        initComponents();
    }

    private void showJpanel(JPanel p) {

        p.setSize(739, 61);
        p.setLocation(0, 0);

        panelTabla.removeAll();
        panelTabla.add(p, BorderLayout.CENTER);
        panelTabla.revalidate();
        panelTabla.repaint();
    }

    private class DateValidation {

        private boolean isValid;
        private String message;

        public DateValidation(boolean isValid, String message) {
            this.isValid = isValid;
            this.message = message;
        }

        public boolean isValid() {
            return isValid;
        }

        public String getMessage() {
            return message;
        }
    }

    private DateValidation verificarFecha(String dia, String mes, String año) {
        boolean fechaCorrecta = false;
        String msg = "Fecha invalida:\n";
        String fecha = dia + "/" + mes + "/" + año;

        System.out.println("Fecha ingresada: " + fecha);

        // Eliminar espacios adicionales
        fecha = fecha.trim();

        // Comprobar formato dd/MM/yyyy
        int diaInt, mesInt, añoInt;
        try {
            diaInt = Integer.parseInt(dia);
            mesInt = Integer.parseInt(mes);
            añoInt = Integer.parseInt(año);
        } catch (NumberFormatException e) {
            msg += "*Los campos de fecha deben ser números válidos\n";
            return new DateValidation(fechaCorrecta, msg);
        }

        // Validar el rango del año
        if (añoInt < 1900 || añoInt > java.time.LocalDate.now().getYear()) {
            msg += "*El año debe estar entre 1900 y el año actual\n";
        }

        boolean mesValid = true;
        // Validar el rango del mes
        if (mesInt < 1 || mesInt > 12) {
            msg += "*El mes debe estar entre 1 y 12\n";
            mesValid = false;
        }

        // Validar el rango del día
        if (mesValid) {
            if (diaInt < 1 || diaInt > diasEnMes(mesInt)) {
                msg += "*El día debe ser válido para el mes y año dados\n";
            }
        }

        // Si todas las comprobaciones son válidas
        if (msg.equals("Fecha invalida:\n")) {
            fechaCorrecta = true;
        }

        return new DateValidation(fechaCorrecta, msg);
    }

    // Método para obtener el número de días en un mes determinado de un año determinado
    private int diasEnMes(int mes) {
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return diasPorMes[mes - 1];
    }

    private void registrarFactura() {
        //obteniendo el texto escrito en los fields
        String transaccion = (String) (boxTipo.getSelectedItem());
        String name = txfName.getText();
        String id = txfID.getText();
        String direccion = txfDireccion.getText();
        String dia = txfDia.getText();
        String mes = txfMes.getText();
        String año = txfAño.getText();
        String fecha = dia + "/" + mes + "/" + año;
        String costo = txfCosto.getText();
        boolean dateCorrect = false;
        boolean fieldCorrect = false;
        boolean fdateCorrect = true;
        boolean formatDate=false;
        
        //comprobar campo de fecha
        if (dia.equals("DD")
                || dia.equals("")
                || mes.equals("MM")
                || mes.equals("")
                || año.equals("AAAA")
                || año.equals("")) {
            fdateCorrect = false;
        }
        
        //comprobar formato de fecha
        if(dia.length()==2 && mes.length()==2 && año.length()==4){
            formatDate=true;
        }
        
        if(!formatDate){
            JOptionPane.showMessageDialog(null, "Ingresar fecha en formato correcto (DD/MM/AAAA)");
        }

        if (transaccion.length() != 0
                && direccion.length() != 0
                && name.length() != 0
                && id.length() != 0
                && costo.length() != 0
                && fdateCorrect
                && !transaccion.equals("Seleccionar")) {

            fieldCorrect = true;
        }

        if (!fieldCorrect && !fdateCorrect) {
            JOptionPane.showMessageDialog(null, "Es necesario rellenar todos los campos!!!");
        }
        //verificar fecha
        DateValidation vfecha = null;
        if (fdateCorrect && formatDate) {
            vfecha = verificarFecha(dia, mes, año);
            dateCorrect = vfecha.isValid;
        }

        if (!dateCorrect && fdateCorrect && fieldCorrect) {
            JOptionPane.showMessageDialog(null, vfecha.getMessage());
        }

        if (fieldCorrect && dateCorrect && !ocupado) {
            Factura.saveFactura(transaccion, name, id, direccion, fecha, costo);
            txfDireccion.setText("");
            txfAño.setText("");
            txfDia.setText("");
            txfMes.setText("");
            txfCosto.setText("");
            txfID.setText("");
            txfName.setText("");
        }
        
        if(ocupado && fieldCorrect && dateCorrect){
            JOptionPane.showMessageDialog(null, "----No se pueden facturar inmuebles ocupados----");
        }
    }

    private void buscarCliente() {
        String id = txfID.getText();
        boolean found = false;
        boolean fieldCorrect = false;

        Cliente[] clientes = Cliente.loadClientes();

        if (id.length() != 0) {
            fieldCorrect = true;
        }

        if (fieldCorrect) {
            for (Cliente cliente : clientes) {
                if (id.equals(cliente.getId())) {
                    found = true;
                    txfName.setText(cliente.getName());
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Rellenar el campo de ID para buscar!!");
        }

        if (found) {
            JOptionPane.showMessageDialog(null, "Se encontró el cliente [" + id + "--" + txfName.getText() + "]");
        }

        if (fieldCorrect && !found) {
            JOptionPane.showMessageDialog(null, "No se encontró el cliente [" + id + "]"
                    + " por favor registrarlo");
        }

    }
    
    boolean ocupado = false;    
    private void buscarInmueble() {
        String direccion = txfDireccion.getText().trim();
        boolean found = false;
        boolean fieldCorrect = false;

        Inmueble[] inmuebles = Inmueble.loadInmuebles();

        if (direccion.length() != 0) {
            fieldCorrect = true;
        }

        if (fieldCorrect) {
            for (Inmueble inmueble : inmuebles) {
                if (direccion.equals(inmueble.getDireccion())) {
                    found = true;
                    txfCosto.setText(String.valueOf("$"+inmueble.getPrecio()));
                    if (!inmueble.getEstado().equals("Desocupado")) {
                        ocupado = true;
                    }
                    break;
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Rellenar el campo de dirección para buscar!!");
        }

        if (found && !ocupado) {
            JOptionPane.showMessageDialog(null, "Se encontró el inmueble con dirección [" + direccion + "]--- Desocupado");
            showJpanel(new TablaBusquedaI(direccion));
        }

        if (found && ocupado) {
            JOptionPane.showMessageDialog(null, "Se encontró el inmueble con dirección [" + direccion + "] --- ocupado");
            showJpanel(new TablaBusquedaI(direccion));
        }

        if (fieldCorrect && !found) {
            JOptionPane.showMessageDialog(null, "No se encontró el inmueble con dirección [" + direccion + "]"
                    + " por favor registrarlo");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenido = new javax.swing.JPanel();
        txfName = new javax.swing.JTextField();
        txfCosto = new javax.swing.JTextField();
        txfID = new javax.swing.JTextField();
        txfDireccion = new javax.swing.JTextField();
        titleRegistrar2 = new javax.swing.JLabel();
        registroTipodeInmueble2 = new javax.swing.JLabel();
        registroNombre2 = new javax.swing.JLabel();
        registroID2 = new javax.swing.JLabel();
        registroValor2 = new javax.swing.JLabel();
        registroDireccion2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        boxTipo = new javax.swing.JComboBox<>();
        registroDireccion3 = new javax.swing.JLabel();
        txfDia = new javax.swing.JTextField();
        txfMes = new javax.swing.JTextField();
        txfAño = new javax.swing.JTextField();
        bntBP = new javax.swing.JButton();
        bntBI = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();

        contenido.setBackground(new java.awt.Color(255, 255, 255));

        txfName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txfCosto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txfID.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txfDireccion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        titleRegistrar2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titleRegistrar2.setForeground(new java.awt.Color(0, 0, 0));
        titleRegistrar2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleRegistrar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/facturacion.png"))); // NOI18N
        titleRegistrar2.setText("Registrar facturas");
        titleRegistrar2.setPreferredSize(new java.awt.Dimension(150, 50));

        registroTipodeInmueble2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        registroTipodeInmueble2.setForeground(new java.awt.Color(0, 0, 0));
        registroTipodeInmueble2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anadir.png"))); // NOI18N
        registroTipodeInmueble2.setText("Seleccionar Tipo de transacción");

        registroNombre2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        registroNombre2.setForeground(new java.awt.Color(0, 0, 0));
        registroNombre2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario (1).png"))); // NOI18N
        registroNombre2.setText("Nombre");

        registroID2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        registroID2.setForeground(new java.awt.Color(0, 0, 0));
        registroID2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tarjeta-de-identificacion.png"))); // NOI18N
        registroID2.setText("ID");

        registroValor2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        registroValor2.setForeground(new java.awt.Color(0, 0, 0));
        registroValor2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registroValor2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dinero.png"))); // NOI18N
        registroValor2.setText("Costo");

        registroDireccion2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        registroDireccion2.setForeground(new java.awt.Color(0, 0, 0));
        registroDireccion2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mapa.png"))); // NOI18N
        registroDireccion2.setText("Dirección de inmueble");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro (1).png"))); // NOI18N
        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        boxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Arrendado", "Vendido" }));

        registroDireccion3.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        registroDireccion3.setForeground(new java.awt.Color(0, 0, 0));
        registroDireccion3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/entrega.png"))); // NOI18N
        registroDireccion3.setText("Fecha");

        txfDia.setText("DD");
        txfDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txfDiaMousePressed(evt);
            }
        });

        txfMes.setText("MM");
        txfMes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txfMesMousePressed(evt);
            }
        });

        txfAño.setText("AAAA");
        txfAño.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txfAñoMousePressed(evt);
            }
        });

        bntBP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscarP.png"))); // NOI18N
        bntBP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntBPActionPerformed(evt);
            }
        });

        bntBI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscarI.png"))); // NOI18N
        bntBI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntBIActionPerformed(evt);
            }
        });

        panelTabla.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelTablaLayout = new javax.swing.GroupLayout(panelTabla);
        panelTabla.setLayout(panelTablaLayout);
        panelTablaLayout.setHorizontalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelTablaLayout.setVerticalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout contenidoLayout = new javax.swing.GroupLayout(contenido);
        contenido.setLayout(contenidoLayout);
        contenidoLayout.setHorizontalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenidoLayout.createSequentialGroup()
                .addGroup(contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contenidoLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(titleRegistrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contenidoLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contenidoLayout.createSequentialGroup()
                                .addComponent(registroTipodeInmueble2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(boxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(contenidoLayout.createSequentialGroup()
                                .addGroup(contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(contenidoLayout.createSequentialGroup()
                                        .addComponent(registroNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txfName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(contenidoLayout.createSequentialGroup()
                                        .addComponent(registroID2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txfID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bntBP)))
                                .addGap(96, 96, 96)
                                .addGroup(contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(contenidoLayout.createSequentialGroup()
                                        .addComponent(registroDireccion3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txfDia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txfMes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txfAño, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(contenidoLayout.createSequentialGroup()
                                        .addComponent(registroValor2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txfCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(contenidoLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(registroDireccion2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txfDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntBI)))
                .addContainerGap(82, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenidoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(326, 326, 326))
            .addComponent(panelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contenidoLayout.setVerticalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleRegistrar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registroTipodeInmueble2)
                    .addComponent(boxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contenidoLayout.createSequentialGroup()
                        .addGroup(contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registroNombre2)
                            .addComponent(txfName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contenidoLayout.createSequentialGroup()
                                .addGroup(contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(registroID2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txfID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(registroDireccion2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txfDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(bntBI))
                                .addGap(54, 54, 54))
                            .addComponent(bntBP)))
                    .addGroup(contenidoLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(registroDireccion3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txfCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(registroValor2))
                        .addGap(113, 113, 113)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bntBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBPActionPerformed
        buscarCliente();
    }//GEN-LAST:event_bntBPActionPerformed

    private void bntBIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBIActionPerformed
        buscarInmueble();
    }//GEN-LAST:event_bntBIActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        registrarFactura();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txfDiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txfDiaMousePressed
        txfDia.setText("");
    }//GEN-LAST:event_txfDiaMousePressed

    private void txfMesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txfMesMousePressed
        txfMes.setText("");
    }//GEN-LAST:event_txfMesMousePressed

    private void txfAñoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txfAñoMousePressed
        txfAño.setText("");
    }//GEN-LAST:event_txfAñoMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntBI;
    private javax.swing.JButton bntBP;
    private javax.swing.JComboBox<String> boxTipo;
    private javax.swing.JPanel contenido;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JLabel registroDireccion2;
    private javax.swing.JLabel registroDireccion3;
    private javax.swing.JLabel registroID2;
    private javax.swing.JLabel registroNombre2;
    private javax.swing.JLabel registroTipodeInmueble2;
    private javax.swing.JLabel registroValor2;
    private javax.swing.JLabel titleRegistrar2;
    private javax.swing.JTextField txfAño;
    private javax.swing.JTextField txfCosto;
    private javax.swing.JTextField txfDia;
    private javax.swing.JTextField txfDireccion;
    private javax.swing.JTextField txfID;
    private javax.swing.JTextField txfMes;
    private javax.swing.JTextField txfName;
    // End of variables declaration//GEN-END:variables
}
