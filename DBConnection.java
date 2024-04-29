/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_management_system;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hp
 */
public class DBConnection {
    public static Connection getConnection(){
        Connection con=null;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management","root","root");
            
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
    
}
