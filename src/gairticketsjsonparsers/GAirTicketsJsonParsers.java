/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gairticketsjsonparsers;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author george
 */
public class GAirTicketsJsonParsers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Properties properties = new Properties();
                
        String amadeusKey;
        String iataCodesKey;
        
        FileInputStream is;
        try {
                is = new FileInputStream("GAirTickets.ini");
                properties.load(is);
                is.close();
        }
        catch (IOException e) {
        }        

        amadeusKey = properties.getProperty("amadeusKey");
        iataCodesKey = properties.getProperty("iataCodesKey");
        
        
        AirlineParser airline = new AirlineParser(iataCodesKey);
        System.out.println(airline.search("OA"));
        
        AirportParser airport = new AirportParser(amadeusKey);
        System.out.println(airport.search("the"));
        
    }
    
}
