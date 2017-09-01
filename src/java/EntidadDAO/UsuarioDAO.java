package EntidadDAO;

import conexioBD.Conexion;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    
    public String agregarUsuario (Usuario u){
    String resultdo = "funciono";
    Conexion con = new Conexion();
    Connection reg = con.getConnection();
    PreparedStatement prs = null;
    String sql ="INSERT INTO `usuarios` (`nombre_usuario`, `e-mail_usuario`, `contrasena_usuario`, `tipo_usuario`) VALUES (?,?,?,?)";
        try {
            prs = reg.prepareStatement(sql);
            prs.setString(1, u.getNombreUsuario());
            prs.setString(2, u.getMail());
            prs.setString(3, u.getContraseña());
            prs.setString(4, u.getTipoUsuario());
            prs.execute();
            con.desconectar();
            
            return resultdo;
        } catch (SQLException e) {
             return e.getMessage();
        }
     
    }
    
    public String validar (String nombre){
     
          String id = null;
          String usuario = null;
          String mail = null;
          String clave =null;
          String tipo = null;
    String resultado=null;
    Conexion con = new Conexion();
    Connection reg = con.getConnection();
    PreparedStatement prs = null;
    String sql ="SELECT * FROM `usuarios` WHERE nombre_usuario =?";
        try {
            prs = reg.prepareStatement(sql);
            prs.setString(1, nombre);
            ResultSet rs = prs.executeQuery(); 
              if (rs.next()) {
                 id = rs.getString(1);
                 usuario = rs.getString(2);
                 mail = rs.getString(3);
                 clave = rs.getString(4);
                 tipo = rs.getString(5);
            }
       
              resultado = id+","+usuario+","+mail+","+clave+","+tipo;
              
            con.desconectar();
            
            return resultado;
        } catch (SQLException e) {
             return e.getMessage();
        }
    }
}
