package planning.webapp.openai;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class generateResponse {
	public static String openAi() {
		
	    String url = "https://api.openai.com/v1/chat/completions";
	    String apiKey = System.getenv("GPT_APIKEY"); 
	    String model = System.getenv("GPT_model");
	    String prompt="Please generate 1 motivation quote and only give me the quote";
	    String responseToOutput= "";
	    try {
	    	@SuppressWarnings("deprecation")
			URL obj = new URL(url);
	    	HttpURLConnection connection = (HttpURLConnection)obj.openConnection();
	    	connection.setRequestMethod("POST");
	    	connection.setRequestProperty("Authorization", "Bearer " + apiKey);
	    	connection.setRequestProperty("Content-Type", "application/json");
	    	//The request body
	    	String body =  "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
	    	connection.setDoOutput(true);
	    	OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
	    	writer.write(body);
	    	writer.flush();
	    	writer.close();
	    	
	    	//Response from ChatGPT
	    	BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    	String line = null;
	    	StringBuffer response = new StringBuffer();
	    	
	    	while ((line = br.readLine()) != null) {
	    		response.append(line);
	    	}
	    	br.close();
	    	
	    	//calls the method to extract the message
	    	responseToOutput = extractMessageFromJSONResponse(response.toString());
	    }catch(IOException e) {
	    	throw new RuntimeException(e);
	    }
		return responseToOutput;
	}
	
	public static String extractMessageFromJSONResponse(String response) {
		int start = response.indexOf("content") + 11;
		int end = response.indexOf("\"", start);
		return response.substring(start, end);
	}

}














