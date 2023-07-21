/*
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

import com.mycompany.entidades.Autor;
import com.mycompany.entidades.Editorial;
import com.mycompany.entidades.Libro;
import java.util.Scanner;

/**
 *
 * @author crist
 */
public class Services {

    private static int i;
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private AutorServices autorSrv = new AutorServices();
    private EditorialServices editorialSrv = new EditorialServices();
    private LibroServices libroSrv = new LibroServices();
    private Libro libro;
    //private Autor autor;
    //private Editorial editorial;

    public void añadirLibro() {
        libroSrv.crearLibro();
        System.out.println("");
    }

    public void modificarLibro() {
        libroSrv.modificarLibro();
        System.out.println("");

    }

    public void buscarLibro() {
        System.out.println("Desea un listado de los libros? (1)");
        System.out.println("Desea un libro en específico (2)");
        if (validarI() == 1) {
            libroSrv.mostrarLista();
            System.out.println("Desea solicitar algún libro? S/N");
            if ("S".equalsIgnoreCase(leer.next())) {
                libroSrv.solicitarLibro(libroSrv.buscarLibro());
            }
        } else {
            libro = libroSrv.buscarLibro();
            System.out.println("");
            if (libro != null) {
                System.out.println("Desea solicitar el libro? S/N");
                if ("S".equalsIgnoreCase(leer.next())) {
                    libroSrv.solicitarLibro(libro);
                }
            }
        }
    }

    public void eliminarLibro() {
        libroSrv.eliminarLibro();
        System.out.println("");
    }

    public void crearAutor() {
        autorSrv.crearAutor();
        System.out.println("");
    }

    public void modificarAutor() {
        autorSrv.modificarAutor();
        System.out.println("");
    }

    public void buscarAutor() {

        if (libroSrv.buscarLibroAutor(autorSrv.buscarAutor())) {
            System.out.println("Desea solicitar algún libro del autor? S/N");
            if ("S".equalsIgnoreCase(leer.nextLine())) {
                libroSrv.solicitarLibro(libroSrv.buscarLibro());
            }
        }
    }

    public void eliminarAutor() {
        autorSrv.eliminarAutor();
        System.out.println("");
    }

    public void crearEditorial() {
        editorialSrv.crearEditorial();
        System.out.println("");
    }

    public void modificarEditorial() {
        editorialSrv.modificarEditorial();
        System.out.println("");
    }

    public void buscarEditorial() {
        if (libroSrv.buscarLibroEditorial(editorialSrv.buscarEditorial())) {
            System.out.println("Desea solicitar algún libro del autor? S/N");
            if ("S".equalsIgnoreCase(leer.next())) {
                libroSrv.solicitarLibro(libro);
            }
        }
    }

    public void eliminarEditorial() {
        editorialSrv.eliminarEditorial();
        System.out.println("");
    }

    public void devolverLibro() {
        libroSrv.devolverLibro();
    }

    private Integer validarI() {

        while (true) {
            try {
                i = leer.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Solo Puedes ingresar Número, NO LETRAS");
                leer.next();
            }
        }
        return i;
    }
}
