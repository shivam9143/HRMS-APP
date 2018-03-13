package info.androidhive.slidingmenu;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.util.Log;

public class JsonParser {
	public String getJSON(String url) {
		StringBuilder builder = new StringBuilder();
		String line;
		HttpGet httpGet;
		String responseMessage;
		HttpClient httpClient = new DefaultHttpClient();
		 httpGet = new HttpGet(url);
		try {
			Log.e("inside Parser above ", "44444444444");

			HttpResponse httpResponse = httpClient.execute(httpGet);
			Log.e("inside Parser", "44444444444");

			StatusLine statusLine = httpResponse.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200)
			{
				
				HttpEntity httpEntity = httpResponse.getEntity();
				InputStream inputStream = httpEntity.getContent();
				//BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
				while ((line = bufferedReader.readLine()) != null) {
					builder.append(line);
				}
			}
			responseMessage = builder.toString();
		} catch (Exception ex) {
			responseMessage = ex.getMessage().toString();
		}
		return responseMessage;
	}

}
