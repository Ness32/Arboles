/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AVL;

import Nodos.Usuario;

/**
 * Esta clase Nodo es donde se almacenara toda la informacion que haga referencia
 * a un usuario.
 * @author zetser
 */
public class NodoAVL {

   //ENLACES PARA USAR EN EL ARBOL
   /**
    * <h1>RAMA IZQUIERDA DEL ARBOL</h1>
    */
   public NodoAVL izqda;
   /**
    * <h1>RAMA DERECHA DEL ARBOL</h1>
    */
   public NodoAVL drcha;
   /**
    * <h1>FACTOR DE EQUILIBRIO O BALANCE DEL NODO</h1>
    */
   int fe;
   /**
    * <h1>NODO USUARIO</h1>
    */
   private Usuario usuario;

   /**
    * <h1>CONSTRUCTOR</h1>
    * Crea un nuevi arbol AVL vacio.
    * @param usuario
    */
   public NodoAVL(Usuario usuario) {
      this.usuario = usuario;
   }

   //======================= BUSQUEDAS =======================\\
   /**
    * Este metodo se encarga de la busqueda de un nodo que contenga
    * la cadena pasada como argumento un alguno de sus atributos.
    * @param cadena EL dato que se desea buscar en el arbol.
    * @return
    */
   public NodoAVL buscarNodo(String cadena) {
      if (usuario != null) {
         String DatoComparacion = usuario.getNick();

         if (cadena.equalsIgnoreCase(DatoComparacion)) {
            return this;
         } else if (cadena.compareToIgnoreCase(DatoComparacion) < 0) {
            if (izqda != null) {
               return izqda.buscarNodo(cadena);
            } else {
               return null;
            }
         } else if (cadena.compareToIgnoreCase(DatoComparacion) > 0) {
            if (drcha != null) {
               return drcha.buscarNodo(cadena);
            } else {
               return null;
            }
         }
      }
      return null;
   }

   //======================= METODOS GET =======================\\
   public Usuario getUsuario() {
      return usuario;
   }

   //======================= METODOS SET =======================\\
   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }
}
