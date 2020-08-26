/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Francisco
 */
public class Productos {
    private int cantidad;
    private double precio;
    private int id;
    private String nombre;
    private String descripcion;
    private String codigo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
   public void registrarProductos(String nombreCampo,String descripcionCampo,String precioCampo,int cantidadCampo,int folioCampo,int tipoProductoCampo,int tipoProvedorCampo){
        Conexion con = new Conexion();
        Connection st = con.conectate();
        try {
           
            Statement execute = st.createStatement();
            PreparedStatement pst =st.prepareStatement("INSERT INTO producto(nombre,descripcion,precio,cantidad,folio,tipo_id,proveedor_id) VALUES(?,?,?,?,?,?,?)");
            pst.setString(1, nombreCampo);
            pst.setString(2, descripcionCampo);
            pst.setString(3, precioCampo);
            pst.setInt(4, cantidadCampo);
            pst.setInt(5, folioCampo);
            pst.setInt(6, tipoProductoCampo);
            pst.setInt(7, tipoProvedorCampo);
            pst.executeUpdate();
            Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("Registro de productos");
                dialogoAlerta.setHeaderText("Correcto");
                dialogoAlerta.setContentText("Ha registrado un producto con éxito");
                dialogoAlerta.initStyle(StageStyle.UTILITY);
                dialogoAlerta.showAndWait();
        } catch (Exception e) {
            System.err.println("Error "+e);
        }
        }
    
}
