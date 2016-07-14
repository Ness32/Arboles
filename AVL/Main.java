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
       arbolindio.Insertar(new Usuario("", "", "a", "", 1, 2, 3));
       arbolindio.Insertar(new Usuario("", "", "b", "", 1, 2, 3));
       arbolindio.Insertar(new Usuario("", "", "c", "", 1, 2, 3));
       arbolindio.Insertar(new Usuario("", "", "d", "", 1, 2, 3));
       arbolindio.Insertar(new Usuario("", "", "e", "", 1, 2, 3));
       arbolindio.Insertar(new Usuario("", "", "f", "", 1, 2, 3));
       arbolindio.Insertar(new Usuario("", "", "g", "", 1, 2, 3));
       arbolindio.Insertar(new Usuario("", "", "h", "", 1, 2, 3));
       arbolindio.Insertar(new Usuario("", "", "i", "", 1, 2, 3));
       arbolindio.Insertar(new Usuario("", "", "j", "", 1, 2, 3));
       arbolindio.Insertar(new Usuario("", "", "k", "", 1, 2, 3));
       arbolindio.InOrden();
       arbolindio.GraficarArbol();
       System.out.println("----------------------------");
       arbolindio.Aniquilar("a");
       arbolindio.InOrden();
       arbolindio.GraficarArbol();
    }
}
