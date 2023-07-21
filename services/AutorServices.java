/*
 Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para
administrar autores (consulta, creación, modificación y eliminación).
 */
package com.mycompany.services;

import com.mycompany.entidades.Autor;
import com.mycompany.persistencia.ServicesPersistencia;
import com.mycompany.persistencia.exceptions.PreexistingEntityException;
import java.util.Scanner;

/**
 *
 * @author crist
 */
public class AutorServices {

    private static int i;
    private Autor autor;
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private ServicesPersistencia JPA = new ServicesPersistencia();

    protected Autor crearAutor() {
        while (true) {
            try {
                autor = new Autor();
                System.out.println("Ingrese el nombre del autor");
                autor.setNombre(leer.next());
                autor.setAlta(true);
                JPA.crearAutor(autor);
                break;
            } catch (PreexistingEntityException e) {
                System.out.println("Autor repetido, Ingresa los datos nuevamente");
                leer.next();
            }
        }
        return autor;
    }

    protected void modificarAutor() {
        while (true || i != 0) {
            try {
                System.out.println("Ingresa el ID del autor que desea modificar");
                autor = JPA.buscarAutor(validarI());
                System.out.println("Ingrese su nuevo ID");
                autor.setId(validarI());
                System.out.println("Ingrese su nuevo nombre");
                autor.setNombre(leer.next());
                autor.setAlta(true);
                System.out.println("");
                JPA.modificarAutor(autor);
                System.out.println("Se ha modificado con éxito");
                break;
            } catch (Exception e) {
                System.out.println("Ingresa los datos nuevamente");
                System.out.println("Escriba cualquier tecla para volver a escribir los datos");
                System.out.println("Si desea volver al menú, digite 0");
                i = validarI();
            }
        }

    }

    protected void eliminarAutor() {
        System.out.println("Desea eliminar (1) o dar de baja (2)");
        if (validarI() == 1) {

            while (true || i != 0) {
                try {
                    System.out.println("Ingresa el ID del autor que desea eliminar");
                    JPA.eliminarAutor(validarI());
                    System.out.println("Se ha eliminado con éxito");
                    break;
                } catch (Exception e) {
                    System.out.println("No se encuentra el autor indicado.. ");
                    System.out.println("Escriba cualquier tecla para volver a escribir los datos");
                    System.out.println("Si desea volver al menú, digite 0");
                    i = validarI();
                }
            }
        } else {
            System.out.println("Para dar de baja se necesita: ");
            autor = buscarAutor();
            autor.setAlta(false);
            System.out.println("Listo se ha dado de baja el autor");
        }
    }

    protected Autor buscarAutor() {
        System.out.println("Ingrese el ID del autor que desea buscar");
        autor = JPA.buscarAutor(validarI());
        if (autor != null) {
            System.out.println("Los datos del autor son: ");
            System.out.println(autor.toString());
        } else {
            System.out.println("No se encontró el autor");
        }

        return autor;
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
