/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package appadmin;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author jeanpi03
 */
public class PanelRegistroU extends javax.swing.JPanel {

    /**
     * Creates new form PanelRegistroU
     */
    public PanelRegistroU() {
        initComponents();
        initStyles();
    }

    private void initStyles() {
        Rlabel.putClientProperty("FlatLaf.styleClass", "h1");
    }

    private PasswordValidation verificarContraseña(String contraseña) {
        boolean passCorrect = false;
        String msg = "Contraseña invalida:\n";

        //comprobar 8 carácteres
        boolean tiene8c = false;
        if (contraseña.length() >= 8) {
            tiene8c = true;
            System.out.println("Tiene 8 o más caracteres");
        } else {
            msg += "*No tiene 8 caracteres\n";
        }

        //Comprobar minúscula
        boolean tieneMinus = false;
        char cm;
        //convertir contraseña a minúsculas
//        String contraseñaMinus[] = new String[contraseñaA.length];
//        for (int i = 0; i < contraseña.length(); i++) {
//            contraseñaMinus[i] = String.valueOf(contraseña.charAt(i)).toLowerCase();
//
//        }
        //comprobrar minúscula
        for (int i = 0; i < contraseña.length(); i++) {
            cm = contraseña.charAt(i);
            if (cm >= 'a' && cm <= 'z') {
                tieneMinus = true;
            }
        }
        if (tieneMinus) {
            System.out.println("Tiene minus");
        } else {
            msg += "*No tiene letras minúsculas\n";
        }

        //Comprobar mayúscula
        String contraseñaMayus[] = new String[contraseña.length()];
        boolean tieneMayus = false;
        char cM;

        //convertir contraseña a mayúsculas
//        for (int i = 0; i < contraseñaMayus.length; i++) {
//            contraseñaMayus[i] = String.valueOf(contraseña.charAt(i)).toUpperCase();
//        }
        //comprobar Mayuscula
        for (int i = 0; i < contraseña.length(); i++) {
            cM = contraseña.charAt(i);
            if (cM >= 'A' && cM <= 'Z') {
                tieneMayus = true;
            }
        }
        if (tieneMayus) {
            System.out.println("Tiene mayus");
        } else {
            msg += "*No tiene letras mayúsculas\n";
        }

        //Comprobar dígito
        boolean tieneDigito = false;
        char c;
        //Comprobar dígito
        for (int i = 0; i < contraseña.length(); i++) {
            c = contraseña.charAt(i);
            if (c >= '0' && c <= '9') {
                tieneDigito = true;
                break;
            }
        }
        if (tieneDigito) {
            System.out.println("Tiene Dígito");
        } else {
            msg += "*No tiene dígitos";
        }

//        String digitos[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
//        boolean tieneDigito = false;
//        for (int i = 0; i < contraseñaA.length; i++) {
//            for (int j = 0; j < digitos.length; j++) {
//                if (contraseñaA[i].equals(digitos[j])) {
//                    tieneDigito = true;
//                }
//            }
//
//        }
        if (tiene8c && tieneDigito && tieneMayus && tieneMinus) {
            passCorrect = true;
        }

        return new PasswordValidation(passCorrect, msg);
    }

    private void registrarUsuarios() {
        //obtener el texto escrito en los fields
        String name = RnameTxt.getText();
        String username = RuserTxt.getText();
        String password = new String(RpassTxt.getPassword());
        String role = (String) RRroleBox.getSelectedItem();
        boolean correctPass = false;
        boolean fieldCorrect = false;
        boolean userExits = false;
        //Verificar si los campos están llenos
        if (name.length() != 0
                && username.length() != 0
                && password.length() != 0
                && !role.equals("Seleccionar Rol")) {
            fieldCorrect = true;
        }

        //Comprobar si el usuario existe
        Usuario[] usuarios = Usuario.loadUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                userExits = true;
            }
        }

        if (!fieldCorrect) {
            JOptionPane.showMessageDialog(null, "Es necesario rellenar todos los campos!!!");
        }
        if (userExits) {
            JOptionPane.showMessageDialog(null, "El usuario [" + username + "] ya está registrado.\n"
                    + "Por favor ingresar otro nombre de usuario");
        }
        //Verificar contraseña
        PasswordValidation pv = verificarContraseña(password);

        if (fieldCorrect) {
            correctPass = pv.isValid;
        }
        if (fieldCorrect && !correctPass) {
            JOptionPane.showMessageDialog(null, pv.msg);
            password = "";
        }

