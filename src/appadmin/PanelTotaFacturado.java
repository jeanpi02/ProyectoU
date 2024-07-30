/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package appadmin;

import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author jeanpi03
 */
public class PanelTotaFacturado extends javax.swing.JPanel {

    /**
     * Creates new form PanelTotaFacturado
     */
    public PanelTotaFacturado() {
        initComponents();
        initStyles();
    }

    private void initStyles() {
        titulo.putClientProperty("FlatLaf.styleClass", "h1");

    }

    private void showTable(JPanel p) {

        p.setSize(750, 292);
        p.setLocation(0, 0);

        Rbusqueda.removeAll();
        Rbusqueda.add(p, BorderLayout.CENTER);
        Rbusqueda.revalidate();
        Rbusqueda.repaint();
    }

    private void verTotalRecogido() {
        txfTotal.setText("");
        String tipot = (String) boxTipo.getSelectedItem();
        int i = 0;
        double total = 0;
        boolean vendidoe = false;
        boolean arrendadoe = false;
        Factura[] facturas = Factura.loadFacturas();
        if (tipot.equals("Arrendado")) {
            i = 1;
        } else if (tipot.equals("Vendido")) {
            i = 2;
        }

        if (i == 1) {
            showTable(new TablaBusquedaFF(i));
            for (Factura factura : facturas) {
                if (factura.getTransaccion().equals(tipot)) {
                    arrendadoe = true;
                    try {
                        total += Double.valueOf(factura.getCosto());
                    } catch (NumberFormatException e) {
                        System.out.println("Conversión fallida");
                    }
                    txfTotal.setText(String.valueOf(total));

                }
            }

            if (!arrendadoe) {
                JOptionPane.showMessageDialog(null, "No se encontraron facturas con este tipo de transacción" + tipot);
                Rbusqueda.removeAll();
                Rbusqueda.repaint();
            }
        } else if (i == 2) {
            for (Factura factura : facturas) {
                if (factura.getTransaccion().equals(tipot)) {
                    vendidoe = true;
                    try {
                        total += Double.valueOf(factura.getCosto());
                    } catch (NumberFormatException e) {
                        System.out.println("Conversión fallida");
                    }
                    txfTotal.setText(String.valueOf(total));

                }
            }
            if (!vendidoe) {
                JOptionPane.showMessageDialog(null, "No se encontraron facturas con este tipo de transacción:" + tipot);
                Rbusqueda.removeAll();
                Rbusqueda.repaint();

            }
        } else if (i == 0) {
            JOptionPane.showMessageDialog(null, "Por favor seleccionar tipo de transacción!!!");
            Rbusqueda.removeAll();
            Rbusqueda.repaint();
        }

    }

