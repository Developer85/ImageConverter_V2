package model;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import utilidades.EntradaDatos;

/**
 *
 * @author usuari
 */
public class Convertidor {
    private ArrayList<Imagen> listaImagenes;

    public Convertidor() {
        listaImagenes = new ArrayList<>();
        mostrarMenuPrincipal();
    }
    
    private Boolean buscarImagenes(File ruta) {        
        File[] elements = ruta.listFiles();
        
        for (File f : elements) {
            if (f.isDirectory()) {
                buscarImagenes(f);
            } else if (f.isFile() && f.getName().endsWith(".jpg")) {
                try {
                    Imagen img = new Imagen(f);
                    listaImagenes.add(img);
                } catch (IOException ex) {
                    System.out.println("Error!!! Se ha producido un error de E/S.");
                }
            }
        }
        
        return !listaImagenes.isEmpty();
    }
    
    private void listarImagenes() {
        listaImagenes.clear();
        
        if (buscarImagenes(new File("."))) {
            System.out.println("");
            for (Imagen img : listaImagenes) {
                System.out.println(img);
            }
        } else {
            System.out.println("Error!!! No se han encontrado imágenes.");
        }
    }
    
    private void convertirImagenes(int ancho, int alto) {
        listaImagenes.clear();
        
        if (buscarImagenes(new File("."))) {
            for (Imagen img : listaImagenes) {
                guardarImagen(img, ancho, alto);
            }
        } else {
            System.out.println("Error!!! No se han encontrado imágenes.");
        }
    }
    
    private void guardarImagen(Imagen img, int ancho, int alto) {
        try {
            float ratioX, ratioY, ratio;
            
            if (img.getOrientacion().equals("horizontal")) {
                ratioX = (float) ancho / img.getAncho();
                ratioY = (float) alto / img.getAlto();
            } else {
                ratioX = (float) alto / img.getAncho();
                ratioY = (float) ancho / img.getAlto();        
            }
            
            ratio = Math.min(ratioX, ratioY);
            
            int nuevoAncho = (int)(img.getAncho() * ratio);
            int nuevoAlto = (int)(img.getAlto() * ratio);
            
            BufferedImage resizedImg = new BufferedImage(nuevoAncho, nuevoAlto, img.getTipo());
            Graphics2D g = resizedImg.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(img.getImg(), 0, 0, nuevoAncho, nuevoAlto, null);
            g.dispose();
            ImageIO.write(resizedImg, "JPEG", new File(img.getRuta() + img.getNombre() + "_resized" + img.getExtension()));
        } catch (IOException ex) {
            System.out.println("Error!!! Se ha producido un error de E/S.");
        }
    }
    
    private void mostrarMenuPrincipal() {
        int opcion;
        
        do {
            System.out.println("Menú Principal\n");
            System.out.println("1. Listar Imágenes");
            System.out.println("2. Convertir Imágenes");
            System.out.println("3. Salir");
            System.out.println("");
            opcion = EntradaDatos.pedirEntero("Elige una opción: ", 1, 3);
            
            switch (opcion) {
                case 1:
                    listarImagenes();
                    System.out.println("");
                    break;
                case 2:
                    mostrarMenuConversiones();
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("\nHasta Pronto!!!");
                    break;
            }
        } while (opcion != 3);
    }
    
    private void mostrarMenuConversiones() {
        int opcion;
        
        do {
            System.out.println("\nResoluciones Disponibles\n");
            System.out.println("1.  640 x 480");
            System.out.println("2.  800 x 600");
            System.out.println("3.  1024 x 768");
            System.out.println("4.  1280 x 1024");
            System.out.println("5.  1600 x 1200");
            System.out.println("");
            opcion = EntradaDatos.pedirEntero("Elige una opción: ", 1, 5);
            
            switch (opcion) {
                case 1:
                    convertirImagenes(640, 480);
                    System.out.println("\nImágenes convertidas a resolución 640 x 480.\n");
                    mostrarMenuPrincipal();
                    break;
                case 2:
                    convertirImagenes(800, 600);
                    System.out.println("\nImágenes convertidas a resolución 800 x 600.\n");
                    mostrarMenuPrincipal();
                    break;
                case 3:
                    convertirImagenes(1014, 768);
                    System.out.println("\nImágenes convertidas a resolución 1024 x 768.\n");
                    mostrarMenuPrincipal();
                    break;
                case 4:
                    convertirImagenes(1280, 1024);
                    System.out.println("\nImágenes convertidas a resolución 1280 x 1024.\n");
                    mostrarMenuPrincipal();
                    break;
                case 5:
                    convertirImagenes(1600, 1200);
                    System.out.println("\nImágenes convertidas a resolución 1600 x 1200.\n");
                    mostrarMenuPrincipal();
                    break;
            }
        } while (opcion != 1 && opcion != 2 && opcion != 3 && opcion != 4 && opcion != 5);
    }
}
