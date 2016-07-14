package AVL;

import Nodos.Usuario;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * En esta clase se implementa la estructura de un arbol AVL con los metodos de
 * insercion, eliminacion y busqueda especialmente.
 * @author zetser
 */
public class ArbolAVL {

   NodoAVL raiz;
   private boolean AumentoAltura;
   /**
    * Esta variable se usa para generar el grafico del arbol
    */
   private String Nodos = "";
   /**
    * En esta variable se guardan las relaciones entre los nodos;
    */
   private String Relaciones = "";
   /**
    * Resultado recorridos
    */
   private String recorrido = "";

   public ArbolAVL() {
      this.raiz = null;
      File bitacoraVieja = new File("admin", "bitacora.txt");
      bitacoraVieja.delete();
   }

   /* ************************************************************************* *
    *                            `METODOS PARA LAS BUSQUEDAS                    *
    * ************************************************************************* */
   /**
    * Este metodo se encarga de la busqueda de un nodo que contenga un dato en
    * especifico.
    * @param Nick EL dato que se desea buscar en el arbol.
    * @return El nodo que contiene el dato buscado
    */
   public NodoAVL buscar(String Nick) {
      if (raiz != null) {
         return raiz.buscarNodo(Nick);
      } else {
         return null;
      }
   }

   public NodoAVL IniciarSesion(String nick, String password) {
      if (raiz != null) {
         try {
            NodoAVL resultado = raiz.buscarNodo(nick);
            if (resultado != null) {
               if (resultado.getUsuario().getPassword().equals(password)) {
                  return resultado;
               }
            }
         } catch (Exception e) {
            return null;
         }
      }
      return null;
   }

   public void InOrden(){
      InOrdenAVL(raiz);
   }

   /**
    * <h1>Recorrido Pre-Orden</h1>
    * Este metodo realiza el recorrido en PreOrden del arbol AVL
    * <br>
    * raiz, izquierda, derecha
    */
   private void PreOrdenAVL(NodoAVL Nodo) {
      if (Nodo == null) {
         return;
      } else {
         recorrido += Nodo.getUsuario().getNick() + "\n";
         PreOrdenAVL(Nodo.izqda);
         PreOrdenAVL(Nodo.drcha);
      }
   }

   /**
    * <h1>Recorrido Post-Orden</h1>
    * Este metodo realiza el recorrido en PostOrden del arbol AVL
    * <br>
    * izquierda, derecha, raiz
    */
   private void PostOrdenAVL(NodoAVL Nodo) {
      if (Nodo == null) {
         return;
      } else {
         PostOrdenAVL(Nodo.izqda);
         PostOrdenAVL(Nodo.drcha);
         recorrido += Nodo.getUsuario().getNick() + "\n";
      }
   }

   /**
    * <h1>Recorrido In-Orden</h1>
    * Este metodo realiza el recorrido en InOrden del arbol AVL
    * <br>
    * izquierda, raiz, derecha
    */
   private void InOrdenAVL(NodoAVL Nodo) {
      if (Nodo == null) {
         return;
      } else {
         InOrdenAVL(Nodo.izqda);
         recorrido += Nodo.getUsuario().getNick() + "\n";
         System.out.println(Nodo.getUsuario().getNick());
         InOrdenAVL(Nodo.drcha);
      }
   }

   /* ************************************************************************* *
    *                          MOSTRAR TODOS LOS USUARIOS                       *
    * ************************************************************************* */
   private String usuarios = "";

   /**
    * Este metodo sirve para mostrar a todos los usuarios, para el area administrativa,
    * se les agrega un boton para eliminar y otro para ver. los nombres de los campos
    * son los siguientes:
    * <br>
    * hiddenField = se utiliza para guardar el nick del usuario
    * eliminar = el boton para eliminar un usuario
    * ver = el boton para ver la lista de seguidores y siguiendo a.
    * @return
    */
   public String MostrarUsuarios() {
      usuarios = "";
      recorrer(raiz);
      return usuarios;
   }

