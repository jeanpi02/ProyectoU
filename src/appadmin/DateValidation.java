/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appadmin;

import appasesor.RegistroFacturas;

/**
 *
 * @author jeanpi03
 */
public class DateValidation {

    private boolean isValid;
    private String message;

    public DateValidation(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }

    public DateValidation verificarFecha(String dia, String mes, String año) {
        boolean fechaCorrecta = false;
        String msg = "Fecha invalida:\n";
        String fecha = dia + "/" + mes + "/" + año;

        System.out.println("Fecha ingresada: " + fecha);

        // Eliminar espacios adicionales
        fecha = fecha.trim();

        // Comprobar formato dd/MM/yyyy
        int diaInt, mesInt, añoInt;
        try {
            diaInt = Integer.parseInt(dia);
            mesInt = Integer.parseInt(mes);
            añoInt = Integer.parseInt(año);
        } catch (NumberFormatException e) {
            msg += "*Los campos de fecha deben ser números válidos\n";
            return new DateValidation(fechaCorrecta, msg);
        }

        // Validar el rango del año
        if (añoInt < 1900 || añoInt > java.time.LocalDate.now().getYear()) {
            msg += "*El año debe estar entre 1900 y el año actual\n";
        }

        boolean mesValid = true;
        // Validar el rango del mes
        if (mesInt < 1 || mesInt > 12) {
            msg += "*El mes debe estar entre 1 y 12\n";
            mesValid = false;
        }

        // Validar el rango del día
        if (mesValid) {
            if (diaInt < 1 || diaInt > diasEnMes(mesInt)) {
                msg += "*El día debe ser válido para el mes y año dados\n";
            }
        }

        // Si todas las comprobaciones son válidas
        if (msg.equals("Fecha invalida:\n")) {
            fechaCorrecta = true;
        }

        return new DateValidation(fechaCorrecta, msg);
    }
    // Método para obtener el número de días en un mes determinado de un año determinado

    private int diasEnMes(int mes) {
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return diasPorMes[mes - 1];
    }

}
