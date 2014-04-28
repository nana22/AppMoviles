package datos;

import java.io.IOException;
import logicaDeNegocios.Publicacion;
 
public class GsonExample {
    
    public static void main(String[] args) throws InterruptedException, IOException {
        Publicacion p = new Publicacion();
        System.out.println(p.toStringPropiedades());
    }
}