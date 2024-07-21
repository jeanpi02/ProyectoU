package appadmin;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author jeanpi03
 */
public class Inmueble {

    String estado;
    String direccion;
    String telefono;
    String areaTotal;
    String documento;
    String barrio;
    double precio;
    int nAlcobas;

    public Inmueble(String estado, String documento, String direccion, String barrio, String telefono, String areaTotal, int nAlcobas, double precio) {
        this.estado = estado;
        this.direccion = direccion;
        this.telefono = telefono;
        this.areaTotal = areaTotal;
        this.precio = precio;
        this.nAlcobas = nAlcobas;
        this.documento = documento;
        this.barrio = barrio;
    }

    public String getEstado() {
        return estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getAreaTotal() {
        return areaTotal;
    }

    public double getPrecio() {
        return precio;
    }

    public int getnAlcobas() {
        return nAlcobas;
    }

    public String getDocumento() {
        return documento;
    }

    public String getBarrio() {
        return barrio;
    }

    public static Inmueble[] loadInmuebles() {
        File file = new File("RegistroI.txt");
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
        Inmueble[] inmuebles = new Inmueble[count];
        int index = 0;

        // Segunda pasada: leer las líneas y llenar el array
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 8) {
                        inmuebles[index++] = new Inmueble(parts[0],parts[1],parts[2],parts[3],parts[4]
                        ,parts[5],Integer.parseInt(parts[6]),Double.parseDouble(parts[7]));

                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return inmuebles;
    }

    public static void saveInmueble(String estado, String documento, String direccion, String barrio, String telefono, String areaTotal, int nAlcobas, double precio) {
        try {

            File file = new File("RegistroI.txt");

            //Si no Existe el fichero lo crea
            if (!file.exists()) {

                file.createNewFile();

            }

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));

            String me = estado + ";"+ documento +";"+ direccion + ";"+ barrio +";" + telefono + ";" + areaTotal + ";" + nAlcobas + ";" + precio;

            bw.write(me + "\r\n");

            System.out.println("Registro realizado");
            JOptionPane.showMessageDialog(null, "¡Inmueble registrado con éxito!");

            bw.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

}
