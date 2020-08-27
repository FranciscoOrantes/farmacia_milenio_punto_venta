/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

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
public class UsuariosController implements Initializable {

    @FXML
    public Button btnModalUsuario;
    static Stage ventanaInicio;
    static FXMLLoader loaderInicioAdmin;

    @FXML
    private TableView<Usuarios> tablaUsuarios;
    @FXML
    private TableColumn nombreCol;
    @FXML
    private TableColumn apellido_paternoCol;
    @FXML
    private TableColumn apellido_maternoCol;
    @FXML
    private TableColumn usuarioCol;
    @FXML
    private TableColumn rolCol;

    private ObservableList<Usuarios> usuarios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usuarios = FXCollections.observableArrayList();
        this.inicializarTablaUsuarios();
    }

    public void abrirVentanaRegistro() throws IOException {
        loaderInicioAdmin = new FXMLLoader(getClass().getResource("/Vista/RegistroUsuario.fxml"));
        Parent root1 = (Parent) loaderInicioAdmin.load();
        ventanaInicio = new Stage();
        ventanaInicio.setScene(new Scene(root1));

        ventanaInicio.show();
    }

    public void inicializarTablaUsuarios() {
        usuarios = FXCollections.observableArrayList();
        Usuarios.llenarInfoUsuarios(usuarios);
        tablaUsuarios.setItems(usuarios);
        nombreCol.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("nombreT"));
        apellido_paternoCol.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("apellidoPaternoT"));
        apellido_maternoCol.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("apellidoMaternoT"));
        usuarioCol.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("usuarioT"));
        rolCol.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("tipo_usuarioT"));
        

    }

}
