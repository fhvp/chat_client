package chat.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import chat.primitive.HttpPrimitive;
import chat.primitive.HttpPrimitive.HttpPrimitiveStruct;
import chat.primitive.HttpPrimitiveType;


public class http_socket implements socket
{
	private int DEFAULT_TIMEOUT = 20000; // ms
	private String DEFAULT_REQUEST_METHOD = "POST";
	
	private String DEFAULT_ENCODE_TYPE = "UTF-8";
	
	private static HttpURLConnection httpConnection;
	private BufferedReader bReader;
	
	private URL url;
	
	public String groupId = "ab_201606271700";
	
	@Override
	public void init()
	{		 
//		try
//		{
//			url = new URL("http://localhost:8080/chat/test.ct");
//			
//			httpConnection = (HttpURLConnection)url.openConnection();
//			
//			//httpConnection.setDoInput(true);
//			//httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//			//httpConnection.setRequestProperty("Accept-Language",  "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
//			//httpConnection.setUseCaches(false);
//			//httpConnection.setReadTimeout(DEFAULT_TIMEOUT);
//			httpConnection.setRequestMethod(DEFAULT_REQUEST_METHOD);
//			httpConnection.setRequestProperty("User-Agent", "Lou");
//			httpConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//			/// POST Type 전송
//			//httpConnection.setDoOutput(true);
//		}
//		catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Override
	public void request( String data ) throws IOException
	{
		url = new URL("http://localhost:8080/chat/single.cht");
		httpConnection = (HttpURLConnection) url.openConnection();

		//add reuqest header
		httpConnection.setRequestMethod("POST");
		httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

		// Send post request
		httpConnection.setDoOutput(true);
		DataOutputStream oStream = new DataOutputStream(httpConnection.getOutputStream());

		HttpPrimitive httpPrim = new HttpPrimitive();
		HttpPrimitiveStruct primitive = httpPrim.primitive;
		
		primitive.m_header.m_groupId = groupId;
		primitive.m_header.m_primType = HttpPrimitiveType.PRIM_TYPE_DATA_REQUEST;
		primitive.m_header.m_userId = "jghan";
		primitive.m_header.m_type = HttpPrimitive.JAVA_TEXT;
		
		primitive.m_object = data;
		
		String encodeData = httpPrim.encode(primitive).toString();

		//Same...
		oStream.write(encodeData.getBytes());
		//oStream.writeBytes(encodeData);
		oStream.flush();
		//oStream.close();
	}
	
	@Override
	public String response() throws IOException
	{		
		int responseCode = httpConnection.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		
		if( responseCode != 200 )
			return null;
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
		
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = bufferReader.readLine()) != null)
		{
			response.append(inputLine);
		}
		
		bufferReader.close();
		
		//print result
		System.out.println(response.toString());
		
		return response.toString();
	}
}
