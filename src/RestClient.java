import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class RestClient {
	private String url;
	private String key;
	
	public RestClient() {
		this(null, null);
	}
	
	public RestClient(String key) {
		this(key, null);
	}
	
	public RestClient(String key, String url) {
		if(key == null)
			this.key = "f56b11cc51bc8cb9643ebc9139ba45a411b94ac6";
		if(url == null)
			this.url = "http://www.soldai.com/hermes/api/v2/hacerpregunta?key=";
	}
	
	public String getResponse(String question) {
		String output = "";
		try {
			//Initialize the url to access via get and accept json
			URL query = new URL(this.url + this.key + "&q=" + URLEncoder.encode(question, "UTF-8"));
			HttpURLConnection connection = (HttpURLConnection) query.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			
			//Throw an exception if the response is not ok
			if (connection.getResponseCode() != 200)
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());

			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			String temp;
			while ((temp = br.readLine()) != null)
				output += temp;
			
			output= this.extractText(output);
			
			connection.disconnect();
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	
	public String extractText(String json) {
		String[] parts = json.split("\"");
		return parts[3];	
	}
}