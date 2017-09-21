package com.ordinov.ersp.product.editor;

import org.springframework.beans.propertyeditors.PropertiesEditor;

public class DoubleEditor extends PropertiesEditor {

	@Override    
    public void setAsText(String text) throws IllegalArgumentException {     // NOPMD by dengfx on 17-9-21 ионГ11:39
        if (text == null || text.equals("")) {    
            text = "0";    
        }    
        setValue(Double.parseDouble(text));    
    }    
    
    @Override    
    public String getAsText() {    
        return getValue().toString();    
    }    
}
