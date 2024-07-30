/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package appadmin;

import javax.swing.JOptionPane;

/**
 *
 * @author jeanpi03
 */
public class PanelRegistroI extends javax.swing.JPanel {

    /**
     * Creates new form PanelRegistroI
     */
    public PanelRegistroI() {
        initComponents();
        initStyles();
    }

    private void initStyles() {
        titulo.putClientProperty("FlatLaf.styleClass", "h1");
    }

    private void añadirInmueble() {
        //obteniendo el texto escrito en los fields
        String estado = (String) (boxTipo.getSelectedItem());
        String direccion = txfDireccion.getText();
        String telefono = txfTelefono.getText();
        String areaTotal = txfArea.getText();
        String precio = txfPrecio.getText();
        String barrio = txfBarrio.getText();
        String documento = txfDocumento.getText();
        boolean fieldCorrect = false;

        ///////////////////////////////////////AÑADIDO//////////////////////////////////////////////////////////// 
        //Validacion de campos (n alcobas)
        int nAlcobas = 0;
        boolean numCorrect = true;
        try {
            // Intentar convertir la cadena a un entero
            nAlcobas = Integer.valueOf(txfNalcobas.getText());
        } catch (NumberFormatException e) {
            // Manejar la excepción si la conversión falla
            numCorrect = false;
        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (estado.length() != 0
                && direccion.length() != 0
                && telefono.length() != 0
                && areaTotal.length() != 0
                && precio.length() != 0
                && barrio.length() != 0
                && documento.length() != 0
                && !estado.equals("Seleccionar")
                && nAlcobas != 0) {

            fieldCorrect = true;
        }

        /////////////////////////////////////AÑADIDO y MODIFICADO///////////////////////////////////////////////////     
        if (!fieldCorrect && numCorrect) {
            JOptionPane.showMessageDialog(null, "Es necesario rellenar todos los campos!!!");
        }
        if (!fieldCorrect && !numCorrect) {
            JOptionPane.showMessageDialog(null, "Error: Ingresar número entero valido para cantidad de alcobas");
        }
//////////////////////////////////////////////AÑADIDO//////////////////////////////////////////////////////
        //Validacion de campos(telefono, documento)
        boolean tlfIDcorrect = false;
        if (fieldCorrect) {
            
            boolean tlfLetram = false;
            boolean tlfLetraM = false;
            char tlfm;
            boolean tieneletraM = false;
            for (int i = 0; i < telefono.length(); i++) {
                tlfm = telefono.charAt(i);
                if (tlfm >= 'a' && tlfm <= 'z') {
                    tlfLetram = true;
                }
            }
            
            char tlfM;
            for (int i = 0; i < telefono.length(); i++) {
                tlfM = telefono.charAt(i);
                if (tlfM >= 'A' && tlfM <= 'Z') {
                    tlfLetraM = true;
                }
            }

            if (tlfLetram || tlfLetraM) {
                JOptionPane.showMessageDialog(null, "El numero de telefono no debe contener letras");
            }

            boolean tieneletram = false;
            char lm;
            for (int i = 0; i < documento.length(); i++) {
                lm = documento.charAt(i);
                if (lm >= 'a' && lm <= 'z') {
                    tieneletram = true;
                }
            }
            
            char lM;
            for (int i = 0; i < documento.length(); i++) {
                lM = documento.charAt(i);
                if (lM >= 'A' && lM <= 'Z') {
                    tieneletraM = true;
                }
            }

            if (tieneletram || tieneletraM) {
                JOptionPane.showMessageDialog(null, "El ID no debe contener letras");
            }

            if (!tieneletram && !tieneletraM && !tlfLetram && !tlfLetraM) {
                tlfIDcorrect = true;
            }
        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (fieldCorrect && tlfIDcorrect) {
            Inmueble.saveInmueble(estado, documento, direccion, barrio, telefono, areaTotal, nAlcobas, Double.parseDouble(txfPrecio.getText()));
            txfDireccion.setText("");
            txfTelefono.setText("");
            txfArea.setText("");
            txfPrecio.setText("");
            txfBarrio.setText("");
            txfNalcobas.setText("");
            txfDocumento.setText("");
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
        titulo = new javax.swing.JLabel();
        jldireccion = new javax.swing.JLabel();
        txfDireccion = new javax.swing.JTextField();
        jltelefono = new javax.swing.JLabel();
        txfTelefono = new javax.swing.JTextField();
        jlArea = new javax.swing.JLabel();
        txfArea = new javax.swing.JTextField();
        jlp = new javax.swing.JLabel();
        txfPrecio = new javax.swing.JTextField();
        boxTipo = new javax.swing.JComboBox<>();
        jlTipo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jlp1 = new javax.swing.JLabel();
        txfNalcobas = new javax.swing.JTextField();
        jldireccion1 = new javax.swing.JLabel();
        txfBarrio = new javax.swing.JTextField();
        RjLabelName = new javax.swing.JLabel();
        txfDocumento = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(750, 490));

        contenido.setBackground(new java.awt.Color(255, 255, 255));
        contenido.setPreferredSize(new java.awt.Dimension(750, 490));
        contenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        titulo.setText("AGREGAR NUEVOS INMUEBLES");
        contenido.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 0, 601, 74));

        jldireccion.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jldireccion.setForeground(new java.awt.Color(0, 0, 0));
        jldireccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mapa.png"))); // NOI18N
        jldireccion.setText("Dirección");
        contenido.add(jldireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        txfDireccion.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        txfDireccion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido.add(txfDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 113, -1));

        jltelefono.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jltelefono.setForeground(new java.awt.Color(0, 0, 0));
        jltelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/llamada-telefonica.png"))); // NOI18N
        jltelefono.setText("Teléfono");
        contenido.add(jltelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, -1, -1));

