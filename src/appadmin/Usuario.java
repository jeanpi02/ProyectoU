package appadmin;

import java.io.*;
import javax.swing.JOptionPane;

public class Usuario {

    private String name;
    private String username;
    private String password;
    private String role;

    public Usuario(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public static Usuario[] loadUsuarios() {
        File file = new File("Registrou.txt");
        int count = 0;

        // contar las líneas para determinar el tamaño del array
        if (file.exists()) {
            try  {
                BufferedReader br = new BufferedReader(new FileReader(file));
                while (br.readLine() != null) {
                    count++;
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Crear el array de usuarios con el tamaño adecuado
        Usuario[] usuarios = new Usuario[count];
        int index = 0;

        // Segunda pasada: leer las líneas y llenar el array
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 4) {
                        usuarios[index++] = new Usuario(parts[0], parts[1], parts[2], parts[3]);
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return usuarios;
    }

    public static void saveUsers(String name, String username, String password, String role) {
        try {

            File reg = new File("Registrou.txt");

            //Si no Existe el fichero lo crea
            if (!reg.exists()) {

                reg.createNewFile();

            }

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(reg, true), "utf-8"));

            String me = name + ";" + username + ";" + password + ";" + role;

            bw.write(me + "\r\n");

            System.out.println("Registro realizado");
            JOptionPane.showMessageDialog(null, "¡Usuario registrado con éxito!");

            bw.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }
}

//    public static void saveUsuarios(Usuario[] usuarios) {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt"))) {
//            for (Usuario usuario : usuarios) {
//                bw.write(usuario.getName()+ "," + usuario.getUsername() + "," + usuario.getPassword() + "," + usuario.getRole()) ;
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

