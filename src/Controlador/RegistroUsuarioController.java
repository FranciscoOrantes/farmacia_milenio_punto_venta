/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuarios;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class RegistroUsuarioController implements Initializable {
    @FXML
    public Button btnRegistrar;
    @FXML
    public ComboBox comboTipos;
    
    @FXML
    TextField user, name, ap, am, pass;
    private String tipoUsuario,username,contrasena,apellidoP,apellidoM,nombre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       btnRegistrar.setDisable(true);
        comboTipos.getItems().addAll(
                "Administrador",
                "Cajero"
        );
    }
public void seleccionarTipo() {
        try {
            tipoUsuario = comboTipos.getValue().toString();
            btnRegistrar.setDisable(false);
        } catch (Exception e) {
        }

    }
public void registrar() throws SQLException, Exception {

        if (name.getText().equals("") || ap.getText().equals("") || am.getText().equals("") || pass.getText().equals("")) {
            Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
            dialogoAlerta.setTitle("Advertencia");
            dialogoAlerta.setHeaderText("Campos No validos!");
            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();
        } else {
            username = user.getText();
            nombre = name.getText();
            apellidoP = ap.getText();
            apellidoM = am.getText();
            contrasena = pass.getText();

            Usuarios usuario = new Usuarios();
            usuario.setUsuario(username);
            usuario.setPassword(contrasena);
            usuario.setTipo_usuario(tipoUsuario);
            usuario.setNombre(nombre);
            usuario.setApellido_paterno(apellidoP);
            usuario.setApellido_materno(apellidoM);
            usuario.registrarUsuario();
            user.setText("");
            name.setText("");
            ap.setText("");
            am.setText("");
            pass.setText("");
            comboTipos.valueProperty().set(null);
            btnRegistrar.setDisable(true);
     
          
            

        }

    }
    
}
