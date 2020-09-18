/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Reportes;
import Modelo.Ventas;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class CorteCajaController implements Initializable {

    @FXML
    private TableView<Ventas> tablaVentas;
    @FXML
    private TableColumn productoCol;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private TableColumn codigoCol;
    @FXML
    private TableColumn cantidadCol;
    @FXML
    private TableColumn montoCol;
    @FXML
    private TableColumn cajeroCol;
    @FXML
    private TableColumn fechaCol;
    @FXML
    private Button btnObtenerVentas;
    private ObservableList<Ventas> ventas;
    private List<String> productos;
    private List<String> codigos;
    private List<String> cajeros;
    private List<String> fechas;
    private List<Integer> cantidades;
    private List<Double> montos;

    private String fecha;

    @FXML
    public Text textTotalTexto;

    private Double total;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productos = new ArrayList<String>();
        codigos = new ArrayList<String>();
        cajeros = new ArrayList<String>();
        fechas = new ArrayList<String>();
        cantidades = new ArrayList<Integer>();
        montos = new ArrayList<Double>();
        ventas = FXCollections.observableArrayList();
        btnObtenerVentas.setDisable(true);
        dateTo.setDisable(true);
        Date objDate = new Date(); // Sistema actual La fecha y la hora se asignan a objDate 
        String strDateFormat = "dd-MM-yyyy"; // El formato de fecha está especificado  
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        fecha = objSDF.format(objDate);
        inicializarTablaVentas();

        try {
            sumaTotalVenta();
        } catch (SQLException ex) {
            Logger.getLogger(CorteCajaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inicializarTablaVentas() {
        ventas = FXCollections.observableArrayList();
        Ventas.llenarInfoVentas(fecha, ventas);
        tablaVentas.setItems(ventas);
        productoCol.setCellValueFactory(new PropertyValueFactory<Ventas, String>("productoT"));
        codigoCol.setCellValueFactory(new PropertyValueFactory<Ventas, String>("codigoT"));
        cantidadCol.setCellValueFactory(new PropertyValueFactory<Ventas, Integer>("cantidadT"));
        montoCol.setCellValueFactory(new PropertyValueFactory<Ventas, Double>("montoT"));
        cajeroCol.setCellValueFactory(new PropertyValueFactory<Ventas, String>("cajeroT"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<Ventas, String>("fechaT"));
    }

    public void sumaTotalVenta() throws SQLException {
        Ventas venta = new Ventas();
        total = venta.sumaTotalDia(fecha);
        textTotalTexto.setText("Total del día: $" + String.valueOf(total));

    }

    public void sumaTotalVentaFechas(String fecha1, String fecha2) throws SQLException {
        Ventas venta = new Ventas();
        total = venta.sumaTotalFechas(fecha1, fecha2);
        textTotalTexto.setText("Total generado: $" + String.valueOf(total));
    }

    public void desbloquear2doDate() {
        if (dateFrom.getValue().toString() != null) {
            dateTo.setDisable(false);
        }

    }

    public void desbloquearButtonObtener() {
        if (dateTo.getValue().toString() != null) {
            btnObtenerVentas.setDisable(false);
        }

    }

    public void llenarListas() {
        productos.clear();
        codigos.clear();
        cajeros.clear();
        cantidades.clear();
        montos.clear();
        fechas.clear();

        for (Ventas venta : ventas) {
            productos.add(venta.getProductoT());
            codigos.add(venta.getCodigoT());
            cajeros.add(venta.getCajeroT());
            cantidades.add(venta.getCantidadT());
            montos.add(venta.getMontoT());
            fechas.add(venta.getFechaT());
        }
    }

    public void generarCorteCaja() {
        llenarListas();
        Reportes reporte = new Reportes();
        reporte.generarCorteCaja(fecha, total, productos, codigos, cantidades, montos, cajeros, fechas);
    }

    public void obtenerVentas() throws SQLException {
        if (dateFrom.getValue().toString() == null || dateTo.getValue().toString() == null) {
            Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
            dialogoAlerta.setTitle("Error");
            dialogoAlerta.setHeaderText("Debe de seleccionar fechas válidas");
            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();
        } else {
            String mes;
            String dia;
            String mes2;
            String dia2;
            if (dateFrom.getValue().getMonthValue() < 10) {
                mes = "0" + dateFrom.getValue().getMonthValue();
            } else {
                mes = String.valueOf(dateFrom.getValue().getMonthValue());
            }

            if (dateFrom.getValue().getDayOfMonth() < 10) {
                dia = "0" + dateFrom.getValue().getDayOfMonth();
            } else {
                dia = String.valueOf(dateFrom.getValue().getDayOfMonth());
            }

            if (dateTo.getValue().getMonthValue() < 10) {
                mes2 = "0" + dateTo.getValue().getMonthValue();
            } else {
                mes2 = String.valueOf(dateTo.getValue().getMonthValue());
            }

            if (dateTo.getValue().getDayOfMonth() < 10) {
                dia2 = "0" + dateTo.getValue().getDayOfMonth();
            } else {
                dia2 = String.valueOf(dateTo.getValue().getDayOfMonth());
            }

            String fecha1 = dateFrom.getValue().getYear() + "-" + mes + "-" + dia;
            String fecha2 = dateTo.getValue().getYear() + "-" + mes2 + "-" + dia2;
            ventas = FXCollections.observableArrayList();
            Ventas.llenarInfoVentasFechas(fecha1, ventas, fecha2);
            System.out.println(ventas.size());
            tablaVentas.setItems(ventas);
            productoCol.setCellValueFactory(new PropertyValueFactory<Ventas, String>("productoT"));
            codigoCol.setCellValueFactory(new PropertyValueFactory<Ventas, String>("codigoT"));
            cantidadCol.setCellValueFactory(new PropertyValueFactory<Ventas, Integer>("cantidadT"));
            montoCol.setCellValueFactory(new PropertyValueFactory<Ventas, Double>("montoT"));
            cajeroCol.setCellValueFactory(new PropertyValueFactory<Ventas, String>("cajeroT"));
            fechaCol.setCellValueFactory(new PropertyValueFactory<Ventas, String>("fechaT"));
            textTotalTexto.setText("Total generado: $");
            sumaTotalVentaFechas(fecha1, fecha2);
        }
    }

    public void volverVentasDia() throws SQLException {
        btnObtenerVentas.setDisable(true);
        dateTo.setDisable(true);
        dateFrom.setValue(null);
        dateTo.setValue(null);
        inicializarTablaVentas();
        textTotalTexto.setText("Total del día: $");
        sumaTotalVenta();
    }

}
