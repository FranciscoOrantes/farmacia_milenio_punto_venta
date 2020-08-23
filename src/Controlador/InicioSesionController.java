/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.InicioSesion;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class InicioSesionController implements Initializable {
@FXML
    public TextField txtUsuario;
    @FXML
    public PasswordField txtPassword;
    @FXML
    public Button btnInicio;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML    
    public void iniciarSesion() throws SQLException, IOException{
    InicioSesion inicio = new InicioSesion();
    inicio.setUsuario(txtUsuario.getText());
    inicio.setContraseña(txtPassword.getText());
    inicio.iniciarSesion();
    }
}
