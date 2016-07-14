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
    * <h1>FACTOR DE BALANCE DEL NODO</h1>
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
    * Este metodo se encarga de la busqueda de un nodo que contenga un dato en
    * especifico, se puede realizar la busqueda para los diferentes atributos
    * que tenga el usuario.
    * <br>
    * Entre los parametros estan:
    * <OL>
    * <LI>Constantes.Nombre
    * <LI>Constantes.Nick
    * </OL>
    * @param Nick EL dato que se desea buscar en el arbol.
    * @param Parametro El mediante el cual se realizara la busqueda
    * @return
    */
   public NodoAVL buscarNodo(String Nick) {
      if (usuario != null) {
         String DatoComparacion = usuario.getNick();

         if (Nick.equalsIgnoreCase(DatoComparacion)) {
            return this;
         } else if (Nick.compareToIgnoreCase(DatoComparacion) < 0) {
            if (izqda != null) {
               return izqda.buscarNodo(Nick);
            } else {
               return null;
            }
         } else if (Nick.compareToIgnoreCase(DatoComparacion) > 0) {
            if (drcha != null) {
               return drcha.buscarNodo(Nick);
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