   private void recorrer(NodoAVL Nodo) {
      if (Nodo == null) {
         return;
      } else {
         recorrer(Nodo.izqda);

         if (Nodo.getUsuario() != null) {
            usuarios += "<li>";
            usuarios += "<form action=\"admUsuarios.jsp\" method=\"post\">";
            usuarios += "<input type=\"hidden\" name=\"hiddenField\" id=\"hiddenField\" value=\"" + Nodo.getUsuario().getNick() + "\">";
            usuarios += Nodo.getUsuario().getNick() + "&nbsp;&nbsp;-&nbsp;";
            usuarios += "<input type=\"image\" name=\"eliminar\" id=\"eliminar\" src=\"imagenes/eliminar.png\"> | "
                    + "<input type=\"image\" name=\"ver\" id=\"ver\" src=\"imagenes/ver.png\">";
            usuarios += "</form>";
            usuarios += " </li>";
         }

         recorrer(Nodo.drcha);
      }
   }

   /* ************************************************************************* *
    *                               METODOS PARA INSERTAR                       *
    * ************************************************************************* */
   /**
    * <h1>Insertar Nuevo Usuario</h1>
    * Este metodo se usa para insertar un nuevo usuario en el arbol, el arbol se
    * mantiene siempre equilibrado.
    * @param Nombre
    * @param Nick
    * @param año
    * @param dia
    * @param mes
    * @param Sexo
    */
   public boolean Insertar(Usuario usuario) {
      if ((!UsuarioExistente(usuario.getNick()))) {
         NodoAVL nuevo = new NodoAVL(usuario);
         raiz = InsertarBalanceado(raiz, nuevo);
         return true;
      } else {
         return false;
      }
   }

   /**
    * <h1>Insertar Balanceado</h1>
    * Metodo que se encarga de insertar el nodo en el arbol y mentener el balance
    * de este.
    * @param subArbol
    * @param Nodo
    * @return
    */
   private NodoAVL InsertarBalanceado(NodoAVL subArbol, NodoAVL Nodo) {
      NodoAVL n1;
      NodoAVL NodoAux = Nodo;

      if (ArbolVacio(subArbol)) {
         subArbol = NodoAux;
         AumentoAltura = true;
      } else if (Nodo.getUsuario().getNick().compareToIgnoreCase(subArbol.getUsuario().getNick()) < 0) {
         subArbol.izqda = InsertarBalanceado(subArbol.izqda, Nodo);
         if (AumentoAltura) {
            switch (subArbol.fe) {
               case 1: {
                  subArbol.fe = 0;
                  AumentoAltura = false;
               }
               break;
               case 0:
                  subArbol.fe = -1;
                  break;
               case -1: {
                  n1 = subArbol.izqda;
                  if (n1.fe == -1) {
                     subArbol = RotacionII(subArbol, n1);
                  } else {
                     subArbol = RotacionID(subArbol, n1);
                  }
                  AumentoAltura = false;
               }
               break;
            }
         }
      } else {
         if (Nodo.getUsuario().getNick().compareToIgnoreCase(subArbol.getUsuario().getNick()) > 0) {
            subArbol.drcha = InsertarBalanceado(subArbol.drcha, Nodo);
            if (AumentoAltura) {
               switch (subArbol.fe) {
                  case -1:
                     subArbol.fe = 0;
                     AumentoAltura = false;
                     break;
                  case 0:
                     subArbol.fe = 1;
                     break;
                  case 1: {
                     n1 = subArbol.drcha;
                     if (n1.fe == 1) {
                        subArbol = RotacionDD(subArbol, n1);
                     } else {
                        subArbol = RotacionDI(subArbol, n1);
                     }
                     AumentoAltura = false;
                  }
                  break;
               }
            }
         } else {
            AumentoAltura = false;
         }
      }
      return subArbol;
   }

   //======================= ROTACIONES =======================\\
   /**
    * <h1>Rotacion Derecha-Derecha</h1>
    * Metodo que se encarga de las rotaciones DD cuando son necesarias.
    * @param n Nodo que tiene un factor de balance diferente de -1, 0, 1
    * @param n1 el hijo derecho del nodo N
    * @return
    */
   private NodoAVL RotacionDD(NodoAVL n, NodoAVL n1) {
      try {
         Bitacora("Derecha-Derecha", n.getUsuario().getNick()
                 + ", " + n1.getUsuario().getNick() + ", "
                 + n1.drcha.getUsuario().getNick(), Altura(n.drcha) - Altura(n.izqda));
      } catch (Exception e) {
      }
      n.drcha = n1.izqda;
      n1.izqda = n;
      if (n1.fe == 1) {
         n.fe = 0;
         n1.fe = 0;
      } else {
         n.fe = 1;
         n1.fe = -1;
      }
      n = n1;
      return n;
   }

