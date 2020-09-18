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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class RegistroProductoController implements Initializable {

    @FXML
    public TextField productoTxt;
    @FXML
    public TextField descripcionTxt;
    @FXML
    public TextField precioTxt;
    @FXML
    public TextField cantidadTxt;
    @FXML
    public TextField codigoTxt;
    @FXML
    public TextField presentacionTxt;
    @FXML
    public TextField sustanciaTxt;
    @FXML
    public TextField tipoTxt;
    @FXML
    public TextField rubroTxt;
    @FXML
    public ComboBox combProveedor;
    @FXML
    public Button btnRegistrar;
    @FXML
    public Button btnLimpiar;
    @FXML
    public Text tituloProducto;

    private int idProveedor;
    private ArrayList<String> proveedores;
    private ArrayList<Integer> idProveedores;
    private int posicionCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tituloProducto.setText(ProductosController.tituloText);
        btnRegistrar.setText(ProductosController.tituloBoton);
        proveedores = new ArrayList<String>();
        idProveedores = new ArrayList<Integer>();
        llenarDatosProveedor();
        combProveedor.getItems().addAll(proveedores);
        combProveedor.setValue(ProductosController.tituloCombo);
        if (tituloProducto.getText().equals("Actualizar Producto")) {
            productoTxt.setText(ProductosController.nombre);
            descripcionTxt.setText(ProductosController.descripcion);
            precioTxt.setText(String.valueOf(ProductosController.precio));
            cantidadTxt.setText(String.valueOf(ProductosController.cantidad));
            codigoTxt.setText(ProductosController.codigo);
            presentacionTxt.setText(ProductosController.presentacion);
            sustanciaTxt.setText(ProductosController.sustancia);
            tipoTxt.setText(ProductosController.tipo);
            rubroTxt.setText(ProductosController.rubro);
            btnLimpiar.setVisible(false);

        }

    }

    public void registrar(Event event) throws SQLException {
        if (btnRegistrar.getText().equals("Registrar")) {
            if (productoTxt.getText().equals("") || descripcionTxt.getText().equals("")
                    || precioTxt.getText().equals("") || cantidadTxt.getText().equals("")
                    || codigoTxt.getText().equals("") || presentacionTxt.getText().equals("")
                    || sustanciaTxt.getText().equals("") || tipoTxt.getText().equals("") || rubroTxt.getText().equals("")) {
                Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
                dialogoAlerta.setTitle("Advertencia");
                dialogoAlerta.setHeaderText("Campos No validos!");
                dialogoAlerta.initStyle(StageStyle.UTILITY);
                dialogoAlerta.showAndWait();
            } else if (idProveedor == 0) {
                Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
                dialogoAlerta.setTitle("Advertencia");
                dialogoAlerta.setHeaderText("NO HA SELECCIONADO AL PROVEEDOR");
                dialogoAlerta.setContentText("Favor de seleccionar a un proveedor o en su caso registrar uno nuevo");
                dialogoAlerta.initStyle(StageStyle.UTILITY);
                dialogoAlerta.showAndWait();
            } else {
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
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        } else {
            actualizar(event);
        }
    }

    public void actualizar(Event event) throws SQLException {
        if (productoTxt.getText().equals("") || descripcionTxt.getText().equals("")
                || precioTxt.getText().equals("") || cantidadTxt.getText().equals("")
                || codigoTxt.getText().equals("") || presentacionTxt.getText().equals("")
                || sustanciaTxt.getText().equals("") || tipoTxt.getText().equals("") || rubroTxt.getText().equals("")) {
            Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
            dialogoAlerta.setTitle("Advertencia");
            dialogoAlerta.setHeaderText("Campos No validos!");
            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();
        } else {
            posicionCombo = proveedores.indexOf(combProveedor.getValue().toString());
            idProveedor = idProveedores.get(posicionCombo);
            String nombre = productoTxt.getText();
            String descripcion = descripcionTxt.getText();
            String codigo_barras = codigoTxt.getText();
            Double precio = Double.parseDouble(precioTxt.getText());
            int cantidad = Integer.parseInt(cantidadTxt.getText());
            String presentacion = presentacionTxt.getText();
            String sustancia_activa = sustanciaTxt.getText();
            String tipo_producto = tipoTxt.getText();
            String rubro = rubroTxt.getText();
            Productos producto = new Productos();
            producto.actualizar(ProductosController.id, nombre, codigo_barras, descripcion, precio, cantidad, presentacion, sustancia_activa, tipo_producto, rubro, idProveedor);
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    public void llenarDatosProveedor() {
        proveedores = new ArrayList<String>();
        idProveedores = new ArrayList<Integer>();
        Proveedores.llenarListaProveedores(proveedores, idProveedores);
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

    public void limpiar() {
        productoTxt.setText("");
        descripcionTxt.setText("");
        precioTxt.setText("");
        cantidadTxt.setText("");
        codigoTxt.setText("");
        presentacionTxt.setText("");
        sustanciaTxt.setText("");
        tipoTxt.setText("");
        rubroTxt.setText("");
        combProveedor.setValue("Proveedor");

    }
}
