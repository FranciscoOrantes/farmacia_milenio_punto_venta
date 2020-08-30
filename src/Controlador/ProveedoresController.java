/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.UsuariosController.loaderInicioAdmin;
import Modelo.Proveedores;
import Modelo.Usuarios;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class ProveedoresController implements Initializable {
    @FXML
    public Button btnModalProveedor;
    
    @FXML
    private TableView<Proveedores> tablaProveedores;
    @FXML
    private TableColumn razon_socialCol;
    @FXML
    private TableColumn direccionCol;
    @FXML
    private TableColumn telefonoCol;
    @FXML
    private TableColumn correoCol;
    
    private ObservableList<Proveedores> proveedores;
    
    static Stage ventanaInicio;
    static FXMLLoader loaderInicioAdmin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        proveedores = FXCollections.observableArrayList();
        this.inicializarTablaProveedores();
        
    }    
    public void abrirVentanaRegistro() throws IOException{
    loaderInicioAdmin = new FXMLLoader(getClass().getResource("/Vista/RegistroProveedor.fxml"));
        Parent root1 = (Parent) loaderInicioAdmin.load();
        ventanaInicio = new Stage();
        ventanaInicio.setScene(new Scene(root1));

        ventanaInicio.show();
    }
    
    public void inicializarTablaProveedores(){
    
        proveedores = FXCollections.observableArrayList();
        Proveedores.llenarInfoProveedores(proveedores);
        tablaProveedores.setItems(proveedores);
        razon_socialCol.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("razon_socialT"));
        direccionCol.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("direccionT"));
        telefonoCol.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("telefonoT"));
        correoCol.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("correoT"));
    }
}
