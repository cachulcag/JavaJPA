/*

 */
package com.mycompany.persistencia;

import com.mycompany.entidades.Autor;
import com.mycompany.entidades.Editorial;
import com.mycompany.entidades.Libro;
import com.mycompany.persistencia.exceptions.NonexistentEntityException;
import com.mycompany.persistencia.exceptions.PreexistingEntityException;
import java.util.List;

/**
 *
 * @author crist
 */
public class ServicesPersistencia {
    
    private LibroJpaController libroJPA = new LibroJpaController();
    private AutorJpaController autorJPA = new AutorJpaController();
    private EditorialJpaController editorialJPA = new EditorialJpaController();

    // MÉTODOS LIBRO    
    public void crearLibro(Libro libro) throws Exception {
        libroJPA.create(libro);
    }
    
    public void modificarLibro(Libro libro) throws Exception {
        libroJPA.edit(libro);
    }
    
    public Libro buscarLibro(Long i) {
        return libroJPA.findLibro(i);
        
    }
    public List<Libro> buscarLibrosAutor(Autor autor){
        return libroJPA.encontrarLibrosAutor(autor);
    }
    
    public List<Libro> buscarLibrosEditorial(Editorial editorial){
        return libroJPA.encontrarLibrosEditorial(editorial);
    }
    
    public void eliminarLibro(Long i) throws NonexistentEntityException {
        libroJPA.destroy(i);
    }

    public List<Libro> mostrarLista(){
        return libroJPA.findLibroEntities();
    }
    // MÉTODOS AUTOR
    public void crearAutor(Autor autor) throws PreexistingEntityException {
        autorJPA.create(autor);
        
    }
    
    public void modificarAutor(Autor autor) throws Exception {
        autorJPA.edit(autor);
    }
    
    public Autor buscarAutor(Integer id) {
        return autorJPA.findAutor(id);
    }
    
    public void eliminarAutor(Integer id) throws NonexistentEntityException {
        autorJPA.destroy(id);
    }

    //MÉTODOS EDITORIAL
    public void crearEditorial(Editorial editorial) {
        editorialJPA.create(editorial);
    }

    public void modificarEditorial(Editorial editorial) throws Exception {
        editorialJPA.edit(editorial);
    }
    
    public Editorial buscarEditorial(Integer id) {
        return editorialJPA.findEditorial(id);
    }
    
    public void eliminarEditorial(Integer id) throws NonexistentEntityException {
        editorialJPA.destroy(id);
    }
    
}
