package br.com.ecommerce.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("numberConverter")
public class NumberConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub

		BigDecimal valor = null;

        if (value != null) {
            if (value.isEmpty() == false) {
                valor = new BigDecimal(value);
            }
        }

		return valor;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		return value != null ? value.toString() : null;
	}

}
