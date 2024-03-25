package weatherAInformationApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import javax.swing.border.LineBorder;


public class WeatherAInformationApp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField cityTextField;
    private JLabel temperatureLabel;
    private JLabel humidityLabel;
    private JLabel windSpeedLabel;
    private JButton fetchButton;
    private JLabel weatherImageLabel; 
    
    // Constants for API access
    private static final String API_KEY = "fe1c96083717255b37dfc05cbc6ae755";
    private static final String API_BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private JLabel label_1;
    private JLabel lblWeatherInformation;

    // Constructor for the Weather Information App
    public WeatherAInformationApp() {
        setTitle("Weather Information App");
        setSize(450, 250); // Increased height to accommodate the weather image
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setForeground(new Color(0, 0, 0));
        mainPanel.setBackground(new Color(0, 0, 0));
        mainPanel.setBorder(new LineBorder(new Color(240, 240, 240), 2));
        mainPanel.setLayout(null);

        // Label and text field for entering the city
        JLabel label_3 = new JLabel("City: ");
        // Set visual properties for the label
        label_3.setForeground(new Color(255, 255, 255));
        label_3.setBackground(new Color(255, 255, 255));
        label_3.setBounds(7, 9, 97, 32);
        label_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(label_3);
        // Text field for entering the city name
        cityTextField = new JTextField();
        cityTextField.setForeground(new Color(0, 0, 0));
        cityTextField.setBounds(103, 9, 145, 32);
        cityTextField.setBackground(new Color(224, 255, 255));
        cityTextField.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
        mainPanel.add(cityTextField);

        
        // Button for fetching weather information
        fetchButton = new JButton("Fetch Weather");
        fetchButton.setBounds(269, 9, 140, 32);
        fetchButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        mainPanel.add(fetchButton);
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchWeatherData();
            }
        });

        
        // New label for the weather image
        JLabel label_4 = new JLabel("");
        label_4.setBounds(7, 41, 140, 32);
        mainPanel.add(label_4);

        // Label for displaying weather image
        weatherImageLabel = new JLabel();
        weatherImageLabel.setBounds(287, 41, 140, 32);
        mainPanel.add(weatherImageLabel);
        
        // Label for indicating weather information section
        lblWeatherInformation = new JLabel("Weather Information");
        lblWeatherInformation.setHorizontalAlignment(SwingConstants.CENTER);
        lblWeatherInformation.setForeground(new Color(0, 0, 255));
        lblWeatherInformation.setBounds(7, 52, 420, 53);
        lblWeatherInformation.setFont(new Font("Times New Roman", Font.BOLD, 24));
        mainPanel.add(lblWeatherInformation);

        // Labels for displaying temperature, humidity, and wind speed
        JLabel label = new JLabel("Temperature: ");
        label.setForeground(new Color(255, 255, 255));
        label.setBounds(36, 105, 111, 32);
        label.setFont(new Font("Times New Roman", Font.BOLD, 14));
        mainPanel.add(label);

        label_1 = new JLabel("Humidity: ");
        label_1.setForeground(new Color(255, 255, 255));
        label_1.setBounds(176, 105, 111, 32);
        label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
        mainPanel.add(label_1);

        JLabel label_2 = new JLabel("Wind Speed: ");
        label_2.setForeground(new Color(255, 255, 255));
        label_2.setBounds(316, 105, 111, 32);
        label_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
        mainPanel.add(label_2);

        // Labels for displaying temperature, humidity, and wind speed values
        temperatureLabel = new JLabel();
        temperatureLabel.setForeground(new Color(255, 255, 255));
        temperatureLabel.setBounds(36, 137, 111, 32);
        temperatureLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        temperatureLabel.setIcon(new ImageIcon("C:\\Users\\admin\\Downloads\\temperature (1) (1).png")); // Placeholder icon
        mainPanel.add(temperatureLabel);

        humidityLabel = new JLabel();
        humidityLabel.setForeground(new Color(255, 255, 255));
        humidityLabel.setBounds(176, 137, 111, 32);
        humidityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        humidityLabel.setIcon(new ImageIcon("C:\\Users\\admin\\Downloads\\humidity (1).png")); // Placeholder icon
        mainPanel.add(humidityLabel);

        windSpeedLabel = new JLabel();
        windSpeedLabel.setForeground(new Color(255, 255, 255));
        windSpeedLabel.setBounds(316, 137, 111, 32);
        windSpeedLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        windSpeedLabel.setIcon(new ImageIcon("C:\\Users\\admin\\Downloads\\wind (1).png")); // Placeholder icon
        mainPanel.add(windSpeedLabel);

        getContentPane().add(mainPanel);
        setVisible(true);

    }

 // Method to fetch weather data from API
    private void fetchWeatherData() {
        String city = cityTextField.getText().trim();
        if (!city.isEmpty()) {
            try {
                String weatherData = getWeatherData(city);
                updateWeatherLabels(weatherData);
                // Set fetchButton background color to green to indicate success
                fetchButton.setBackground(Color.green);
            } catch (IOException e) {
                // Set fetchButton background color to red to indicate error
                fetchButton.setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "Error fetching weather data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a city name", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

 // Method to make HTTP request and retrieve weather data
    private String getWeatherData(String city) throws IOException {
        String urlString = API_BASE_URL + "?q=" + city + "&appid=" + API_KEY;
        @SuppressWarnings("deprecation")
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();
        return response.toString();
    }

    // Method to update weather information labels with retrieved data
    private void updateWeatherLabels(String weatherData) {
        JSONObject json = new JSONObject(weatherData);
        JSONObject main = json.getJSONObject("main");
        double temperature = main.getDouble("temp") - 273.15; // Convert temperature to Celsius
        String formattedTemperature = String.format("%.2f", temperature); // Round temperature to 2 decimal places
        double humidity = main.getDouble("humidity");

        JSONObject wind = json.getJSONObject("wind");
        double windSpeed = wind.getDouble("speed");

        temperatureLabel.setText(formattedTemperature + "°C");
        humidityLabel.setText(humidity + "%");
        windSpeedLabel.setText(windSpeed + "m/s");
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeatherAInformationApp();
            }
        });
    }

}


    