/*
 Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para
interactuar con el usuario. En esta clase se muestra el menú de opciones con las operaciones
disponibles que podrá realizar el usuario.

1) Crear base de datos Librería
2) Crear unidad de persistencia
3) Crear entidades previamente mencionadas (excepto Préstamo)
4) Generar las tablas con JPA
5) Crear servicios previamente mencionados.
6) Crear los métodos para persistir entidades en la base de datos librería
7) Crear los métodos para dar de alta/bajo o editar dichas entidades.
8) Búsqueda de un Autor por nombre.
9) Búsqueda de un libro por ISBN.
10) Búsqueda de un libro por Título.
11) Búsqueda de un libro/s por nombre de Autor.
12) Búsqueda de un libro/s por nombre de Editorial.
13) Agregar las siguientes validaciones a todas las funcionalidades de la aplicación:
• Validar campos obligatorios.
• No ingresar datos duplicados.
 */
package com.mycompany.services;

import java.util.Scanner;

/**
 *
 * @author crist
 */
public class Main {

    static Services servicio = new Services();
    static Scanner leer = new Scanner(System.in).useDelimiter("\n");   
    static int i = 0;

    public static void main(String[] args) {

        System.out.println("BIENVENIDO A LA LIBRERÍA EGG");

        do {
            System.out.println("Que desea realizar?");
            System.out.println("1. Buscar libro");
            System.out.println("2. Buscar por autor");
            System.out.println("3. Buscar por editorial");
            System.out.println("4. Devolver un libro");
            System.out.println("5. Opciones Avanzadas");
            System.out.println("6. Salir");
            switch (validarI(i)) {
                case 1:
                    servicio.buscarLibro();
                    break;
                    
                case 2:
                    servicio.buscarAutor();
                    break;
                    
                case 3:
                    servicio.buscarEditorial();
                    break;

                case 4:
                    servicio.devolverLibro();
                    break;
                    
                case 5:

                    do {
                        System.out.println("Que desea realizar?");
                        System.out.println("1. Ingresar un libro nuevo");
                        System.out.println("2. Modificar un libro");
                        System.out.println("3. Eliminar un libro");
                        System.out.println("4. Crear un autor");
                        System.out.println("5. Modificar un autor");
                        System.out.println("6. Eliminar un autor");
                        System.out.println("7. Crear una editorial");
                        System.out.println("8. Modificar una editorial");
                        System.out.println("9. Eliminar una editorial");
                        System.out.println("0. Menú anterior");
                        switch (validarI(i)) {
                            case 1:
                                servicio.añadirLibro();
                                break;

                            case 2:
                                servicio.modificarLibro();
                                break;

                            case 3:
                                servicio.eliminarLibro();
                                break;

                            case 4:
                                servicio.crearAutor();
                                break;

                            case 5:
                                servicio.modificarAutor();
                                break;

                            case 6:
                                servicio.eliminarAutor();
                                break;

                            case 7:
                                servicio.crearEditorial();
                                break;
                                
                            case 8:
                                servicio.modificarEditorial();
                                break;
                                
                            case 9:
                                servicio.eliminarEditorial();
                                break;
                            default:
                                System.out.println("Solo puedes ingresar numeros del 1 al 9");
                        }
                    } while (i != 0);
                    break;
                case 6:
                    System.out.println("GRACIAS POR PREFERIRNOS");
                        break;
                default:
                    System.out.println("Solo puedes ingresar numeros del 1 al 6");
            }

        } while (i == 6);
    }

    public static int validarI(int i) {
        while (true) {
            try {
                i = leer.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Solo Puedes ingresar Número, NO LETRAS");
                //leer.next();
            }
        }
        return i;
    }
}