    private void VerfacturadoPorRango() {
        txfTotal.setText("");
        String fecha1 = txfDate1.getText();
        String fecha2 = txfDate2.getText();
        String tipo = (String) boxTipo.getSelectedItem();
        boolean vendidoe = false;
        boolean arrendadoe = false;
        double total = 0;
        Factura[] facturas = Factura.loadFacturas();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if (tipo.equals("Arrendado")) {
            arrendadoe = true;
            try {
                Date date1 = dateFormat.parse(fecha1);
                Date date2 = dateFormat.parse(fecha2);

                for (Factura factura : facturas) {
                    try {
                        Date fechaFactura = dateFormat.parse(factura.getFecha());
                        if (!fechaFactura.before(date1) && !fechaFactura.after(date2) && factura.getTransaccion().equals(tipo)) {
                            total += Double.valueOf(factura.getCosto());
                        }
                    } catch (ParseException e) {
                        System.out.println("Error al parsear la fecha de la factura: " + factura.getFecha());
                    } catch (NumberFormatException e) {
                        System.out.println("Conversión fallida del costo de la factura: " + factura.getCosto());
                    }
                }
            } catch (ParseException e) {
                System.out.println("Error al parsear las fechas de entrada: " + fecha1 + " o " + fecha2);
            }

            txfTotal.setText(String.valueOf(total));
            showTable(new TablaBusquedaFF(fecha1, fecha2, tipo));

            if (!arrendadoe) {
                JOptionPane.showMessageDialog(null, "No se encontraron facturas con este tipo de transacción" + tipo);
                Rbusqueda.removeAll();
                Rbusqueda.repaint();
            }
        } else if (tipo.equals("Vendido")) {
            vendidoe = true;

            try {
                Date date1 = dateFormat.parse(fecha1);
                Date date2 = dateFormat.parse(fecha2);

                for (Factura factura : facturas) {
                    try {
                        Date fechaFactura = dateFormat.parse(factura.getFecha());
                        if (!fechaFactura.before(date1) && !fechaFactura.after(date2) && factura.getTransaccion().equals(tipo)) {
                            total += Double.valueOf(factura.getCosto());
                        }
                    } catch (ParseException e) {
                        System.out.println("Error al parsear la fecha de la factura: " + factura.getFecha());
                    } catch (NumberFormatException e) {
                        System.out.println("Conversión fallida del costo de la factura: " + factura.getCosto());
                    }
                }
            } catch (ParseException e) {
                System.out.println("Error al parsear las fechas de entrada: " + fecha1 + " o " + fecha2);
            }

            txfTotal.setText(String.valueOf(total));
            showTable(new TablaBusquedaFF(fecha1, fecha2, tipo));
            if (!vendidoe) {
                JOptionPane.showMessageDialog(null, "No se encontraron facturas con este tipo de transacción:" + tipo);
                Rbusqueda.removeAll();
                Rbusqueda.repaint();

            }
        } else if (tipo.equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null, "Porfavor seleccionar tipo de transación!!!");
            Rbusqueda.removeAll();
            Rbusqueda.repaint();
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
        dateChooser2 = new com.raven.datechooser.DateChooser();
        contenido = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        jlInfo = new javax.swing.JLabel();
        jlTipo = new javax.swing.JLabel();
        boxTipo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Rbusqueda = new javax.swing.JPanel();
        txfTotal = new javax.swing.JLabel();
        registroValor2 = new javax.swing.JLabel();
        txfDate1 = new javax.swing.JTextField();
        btnMostrarC1 = new javax.swing.JButton();
        btnHoy1 = new javax.swing.JButton();
        txfDate2 = new javax.swing.JTextField();
        btnHoy2 = new javax.swing.JButton();
        btnMostrarC2 = new javax.swing.JButton();

        dateChooser1.setForeground(new java.awt.Color(0, 102, 153));
        dateChooser1.setTextRefernce(txfDate1);

        dateChooser2.setForeground(new java.awt.Color(0, 102, 153));
        dateChooser2.setTextRefernce(txfDate2);

        contenido.setBackground(new java.awt.Color(255, 255, 255));
        contenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/factura.png"))); // NOI18N
        titulo.setText("INFORME DE FACTURAS Y DINERO");
        contenido.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 6, 586, -1));

        jlInfo.setForeground(new java.awt.Color(102, 0, 0));
        jlInfo.setText("<html>Aquí puedes ver el total facturado y recogido por tipo de inmuebles en una fecha específica<br>(selecciona el tipo de inmueble y escribe la fecha en sus campos correspondientes)<html>");
        contenido.add(jlInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));

        jlTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/contrato.png"))); // NOI18N
        jlTipo.setText("Tipo");
        contenido.add(jlTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, -1, -1));

        boxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Arrendado", "Vendido" }));
        contenido.add(boxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, -1, -1));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/entrega.png"))); // NOI18N
        jLabel1.setText("Rango de Fechas");
        contenido.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dinero.png"))); // NOI18N
        jButton1.setText("Ver total recogido");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        contenido.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/factura (1).png"))); // NOI18N
        jButton2.setText("Ver total facturado");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        contenido.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 450, -1, -1));

        Rbusqueda.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout RbusquedaLayout = new javax.swing.GroupLayout(Rbusqueda);
        Rbusqueda.setLayout(RbusquedaLayout);
        RbusquedaLayout.setHorizontalGroup(
            RbusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        RbusquedaLayout.setVerticalGroup(
            RbusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        contenido.add(Rbusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 769, -1));

        txfTotal.setForeground(new java.awt.Color(0, 0, 0));
        txfTotal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido.add(txfTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, 109, 22));

        registroValor2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        registroValor2.setForeground(new java.awt.Color(0, 0, 0));
        registroValor2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registroValor2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dinero.png"))); // NOI18N
        registroValor2.setText("Total");
        contenido.add(registroValor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 87, -1));

        txfDate1.setForeground(new java.awt.Color(0, 0, 0));
        txfDate1.setFocusable(false);
        contenido.add(txfDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 100, -1));

        btnMostrarC1.setText("...");
        btnMostrarC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarC1ActionPerformed(evt);
            }
        });
        contenido.add(btnMostrarC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, -1, -1));

        btnHoy1.setText("Hoy");
        btnHoy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoy1ActionPerformed(evt);
            }
        });
        contenido.add(btnHoy1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, -1, -1));

        txfDate2.setForeground(new java.awt.Color(0, 0, 0));
        txfDate2.setFocusable(false);
        contenido.add(txfDate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 110, -1));

        btnHoy2.setText("Hoy");
        btnHoy2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoy2ActionPerformed(evt);
            }
        });
        contenido.add(btnHoy2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, -1, -1));

        btnMostrarC2.setText("...");
        btnMostrarC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarC2ActionPerformed(evt);
            }
        });
        contenido.add(btnMostrarC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, -1, -1));

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        verTotalRecogido();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnMostrarC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarC1ActionPerformed
        dateChooser1.showPopup();
    }//GEN-LAST:event_btnMostrarC1ActionPerformed

    private void btnHoy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoy1ActionPerformed
        dateChooser1.toDay();
    }//GEN-LAST:event_btnHoy1ActionPerformed

    private void btnHoy2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoy2ActionPerformed
        dateChooser2.toDay();
    }//GEN-LAST:event_btnHoy2ActionPerformed

    private void btnMostrarC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarC2ActionPerformed
        dateChooser2.showPopup();
    }//GEN-LAST:event_btnMostrarC2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        VerfacturadoPorRango();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Rbusqueda;
    private javax.swing.JComboBox<String> boxTipo;
    private javax.swing.JButton btnHoy1;
    private javax.swing.JButton btnHoy2;
    private javax.swing.JButton btnMostrarC1;
    private javax.swing.JButton btnMostrarC2;
    private javax.swing.JPanel contenido;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlInfo;
    private javax.swing.JLabel jlTipo;
    private javax.swing.JLabel registroValor2;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField txfDate1;
    private javax.swing.JTextField txfDate2;
    private javax.swing.JLabel txfTotal;
    // End of variables declaration//GEN-END:variables
}
