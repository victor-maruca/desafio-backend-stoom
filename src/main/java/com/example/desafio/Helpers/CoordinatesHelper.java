package com.example.desafio.Helpers;

import com.example.desafio.Models.Address;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CoordinatesHelper {

    public static Double[] getCoordinates(Address address) throws Exception {
        StringBuilder str = new StringBuilder();
        str.append("https://maps.googleapis.com/maps/api/geocode/json?address=");
        str.append(getFormattedAddress(address));
        str.append("&key=AIzaSyDTK0igIQTCi5EYKL9tzOIJ9N6FUASGZos");

        URL url = new URL(str.toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        int status = con.getResponseCode();
        if (status > 299) {
            throw new Exception();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return getCoordinatesFromResponse(content.toString());
    }

    private static Double[] getCoordinatesFromResponse(String response) {
        try {
            JSONObject json = new JSONObject(response);
            JSONObject coordinates =
                    json.getJSONArray("results")
                            .getJSONObject(0)
                            .getJSONObject("geometry")
                            .getJSONObject("location");
            Double[] ret = new Double[2];
            ret[0] = coordinates.getDouble("lat");
            ret[1] = coordinates.getDouble("lng");
            return ret;
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private static String getFormattedAddress(Address address) {
        return address.getNumber() +
                "+" +
                address.getStreetName().replace(" ", "+") +
                ",+" +
                address.getCity().replace(" ", "+") +
                ",+" +
                address.getState().replace(" ", "+");
    }

}