   /**
    * <h1>Rotacion Derecha-Izquierda</h1>
    * Metodo que se encarga de las rotaciones DI cuando son necesarias.
    * @param n Nodo que tiene un factor de balance diferente de -1, 0, 1
    * @param n1 el hijo derecho del nodo N
    * @return
    */
   private NodoAVL RotacionDI(NodoAVL n, NodoAVL n1) {
      try {
         Bitacora("Derecha-Izquierda", n.getUsuario().getNick()
                 + ", " + n1.getUsuario().getNick() + ", "
                 + n1.izqda.getUsuario().getNick(), Altura(n.drcha) - Altura(n.izqda));
      } catch (Exception e) {
      }
      NodoAVL n2;
      n2 = n1.izqda;
      n.drcha = n2.izqda;
      n2.izqda = n;
      n1.izqda = n2.drcha;
      n2.drcha = n1;
      if (n2.fe == 1) {
         n.fe = -1;
      } else {
         n.fe = 0;
      }
      if (n2.fe == -1) {
         n1.fe = 1;
      } else {
         n1.fe = 0;
      }
      n2.fe = 0;
      n = n2;
      return n;
   }

   /**
    * <h1>Rotacion Izquierda-Izquierda</h1>
    * Metodo que se encarga de las rotaciones DD cuando son necesarias.
    * @param n Nodo que tiene un factor de balance diferente de -1, 0, 1
    * @param n1 el hijo izquierdo del nodo N
    * @return
    */
   private NodoAVL RotacionII(NodoAVL n, NodoAVL n1) {
      try {
         Bitacora("Izquierda-Izquierda", n.getUsuario().getNick()
                 + ", " + n1.getUsuario().getNick() + ", "
                 + n1.izqda.getUsuario().getNick(), Altura(n.drcha) - Altura(n.izqda));
      } catch (Exception e) {
      }
      n.izqda = n1.drcha;
      n1.drcha = n;
      if (n1.fe == -1) {
         n.fe = 0;
         n1.fe = 0;
      } else {
         n.fe = -1;
         n1.fe = 1;
      }
      n = n1;
      return n;
   }

   /**
    * <h1>Rotacion Izquierda-Derecha</h1>
    * Metodo que se encarga de las rotaciones DD cuando son necesarias.
    * @param n Nodo que tiene un factor de balance diferente de -1, 0, 1
    * @param n1 el hijo izquierdo del nodo N
    * @return
    */
   private NodoAVL RotacionID(NodoAVL n, NodoAVL n1) {
      try {
         Bitacora("Izquierda-Derecha", n.getUsuario().getNick() + ", "
                 + n1.getUsuario().getNick() + ", "
                 + n1.drcha.getUsuario().getNick(), Altura(n.drcha) - Altura(n.izqda));
      } catch (Exception e) {
      }
      NodoAVL N2;
      N2 = n1.drcha;
      n.izqda = N2.drcha;
      N2.drcha = n;
      n1.drcha = N2.izqda;
      N2.izqda = n1;
      if (N2.fe == 1) {
         n1.fe = -1;
      } else {
         n1.fe = 0;
      }
      if (N2.fe == -1) {
         n.fe = 1;
      } else {
         n.fe = 0;
      }
      N2.fe = 0;
      n = N2;
      return n;
   }

   /* ************************************************************************* *
    *                            METODOS PARA ELIMINAR                          *
    * ************************************************************************* */
   public NodoAVL Aniquilar(String Nick) {
      NodoAVL usuario = buscar(Nick);
      if (usuario != null) {
         if (UsuarioExistente(Nick)) {
            boolean cambioDEaltura = false;
            supermacho = cambioDEaltura;
            raiz = BorrarNodo(raiz, Nick, cambioDEaltura);
         }
      }
      return raiz;
   }
   private boolean supermacho = false;

