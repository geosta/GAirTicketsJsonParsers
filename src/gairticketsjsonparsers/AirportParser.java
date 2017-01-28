/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gairticketsjsonparsers;

import com.sun.jndi.toolkit.url.Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
        
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String airportJsonStr = null;
        
        try {
            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, params[0])
                    .appendQueryParameter(FORMAT_PARAM, format)
                    .appendQueryParameter(UNITS_PARAM, units)
                    .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
                    .appendQueryParameter(APPID_PARAM, "27949ea6b6dffa1dad1deb925c9b024b")
                    .build();

            URL url = new URL(builtUri.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            airportJsonStr = buffer.toString();            
            
        } catch (IOException e) {
            
        }
        
    }
}
