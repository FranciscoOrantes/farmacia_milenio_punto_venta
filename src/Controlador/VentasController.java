/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Productos;
import Modelo.Reportes;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class VentasController implements Initializable {

    @FXML
    public Button btnVenta;
    @FXML
    private TableView<Productos> tablaVenta;
    @FXML
    private TableColumn cantCol;
    @FXML
    private TableColumn codCol;
    @FXML
    private TableColumn desCol;
    @FXML
    private TableColumn nomCol;
    @FXML
    private TableColumn imporCol;

    @FXML
    private TextField cantidadTxt;
    @FXML
    private TextField codigoTxt;
    @FXML
    private Text cantidadText;
    @FXML
    private Text importeText;
    private ObservableList<Productos> productos;
    private int cantidad;
    private String codigo;
    private int contador = 0;
    private List<String> nombresProductos;
    private List<Integer> cantidadProductos;
    private List<Double> precioProductos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productos = FXCollections.observableArrayList();
        nombresProductos = new ArrayList<String>();
        cantidadProductos = new ArrayList<Integer>();
        precioProductos = new ArrayList<Double>();
    }

 

    public void agregarProducto() throws SQLException {
        cantidad = Integer.valueOf(cantidadTxt.getText());
        codigo = codigoTxt.getText();
        //productos = FXCollections.observableArrayList();
        Productos.filtradoCodigo(productos, cantidad, codigo);
        tablaVenta.setItems(productos);
        cantCol.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("cantidadT"));
        codCol.setCellValueFactory(new PropertyValueFactory<Productos, String>("codigoT"));
        desCol.setCellValueFactory(new PropertyValueFactory<Productos, String>("descripcionT"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Productos, String>("nombreT"));
        imporCol.setCellValueFactory(new PropertyValueFactory<Productos, String>("precioT"));
        calcularTotal();
        contador = contador + cantidad;
        
        cantidadText.setText(String.valueOf(contador));
        cantidadTxt.setText("");
        codigoTxt.setText("");
        System.out.println("ARRAY DE PRODUCTOS NOMBRES " +nombresProductos.size());
    }
public void calcularTotal() {
        String nombre = null;
        Double precio = null;
        double res = 0;
        for (Productos producto1 : productos) {
            
            res = res + (producto1.getCantidadT() * Double.valueOf(producto1.getPrecioT()));
            System.out.println("Productoo " + producto1.getCantidad());
            nombre = producto1.getNombreT();
            precio = producto1.getPrecioT();
        }
        llenarListaTicket(cantidad, nombre, precio);
        importeText.setText(String.valueOf(res));
    }
public void llenarListaTicket(int cantidad, String nombre, Double precio){
    cantidadProductos.add(cantidad);
    nombresProductos.add(nombre);
    precioProductos.add(precio);
}
   public void imprimirTicket() {
        System.out.println(nombresProductos.get(nombresProductos.size()-1));
        Reportes ticket = new Reportes();
        ticket.generarTicket(Integer.valueOf(cantidadText.getText()),Double.parseDouble(importeText.getText()),nombresProductos,cantidadProductos,precioProductos);
    
   
   }

}
