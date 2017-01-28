/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gairticketsjsonparsers;

/**
 *
 * @author george
 */
public class AirportParser {
    public String airportSearchString = null;
    
    public void AirportParser(){
    }
    
    public void AirportParser(String _airportSearchString) {
        airportSearchString = _airportSearchString;
    }
    
    public String search(String _airportSearchString) {
        airportSearchString = _airportSearchString;
        return search();
    }
    
    public String search() {
        if (airportSearchString == null) {
            return null;
        }
        
        
    }
}
