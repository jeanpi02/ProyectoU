/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package appasesor;

import appadmin.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
    DefaultTableModel model;
    String inmueblesF = "";
    
    private void registrarFactura() {
        //obteniendo el texto escrito en los fields
        String transaccion = (String) (boxTipo.getSelectedItem());
        String name = txfName.getText();
        String id = txfID.getText();
        String direccion = inmueblesF;
        String fecha = txfDate.getText();
        String total = txfTotal.getText();

        boolean fieldCorrect = false;

        System.out.println("Fecha ingresada: " + fecha);

        if (transaccion.length() != 0
                && direccion.length() != 0
                && name.length() != 0
                && id.length() != 0
                && total.length() != 0
                && !transaccion.equals("Seleccionar")) {

            fieldCorrect = true;
        }

        if (!fieldCorrect) {
            JOptionPane.showMessageDialog(null, "Es necesario rellenar todos los campos!!!");
        }

        if (fieldCorrect && !ocupado) {
            Factura.saveFactura(transaccion, name, id, direccion, fecha, total);
            //limpiar y reset variables
            txfDireccion.setText("");
            txfTotal.setText("");
            txfID.setText("");
            txfName.setText("");
        }

        if (ocupado && fieldCorrect) {
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

    boolean ocupado = true;
    boolean añadirc = false;
    double sumaTotal = 0;

    private void añadirAlcarrito() {
        model = (DefaultTableModel) this.jTable1.getModel();
        String transaccion = (String) (boxTipo.getSelectedItem());
        String name = txfName.getText();
        String id = txfID.getText();
        String direccion = txfDireccion.getText();
        String fecha = txfDate.getText();

        boolean transaccionllena = false;
        boolean fieldCorrect = false;

        if (transaccion.length() != 0
                && direccion.length() != 0
                && name.length() != 0
                && id.length() != 0
                && !transaccion.equals("Seleccionar")) {

            fieldCorrect = true;
        }

        if (!fieldCorrect) {
            JOptionPane.showMessageDialog(null, "Es necesario rellenar todos los campos!!!");
        }

        if (!transaccion.equals("Seleccionar")) {
            transaccionllena = true;
        }

        double precio = 0;
        int cantidad = 0;
        try {
            // Intentar convertir la cadena a un entero
            cantidad = Integer.valueOf(txfCantidad.getText());
        } catch (NumberFormatException e) {
            // Manejar la excepción si la conversión falla
            System.out.println("Error: Ingresar número entero valido para cantidad");

        }

        Inmueble[] inmuebles = Inmueble.loadInmuebles();

        for (Inmueble inmueble : inmuebles) {
            if (inmueble.getDireccion().equals(direccion)) {
                precio = inmueble.getPrecio();
            }
        }

        if (añadirc && inmubeleEcontrado && !ocupado && (cantidad > 0) && transaccionllena) {
            model.addRow(new Object[]{
                fecha,
                direccion,
                id,
                name,
                transaccion,
                precio,
                cantidad
            });
            precio = precio * cantidad;
            sumaTotal += precio;
            txfTotal.setText(String.valueOf(sumaTotal));
            inmueblesF += direccion + "(" + cantidad + ")" + "-";

        }
        if (ocupado && fieldCorrect) {
            JOptionPane.showMessageDialog(null, "El inmueble con dirección :" + direccion + " está ocupado");

        }
        if (!inmubeleEcontrado && fieldCorrect) {
            JOptionPane.showMessageDialog(null, "El inmueble con dirección :" + direccion + " no se encuentra registrado");
        }

        if (cantidad <= 0 && fieldCorrect) {
            JOptionPane.showMessageDialog(null, "Ingresar una cantidad mayor a 0");
        }

        if (!transaccionllena && fieldCorrect) {
            JOptionPane.showMessageDialog(null, "Escoger tipo de transacción para poder agregar");
        }
        añadirc = false;

    }
    boolean inmubeleEcontrado = false;

    private void buscarInmueble() {
        String direccion = txfDireccion.getText().trim();

        boolean fieldCorrect = false;

        Inmueble[] inmuebles = Inmueble.loadInmuebles();

        if (direccion.length() != 0) {
            fieldCorrect = true;
        }

        if (fieldCorrect) {
            for (Inmueble inmueble : inmuebles) {
                if (direccion.equals(inmueble.getDireccion())) {
                    inmubeleEcontrado = true;
                    if (inmueble.getEstado().equals("Desocupado")) {
                        ocupado = false;

                    }
                    break;
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Rellenar el campo de dirección para buscar!!");
        }

        if (inmubeleEcontrado && !ocupado) {
            JOptionPane.showMessageDialog(null, "Se encontró el inmueble con dirección [" + direccion + "]--- Desocupado");

        }

        if (inmubeleEcontrado && ocupado) {
            JOptionPane.showMessageDialog(null, "Se encontró el inmueble con dirección [" + direccion + "] --- ocupado");
        }

        if (fieldCorrect && !inmubeleEcontrado) {
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

        dateChooser1 = new com.raven.datechooser.DateChooser();
        contenido = new javax.swing.JPanel();
        txfName = new javax.swing.JTextField();
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
        bntBP = new javax.swing.JButton();
        bntBI = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txfTotal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txfCantidad = new javax.swing.JTextField();
        txfDate = new javax.swing.JTextField();

        dateChooser1.setForeground(new java.awt.Color(0, 51, 204));
        dateChooser1.setTextRefernce(txfDate);

        setPreferredSize(new java.awt.Dimension(750, 490));

        contenido.setBackground(new java.awt.Color(255, 255, 255));
        contenido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido.setForeground(new java.awt.Color(0, 0, 0));
        contenido.setPreferredSize(new java.awt.Dimension(750, 490));
        contenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txfName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido.add(txfName, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 141, 160, 32));

        txfID.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido.add(txfID, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 202, 160, 32));

        txfDireccion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido.add(txfDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 267, 160, 32));

        titleRegistrar2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titleRegistrar2.setForeground(new java.awt.Color(0, 0, 0));
        titleRegistrar2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleRegistrar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/facturacion.png"))); // NOI18N
        titleRegistrar2.setText("Registrar facturas");
        titleRegistrar2.setPreferredSize(new java.awt.Dimension(150, 50));
        contenido.add(titleRegistrar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 8, 442, -1));

        registroTipodeInmueble2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        registroTipodeInmueble2.setForeground(new java.awt.Color(0, 0, 0));
        registroTipodeInmueble2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anadir.png"))); // NOI18N
        registroTipodeInmueble2.setText("Seleccionar Tipo de transacción");
        contenido.add(registroTipodeInmueble2, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 89, -1, -1));

        registroNombre2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        registroNombre2.setForeground(new java.awt.Color(0, 0, 0));
        registroNombre2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario (1).png"))); // NOI18N
        registroNombre2.setText("Nombre");
        contenido.add(registroNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 143, 101, -1));

        registroID2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        registroID2.setForeground(new java.awt.Color(0, 0, 0));
        registroID2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tarjeta-de-identificacion.png"))); // NOI18N
        registroID2.setText("ID");
        contenido.add(registroID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 200, 101, -1));

        registroValor2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        registroValor2.setForeground(new java.awt.Color(0, 0, 0));
        registroValor2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registroValor2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dinero.png"))); // NOI18N
        registroValor2.setText("Total");
        contenido.add(registroValor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 466, 87, -1));

        registroDireccion2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        registroDireccion2.setForeground(new java.awt.Color(0, 0, 0));
        registroDireccion2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mapa.png"))); // NOI18N
        registroDireccion2.setText("Dirección de inmueble");
        contenido.add(registroDireccion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 265, -1, 32));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro (1).png"))); // NOI18N
        jButton1.setText("Facturar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        contenido.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 464, -1, -1));

        boxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Arrendado", "Vendido" }));
        contenido.add(boxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 95, -1, -1));

        registroDireccion3.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        registroDireccion3.setForeground(new java.awt.Color(0, 0, 0));
        registroDireccion3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/entrega.png"))); // NOI18N
        registroDireccion3.setText("Fecha");
        contenido.add(registroDireccion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, -1, 32));

        bntBP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscarP.png"))); // NOI18N
        bntBP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntBPActionPerformed(evt);
            }
        });
        contenido.add(bntBP, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        bntBI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscarI.png"))); // NOI18N
        bntBI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntBIActionPerformed(evt);
            }
        });
        contenido.add(bntBI, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 265, -1, -1));

        jButton2.setText("...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        contenido.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, 35, -1));

        jButton3.setText("Hoy");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        contenido.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, -1, -1));

        txfTotal.setForeground(new java.awt.Color(0, 0, 0));
        txfTotal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido.add(txfTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, 109, 22));

        jLabel1.setFont(new java.awt.Font("Abyssinica SIL", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Cantidad");
        contenido.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 282, -1, -1));

        jButton4.setText("agregar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        contenido.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(634, 280, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Inmueble D", "Documento", "Nombre", "Transacción", "Costo unidad", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        contenido.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 680, 140));
        contenido.add(txfCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 280, 30, -1));

        txfDate.setForeground(new java.awt.Color(0, 0, 0));
        txfDate.setFocusable(false);
        contenido.add(txfDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, 100, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenido, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bntBIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBIActionPerformed
        buscarInmueble();
    }//GEN-LAST:event_bntBIActionPerformed

    private void bntBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBPActionPerformed
        buscarCliente();
    }//GEN-LAST:event_bntBPActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        registrarFactura();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dateChooser1.showPopup();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dateChooser1.toDay();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        añadirc = true;
        añadirAlcarrito();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntBI;
    private javax.swing.JButton bntBP;
    private javax.swing.JComboBox<String> boxTipo;
    private javax.swing.JPanel contenido;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel registroDireccion2;
    private javax.swing.JLabel registroDireccion3;
    private javax.swing.JLabel registroID2;
    private javax.swing.JLabel registroNombre2;
    private javax.swing.JLabel registroTipodeInmueble2;
    private javax.swing.JLabel registroValor2;
    private javax.swing.JLabel titleRegistrar2;
    private javax.swing.JTextField txfCantidad;
    private javax.swing.JTextField txfDate;
    private javax.swing.JTextField txfDireccion;
    private javax.swing.JTextField txfID;
    private javax.swing.JTextField txfName;
    private javax.swing.JLabel txfTotal;
    // End of variables declaration//GEN-END:variables
}
