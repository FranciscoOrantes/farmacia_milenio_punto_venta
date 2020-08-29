/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class VentanaInicialController implements Initializable {
  @FXML
  public Button btnProductos;
  public BorderPane panePrincipal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
    }    
    
   @FXML
    public void cambiarVista(){
        System.out.println("ENTRO AQUI ");
        FXMLoader objeto = new FXMLoader();
        AnchorPane vista = objeto.getPage("Productos");
        System.out.println(vista);
        panePrincipal.setCenter(vista);    
}    
    @FXML
    public void cambiarVistaUsuarios(){
    FXMLoader objeto = new FXMLoader();
        AnchorPane vista = objeto.getPage("Usuarios");
        panePrincipal.setCenter(vista);    
    }
    
     @FXML
    public void cambiarVistaProveedores(){
    FXMLoader objeto = new FXMLoader();
        AnchorPane vista = objeto.getPage("Proveedores");
        panePrincipal.setCenter(vista);    
    }
    
    @FXML
    public void cambiarVistaVentas(){
    FXMLoader objeto = new FXMLoader();
        AnchorPane vista = objeto.getPage("Ventas");
        panePrincipal.setCenter(vista);    
    }
    
}
