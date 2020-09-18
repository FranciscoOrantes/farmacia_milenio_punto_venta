/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmaciamilenio;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Francisco
 */
public class FarmaciaMilenio extends Application {
    
    static Stage ventanaLogin;
    String path = "/Imagenes/icono.png";
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        this.ventanaLogin = primaryStage;
        FarmaciaMilenio.ventanaLogin.getIcons().add(new Image(String.valueOf(getClass().getResource(path))));
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/InicioSesion.fxml"));
        Scene scene = new Scene(root);
        ventanaLogin.setScene(scene);
        ventanaLogin.setResizable(false);
        ventanaLogin.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