//////////////////////////////////////////AÑADIDO//////////////////////////////////////////////////////////
        //Validacion de campos (nombre, ID)
        boolean nameIDcorrect = false;
        boolean tieneletram = false;
        char lm;

        if (fieldCorrect) {

            for (int i = 0; i < username.length(); i++) {
                lm = username.charAt(i);
                if (lm >= 'a' && lm <= 'z') {
                    tieneletram = true;
                }
            }
            boolean tieneletraM = false;
            char lM;
            for (int i = 0; i < username.length(); i++) {
                lM = username.charAt(i);
                if (lM >= 'A' && lM <= 'Z') {
                    tieneletraM = true;
                }
            }

            if (tieneletram || tieneletraM) {
                JOptionPane.showMessageDialog(null, "El ID no debe contener letras");
            }

            boolean tieneNumero = false;
            char c;
            //Comprobar dígito
            for (int i = 0; i < name.length(); i++) {
                c = name.charAt(i);
                if (c >= '0' && c <= '9') {
                    tieneNumero = true;
                }
            }

            if (tieneNumero) {
                JOptionPane.showMessageDialog(null, "El nombre no debe contener numeros");
            }

            if (!tieneletram && !tieneletraM && !tieneNumero) {
                nameIDcorrect = true;
            }
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (fieldCorrect && correctPass && !userExits && nameIDcorrect) {

            Usuario.saveUsers(name, username, password, role);

            RnameTxt.setText("");

            RpassTxt.setText("");

            RuserTxt.setText("");

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

        content = new javax.swing.JPanel();
        RjLabelName = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        Rlabel = new javax.swing.JLabel();
        RnameTxt = new javax.swing.JTextField();
        RRroleBox = new javax.swing.JComboBox<>();
        RuserTxt = new javax.swing.JTextField();
        RjLabelPass = new javax.swing.JLabel();
        RpassTxt = new javax.swing.JPasswordField();
        RjLabelName1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(750, 490));

        content.setBackground(new java.awt.Color(255, 255, 255));

        RjLabelName.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        RjLabelName.setForeground(new java.awt.Color(0, 0, 0));
        RjLabelName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tarjeta-de-identificacion.png"))); // NOI18N
        RjLabelName.setText("Documento");

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user-interface.png"))); // NOI18N

        Rlabel.setText("REGISTRO DE USUARIOS");

        RnameTxt.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        RnameTxt.setForeground(new java.awt.Color(0, 0, 0));
        RnameTxt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        RRroleBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Rol", "Asesor", "Administrador" }));

        RuserTxt.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        RuserTxt.setForeground(new java.awt.Color(0, 0, 0));
        RuserTxt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        RjLabelPass.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        RjLabelPass.setForeground(new java.awt.Color(0, 0, 0));
        RjLabelPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/candado.png"))); // NOI18N
        RjLabelPass.setText("Contraseña");

        RpassTxt.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        RpassTxt.setForeground(new java.awt.Color(0, 0, 0));
        RpassTxt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        RjLabelName1.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        RjLabelName1.setForeground(new java.awt.Color(0, 0, 0));
        RjLabelName1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
        RjLabelName1.setText("Nombre");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registro.png"))); // NOI18N
        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RjLabelName)
                            .addComponent(RuserTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RRroleBox, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(RjLabelName1))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(RjLabelPass))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(RpassTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(icon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Rlabel))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(RnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addGap(297, 297, 297))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(icon))
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(Rlabel)))
                .addGap(42, 42, 42)
                .addComponent(RRroleBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RjLabelName)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(RuserTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addComponent(RjLabelName1)
                .addGap(6, 6, 6)
                .addComponent(RnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(RjLabelPass)
                .addGap(6, 6, 6)
                .addComponent(RpassTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jButton1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        registrarUsuarios();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> RRroleBox;
    private javax.swing.JLabel RjLabelName;
    private javax.swing.JLabel RjLabelName1;
    private javax.swing.JLabel RjLabelPass;
    private javax.swing.JLabel Rlabel;
    private javax.swing.JTextField RnameTxt;
    private javax.swing.JPasswordField RpassTxt;
    private javax.swing.JTextField RuserTxt;
    private javax.swing.JPanel content;
    private javax.swing.JLabel icon;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}

class PasswordValidation {

    public boolean isValid;
    public String msg = "";

    public PasswordValidation(boolean isValid, String msg) {
        this.isValid = isValid;
        this.msg = msg;
    }
}
