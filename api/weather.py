import requests

def get_weather(city, api_key):
    # URL to get weather data for the city
    url = f"http://api.openweathermap.org/data/2.5/weather?q={city}&appid={api_key}&units=metric"
    
    # Sending the request and getting the response
    response = requests.get(url)
    
    # Check if the response is valid
    if response.status_code == 200:
        data = response.json()
        # Extracting the relevant weather data
        weather = {
            "city": data["name"],
            "temperature": data["main"]["temp"],
            "description": data["weather"][0]["description"],
            "humidity": data["main"]["humidity"],
            "wind_speed": data["wind"]["speed"]
        }
        return weather
    else:
        return None

def print_weather_report(cities, api_key):
    for city in cities:
        weather = get_weather(city, api_key)
        if weather:
            print(f"Weather in {weather['city']}:")
            print(f"Temperature: {weather['temperature']}°C")
            print(f"Condition: {weather['description']}")
            print(f"Humidity: {weather['humidity']}%")
            print(f"Wind Speed: {weather['wind_speed']} m/s")
            print("-" * 40)
        else:
            print(f"Could not retrieve weather for {city}")

if __name__ == "__main__":
    # Your OpenWeatherMap API Key
    API_KEY = "your_openweather_api_key"

    # List of cities to get the weather for
    cities = ["London", "New York", "Tokyo", "Mumbai"]

    # Fetch and print weather for each city
    print_weather_report(cities, API_KEY)
