/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.VentasController.cantidadProductos;
import static Controlador.VentasController.nombresProductos;
import static Controlador.VentasController.precioProductos;
import Modelo.Reportes;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class CobroController implements Initializable {
    @FXML
    public Text totalText;
    @FXML
    public TextField pagoTxt;
    @FXML
    public Text cambioText;
    
    private Double cambio;
    private Double pago;
    private Double total;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        totalText.setText(String.valueOf(VentasController.importeTotal));
        total = Double.parseDouble(totalText.getText());
        EnviarConEnter();
      
    }
    
    public void EnviarConEnter(){

    pagoTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER){
                cambio =Double.parseDouble(pagoTxt.getText())-Double.parseDouble(totalText.getText());
                cambioText.setText(String.valueOf(cambio));
            }
        }
    });
    }
    public void imprimirTicket() {
        pago = Double.parseDouble(pagoTxt.getText());
        cambio = Double.parseDouble(cambioText.getText());
        
        Reportes ticket = new Reportes();
        ticket.generarTicket(VentasController.cantidad, total,pago,cambio, VentasController.nombresProductos, VentasController.cantidadProductos, VentasController.precioProductos);

    }

}
