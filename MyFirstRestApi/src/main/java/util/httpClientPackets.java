package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


public class httpClientPackets {

	httpClient client = new httpClient();
	
	public List<Cat> getCats()
	{
		HashMap<String,String> myquery = new HashMap<String,String>();
		ResponseEntity<List<Cat>> response = client.GetRestTemplate().exchange(
				  client.GetServer()+"/api/Cats",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Cat>>(){});
		List<Cat> cats = response.getBody();
		return cats;
	}
	public Cat getCat(int Id)
	{
		HashMap<String,String> myquery = new HashMap<String,String>();
		myquery.put("Id", "190");
		String url = client.getUrlForRequest("/api/Cat", myquery);		
		ResponseEntity<Cat> response = client.GetRestTemplate().exchange(
				url,
				  HttpMethod.GET,
				  null,
				  Cat.class);
		Cat cats = response.getBody();
		return cats;		
	}
	//update with query parms 
	public Cat updateCat(Cat cat) {
		HttpEntity<Cat> reqData = new HttpEntity<>(cat);
		HashMap<String,String> urlQuery = new HashMap<String,String>();
		urlQuery.put("Id", "190");
		String url = client.getUrlForRequest("/api/Cat", urlQuery);		
		ResponseEntity<Cat> response = client.GetRestTemplate().exchange(
				url,
				  HttpMethod.POST,
				  reqData,
				  Cat.class);
		Cat cats = response.getBody();
		return cats;
	}
	
}