        txfTelefono.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        txfTelefono.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido.add(txfTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 113, -1));

        jlArea.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jlArea.setForeground(new java.awt.Color(0, 0, 0));
        jlArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gobernante.png"))); // NOI18N
        jlArea.setText("Área total");
        contenido.add(jlArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        txfArea.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        txfArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido.add(txfArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 113, -1));

        jlp.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jlp.setForeground(new java.awt.Color(0, 0, 0));
        jlp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/etiqueta-del-precio.png"))); // NOI18N
        jlp.setText("Precio");
        contenido.add(jlp, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, -1, -1));

        txfPrecio.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        txfPrecio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido.add(txfPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 110, -1));

        boxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Desocupado", "Arrendado", "Vendido" }));
        contenido.add(boxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 130, -1));

        jlTipo.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jlTipo.setForeground(new java.awt.Color(0, 0, 0));
        jlTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/contrato.png"))); // NOI18N
        jlTipo.setText("Estado");
        contenido.add(jlTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 88, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/añadircasa.png"))); // NOI18N
        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        contenido.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 424, 183, -1));

        jlp1.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jlp1.setForeground(new java.awt.Color(0, 0, 0));
        jlp1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/aislamiento.png"))); // NOI18N
        jlp1.setText("Número de alcobas");
        contenido.add(jlp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, -1, -1));

        txfNalcobas.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        txfNalcobas.setText("0");
        txfNalcobas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido.add(txfNalcobas, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 40, -1));

        jldireccion1.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jldireccion1.setForeground(new java.awt.Color(0, 0, 0));
        jldireccion1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pueblo.png"))); // NOI18N
        jldireccion1.setText("Barrio");
        contenido.add(jldireccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        txfBarrio.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        txfBarrio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido.add(txfBarrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 113, -1));

        RjLabelName.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        RjLabelName.setForeground(new java.awt.Color(0, 0, 0));
        RjLabelName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tarjeta-de-identificacion.png"))); // NOI18N
        RjLabelName.setText("Documento");
        contenido.add(RjLabelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, -1));

        txfDocumento.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        txfDocumento.setForeground(new java.awt.Color(0, 0, 0));
        txfDocumento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contenido.add(txfDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 96, 110, 20));

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
        añadirInmueble();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RjLabelName;
    private javax.swing.JComboBox<String> boxTipo;
    private javax.swing.JPanel contenido;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jlArea;
    private javax.swing.JLabel jlTipo;
    private javax.swing.JLabel jldireccion;
    private javax.swing.JLabel jldireccion1;
    private javax.swing.JLabel jlp;
    private javax.swing.JLabel jlp1;
    private javax.swing.JLabel jltelefono;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField txfArea;
    private javax.swing.JTextField txfBarrio;
    private javax.swing.JTextField txfDireccion;
    private javax.swing.JTextField txfDocumento;
    private javax.swing.JTextField txfNalcobas;
    private javax.swing.JTextField txfPrecio;
    private javax.swing.JTextField txfTelefono;
    // End of variables declaration//GEN-END:variables
}
