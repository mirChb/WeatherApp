# WeatherApp
This is a simple weather app that allows users to search for the weather details of a location using the [WeatherAPI](https://www.weatherapi.com/).

## Features
* Search for the weather details of a location
* View the current weather conditions, temperature, wind speed, and humidity

## Libraries Used
* Retrofit: For making network requests to the Weather API
* Moshi: For parsing JSON responses from the API
* Coroutines: For asynchronous programming
* ViewModel: For implementing the MVVM architecture
* LiveData: For observing changes in data
* RecyclerView: For displaying lists of weather data

## Architecture
The app follows the MVVM architecture and implements the repository pattern. The app has two screens:

#### Search Screen: 
Allows users to search for a location by name or zip code

#### Weather Details Screen: 
Displays the weather details for a location

The app has the following components:

* View: The UI layer, includes the Search Screen and Weather Details Screen
* ViewModel: Acts as a mediator between the View and the Repository. It exposes LiveData 
objects that the View can observe for changes.
* Repository: The data layer, responsible for fetching weather data from the Weather API. It caches the data in memory to reduce network calls.

