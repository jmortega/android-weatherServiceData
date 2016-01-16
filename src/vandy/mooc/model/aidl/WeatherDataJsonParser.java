package vandy.mooc.model.aidl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import vandy.mooc.model.aidl.WeatherData.Main;
import vandy.mooc.model.aidl.WeatherData.Sys;
import vandy.mooc.model.aidl.WeatherData.Weather;
import vandy.mooc.model.aidl.WeatherData.Wind;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

/**
 * Parses the Json weather data returned from the Weather Services API
 * and returns a List of WeatherData objects that contain this data.
 */
public class WeatherDataJsonParser {
    /**
     * Used for logging purposes.
     */
    private final String TAG =
        this.getClass().getCanonicalName();

    /**
     * Parse the @a inputStream and convert it into a List of JsonWeather
     * objects.
     */
    public List<WeatherData> parseJsonStream(InputStream inputStream)
        throws IOException {

        // TODO -- you fill in here.
    	// Create a JsonReader for the inputStream.
    	try (JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"))){
    		
    		Log.d(TAG, "Parsing the results returned as an array");
    		
    		// Handle the array returned from the Weather Service.
    		return parseJsonWeatherDataArray(reader);
    		
        }
    }

    /**
     * Parse a Json stream and convert it into a List of WeatherData
     * objects.
     */
    public List<WeatherData> parseJsonWeatherDataArray(JsonReader reader)
        throws IOException {
    	
        // TODO -- you fill in here.
    	
    	List<WeatherData> weathers = new ArrayList<WeatherData>();
		reader.beginObject();

		try
		{
			// If the weather wasn't expanded return null;
			if (reader.peek() == JsonToken.END_OBJECT)
				return null;
			
			WeatherData jsonWeather = new WeatherData();
			weathers.add(jsonWeather);
			
			while (reader.hasNext())
			{
				String name = reader.nextName();
				switch (name)
				{
				case WeatherData.name_JSON:
					Log.d(TAG, "reading name field");
					String cityName = reader.nextString();
					jsonWeather.setName(cityName);
					Log.d(TAG, "reading cityname " + jsonWeather.getName());
					break;
				case WeatherData.main_JSON:
					Log.d(TAG, "reading main field");
					Main main = parseMain(reader);
					jsonWeather.setMain(main);
					break;
				case WeatherData.sys_JSON:
					Log.d(TAG, "reading sys field");
					Sys sys = parseSys(reader);
					jsonWeather.setSys(sys);
					break;					
				case WeatherData.wind_JSON:
					Log.d(TAG, "reading wind field");
					Wind wind = parseWind(reader);
					jsonWeather.setWind(wind);					
					break;					
				case WeatherData.weather_JSON:
					Log.d(TAG, "reading weather field");
					if (reader.peek() == JsonToken.BEGIN_ARRAY)
					{
						List<Weather> weatherList = parseWeathers(reader);
						jsonWeather.setWeathers(weatherList);
					}
					break;
				default:
					reader.skipValue();
					Log.d(TAG, "Skipping " + name + " field");
					break;
				}				
			}
		}
		finally
		{
			reader.endObject();
		}
	
		return weathers;
    }

    /**
     * Parse a Json stream and return a WeatherData object.
     */
    public WeatherData parseJsonWeatherData(JsonReader reader) 
        throws IOException {

        // TODO -- you fill in here.
    	reader.beginObject();

    	WeatherData weather = new WeatherData();
		try
		{
			while (reader.hasNext())
			{
				String name = reader.nextName();
				switch (name)
				{
				case WeatherData.cod_JSON:
					weather.setCod(reader.nextInt());
					Log.d(TAG, "reading weather main " + weather.getMain());
					break;
				case WeatherData.message_JSON:
					weather.setMessage(reader.nextString());
					Log.d(TAG, "reading message " + weather.getMessage());
					break;
				case WeatherData.name_JSON:
					weather.setName(reader.nextString());
					Log.d(TAG, "reading name " + weather.getName());
					break;
				default:
					reader.skipValue();
					Log.d(TAG, "ignoring " + name);
					break;
				}
			}
		}
		finally
		{
			reader.endObject();
		}
		return weather;
    }
    
