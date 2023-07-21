/*
 Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para
administrar libros (consulta, creación, modificación y eliminación).
 */
package com.mycompany.services;

import com.mycompany.entidades.Autor;
import com.mycompany.entidades.Editorial;
import com.mycompany.entidades.Libro;
import com.mycompany.persistencia.ServicesPersistencia;
import com.mycompany.persistencia.exceptions.NonexistentEntityException; // para eliminar
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author crist
 */
public class LibroServices {

    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private static int i = 0;
    private static long l = 0;
    private Libro libro;
    private ServicesPersistencia JPA = new ServicesPersistencia();
    private AutorServices autorSrv = new AutorServices();
    private EditorialServices editorialSrv = new EditorialServices();
    private List<Libro> libros = new ArrayList();

    protected Libro crearLibro() {
        libro = new Libro();
        while (true || i != 0) {
            try {
                System.out.println("Ingrese el ISBN del libro");
                libro.setIsbn(validarL());
                System.out.println("Ingrese el título");
                libro.setTitulo(leer.next());
                System.out.println("Ingrese el año");
                libro.setAnio(leer.nextInt());
                System.out.println("Ingrese el número de ejemplares");
                libro.setEjemplares(validarI());
                libro.setEjemplaresRestantes();
                libro.setAlta(true);
                System.out.println("Desea crear un nuevo autor para el libro? S/N");
                if ("S".equalsIgnoreCase(leer.next())) {
                    libro.setAutor(autorSrv.crearAutor());
                } else {
                    libro.setAutor(autorSrv.buscarAutor());
                }
                System.out.println("Desea crear una nueva editorial para el libro? S/N");
                if ("N".equalsIgnoreCase(leer.next())) {
                    libro.setEditorial(editorialSrv.buscarEditorial());
                } else {
                    libro.setEditorial(editorialSrv.crearEditorial());
                }
                JPA.crearLibro(libro);
                System.out.println("Se ha ingresado con ÉXITO");
                break;
            } catch (Exception e) {
                System.out.println("Ingresa nuevamente los datos, NO PUEDEN TENER UN ISBN IGUAL");
                System.out.println("Digite cualquier tecla para volver a ingresar los datos");
                System.out.println("*Si desea volver al menú digite 0 ");
                i = validarI();
            }
        }
        return libro;
    }

    protected void modificarLibro() {

        while (true || i != 0) {
            try {
                libro = new Libro();
                System.out.println("Ingresa los nuevos datos del libro");
                System.out.println("Ingrese el ISBN del libro");
                libro.setIsbn(validarL());
                System.out.println("Ingrese el título");
                libro.setTitulo(leer.next());
                System.out.println("Ingrese el año");
                libro.setAnio(leer.nextInt());
                System.out.println("Ingrese el número de ejemplares");
                libro.setEjemplares(validarI());
                System.out.println("Cuantos ejemplares estan prestados?");
                libro.setEjemplaresPrestados(validarI());
                libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
                libro.setAlta(true);
                System.out.println("Desea crear un nuevo autor para el libro? S/N");
                if ("S".equalsIgnoreCase(leer.next())) {
                    libro.setAutor(autorSrv.crearAutor());
                } else {
                    libro.setAutor(autorSrv.buscarAutor());
                }
                System.out.println("Desea crear una nueva editorial para el libro? S/N");
                if ("N".equalsIgnoreCase(leer.next())) {
                    libro.setEditorial(editorialSrv.buscarEditorial());
                } else {
                    libro.setEditorial(editorialSrv.crearEditorial());
                }
                JPA.modificarLibro(libro);
                System.out.println("El libro se ha modificado con éxito");
                break;
            } catch (Exception e) {
                System.out.println("Ingresa nuevamente los datos, NO PUEDEN TENER UN ISBN IGUAL");
                System.out.println("Digite cualquier tecla para volver a ingresar los datos");
                System.out.println("*Si desea volver al menú digite 0 ");
                i = validarI();
            }
        }
    }

    protected void eliminarLibro() {
        System.out.println("Desea eliminar (1) o dar de baja (2)");
        if (validarI() == 1) {
            while (true || i != 0) {
                try {
                    System.out.println("Ingrese el ISBN del libro que desea eliminar");
                    JPA.eliminarLibro(validarL());
                    break;
                } catch (NonexistentEntityException e) {
                    System.out.println("No existe el libro ingresado en la base de datos");
                    System.out.println("Digite cualquier tecla para volver a ingresar los datos");
                    System.out.println("*Si desea regresar digite 0");
                    i = validarI();
                }
            }
        } else {
            System.out.println("Para eliminar un libro necesita:");
            libro = buscarLibro();
            libro.setAlta(false);
            System.out.println("Se ha dado de baja con éxito");
        }
    }

    protected Libro buscarLibro() {

        System.out.println("Ingrese el ISBN del libro que desea buscar");
        libro = JPA.buscarLibro(validarL());
        if (libro != null) {
            System.out.println("Los datos del libro son:");
            System.out.println(libro.toString());
        } else {
            System.out.println("No se encontró el libro");
        }
        return libro;
    }

    protected boolean buscarLibroAutor(Autor autor) {
        libros.clear();
        libros = JPA.buscarLibrosAutor(autor);
        if (!libros.isEmpty()) {
            System.out.println("Los libros del autor son:");
            libros.stream().filter(Libro -> Libro.getAlta() == true).forEach(Autor -> System.out.println(Autor.toString()));

        } else {
            System.out.println("No se encontraron libros");
        }
        return !libros.isEmpty();
    }

    protected boolean buscarLibroEditorial(Editorial editorial) {
        libros.clear();
        libros = JPA.buscarLibrosEditorial(editorial);
        if (!libros.isEmpty()) {
            System.out.println("Los libros de la editorial son:");
            libros.stream().filter(Libro -> Libro.getAlta() == true).forEach(Editorial -> System.out.println(Editorial.toString()));
        } else {
            System.out.println("No se encontraron libros");
        }
        return !libros.isEmpty();
    }

    protected void solicitarLibro(Libro libro) {
        System.out.println("Cuantos ejemplares desea solicitar? (pueden ser hasta "+libro.getEjemplaresRestantes()+")");
        i = validarI();
        if (libro.getEjemplaresRestantes() >= i) {
            libro.conteoDeEjemplares(i);
            try {
                JPA.modificarLibro(libro);
            } catch (Exception e) {
                System.out.println("Lo sentimos, hubo un error por favor intente nuevamente");
            }

            System.out.println("Solicitado con éxito");
        } else {
            System.out.println("Lo sentimos, no tenemos libros disponibles");
        }

    }

    protected void devolverLibro() {
        System.out.println("Ingrese el ISBN del libro que desea devolver");
        libro = JPA.buscarLibro(validarL());
        if (libro.getEjemplaresRestantes() <= libro.getEjemplares()) {
            try {
                JPA.modificarLibro(libro);
                System.out.println("Se ha devuelto correctamente");
            } catch (Exception e) {
                System.out.println("Lo sentimos, hubo un error por favor intente nuevamente");
            }

        } else {
            System.out.println("Lo sentimos, verifica el total de ejemplares");
        }
    }

    protected void mostrarLista() {
        libros.clear();
        libros = JPA.mostrarLista();
        libros.stream().filter(Libro -> Libro.getAlta()==true).forEach(Libro -> System.out.println(Libro.toString()));
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

    private Long validarL() {

        while (true) {
            try {
                l = leer.nextLong();
                break;
            } catch (Exception e) {
                System.out.println("Solo Puedes ingresar Número, NO LETRAS");
                leer.next();
            }
        }
        return l;
    }
}
