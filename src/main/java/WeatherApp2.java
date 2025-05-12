import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApp2 {
    public static void main(String[] args) {
        try {
            // Open-Meteo API URL
            String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,wind_speed_10m";

            // Create URL object
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read API response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Extract data manually (using basic String operations)
            String jsonResponse = response.toString();

            // Extract temperature
            int tempIndex = jsonResponse.indexOf("\"temperature_2m\":") + 17;
            String temperature = jsonResponse.substring(tempIndex, jsonResponse.indexOf(",", tempIndex));

            // Extract wind speed
            int windIndex = jsonResponse.indexOf("\"wind_speed_10m\":") + 18;
            String windSpeed = jsonResponse.substring(windIndex, jsonResponse.indexOf("}", windIndex));

            // Display weather data
            System.out.println("Weather Data:");
            System.out.println("Current Temperature: " + temperature + "°C");
            System.out.println("Current Wind Speed: " + windSpeed + " km/h");

        } catch (Exception e) {
            System.out.println("Error fetching weather data: " + e.getMessage());
        }
    }
}