    /**
     * Parse a Json stream and return a List of Weather objects.
     */
    public List<Weather> parseWeathers(JsonReader reader) throws IOException {
        // TODO -- you fill in here.
    	
    	Log.d(TAG, "reading weather elements");

		reader.beginArray();

		try
		{
			List<Weather> weathers = new ArrayList<Weather>();

			while (reader.hasNext())
				weathers.add(parseWeather(reader));

			return weathers;
		}
		finally
		{
			reader.endArray();
		}
    }

    /**
     * Parse a Json stream and return a Weather object.
     */
    public Weather parseWeather(JsonReader reader) throws IOException {
        // TODO -- you fill in here.
    	
    	reader.beginObject();

		Weather weather = new Weather();
		try
		{
			while (reader.hasNext())
			{
				String name = reader.nextName();
				switch (name)
				{
				case Weather.main_JSON:
					weather.setMain(reader.nextString());
					Log.d(TAG, "reading weather main " + weather.getMain());
					break;
				case Weather.description_JSON:
					weather.setDescription(reader.nextString());
					Log.d(TAG, "reading desc " + weather.getDescription());
					break;
				case Weather.icon_JSON:
					weather.setIcon(reader.nextString());
					Log.d(TAG, "reading icon " + weather.getIcon());
					break;
				case Weather.id_JSON:
					weather.setId(reader.nextLong());
					Log.d(TAG, "reading id " + weather.getId());
					break;
				default:
					reader.skipValue();
					Log.d(TAG, "ignoring " + name);
					break;
				}
			}
		}
		finally
		{
			reader.endObject();
		}
		return weather;
    }

    /**
     * Parse a Json stream and return a Main Object.
     */
    public Main parseMain(JsonReader reader) 
        throws IOException {
        // TODO -- you fill in here.
    	reader.beginObject();

		Main main = new Main();
		try
		{
			while (reader.hasNext())
			{
				String name = reader.nextName();
				switch (name)
				{
				case Main.temp_JSON:
					main.setTemp(reader.nextDouble());
					Log.d(TAG, "reading temp " + main.getTemp());
					break;
				case Main.pressure_JSON:
					main.setPressure(reader.nextDouble());
					Log.d(TAG, "reading preasure " + main.getPressure());
					break;
				case Main.humidity_JSON:
					main.setHumidity(reader.nextInt());
					Log.d(TAG, "reading humidity " + main.getHumidity());
					break;					
				default:
					reader.skipValue();
					Log.d(TAG, "ignoring " + name);
					break;
				}
			}
		}
		finally
		{
			reader.endObject();
		}
		
		return main;
	
    }

    /**
     * Parse a Json stream and return a Wind Object.
     */
    public Wind parseWind(JsonReader reader) throws IOException {
        // TODO -- you fill in here.
    	reader.beginObject();

		Wind wind = new Wind();
		try
		{
			while (reader.hasNext())
			{
				String name = reader.nextName();
				switch (name)
				{
				case Wind.deg_JSON:
					wind.setDeg(reader.nextDouble());
					Log.d(TAG, "reading wind deg " + wind.getDeg());
					break;
				case Wind.speed_JSON:
					wind.setSpeed(reader.nextDouble());
					Log.d(TAG, "reading wind speed " + wind.getSpeed());
					break;
				default:
					reader.skipValue();
					Log.d(TAG, "ignoring " + name);
					break;
				}
			}
		}
		finally
		{
			reader.endObject();
		}
		return wind;
    }

    /**
     * Parse a Json stream and return a Sys Object.
     */
    public Sys parseSys(JsonReader reader)
        throws IOException {
        // TODO -- you fill in here.
    	reader.beginObject();

		Sys sys = new Sys();
		try
		{
			while (reader.hasNext())
			{
				String name = reader.nextName();
				switch (name)
				{
				case Sys.sunrise_JSON:
					sys.setSunrise(reader.nextLong());
					Log.d(TAG, "reading sunrise " + sys.getSunrise());
					break;
				case Sys.sunset_JSON:
					sys.setSunset(reader.nextLong());
					Log.d(TAG, "reading sunset " + sys.getSunset());
					break;
				case Sys.country_JSON:
					sys.setCountry(reader.nextString());
					Log.d(TAG, "reading country " + sys.getCountry());
					break;
				case Sys.message_JSON:
					sys.setMessage(reader.nextDouble());
					Log.d(TAG, "reading message " + sys.getMessage());
					break;	
				default:
					reader.skipValue();
					Log.d(TAG, "ignoring " + name);
					break;
				}
			}
		}
		finally
		{
			reader.endObject();
		}
		return sys;
		
    }
}
