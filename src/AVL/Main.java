/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AVL;

import Nodos.Usuario;

/**
 *
 * @author Zetser
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ArbolAVL arbolindio = new ArbolAVL();
       arbolindio.Insertar(new Usuario("", "", "a", ""));
       arbolindio.Insertar(new Usuario("", "", "b", ""));
       arbolindio.Insertar(new Usuario("", "", "c", ""));
       arbolindio.Insertar(new Usuario("", "", "d", ""));
       arbolindio.Insertar(new Usuario("", "", "HAiro", ""));
       arbolindio.Insertar(new Usuario("", "", "f", ""));
       arbolindio.Insertar(new Usuario("", "", "g", ""));
       arbolindio.Insertar(new Usuario("", "", "h", ""));
       arbolindio.Insertar(new Usuario("", "", "i", ""));
       arbolindio.Insertar(new Usuario("", "", "j", ""));
       arbolindio.Insertar(new Usuario("", "", "k", ""));
       
       arbolindio.Aniquilar("a");
       arbolindio.InOrden();
       arbolindio.GraficarArbol();
       System.out.println("----------------------------");       
       arbolindio.IniciarSesion("Hauro", "sdfas");
    }
}
