/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.Productos;
import Modelo.Proveedores;
import Modelo.Usuarios;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class RegistroProductoController implements Initializable {
    @FXML public TextField productoTxt;
    @FXML public TextField descripcionTxt;
    @FXML public TextField precioTxt;
    @FXML public TextField cantidadTxt;
    @FXML public TextField codigoTxt;
    @FXML public TextField presentacionTxt;
    @FXML public TextField sustanciaTxt;
    @FXML public TextField tipoTxt;
    @FXML public TextField rubroTxt;
    @FXML public ComboBox combProveedor;
    @FXML public Button btnRegistrar;
    private int idProveedor;
    private ArrayList<String> proveedores;
    private ArrayList<Integer> idProveedores;
    private int posicionCombo;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        proveedores = new ArrayList<String>();
         idProveedores= new ArrayList<Integer>();
        llenarDatosProveedor();
        combProveedor.getItems().addAll(proveedores);
        
    }    
    public void registrar(){
    Productos producto = new Productos();
    producto.setNombre(productoTxt.getText());
    producto.setDescripcion(descripcionTxt.getText());
    producto.setPrecio(Double.valueOf(precioTxt.getText()));
    producto.setCantidad(Integer.valueOf(cantidadTxt.getText()));
    producto.setCodigo(codigoTxt.getText());
    producto.setPresentacion(presentacionTxt.getText());
    producto.setSustancia_activa(sustanciaTxt.getText());
    producto.setTipo(tipoTxt.getText());
    producto.setRubro(rubroTxt.getText());
    producto.setIdProveedor(idProveedor);
    producto.registrarProductos();
    }
    
    public void llenarDatosProveedor(){
    proveedores = new ArrayList<String>();
    idProveedores= new ArrayList<Integer>();
    Proveedores.llenarListaProveedores(proveedores,idProveedores);
    }
    
    public void seleccionarProveedor() {
        try {
            posicionCombo = proveedores.indexOf(combProveedor.getValue().toString());
            idProveedor = idProveedores.get(posicionCombo);
            btnRegistrar.setDisable(false);
        } catch (Exception e) {
        }

    }
    public void txtNumerico(KeyEvent evt) {
        char car = evt.getCharacter().charAt(0);
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }
     public void txtNumericoPunto(KeyEvent evt) {
        char car = evt.getCharacter().charAt(0);
        if ((car < '0' || car > '9') && (car > '.')) {
            evt.consume();
        }
    }
}
