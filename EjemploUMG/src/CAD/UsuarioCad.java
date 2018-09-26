/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import DTO.usuario;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author JuaNFeR
 */
public class UsuarioCad extends CN{
    public usuario validausuario(String nombre, String password)throws  Exception{
          usuario miUser= new usuario();
        try {
          this.Conectar();
        PreparedStatement ps = this.getCon().prepareStatement("select * from usuario where usuario=? ");
            ps. setString(1, nombre);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                miUser.setUsuario(rs.getString(1));
                miUser.setPass(rs.getString(2));
             
            }
      
        } catch (Exception e) {
         
            throw e;
        }
       finally{
            this.Cerrar();
        }
        return miUser;
    }
      public List<usuario> listarUsuarios() throws Exception {
      List<usuario> misUsuarios= new ArrayList<>();

        try {
            this.Conectar();
            Statement st = this.getCon().createStatement();
            ResultSet rs = st.executeQuery("select * from usuario");

            while (rs.next()) {
                  usuario miUser = new usuario();
                miUser.setId(rs.getInt(1));
                miUser.setUsuario(rs.getString(2));
                miUser.setPass(rs.getString(3));
                misUsuarios.add(miUser);
            }
        } catch (Exception e) {
            misUsuarios = null;
            throw e;
        } finally {
            this.Cerrar();
        }
        return misUsuarios;
    }
     public int insertaUsuario(usuario miUsuario) throws Exception {
        int res = 0;
        String query = "insert into usuario( idusuario, usuario , password)"
                + " values(?,?,?)";
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, miUsuario.getId());
            st.setString(2, miUsuario.getUsuario());
            st.setString(3, miUsuario.getPass());
            
            st.executeUpdate();
            res = 1;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            this.Cerrar();
        }
        return res;
    }
public int actualizaUsuario(usuario miUsuario) throws Exception {
        int res = 0;
        String query = "update usuario set usuario=?,password=? where idusuario= '"+miUsuario.getId()+"'";
        try {
            this.Conectar();
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, miUsuario.getUsuario());
            st.setString(2, miUsuario.getPass());
            st.executeUpdate();
            res = 1;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            this.Cerrar();
        }
        return res;
    }

    public usuario consultaUsuario(int id) throws Exception {
        usuario miUser = new usuario();

        try {
            this.Conectar();
            Statement st = this.getCon().createStatement();
            ResultSet rs = st.executeQuery("select * from usuario where idusuario= '"+id+"'");
         if (rs.next()) {
                miUser.setId(rs.getInt(1));
                miUser.setUsuario(rs.getString(2));
                miUser.setPass(rs.getString(3));
            }

        } catch (Exception e) {
            miUser = null;
            throw e;
        } finally {
            this.Cerrar();
        }
        return miUser;
    }
   
}