   private NodoAVL BorrarNodo(NodoAVL subArbol, String clave, boolean CambioDeAltura) {
      if (subArbol != null) {
         int comparacion = clave.compareTo(subArbol.getUsuario().getNick());
         if (comparacion > 0) {
            NodoAVL auxiliar;
            auxiliar = BorrarNodo(subArbol.drcha, clave, CambioDeAltura);
            subArbol.drcha = auxiliar;
            CambioDeAltura = supermacho;
            if (CambioDeAltura == true) {
               subArbol = Equilibrar2(subArbol, CambioDeAltura);
            }
         } else if (comparacion < 0) {
            NodoAVL auxiliar;
            auxiliar = BorrarNodo(subArbol.izqda, clave, CambioDeAltura);
            subArbol.izqda = auxiliar;
            CambioDeAltura = supermacho;
            if (CambioDeAltura == true) {
               subArbol = Equilibrar(subArbol, CambioDeAltura);
            }
         } else if (comparacion == 0) {
            NodoAVL auxiliar;
            auxiliar = subArbol;
            if (auxiliar.izqda == null) {
               subArbol = auxiliar.drcha;
               CambioDeAltura = true;
               supermacho = CambioDeAltura;
            } else if (auxiliar.drcha == null) {
               subArbol = auxiliar.izqda;
               CambioDeAltura = true;
               supermacho = CambioDeAltura;
            } else {
               NodoAVL auxiliar2;
               auxiliar2 = RealizarCambio(subArbol, subArbol.izqda, CambioDeAltura);
               subArbol.izqda = auxiliar2;
               CambioDeAltura = supermacho;//para sustituirlo
               if (CambioDeAltura == true) {
                  subArbol = Equilibrar(subArbol, CambioDeAltura);
               }
            }
            auxiliar = null;
         }
      }
      return subArbol;
   }

   private NodoAVL Equilibrar(NodoAVL n, Boolean CambiaH) {
      NodoAVL n1;
      switch (n.fe) {//Factor de Equilibrio del nodo raiz en el subarbol actual
         case -1: {
            n.fe = 0;// como agregue uno or la derecha qda en 0
            break;
         }
         case 0: {
            n.fe = 1;  // le sumo uno
            CambiaH = false;
            supermacho = false;
            break;
         }
         case +1: {
            n1 = n.drcha;
            if (n1.fe >= 0) {
               if (n1.fe == 0) {
                  CambiaH = false;
                  supermacho = false;
               }
               n = RotacionDD(n, n1);
            } else {
               n = RotacionDI(n, n1);
            }
            break;
         }
      }

      return n;
   }

   private NodoAVL Equilibrar2(NodoAVL n, Boolean CambiaH) {
      NodoAVL n1;
      System.out.println("alguien llamo2");
      switch (n.fe) {//Factor de Equilibrio del nodo raiz en el subarbol actual
         case +1: {
            n.fe = 0;// como agregue uno or la derecha qda en 0
            break;
         }
         case 0: {
            n.fe = -1;  // le sumo uno
            CambiaH = false;
            supermacho = false;
            break;
         }
         case -1: {

            n1 = n.izqda;

            if (n1.fe <= 0) {
               if (n1.fe == 0) {

                  CambiaH = false;
                  supermacho = false;
               }

               n = RotacionII(n, n1);
               break;
            } else {
               n = RotacionID(n, n1);
            }
            break;
         }
      }

      return n;
   }

   private NodoAVL RealizarCambio(NodoAVL n, NodoAVL actual, Boolean CambiaH) {
      if (actual.drcha != null) {
         NodoAVL auxiliar3;
         auxiliar3 = RealizarCambio(n, actual.drcha, CambiaH);
         CambiaH = supermacho;
         actual.drcha = auxiliar3;
         if (CambiaH == true) {
            actual = Equilibrar2(actual, CambiaH);
         }
      } else {
         n.setUsuario(actual.getUsuario());
         n = actual;
         actual = actual.izqda;
         n.setUsuario(null);
         n = null;
         CambiaH = true;
         supermacho = CambiaH;
      }

      return actual;
   }

