package appadmin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import appadmin.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author jeanpi03
 */
public class Cliente {
    
        private String id;
        private String name;
        private String correo;
        private String direccion;
        private String telefono;

    public Cliente(String id, String name, String correo, String direccion, String telefono) {
        this.id = id;
        this.name = name;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
        
    
    public static Cliente[] loadClientes() {
        File file = new File("RegistroC.txt");
        int count = 0;

        // contar las líneas para determinar el tamaño del array
        if (file.exists()) {
            try {
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
        Cliente[] clientes = new Cliente[count];
        int index = 0;

        // Segunda pasada: leer las líneas y llenar el array
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 5) {
                        clientes[index++] = new Cliente(parts[0],parts[1],parts[2],parts[3],parts[4]);

                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return clientes;
    }
    
    
    public static void saveCliente(String id, String name, String correo, String direccion, String telefono) {
        try {

            File file = new File("RegistroC.txt");

            //Si no Existe el fichero lo crea
            if (!file.exists()) {

                file.createNewFile();

            }

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));

            String me = id + ";"+ name +";"+ correo + ";"+ direccion +";" + telefono;

            bw.write(me + "\r\n");

            System.out.println("Registro realizado");
            JOptionPane.showMessageDialog(null, "¡Cliente registrado con éxito!");

            bw.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

}
        

