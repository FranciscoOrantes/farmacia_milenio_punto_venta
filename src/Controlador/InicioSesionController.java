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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
       EnviarConEnter();
    }

   
    public void iniciarSesion(Event event) throws SQLException, IOException {
        InicioSesion inicio = new InicioSesion();
        inicio.setUsuario(txtUsuario.getText());
        inicio.setContraseña(txtPassword.getText());
        if (inicio.iniciarSesion()) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } else {
            txtUsuario.setText("");
            txtPassword.setText("");
        }

    }
    public void EnviarConEnter(){

    txtPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER){
                try {
                    iniciarSesion(event);
                } catch (SQLException ex) {
                    Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
    }
}
