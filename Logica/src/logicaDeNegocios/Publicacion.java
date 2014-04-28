package logicaDeNegocios;

import Datos.Consultas;
import java.io.IOException;
import java.util.List;

public class Publicacion {
    
    //Atributos
    List<Propiedad> propiedades = null;
    List<Persona> dueños = null;
    
    //Constructor
    public Publicacion() {
        consultarPropiedades();
        consultarDueños();
    }
    
    //Métodos
    public void setPropiedades(List<Propiedad> pPropiedades) {
        propiedades = pPropiedades;
    }
    
    public List<Propiedad> getPropiedades() {
        return propiedades;
    }
    
    public void setDueños(List<Persona> pDueños) {
        dueños = pDueños;
    }
    
    public List<Persona> getDueños() {
        return dueños;
    }
    
    public void consultarPropiedades() {
        Consultas query = new Consultas();
        propiedades = query.consultarPropiedades();
        for(int i = 0; i < propiedades.size(); i++) {
            propiedades.get(i).consultarDueño();
        }
    }
    
    public void consultarDueños() {
        Consultas query = new Consultas();
        dueños = query.consultarDueños();
        for(int i = 0; i < dueños.size(); i++) {
            dueños.get(i).consultarPropiedades();
        }
    }
    
    public String toStringPropiedades() {
        String msg = "";
        for(int i = 0; i < propiedades.size(); i++) {
            msg += propiedades.get(i).toString();
        }
        return msg;
    }
    
    public String toStringDueños() {
        String msg = "";
        for(int i = 0; i < dueños.size(); i++) {
            msg += dueños.get(i).toString();
        }
        return msg;
    }
    
    public String toStringPropiedadesXDueño(String pFacebook) {
        String msg = "";
        for(int i = 0; i < propiedades.size(); i++) {
            if (propiedades.get(i).getFbDueño().equals(pFacebook)) {
                msg += propiedades.get(i).toString();
                msg += "\n";
            }
        }
        return msg;
    }
    
    public String toStringDueñoXPropiedad(String pFbDueño) {
        String msg = "";
        for(int i = 0; i < dueños.size(); i++) {
            if (dueños.get(i).getFacebook().equals(pFbDueño)) {
                msg += dueños.get(i).toString() + "\n";
                break;
            }
        }
        return msg;
    }
    
    private boolean validarSubmitPersona(String pFbDueño) {
        for(int i = 0; i < dueños.size(); i++) {
            if (dueños.get(i).getFacebook().equals(pFbDueño))
                return false;
        }
        return true;
    }
    
    //Registra a una persona
    public String submitPersona(Persona pPersona) throws IOException {
        if (validarSubmitPersona(pPersona.getFacebook())) {
            Consultas query = new Consultas();
            query.submitDueño(pPersona);
            pPersona.consultarPropiedades();
            dueños.add(pPersona);
            return "El registro ha sido completado correctamente";
        }
        else
            return "Esta persona ya ha sido registrada";
    }
    
    private boolean validarSubmitPropiedad(Propiedad pPropiedad) {
        for(Persona dueño: dueños) {
            if (dueño.getFacebook().equals(pPropiedad.getFbDueño()))
                return true;
        }
        return false;
    }
    
    //Registra una propiedad
    public String submitPropiedad(Propiedad pPropiedad) throws IOException {
        if(validarSubmitPropiedad(pPropiedad)) {
            Consultas query = new Consultas();
            query.submitPropiedad(pPropiedad);
            pPropiedad.consultarDueño();
            propiedades.add(pPropiedad);
            return "La propiedad ha sido registrada correctamente";
        }
        else
            return "Debe ingresar el facebook de un dueño registrado";
    }
}