   /* ************************************************************************* *
    *                         METODOS PARA MANEJAR LA BITACORA                  *
    * ************************************************************************* */
   /**
    * <h1>Bitacora</h1>
    * Este metodo se utiliza para llevar un control de las rotaciones que se han
    * realizado en el arbol, se guarda en un archivo de texto llamado Bitacora.txt
    * @param TipoDeRotacion
    * @param NodosInvolucrados
    */
   private void Bitacora(String TipoDeRotacion, String NodosInvolucrados, int fe) {
      try {
         File bitacora = new File("bitacora.txt");
         FileWriter fw = new FileWriter(bitacora, true);
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter salida = new PrintWriter(bw);
         salida.println("Factor de equilibrio: " + fe);
         salida.println("Rotacion " + TipoDeRotacion);
         salida.println("Nodos en la rotación: " + NodosInvolucrados);
         salida.println("Fecha: " + Calendar.getInstance().getTime().toLocaleString());
         salida.println();
         salida.close();
      } catch (IOException io) {
      }
   }

   /**
    * Metodo para abrir la bitacora
    * @return
    */
   public String AbrirBitacora() {
      String s = "";
      String Texto = "";
      try {
         FileReader fr = new FileReader(new File("temp", "bitacora.txt"));
         BufferedReader entrada = new BufferedReader(fr);
         while (true) {
            s = entrada.readLine();
            if (s != null) {
               Texto = Texto + s + "\n";
            } else {
               return Texto;
            }
         }
      } catch (Exception x) {
      }
      return Texto;
   }

   /* ************************************************************************* *
    *                     METODOS PARA GENERAR EL GRAFICO                       *
    * ************************************************************************* */
   public void GraficarArbol() {
      Graficar grafica = new Graficar(NodosRelaciones());
   }

   /**
    * Llama al metodo GenerarNodosYRelaciones.
    * @return Los Nodos y relaciones que tiene el arbol
    */
   private String NodosRelaciones() {
      Nodos = "";
      Relaciones = "";
      GenerarNodosYRelaciones(raiz);
      return Nodos + Relaciones;
   }

   /**
    * Hece un recorrido InOrden al arbol y genera cada uno de los nodos
    * y relaciones para poder dibujar el grafico
    * @param Nodo
    */
   private void GenerarNodosYRelaciones(NodoAVL Nodo) {
      if (Nodo == null) {
         return;
      } else {
         //================== CREACION DE LOS NODOS ==================\\
         Nodos += "\t\"" + Nodo.getUsuario().getNick() + "\" [\n"; //nombre del nodo
         Nodos += "\t\tfontsize = \"14\"\n";//tamaño de fuente en el nodo
         Nodos += "\t\tfontname = \"Times-Roman\"\n";//tipo de fuente en el nodo
         Nodos += "\t\tfontcolor = \"black\"\n";//color del texto en el nodo
         Nodos += "\t\tlabel = \"<f0> |<f1>" + Nodo.getUsuario().getNick() + "|<f2>\"\n";//texto en el nodo
         //control del color y forma del nodo
         if (Nodo.izqda == null && Nodo.drcha == null) { // para los nodos hoja
            Nodos += "\t\tshape = \"record\"\n";
            Nodos += "\t\tfillcolor = \"greenyellow\"\n";
         } else {
            Nodos += "\t\tshape = \"record\"\n";
            Nodos += "\t\tfillcolor = \"lightblue\"\n";
         }
         Nodos += "\t\tstyle = \"filled\"\n";
         Nodos += "\t]\n";

         //================== CREACION DE LAS RELACIONES ==================\\
         //DERECHA
         if (Nodo.drcha != null) {
            Relaciones += "\t\"" + Nodo.getUsuario().getNick() + "\":f2 -> \"" + Nodo.drcha.getUsuario().getNick() + "\" [\n";
            Relaciones += "\t\tfontsize = \"14\"\n";
            Relaciones += "\t\tfontname = \"Times-Roman\"\n";
            Relaciones += "\t\tfontcolor = \"black\"\n";
            Relaciones += "\t\tstyle = \"solid\"\n";
            Relaciones += "\t]\n";
         }
         //IZQUIERDA
         if (Nodo.izqda != null) {
            Relaciones += "\t\"" + Nodo.getUsuario().getNick() + "\":f0 -> \"" + Nodo.izqda.getUsuario().getNick() + "\" [\n";
            Relaciones += "\t\tfontsize = \"14\"\n";
            Relaciones += "\t\tfontname = \"Times-Roman\"\n";
            Relaciones += "\t\tfontcolor = \"black\"\n";
            Relaciones += "\t\tstyle = \"solid\"\n";
            Relaciones += "\t]\n";
         }
         //----------------------------------------------------------------------
         GenerarNodosYRelaciones(Nodo.izqda);
         GenerarNodosYRelaciones(Nodo.drcha);
      }
   }

