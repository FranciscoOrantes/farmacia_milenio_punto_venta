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
    public Button btnEditar;
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
    public static String titulo;
    public static String tituloBoton;
    public static int idProveedor;
    public static String razon_social;
    public static String telefono;
    public static String direccion;
    public static String correo;
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
        btnEditar.setDisable(true);

    }

    public void abrirVentanaRegistro() throws IOException {
        titulo = "Registrar Proveedor";
        tituloBoton = "Registrar";
        loaderInicioAdmin = new FXMLLoader(getClass().getResource("/Vista/RegistroProveedor.fxml"));
        Parent root1 = (Parent) loaderInicioAdmin.load();
        ventanaInicio = new Stage();
        ventanaInicio.setScene(new Scene(root1));

        ventanaInicio.show();
    }

    public void inicializarTablaProveedores() {

        proveedores = FXCollections.observableArrayList();
        Proveedores.llenarInfoProveedores(proveedores);
        tablaProveedores.setItems(proveedores);
        razon_socialCol.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("razon_socialT"));
        direccionCol.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("direccionT"));
        telefonoCol.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("telefonoT"));
        correoCol.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("correoT"));
    }

    public void actualizarProveedor() throws IOException {
        titulo = "Actualizar Proveedor";
        tituloBoton = "Actualizar";
        loaderInicioAdmin = new FXMLLoader(getClass().getResource("/Vista/RegistroProveedor.fxml"));
        Parent root1 = (Parent) loaderInicioAdmin.load();
        ventanaInicio = new Stage();
        ventanaInicio.setScene(new Scene(root1));

        ventanaInicio.show();
    }

    public void seleccionProveedor() {
        btnEditar.setDisable(false);
        Proveedores proveedor = tablaProveedores.getSelectionModel().getSelectedItem();
        idProveedor = proveedor.getId();
        razon_social = proveedor.getRazon_socialT();
        direccion = proveedor.getDireccionT();
        telefono = proveedor.getTelefonoT();
        correo = proveedor.getCorreoT();
    }
}
