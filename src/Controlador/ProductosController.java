/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.ProveedoresController.loaderInicioAdmin;
import Modelo.Productos;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class ProductosController implements Initializable {

    static Stage ventanaInicio;
    static FXMLLoader loaderInicioAdmin;

    @FXML
    private TableView<Productos> tablaProductos;
    @FXML
    private TableColumn codigoCol;
    @FXML
    private TableColumn nombreCol;
    @FXML
    private TableColumn descripcionCol;
    @FXML
    private TableColumn precioCol;
    @FXML
    private TableColumn cantidadCol;
    @FXML
    private TableColumn presentacionCol;
    @FXML
    private TableColumn sustanciaCol;
    @FXML
    private TableColumn tipoCol;
    @FXML
    private TableColumn rubroCol;
    @FXML
    private TableColumn proveedorCol;

    private ObservableList<Productos> productos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productos = FXCollections.observableArrayList();
        this.inicializarTablaProductos();
    }

    public void abrirVentanaRegistro() throws IOException {
        loaderInicioAdmin = new FXMLLoader(getClass().getResource("/Vista/RegistroProducto.fxml"));
        Parent root1 = (Parent) loaderInicioAdmin.load();
        ventanaInicio = new Stage();
        ventanaInicio.setScene(new Scene(root1));

        ventanaInicio.show();
    }

    public void inicializarTablaProductos() {
        productos = FXCollections.observableArrayList();
        Productos.llenarInfoProductos(productos);
        tablaProductos.setItems(productos);
        codigoCol.setCellValueFactory(new PropertyValueFactory<Productos, String>("codigoT"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<Productos, String>("nombreT"));
        descripcionCol.setCellValueFactory(new PropertyValueFactory<Productos, String>("descripcionT"));
        precioCol.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precioT"));
        cantidadCol.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("cantidadT"));
        presentacionCol.setCellValueFactory(new PropertyValueFactory<Productos, String>("presentacionT"));
        sustanciaCol.setCellValueFactory(new PropertyValueFactory<Productos, String>("sustanciaT"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<Productos, String>("tipoT"));
        rubroCol.setCellValueFactory(new PropertyValueFactory<Productos, String>("rubroT"));
        proveedorCol.setCellValueFactory(new PropertyValueFactory<Productos, String>("proveedorT"));
    }
}