   /* ************************************************************************* *
    *                               OTROS METODOS                               *
    * ************************************************************************* */
   /**
    * <h1>Altura</h1>
    * @param raiz El nodo del que se desea saber su altura
    * @return La altura de nodo raiz
    */
   private int Altura(NodoAVL raiz) {
      if (raiz == null) {
         return 0;
      } else {
         return 1 + Math.max(Altura(raiz.izqda), Altura(raiz.drcha));
      }
   }

   /**
    * <h1>ArbolVacio</h1>
    * @param R El nodo del que se desea saber si esta vacio.
    * @return true si el nodo no tiene descendientes false de lo contrario
    */
   private boolean ArbolVacio(NodoAVL R) {
      return (R == null);
   }

   /**
    * <h1>UsuarioExistente</h1>
    * metodo para buscar a un usuario determindo por medio de su Nick.
    * @param Nick
    * @return
    */
   private boolean UsuarioExistente(String Nick) {
      NodoAVL Aux = raiz;
      boolean miembro = false;
      while (Aux != null) {
         if (Nick.equalsIgnoreCase(Aux.getUsuario().getNick())) {
            miembro = true;
            Aux = null;
         } else {
            if (Nick.compareToIgnoreCase(Aux.getUsuario().getNick()) > 0) {
               Aux = Aux.drcha;
            } else {
               Aux = Aux.izqda;
               if (Aux == null) {
                  miembro = false;
               }
            }
         }
      }
      return miembro;
   }
}

class Graficar {

   public Graficar(String NodosRelaciones) {
      EscribirArchivo(NodosRelaciones);
      GenerarImagen();
   }

   private void EscribirArchivo(String NodosRelaciones) {
      try {
         FileWriter fw = new FileWriter(new File("grafico.lol"), false);
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter salida = new PrintWriter(bw);
         // Linea de inicio
         salida.println("digraph \"G\" {");
//            salida.println("\trankdir=LR;");
         salida.println("\tsize =\"10,6\";");
         salida.println("\tratio=auto;");
         // ATRIBUTOS PARA TODA EL GRAFICO EN GENERAL
         salida.println("\tgraph [");
         salida.println("\t\tfontsize = \"14\"");
         salida.println("\t\tfontname = \"Times-Roman\"");
         salida.println("\t\tfontcolor = \"black\"");
         salida.println("\t]");
         // ATRIBUTOS PARA TODOS LOS NODOS
         salida.println("\tnode  [");
         salida.println("\t\tfontsize = \"14\"");
         salida.println("\t\tfontname = \"Times-Roman\"");
         salida.println("\t\tfontcolor = \"black\"");
         salida.println("\t\tshape = \"record\"");
         salida.println("\t\tstyle = \"solid\"");
         salida.println("\t]");
         // ATRIBUTOS PARA TODOS LOS LADOS
         salida.println("\tedge [");
         salida.println("\t\tfontsize = \"14\"");
         salida.println("\t\tfontname = \"Times-Roman\"");
         salida.println("\t\tfontcolor = \"black\"");
         salida.println("\t\tstyle = \"solid\"");
         salida.println("\t]");
         //se genera cada uno de los nodos y las relaciones
         salida.println(NodosRelaciones);
         // Linea Final
         salida.println("}");
         salida.close();
      } catch (IOException IO) {
      }
   }

   /**
    * Metodo para generar el grafico usando graphviz
    * @param nombre El nombre con que sera guardado el archivo
    * @param ruta File que representa la ruta del archivo a ser creado
    */
   private void GenerarImagen() {
      try {
         String dotPath = "dot";

         String fileInputPath = new File("grafico.lol").getAbsolutePath();
         String fileOutputPath = new File("grafico.png").getAbsolutePath();

         String tParam = "-Tpng";
         String tOParam = "-o";

         String[] cmd = {dotPath, tParam, fileInputPath, tOParam, fileOutputPath};

         Runtime rt = Runtime.getRuntime();

         rt.exec(cmd);

         Thread.sleep(500);

         Desktop.getDesktop().open(new File("grafico.png"));

      } catch (Exception ex) {
      }
   }
}
