/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package appadmin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jeanpi03
 */
public class TablaBusquedaFF extends javax.swing.JPanel {

    /**
     * Creates new form TablaBusquedaFF
     */
    public TablaBusquedaFF(int i) {
        initComponents();
        initTable(i);
    }

    public TablaBusquedaFF(long id) {
        initComponents();
        initTableId(id);
    }

    public TablaBusquedaFF(String fecha) {
        initComponents();
        initTableFecha(fecha);
    }

    public TablaBusquedaFF(String fecha1, String fecha2, String tipo) {
        initComponents();
        initTableFechaR(fecha1, fecha2, tipo);
    }

    boolean foundid;

    private void initTable(int i) {
        model = (DefaultTableModel) this.jTable1.getModel();
        Factura[] facturas = Factura.loadFacturas();
        String tipo = "";
        switch (i) {
            case 1: {
                tipo = "Arrendado";
                break;
            }
            case 2: {
                tipo = "Vendido";
                break;
            }
        }

        for (Factura factura : facturas) {
            if (factura.getTransaccion().equals(tipo)) {
                model.addRow(new Object[]{
                    factura.getTransaccion(),
                    factura.getName(),
                    factura.getId(),
                    factura.getDireccion(),
                    factura.getFecha(),
                    "$" + factura.getCosto()});

            }
        }
    }

    private void initTableId(long id) {
        model = (DefaultTableModel) this.jTable1.getModel();
        Factura[] facturas = Factura.loadFacturas();
        for (Factura factura : facturas) {
            if (id == factura.getId()) {
                foundid = true;
                model.addRow(new Object[]{
                    factura.getTransaccion(),
                    factura.getName(),
                    factura.getId(),
                    factura.getDireccion(),
                    factura.getFecha(),
                    "$" + factura.getCosto()});
            }
        }

        if (!foundid) {
            JOptionPane.showMessageDialog(null, "No se encontraron facturas de la persona con id: " + id);
        }
    }

    DefaultTableModel model;
    boolean foundfecha = false;

    private void initTableFecha(String fecha) {
        model = (DefaultTableModel) this.jTable1.getModel();
        System.out.println("fecha ingresada para buscar: " + fecha);
        Factura[] facturas = Factura.loadFacturas();
        for (Factura factura : facturas) {
            if (fecha.equals(factura.getFecha())) {
                foundfecha = true;
                model.addRow(new Object[]{
                    factura.getTransaccion(),
                    factura.getName(),
                    factura.getId(),
                    factura.getDireccion(),
                    factura.getFecha(),
                    "$" + factura.getCosto()});
            }
        }

        if (!foundfecha) {
            JOptionPane.showMessageDialog(null, "No se encontraron facturas de la fecha ingresada!!");
        }
    }

    private void initTableFechaR(String fecha1, String fecha2, String tipo) {
        model = (DefaultTableModel) this.jTable1.getModel();
        Factura[] facturas = Factura.loadFacturas();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date date1 = dateFormat.parse(fecha1);
            Date date2 = dateFormat.parse(fecha2);

            for (Factura factura : facturas) {
                try {
                    Date fechaFactura = dateFormat.parse(factura.getFecha());
                    if (!fechaFactura.before(date1) && !fechaFactura.after(date2) && tipo.equals(factura.getTransaccion())) {
                        model.addRow(new Object[]{
                            factura.getTransaccion(),
                            factura.getName(),
                            factura.getId(),
                            factura.getDireccion(),
                            factura.getFecha(),
                            "$" + factura.getCosto()});
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaccion", "Nombre", "Documento", "Inmuebles", "Fecha", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
