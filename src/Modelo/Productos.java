/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Francisco
 */
public class Productos {

    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    private String codigo;

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getSustancia_activa() {
        return sustancia_activa;
    }

    public void setSustancia_activa(String sustancia_activa) {
        this.sustancia_activa = sustancia_activa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    private String presentacion;
    private String sustancia_activa;
    private String tipo;
    private String rubro;
    private int idProveedor;

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

    public Integer getId() {
        return id.get();
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public String getCodigoT() {
        return codigoT.get();
    }

    public void setCodigoT(SimpleStringProperty codigoT) {
        this.codigoT = codigoT;
    }

    public String getNombreT() {
        return nombreT.get();
    }

    public void setNombreT(SimpleStringProperty nombreT) {
        this.nombreT = nombreT;
    }

    public String getDescripcionT() {
        return descripcionT.get();
    }

    public void setDescripcionT(SimpleStringProperty descripcionT) {
        this.descripcionT = descripcionT;
    }

    public Double getPrecioT() {
        return precioT.get();
    }

    public void setPrecioT(SimpleDoubleProperty precioT) {
        this.precioT = precioT;
    }

    public Integer getCantidadT() {
        return cantidadT.get();
    }

    public void setCantidadT(SimpleIntegerProperty cantidadT) {
        this.cantidadT = cantidadT;
    }

    public String getPresentacionT() {
        return presentacionT.get();
    }

    public void setPresentacionT(SimpleStringProperty presentacionT) {
        this.presentacionT = presentacionT;
    }

    public String getSustanciaT() {
        return sustanciaT.get();
    }

    public void setSustanciaT(SimpleStringProperty sustanciaT) {
        this.sustanciaT = sustanciaT;
    }

    public String getTipoT() {
        return tipoT.get();
    }

    public void setTipoT(SimpleStringProperty tipoT) {
        this.tipoT = tipoT;
    }

    public String getRubroT() {
        return rubroT.get();
    }

    public void setRubroT(SimpleStringProperty rubroT) {
        this.rubroT = rubroT;
    }

    public String getProveedorT() {
        return proveedorT.get();
    }

    public void setProveedorT(SimpleStringProperty proveedorT) {
        this.proveedorT = proveedorT;
    }

    public SimpleIntegerProperty id = new SimpleIntegerProperty();
    public SimpleStringProperty codigoT = new SimpleStringProperty();
    public SimpleStringProperty nombreT = new SimpleStringProperty();
    public SimpleStringProperty descripcionT = new SimpleStringProperty();
    public SimpleDoubleProperty precioT = new SimpleDoubleProperty();
    public SimpleIntegerProperty cantidadT = new SimpleIntegerProperty();
    public SimpleStringProperty presentacionT = new SimpleStringProperty();
    public SimpleStringProperty sustanciaT = new SimpleStringProperty();
    public SimpleStringProperty tipoT = new SimpleStringProperty();
    public SimpleStringProperty rubroT = new SimpleStringProperty();
    public SimpleStringProperty proveedorT = new SimpleStringProperty();

    public Productos() {
    }
    //Agregar a la tabla Ventas
    public Productos(Integer id,int cantidad, String codigo, String descripcion, String nombre, Double precio) {
        this.id = new SimpleIntegerProperty(id);
        this.cantidadT = new SimpleIntegerProperty(cantidad);
        this.codigoT = new SimpleStringProperty(codigo);
        this.nombreT = new SimpleStringProperty(nombre);
        this.descripcionT = new SimpleStringProperty(descripcion);
        this.precioT = new SimpleDoubleProperty(precio);
    }

    public Productos(Integer id, String codigo, String nombre, String descripcion, Double precio, Integer cantidad,
            String presentacion, String sustancia, String tipo, String rubro, String proveedor) {
        this.id = new SimpleIntegerProperty(id);
        this.codigoT = new SimpleStringProperty(codigo);
        this.nombreT = new SimpleStringProperty(nombre);
        this.descripcionT = new SimpleStringProperty(descripcion);
        this.precioT = new SimpleDoubleProperty(precio);
        this.cantidadT = new SimpleIntegerProperty(cantidad);
        this.presentacionT = new SimpleStringProperty(presentacion);
        this.sustanciaT = new SimpleStringProperty(sustancia);
        this.tipoT = new SimpleStringProperty(tipo);
        this.rubroT = new SimpleStringProperty(rubro);
        this.proveedorT = new SimpleStringProperty(proveedor);
    }

    public void registrarProductos() {
        Conexion con = new Conexion();
        Connection st = con.conectate();
        try {

            Statement execute = st.createStatement();
            PreparedStatement pst = st.prepareStatement("INSERT INTO producto(nombre,descripcion,precio,cantidad,codigo_barras,presentacion,sustancia_activa,tipo_producto,rubro,proveedor_id) VALUES(?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, getNombre());
            pst.setString(2, getDescripcion());
            pst.setDouble(3, getPrecio());
            pst.setInt(4, getCantidad());
            pst.setString(5, getCodigo());
            pst.setString(6, getPresentacion());
            pst.setString(7, getSustancia_activa());
            pst.setString(8, getTipo());
            pst.setString(9, getRubro());
            pst.setInt(10, getIdProveedor());
            int res = pst.executeUpdate();
            if (res > 0) {
                mensajeExito();
            }

        } catch (Exception e) {
            System.err.println("Error " + e);
        }
    }

    public void mensajeExito() {
        Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
        dialogoAlerta.setTitle("Registro de productos");
        dialogoAlerta.setHeaderText("Correcto");
        dialogoAlerta.setContentText("Ha registrado un producto con éxito");
        dialogoAlerta.initStyle(StageStyle.UTILITY);
        dialogoAlerta.showAndWait();
    }

    public static void llenarInfoProductos(ObservableList<Productos> lista) {
        Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;

        try {
            Statement execute = st.createStatement();

            PreparedStatement pst = st.prepareStatement(
                    "SELECT * FROM producto INNER JOIN proveedor ON producto.proveedor_id=proveedor.id");
            
            rs = pst.executeQuery();
            while (rs.next()) {

                lista.add(
                        new Productos(
                                rs.getInt("producto.id"),
                                rs.getString("producto.codigo_barras"),
                                rs.getString("producto.nombre"),
                                rs.getString("producto.descripcion"),
                                rs.getDouble("producto.precio"),
                                rs.getInt("producto.cantidad"),
                                rs.getString("producto.presentacion"),
                                rs.getString("producto.sustancia_activa"),
                                rs.getString("producto.tipo_producto"),
                                rs.getString("producto.rubro"),
                                rs.getString("proveedor.razon_social")
                        )
                );

            }

        } catch (Exception e) {
            System.err.println("excetpcion " + e);

        }
    }

    public static void filtradoCodigo(ObservableList<Productos> lista,int cantidad, String codigo) throws SQLException {
        Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;
        Statement execute = st.createStatement();
        PreparedStatement pst = st.prepareStatement(
                "SELECT * FROM producto WHERE codigo_barras= ?");
        pst.setString(1, codigo);
        rs = pst.executeQuery();
        while (rs.next()) {
            lista.add(
                    new Productos(
                            rs.getInt("producto.id"),
                            cantidad,
                            rs.getString("producto.codigo_barras"),
                            rs.getString("producto.descripcion"),
                            rs.getString("producto.nombre"),
                            rs.getDouble("producto.precio")
                    )
            );
        }
    }

}
