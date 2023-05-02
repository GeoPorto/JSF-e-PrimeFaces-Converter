package br.edu.ifsp.pep.converter;

import br.edu.ifsp.pep.dao.CategoriaDAO;
import br.edu.ifsp.pep.model.Categoria;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author Geova
 */
@FacesConverter("categoriaConverter")
public class CategoriaConverter implements Converter<Categoria> {

    @Override
    public Categoria getAsObject(FacesContext context,
            UIComponent component, String value) {

        if (value == null || value.isEmpty()) {
            return null;
        }
        CategoriaDAO categoriaDAO = CDI.current().select(CategoriaDAO.class).get();
        return categoriaDAO.findById(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context,
            UIComponent component, Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return categoria.getId().toString();
    }

}
