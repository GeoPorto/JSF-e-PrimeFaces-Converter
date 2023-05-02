package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Categoria;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoriaDAO {

    @PersistenceContext(unitName = "conexaoPU")
    private EntityManager em;

    public void persist(Categoria categoria) {
        em.persist(categoria);
    }

    public List<Categoria> findAll() {
        return em.createNamedQuery("Categoria.findAll", Categoria.class)
                .getResultList();
    }

    public Categoria findById(Integer id) {
        return em.find(Categoria.class, id);
    }
}
