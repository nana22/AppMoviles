package logicaDeNegocios;

import Datos.Consultas;
import java.lang.String;import java.util.List;

public class Propiedad {

    //Atributos
    private String tipo = "";   //Casa, apartamento...
    private String contrato = "";   //Venta o alquiler
    private String precio = "";
    private String provincia = "";
    private String ciudad = "";
    private String señal1 = "";
    private String señal2 = "";
    private String latitud = "";
    private String longitud = "";
    private List<String> caracteristicas;   //0: Generales, 1: Estructura del lugar, 2: Amueblado, 3: Servicios
    private String fbDueño = "";
    private Persona dueño = null;

    //Constructor
    public Propiedad(String pTipo, String pContrato, String pPrecio, String pProvincia, String pCiudad, String pLatitud, String pLongitud, String pSeñal1, String pSeñal2, List<String> pCaracteristicas, String pFbDueño) {
        tipo = pTipo;
        contrato = pContrato;
        provincia = pProvincia;
        ciudad = pCiudad;
        señal1 = pSeñal1;
        señal2 = pSeñal2;
        precio = pPrecio;
        caracteristicas = pCaracteristicas;
        fbDueño = pFbDueño;
        latitud = pLatitud;
        longitud = pLongitud;
    }

    public Propiedad(String pTipo, String pContrato, String pPrecio, String pProvincia, String pCiudad, String pSeñal1, String pSeñal2, List<String> pCaracteristicas, String pFbDueño) {
        tipo = pTipo;
        contrato = pContrato;
        precio = pPrecio;
        provincia = pProvincia;
        ciudad = pCiudad;
        señal1 = pSeñal1;
        señal2 = pSeñal2;
        caracteristicas = pCaracteristicas;
        fbDueño = pFbDueño;
    }

    //Métodos
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String pTipo) {
        tipo = pTipo;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String pContrato) {
        contrato = pContrato;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String pPrecio) {
        precio = pPrecio;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String pProvincia) {
        provincia = pProvincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String pCiudad) {
        ciudad = pCiudad;
    }

    public String getSeñal1() {
        return señal1;
    }

    public void setSeñal1(String pSeñal1) {
        señal1 = pSeñal1;
    }

    public String getSeñal2() {
        return señal2;
    }

    public void setSeñal2(String pSeñal2) {
        señal2 = pSeñal2;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String pLatitud) {
        latitud = pLatitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String pLongitud) {
        longitud = pLongitud;
    }

    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<String> pCaracteristicas) {
        caracteristicas = pCaracteristicas;
    }

    public String getFbDueño() {
        return fbDueño;
    }

    public void setFbDueño(String pFbDueño) {
        fbDueño = pFbDueño;
    }

    public void setDueño(Persona pDueño) {
        dueño = pDueño;
    }

    public Persona getDueño() {
        return dueño;
    }

    //Agrega al dueño de esta propiedad al atributo dueño
    public void consultarDueño() {
        Consultas query = new Consultas();
        dueño = query.consultarDueñoXPropiedad(fbDueño);
    }

    public String toString() {
        String msg = "==========~PROPIEDAD~==========\n";
        msg += "Tipo:\t\t" + tipo + "\n";
        msg += "Contrato:\t" + contrato + "\n";
        msg += "Precio:\t\t" + precio + "\n";
        msg += "Provincia:\t" + provincia + "\n";
        msg += "Ciudad:\t\t" + ciudad + "\n";
        msg += "Dirección:\t\t" + señal1 + "\n";
        if (!señal2.equals(""))
            msg += "Otras señas:\t\t" + señal2 + "\n";
        if (!latitud.equals(""))
            msg += "Latitud:\t" + latitud + "\n";
        if (!longitud.equals(""))
            msg += "Longitud:\t" + longitud + "\n";
        if (caracteristicas.size() > 0) {
            msg += "Características:\n";
            if (!caracteristicas.get(0).equals(""))
                msg += "  - Generales:  " + caracteristicas.get(0) + "\n";
            if (!caracteristicas.get(1).equals(""))
                msg += "  - Estructura: " + caracteristicas.get(1) + "\n";
            if (!caracteristicas.get(2).equals(""))
                msg += "  - Amueblado:  " + caracteristicas.get(2) + "\n";
            if (!caracteristicas.get(3).equals(""))
                msg += "  - Servicios:  " + caracteristicas.get(3) + "\n";
        }
        return msg;
    }
}