package com.mycompany.proyectohawkins; 
import java.util.Scanner;
import java.util.InputMismatchException;
        
public class ProyectoHawkins{
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
       
     final double MAX_ENERGIA = 200.0;   
     final double LIMITE_RIESGO = 150.0;
     String operadorActual;
     double sensorMedido;
     int codigoRiesgo;
     int porcentajeEstado;
     boolean portalAbierto;
     int opcionMenu;
        
    System.out.println("Nombre operativo: ");
        operadorActual = sc.nextLine();
            mostrarEncabezado(operadorActual);

    System.out.println("Elige una opcion: ");
        opcionMenu = sc.nextInt();
        
    
        
    switch(opcionMenu){
        case 1:
        sensorMedido = leerLecturaSegura(teclado, "Introduce lectura µT: "); 
        codigoRiesgo = evaluarNivelRiesgo(sensorMedido);
        porcentajeEstado = calcularPorcentajeTruncado(sensorMedido, MAX_ENERGIA); 
        portalAbierto = ejecutarProtocoloAccion(codigoRiesgo);
            System.out.println("Capacidad: " + MAX_ENERGIA + "%");
            System.out.println("El portal esta avierto: " + portalAbierto);
        
            break;
            
        case 2:
        simularMuestreoContinuo(teclado, LIMITE_RIESGO);
            break;
            
        case 0:
            System.out.println("MUchas gracias por usar SISTEMA HAWKINS");    
                break;
        
        default:
            System.out.println("Opción no válida");
    
    
    }  
        
        
        
        
    }
    public static void mostrarEncabezado(String operador) {
        /* % = "obligatorio" en Printf.  -35 = reserva 35 espacios hacia la derecha 
            existen los tipos s = string f = float c = char b = boolean y otros.
        \t es una tabulacion, el %t es un formato de variable para trabajar con fechas ( %tF muestra una fecha en formato YYYY-MM-DD).
        \n es un salto de linea pero se recomienda mejor usar %n dentro del formato Printf
        */
        System.out.printf("Hola, %s%n", operador);
        System.out.printf("\t--- SISTEMA HAWKINS ---%n");
        System.out.printf("%s %-35s \n", "\t1.", "Inspeccionar Sensor Individual");
        System.out.printf("%s %-35s \n", "\t2.", "Iniciar Muestreo Continuo");
        System.out.printf("%s %-35s \n", "\t0.", "Salir de la Consola");
        System.out.println("");
    }
    
    
    public static double leerLecturaSegura(Scanner teclado, String mensaje){
        double lectura = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(mensaje);
                lectura = sc.nexdouble();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Introduce un número decimal.");
                teclado.next(); 
            }
        }
        return lectura;
    }
    
    
    public static int evaluarNivelRiesgo(double microTeslas){
        String etiqueta = (microTeslas > 150.0) ? "Crítico" : (microTeslas >= 50.0 ? "Alerta" : "Seguro");
        if (microTeslas < 50.0) {
            return 1;
        } else if (microTeslas <= 150.0) {
            return 2;
        } else {
            return 3;
        }
    } 
    
    
    public static int calcularPorcentajeTruncado(double lectura , double maxEnergia){
        return (int) ((lectura / maxEnergia) * 100);
    }
    
    
    public static boolean ejecutarProtocoloAccion(int codigoRiesgo){
        switch (codigoRiesgo) {
            case 1:
                System.out.println("Modo Estable");
                return false;
            case 2:
                System.out.println("Anomalía Detectada");
                return false;
            case 3:
                System.out.println("¡ALERTA MÁXIMA!");
                return true;
            default:
                System.out.println("Código no válido");
                return false;
        }
    }
    
    
    public static void simularMuestreoContinuo(Scanner teclado, double limite){
    double suma = 0;
        int contador = 0;
        while (true) {
            double lectura = leerLecturaSegura(teclado, "Lectura µT (negativo=salir, 0=omitir): ");
            if (lectura < 0) {
                break;
            }
            if (lectura == 0) {
                continue;
            }
            if (lectura > limite) {
                System.out.println("ADVERTENCIA: Umbral superado.");
            }
            suma += lectura;
            contador++;
        }
        if (contador > 0) {
            System.out.printf("Media: %.2f µT\n", (suma / contador));
        }
    }
    
}

  