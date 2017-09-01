package MiServets;

import EntidadDAO.UsuarioDAO;
import Tokens.CodificarToken;
import com.google.gson.Gson;
import entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Manejador", urlPatterns = {"/Manejador"})
public class Manejador extends HttpServlet {

    
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
        String usuario =request.getParameter("usuario");
        String clave =request.getParameter("clave");
         
        PrintWriter out = response.getWriter();
        UsuarioDAO validar = new UsuarioDAO();
        String resul = validar.validar(usuario);
        
       String[] resultado = resul.split(",");
       String id = resultado[0];
       String nombre = resultado[1];
       String correo = resultado[2];
       String contraseña = resultado[3];
       String tipoUsuario = resultado[4];
       
       if (nombre.equals(usuario)) {
           if (contraseña.equals(clave)) {
          CodificarToken token = new CodificarToken();
           
           String res = token.token(usuario);
             
           out.print(res);  
           }else{
           out.print("La contraseña es incorecta");
           }
       }
   }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String usuario =request.getParameter("usuario");
        String correo =request.getParameter("correo");
        String clave =request.getParameter("clave");
        String tipo =request.getParameter("tipo");
        PrintWriter out = response.getWriter();
        Usuario u = new Usuario();
        u.setNombreUsuario(usuario);
        u.setMail(correo);
        u.setContraseña(clave);
        u.setTipoUsuario(tipo);
        
        UsuarioDAO ingresar = new UsuarioDAO();
        String resul = ingresar.agregarUsuario(u);
        
        out.print(resul);
    }


}
