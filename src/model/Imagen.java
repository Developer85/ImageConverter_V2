package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author usuari
 */
public class Imagen {
    private String ruta;
    private String nombre;
    private String extension;
    private BufferedImage img;
    private int tipo;
    private int ancho;
    private int alto;
    private String orientacion;

    public Imagen(File fichero) throws IOException {
        int posicion = fichero.getName().lastIndexOf(".");
        
        ruta = fichero.getParent() + "/";
        nombre = fichero.getName().substring(0, posicion);
        extension = ".jpg";
        img = ImageIO.read(fichero);
        tipo = img.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : img.getType();
        ancho = img.getWidth();
        alto = img.getHeight();
        
        if (ancho > alto) {
            orientacion = "horizontal";
        } else {
            orientacion = "vertical";
        }
    }
    
    public String getRuta() {
        return ruta;
    }       

    public String getNombre() {
        return nombre;
    }  

    public String getExtension() {
        return extension;
    }
    
    public BufferedImage getImg() {
        return img;
    }
    
    public int getTipo() {
        return tipo;
    }
    
    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
    
    public String getOrientacion() {
        return orientacion;
    }

    @Override
    public String toString() {
        return "ruta=" + ruta + ", nombre=" + nombre + ", extension=" + extension +
                ", resolucion=" + ancho + "x" + alto + ", orientacion=" + orientacion;
    }
}
