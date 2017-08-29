package com.crypto.dev.bithumb;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientBitHumb {
	private static String ticker = "https://api.bithumb.com/public/ticker/";
	
	private  ObjectMapper objectMapper = new ObjectMapper();
	
	public ClientBitHumb() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void currency(String currency) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, ClientProtocolException, IOException{
		
		 SSLContext sslContext = new SSLContextBuilder()
			      .loadTrustMaterial(null, (certificate, authType) -> true).build();
			 
			    CloseableHttpClient client = HttpClients.custom()
			      .setSSLContext(sslContext)
			      .setSSLHostnameVerifier(new NoopHostnameVerifier())
			      .build();
			    HttpGet httpGet = new HttpGet(ticker+currency);
			    httpGet.setHeader("Accept", "application/json");
			 
			    HttpResponse response = client.execute(httpGet);
			    HttpEntity entity = response.getEntity();
			    String content = EntityUtils.toString(entity);
			    
			    System.out.println(content);
			    
			    client.close();
			    
			    Ticker ticker = objectMapper.readValue(content, Ticker.class);
			    System.out.println("Binding To Object");
			    
			    System.out.println(ticker);
	}
}
