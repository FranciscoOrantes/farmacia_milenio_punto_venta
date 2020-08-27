/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Proveedores;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class RegistroProveedorController implements Initializable {

    @FXML
    public TextField razon_socialTxt;
    @FXML
    public TextField direccionTxt;
    @FXML
    public TextField telefonoTxt;
    @FXML
    public TextField correoTxt;
    @FXML
    public Button btnRegistrar;

    private String razon_social;
    private String direccion;
    private String telefono;
    private String correo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void registrar() throws Exception {
        if (razon_socialTxt.getText().equals("") || direccionTxt.getText().equals("") || telefonoTxt.getText().equals("") || correoTxt.getText().equals("")) {
            Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
            dialogoAlerta.setTitle("Advertencia");
            dialogoAlerta.setHeaderText("Campos No validos!");
            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();
        } else {
            razon_social = razon_socialTxt.getText();
            direccion = direccionTxt.getText();
            telefono = telefonoTxt.getText();
            correo = correoTxt.getText();

            Proveedores proveedor = new Proveedores();
            proveedor.setRazonSocial(razon_social);
            proveedor.setDireccion(direccion);
            proveedor.setTelefono(telefono);
            proveedor.setCorreo(correo);
            proveedor.registrarProveedor();
            razon_socialTxt.setText("");
            direccionTxt.setText("");
            telefonoTxt.setText("");
            correoTxt.setText("");

        }
    }
}
