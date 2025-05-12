import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherGui {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // Create label and input field
        JLabel label = new JLabel("Enter City Name:");
        JTextField cityInput = new JTextField();
        JButton fetchButton = new JButton("Get Weather");

        // Result label
        JLabel resultLabel = new JLabel("Weather data will appear here...");

        // Add components to panel
        panel.add(label);
        panel.add(cityInput);
        panel.add(fetchButton);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);

        // Button action to fetch weather data
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cityName = cityInput.getText().trim();

                if (cityName.isEmpty()) {
                    resultLabel.setText("Please enter a valid city name!");
                    return;
                }

                String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName +
                        "&appid=YOUR_API_KEY&units=metric";

                try {
                    URL url = new URL(apiUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // Extract temperature manually from JSON response
                    String jsonResponse = response.toString();
                    int tempIndex = jsonResponse.indexOf("\"temp\":") + 7;
                    String temperature = jsonResponse.substring(tempIndex, jsonResponse.indexOf(",", tempIndex)).replace("\"", "");

                    int windIndex = jsonResponse.indexOf("\"speed\":") + 8;
                    String windSpeed = jsonResponse.substring(windIndex, jsonResponse.indexOf(",", windIndex)).replace("\"", "");

                    resultLabel.setText("Temp: " + temperature + "°C | Wind: " + windSpeed + " km/h");

                } catch (Exception ex) {
                    resultLabel.setText("Error fetching weather data!");
                }
            }
        });
    }
}

