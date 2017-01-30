/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gairticketsjsonparsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 *
 * @author george
 */
public class AirlineParser {
    
    public final String LOG_TAG = "AirlineParser";
    public String airlineSearchString = null;
    private String API_KEY = null;

    AirlineParser(String _apiKey) {
        API_KEY = _apiKey;
    }
    
   
    
    public String search(String _airlineSearchString) {
        airlineSearchString = _airlineSearchString;
        return search();
    }
    
    public String search() {
        if (airlineSearchString == null) {
            return null;
        }
        
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String airportJsonStr = null;
        
        String API_BASE_URL = "https://iatacodes.org/api/v6/airlines";
        
        try {

            String theURL = API_BASE_URL + "?api_key=" + API_KEY +
                                "&code=\"" + URLEncoder.encode(airlineSearchString, "UTF-8") + "\"";
            URL url = new URL(theURL);
            

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
            System.out.println(LOG_TAG + "Error " + e);
        } catch (Exception  e) {
            System.out.println(LOG_TAG + e.getMessage() + e);
            e.printStackTrace();
             
        } finally {
                        if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    System.out.println(LOG_TAG + "Error closing stream" + e);
                }
            }
        }
        
        return airportJsonStr;        
    }
}
