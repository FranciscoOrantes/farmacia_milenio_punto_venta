/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Francisco
 */
public class InicioSesion {

    private String usuario;
    private String contraseña;
    public int idUsuarioActivo;
    static Stage ventanaInicio;
    static Stage ventanaLogin;
    static FXMLLoader loaderInicioAdmin;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void iniciarSesion() throws SQLException, IOException {
        Conexion con = new Conexion();
        Connection st = con.conectate();
        ResultSet rs;

        Statement execute2 = st.createStatement();
        PreparedStatement pst2 = st.prepareStatement(
                "SELECT * FROM usuario WHERE username= ? And password = ?");
        pst2.setString(1, this.getUsuario());
        pst2.setString(2, this.getContraseña());
        rs = pst2.executeQuery();
        if (rs.next()) {
            idUsuarioActivo = rs.getInt("id");
            Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
            dialogoAlerta.setTitle("Inicio de sesion");
            dialogoAlerta.setContentText("Iniciando sesion...");
            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();

            loaderInicioAdmin = new FXMLLoader(getClass().getResource("/Vista/VentanaInicial.fxml"));
            Parent root1 = (Parent) loaderInicioAdmin.load();
            ventanaInicio = new Stage();
            ventanaInicio.setScene(new Scene(root1));
            ventanaInicio.setResizable(false);
            ventanaInicio.setTitle("Inventario");
            ventanaInicio.show();
        } else {
            Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
            dialogoAlerta.setTitle("Advertencia");
            dialogoAlerta.setHeaderText("Datos No Validos");
            dialogoAlerta.setContentText("Usuario o Contraseña incorrecta");
            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();
            loaderInicioAdmin = new FXMLLoader(getClass().getResource("/Vista/InicioSesion.fxml"));
            Parent root1 = (Parent) loaderInicioAdmin.load();
            ventanaInicio = new Stage();
            ventanaInicio.setScene(new Scene(root1));
            ventanaInicio.setResizable(false);
            ventanaInicio.show();
        }
    }
}