/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samplecenter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author loris
 */
@FacesValidator
public class FileValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Part part = (Part) value;
        String type = part.getContentType().split("/")[0];
        if (!"audio".equals(type))
        {
            Logger.getAnonymousLogger().log(Level.INFO, type);
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "The file isnt an audio file", "The file isnt an audio file"));
        }
    }

}