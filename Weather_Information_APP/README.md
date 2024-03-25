---

# Weather Information App

## Overview
This Weather Information App provides real-time weather information for a given city. It retrieves data from the OpenWeatherMap API and displays details such as temperature, humidity, and wind speed.

## Features
- User-friendly interface for entering the city name and fetching weather data.
- Displays temperature, humidity, and wind speed information.
- Indicates success or failure of data fetching with color-coded buttons.

## Installation
1. Clone the repository to your local machine:

```
git clone <repository_url>
```

2. Ensure you have Java Development Kit (JDK) installed on your machine.

3. Compile the Java source file:

```
javac weatherAInformationApp/WeatherAInformationApp.java
```

4. Run the application:

```
java weatherAInformationApp.WeatherAInformationApp
```

## Usage
1. Upon launching the application, enter the name of the city for which you want to fetch weather information in the provided text field.
2. Click the "Fetch Weather" button to retrieve weather data.
3. The application will display temperature, humidity, and wind speed information for the specified city.
4. If there is an error fetching data (e.g., invalid city name or network issue), an error message will be displayed.

## Dependencies
- Java Development Kit (JDK)
- OpenWeatherMap API

## Contributing
Contributions are welcome! If you encounter any bugs or have suggestions for improvements, please feel free to open an issue or submit a pull request.

## License
This project is licensed under the [MIT License](LICENSE).

---