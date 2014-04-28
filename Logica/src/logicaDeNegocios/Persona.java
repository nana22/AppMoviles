package logicaDeNegocios;

import Datos.Consultas;
import java.util.List;

public class Persona {
    
    //Atributos
    private String nombre = "";
    private String apellido1 = "";
    private String apellido2 = "";
    private String telefono1 = "";
    private String telefono2 = "";
    private String facebook = "";
    private List<Propiedad> propiedades = null;
    
    //Contructores
    public Persona(String pNombre, String pApellido1, String pApellido2, String pTelefono1, String pTelefono2, String pFacebook) {
        nombre = pNombre;
        apellido1 = pApellido1;
        apellido2 = pApellido2;
        telefono1 = pTelefono1;
        telefono2 = pTelefono2;
        facebook = pFacebook;
    }
    
    public Persona(String pNombre, String pApellido1, String pApellido2, String pTelefono1, String pFacebook) {
        nombre = pNombre;
        apellido1 = pApellido1;
        apellido2 = pApellido2;
        telefono1 = pTelefono1;
        facebook = pFacebook;
    }
    
    //Métodos
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }
    
    public String getApellido1() {
        return apellido1;
    }
    
    public void setApellido1(String pApellido1) {
        apellido1 = pApellido1;
    }
    
    public String getApellido2() {
        return apellido2;
    }
    
    public void setApellido2(String pApellido2) {
        apellido2 = pApellido2;
    }
    
    public String getTelefono1() {
        return telefono1;
    }
    
    public void setTelefono1(String pTelefono1) {
        telefono1 = pTelefono1;
    }
    
    public String getTelefono2() {
        return telefono2;
    }
    
    public void setTelefono2(String pTelefono2) {
        telefono2 = pTelefono2;
    }
    
    public String getFacebook() {
        return facebook;
    }
    
    public void setFacebook(String pFacebook) {
        facebook = pFacebook;
    }
    
    public void setPropiedades(List<Propiedad> pPropiedades) {
        propiedades = pPropiedades;
    }
    
    public List<Propiedad> getPropiedades() {
        return propiedades;
    }
    
    //Agrega las propiedades registradas por este dueño a propiedades
    public void consultarPropiedades() {
        Consultas query = new Consultas();
        propiedades = query.consultarPropiedadesXDuenio(facebook);
    }
    
    public String toString() {
        String msg = "=====~Dueño~=====\n";
        msg += "Nombre:\t\t  " + nombre + "\n";
        msg += "Primer apellido:  " + apellido1 + "\n";
        msg += "Segundo apellido: " + apellido2 + "\n";
        msg += "Teléfono 1:\t  " + telefono1 + "\n";
        msg += "Teléfono 2:\t  " + telefono2 + "\n";
        msg += "Facebook:\t  " + facebook + "\n";
        return msg;
    }
}
