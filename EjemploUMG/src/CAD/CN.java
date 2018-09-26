/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

/**
 *
 * @author JuaNFeR
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author JuaNFeR
 */
public class CN extends DatosActividad{
private Connection cn;
public Connection getCon(){
    return cn;
}
public void setCon(Connection cn){
    this.cn=cn;
    
}

public void Conectar() throws Exception{
    try
     {
        Class.forName(CN.DRIVER);
        cn = DriverManager.getConnection(CN.URL,CN.USER,CN.PASS);
     }catch(ClassNotFoundException | SQLException e)
     {
         System.out.println(e);
     }
}
public void Cerrar()throws Exception{
    try {
        if(cn!= null){
            if(cn.isClosed()==false){
                cn.close();
            }
        }
    } catch (Exception e) {
        throw e;
    }
}
}
