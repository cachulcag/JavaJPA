/*
 Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para
administrar editoriales (consulta, creación, modificación y eliminación)
 */
package com.mycompany.services;

import com.mycompany.entidades.Editorial;
import com.mycompany.persistencia.ServicesPersistencia;
import java.util.Scanner;

/**
 *
 * @author crist
 */
public class EditorialServices {

    private static int i;
    private ServicesPersistencia JPA = new ServicesPersistencia();
    private static Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private Editorial editorial;

    protected Editorial crearEditorial() {
        System.out.println("Ingrese el nombre de la editorial");
        editorial = new Editorial(null, leer.next(), true);
        JPA.crearEditorial(editorial);
        return editorial;

    }

    protected void modificarEditorial() {
        while (true || i!=0) {
            try {
                System.out.println("Ingrese los nuevos datos de la editorial");
                JPA.modificarEditorial(crearEditorial());
                System.out.println("Se ha modificado la editoial con éxito");
                break;
            } catch (Exception e) {
                System.out.println("No se ha podido modificar la editorial intentalo nuevamente");
                System.out.println("Escriba cualquier tecla para volver a escribir los datos");
                System.out.println("Si desea volver al menú, digite 0");
                i=validarI();
            }
        }
    }

    protected Editorial buscarEditorial() {

        System.out.println("Ingrese el id de la Editorial que desea buscar");
        editorial = JPA.buscarEditorial(validarI());
        if (editorial != null) {
            System.out.println("Los datos de la editorial son: ");
            System.out.println(editorial.toString());
        } else {
            System.out.println("No se encontró la editorial");
        }

        return editorial;

    }

    protected void eliminarEditorial() {
        editorial = buscarEditorial();
        editorial.setAlta(false);
        System.out.println("Se ha eliminado con éxito");

    }

    private Integer validarI() {
        while (true) {
            try {
                i = leer.nextInt();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Solo puedes ingresar números enteros");
                leer.next();
            }
        }
        return i;
    }

}
