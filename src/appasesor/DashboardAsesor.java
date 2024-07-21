/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package appasesor;

import appadmin.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jeanpi03
 */
public class DashboardAsesor extends javax.swing.JFrame {

    /**
     * Creates new form DashboardAmin
     */
    public DashboardAsesor(String user) {
        initComponents();
        initStyles();
        usrActual.setText(user);

    }

    private void initStyles() {
        bienvenida.putClientProperty("FlatLaf.styleClass", "h1");
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showJpanel(JPanel p) {

        p.setSize(750, 490);
        p.setLocation(0, 0);

        contenido.removeAll();
        contenido.add(p, BorderLayout.CENTER);
        contenido.revalidate();
        contenido.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        btnPrincipal = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnBusquedaI = new javax.swing.JButton();
        btnBusquedaF = new javax.swing.JButton();
        btnRegistrarC = new javax.swing.JButton();
        header = new javax.swing.JPanel();
        bienvenida = new javax.swing.JLabel();
        usrActual = new javax.swing.JLabel();
        btnCs = new javax.swing.JButton();
        contenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        background.setBackground(new java.awt.Color(255, 255, 255));

        menu.setBackground(new java.awt.Color(13, 71, 161));
        menu.setForeground(new java.awt.Color(240, 240, 240));
        menu.setPreferredSize(new java.awt.Dimension(270, 640));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/casa.png"))); // NOI18N

        btnPrincipal.setBackground(new java.awt.Color(0, 51, 153));
        btnPrincipal.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        btnPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        btnPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/casa2.png"))); // NOI18N
        btnPrincipal.setText("Principal");
        btnPrincipal.setBorder(null);
        btnPrincipal.setBorderPainted(false);
        btnPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnRegistrar.setBackground(new java.awt.Color(0, 51, 153));
        btnRegistrar.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/debito.png"))); // NOI18N
        btnRegistrar.setText("Registrar Facturas");
        btnRegistrar.setBorder(null);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnBusquedaI.setBackground(new java.awt.Color(0, 51, 153));
        btnBusquedaI.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        btnBusquedaI.setForeground(new java.awt.Color(255, 255, 255));
        btnBusquedaI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/busqueda-de-casa.png"))); // NOI18N
        btnBusquedaI.setText("Busqueda inmuebles");
        btnBusquedaI.setBorder(null);
        btnBusquedaI.setBorderPainted(false);
        btnBusquedaI.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusquedaI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaIActionPerformed(evt);
            }
        });

        btnBusquedaF.setBackground(new java.awt.Color(0, 51, 153));
        btnBusquedaF.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        btnBusquedaF.setForeground(new java.awt.Color(255, 255, 255));
        btnBusquedaF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cuenta (1).png"))); // NOI18N
        btnBusquedaF.setText("Informe de facturas");
        btnBusquedaF.setBorder(null);
        btnBusquedaF.setBorderPainted(false);
        btnBusquedaF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusquedaF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaFActionPerformed(evt);
            }
        });

        btnRegistrarC.setBackground(new java.awt.Color(0, 51, 153));
        btnRegistrarC.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        btnRegistrarC.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/opinion-del-cliente.png"))); // NOI18N
        btnRegistrarC.setText("Registrar Clientes");
        btnRegistrarC.setBorder(null);
        btnRegistrarC.setBorderPainted(false);
        btnRegistrarC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBusquedaI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBusquedaF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menuLayout.createSequentialGroup()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(logo))
                    .addComponent(btnRegistrarC, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        menuLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnRegistrar, btnRegistrarC});

        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(logo)
                .addGap(18, 18, 18)
                .addComponent(btnPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnRegistrarC, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnBusquedaI, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnBusquedaF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        menuLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBusquedaF, btnBusquedaI, btnPrincipal, btnRegistrar, btnRegistrarC});

        header.setBackground(new java.awt.Color(25, 118, 210));
        header.setPreferredSize(new java.awt.Dimension(750, 150));

        bienvenida.setForeground(new java.awt.Color(255, 255, 255));
        bienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bienvenida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hola.png"))); // NOI18N
        bienvenida.setText("BIENVENIDO A TU PORTAL DE HOGAR IDEAL INMUEBLES");

        usrActual.setForeground(new java.awt.Color(255, 255, 255));
        usrActual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/avatar.png"))); // NOI18N
        usrActual.setText("User");

        btnCs.setBackground(new java.awt.Color(51, 51, 255));
        btnCs.setForeground(new java.awt.Color(255, 255, 255));
        btnCs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar-sesion.png"))); // NOI18N
        btnCs.setText("Cerrar sesión");
        btnCs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(bienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(usrActual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCs))))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usrActual)
                    .addComponent(btnCs))
                .addGap(5, 5, 5)
                .addComponent(bienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        contenido.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contenidoLayout = new javax.swing.GroupLayout(contenido);
        contenido.setLayout(contenidoLayout);
        contenidoLayout.setHorizontalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        contenidoLayout.setVerticalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        showJpanel(new RegistroFacturas());
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBusquedaIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaIActionPerformed
        showJpanel(new PanelListarInmuebles());
    }//GEN-LAST:event_btnBusquedaIActionPerformed

    private void btnBusquedaFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaFActionPerformed
        showJpanel(new PanelTotaFacturado());
    }//GEN-LAST:event_btnBusquedaFActionPerformed

    private void btnRegistrarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarCActionPerformed
        showJpanel(new PanelRegistroC());
    }//GEN-LAST:event_btnRegistrarCActionPerformed

    private void btnCsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCsActionPerformed
        this.dispose();
        new Login().setVisible(true);

    }//GEN-LAST:event_btnCsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel bienvenida;
    private javax.swing.JButton btnBusquedaF;
    private javax.swing.JButton btnBusquedaI;
    private javax.swing.JButton btnCs;
    private javax.swing.JButton btnPrincipal;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegistrarC;
    private javax.swing.JPanel contenido;
    private javax.swing.JPanel header;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel usrActual;
    // End of variables declaration//GEN-END:variables
}
