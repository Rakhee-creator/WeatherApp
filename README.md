# Weather App 🌤️  

## 📌 Overview  
A simple **Java-based Weather App** that allows users to enter a city name and fetch live weather data. No need to manually input latitude or longitude—the app does it for you!  

## ⚡ Features  
✔ Fetch real-time weather data 🌍  
✔ User-friendly GUI using **Java Swing** 💻  
✔ Uses **OpenWeatherMap API** for city-based weather info  
✔ Displays **temperature** & **wind speed**  

## 🛠 Tech Stack  
- **Java** (Core Logic & GUI)  
- **Swing** (User Interface)  
- **OpenWeatherMap API** (Weather Data)  

## 🔧 Installation  
1️⃣ Clone this repository:  
   ```bash
   git clone https://github.com/YOUR_GITHUB_USERNAME/WeatherApp.git  # WeatherApp
2️⃣ Open the project in your Java IDE (Eclipse/IntelliJ/VS Code)
3️⃣ Run the WeatherGui.java file
🚀 Usage
1️⃣ Enter the city name in the input field 🏙️
2️⃣ Click "Get Weather" 🌦️
3️⃣ View live weather details (temperature, wind speed, etc.) 🌀
📅 Future Enhancements
✅ Add humidity, pressure & forecast info
✅ Improve UI design & responsiveness
✅ Implement dark mode theme

##Code
📌 1. Importing Required Libraries
✔ BufferedReader & InputStreamReader → Help read the API response.
✔ HttpURLConnection → Allows connecting to external APIs via HTTP.
✔ URL → Helps create a network request to fetch data.

 3. Setting Up the API Call
String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,wind_speed_10m";

✔ Defines the API URL used to fetch weather data.
✔ Uses latitude (52.52) & longitude (13.41) for Berlin, Germany.
✔ Fetches current temperature & wind speed from Open-Meteo API.

📌 4. Creating the API Request
URL url = new URL(apiUrl);
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");
✔ Converts the apiUrl string into a valid URL object.
✔ Opens a connection using HttpURLConnection.
✔ Uses "GET" method to request data from API.

📌 5. Reading API Response Data
BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
StringBuilder response = new StringBuilder();
String line;
while ((line = reader.readLine()) != null) {
    response.append(line);
}
reader.close();


✔ BufferedReader reads response data line by line.
✔ StringBuilder response accumulates the full response.
✔ while loop reads API response until all data is fetched.
✔ reader.close() closes the stream once data retrieval is done.

📌 6. Extracting Temperature Data from JSON Response
String jsonResponse = response.toString();
int tempIndex = jsonResponse.indexOf("\"temperature_2m\":") + 17;
String temperature = jsonResponse.substring(tempIndex, jsonResponse.indexOf(",", tempIndex));


✔ Converts the API response into a String format for processing.
✔ Finds "temperature_2m" in the JSON response using indexOf.
✔ Extracts the temperature value using substring.

📌 7. Extracting Wind Speed from JSON Response
int windIndex = jsonResponse.indexOf("\"wind_speed_10m\":") + 18;
String windSpeed = jsonResponse.substring(windIndex, jsonResponse.indexOf("}", windIndex));


✔ Finds "wind_speed_10m" inside the JSON response.
✔ Extracts the wind speed value using substring.

📌 8. Displaying Weather Data
System.out.println("Weather Data:");
System.out.println("Current Temperature: " + temperature + "°C");
System.out.println("Current Wind Speed: " + windSpeed + " km/h");


✔ Prints weather details (temperature & wind speed) to the console!

📌 9. Error Handling Using try-catch
} catch (Exception e) {
    System.out.println("Error fetching weather data: " + e.getMessage());
}


✔ Wraps the API request in a try-catch block to handle errors.
✔ Prints an error message if the API request fails.

🚀 Summary of What  Code Does
✔ Makes an HTTP request to Open-Meteo API using latitude & longitude.
✔ Fetches weather data (temperature & wind speed) in JSON format.
✔ Extracts temperature & wind speed using string operations.
✔ Displays the extracted weather info in the console.
✔ Handles errors gracefully using exception handling.





