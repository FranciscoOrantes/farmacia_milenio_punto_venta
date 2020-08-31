/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.VentasController;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Francisco
 */
public class Reportes {

    public void generarTicket(int articulosTotales,Double total,Double pago, Double cambio, List<String> nombres, List<Integer> cantidades, List<Double> precios) {
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Hora: " + hourFormat.format(date));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Fecha: " + dateFormat.format(date));
        String fechaHora = dateFormat.format(date).toString() + " " + hourFormat.format(date).toString();

        JRDataSource jr = new JREmptyDataSource();

        try {
            JasperReport ticket = null;
            String path = "/reportes/ticket.jasper";
            String path2 = "/Imagenes/logo.png";
            Map parametro = new HashMap();
            parametro.put("logo", this.getClass().getResourceAsStream(path2));
            parametro.put("fechahora", fechaHora);
            parametro.put("total", total);
            parametro.put("pago",pago);
            parametro.put("cambio",cambio);
            parametro.put("totalArticulos", articulosTotales);
            parametro.put("cantidades", cantidades);
            parametro.put("nombres", nombres);
            parametro.put("precios", precios);
            ticket = (JasperReport) JRLoader.loadObject(getClass().getResource(path));

            JasperPrint jprint = JasperFillManager.fillReport(ticket, parametro, jr);
            System.out.println(parametro.get("cantidades"));
            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            view.setVisible(true);
        } catch (Exception e) {
            System.err.println("Heey " + e);

        }
    }
    public void generarCorteCaja(String fecha,Double total,List<String>productos,List<String>codigos,List<Integer>cantidades,
            List<Double>montos,List<String>cajeros){
    JRDataSource jr = new JREmptyDataSource();
    try{
        JasperReport ticket = null;
        String path = "/reportes/cortecaja.jasper";
       
        Map parametro = new HashMap();
        parametro.put("fecha", fecha);
        parametro.put("total", total);
        parametro.put("productos", productos);
        parametro.put("folios", codigos);
        parametro.put("cantidades", cantidades);
        parametro.put("montos", montos);
        parametro.put("cajeros", cajeros);
        
      
         ticket = (JasperReport) JRLoader.loadObject(getClass().getResource(path));
         
          JasperPrint jprint = JasperFillManager.fillReport(ticket, parametro,jr);
          
          

        JasperViewer view = new JasperViewer(jprint, false);
       
        view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        view.setVisible(true);
            }catch(Exception e){
        System.err.println("Heey " +e);
        
            
            }
    }
}
