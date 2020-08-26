/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;

/**
 *
 * @author Francisco
 */
public class Ventas {
    public static void registrarVentas(ObservableList<Productos>ventas,String fecha,int usuario_id) throws SQLException{
     Conexion con = new Conexion();
     SecureRandom random = new SecureRandom();
     String folio = new BigInteger(130, random).toString(32);
     double monto =0;
        Connection st = con.conectate();
        try {
            Statement execute = st.createStatement();
        PreparedStatement pst =st.prepareStatement("INSERT INTO venta(producto_id,cantidad,monto,fecha,folio,usuario_id) VALUES(?,?,?,?,?,?)");
        for (Productos producto1 : ventas) {
            monto = producto1.getCantidad()*(producto1.getPrecio());
            System.out.println("Monto " + monto);
            pst.setInt(1, producto1.getId());
            pst.setInt(2, producto1.getCantidad());
            pst.setDouble(3, monto);
            pst.setString(4, fecha);
            pst.setString(5, folio);
            pst.setInt(6, usuario_id);
            pst.executeUpdate();
        }
        }catch(Exception e){
        System.err.println("ERROR " + e);
        }
        }
}
