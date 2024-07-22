/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appadmin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author jeanpi03
 */
public class Factura {

    private String transaccion;
    private String name;
    private String id;
    private String direccion;
    private String fecha;
    private String costo;

    public Factura(String transaccion, String name, String id, String direccion, String fecha, String costo) {
        this.transaccion = transaccion;
        this.name = name;
        this.id = id;
        this.direccion = direccion;
        this.fecha = fecha;
        this.costo = costo;
    }

    public String getTransaccion() {
        return transaccion;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCosto() {
        return costo;
    }

    public static Factura[] loadFacturas() {
        File file = new File("RegistroF.txt");
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
        Factura[] facturas = new Factura[count];
        int index = 0;

        // Segunda pasada: leer las líneas y llenar el array
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 6) {
                        facturas[index++] = new Factura(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);

                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return facturas;
    }

    public static void saveFactura(String transaccion, String name, String id, String direccion, String fecha, String costo) {
        try {

            File file = new File("RegistroF.txt");

            //Si no Existe el fichero lo crea
            if (!file.exists()) {

                file.createNewFile();

            }

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));

            String me = transaccion + ";" + name + ";" + id + ";" + direccion + ";" + fecha +";"+ costo;

            bw.write(me + "\r\n");

            System.out.println("Registro realizado");
            JOptionPane.showMessageDialog(null, "¡Factura registrada con éxito!");

            bw.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

}
