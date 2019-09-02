package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sun.tools.javac.code.Type;

public class httpClient {
	
	  private String server = "http://localhost:81";
	  private RestTemplate rest;
	  private HttpHeaders headers;
	
	public httpClient()
	{
	    this.rest = new RestTemplate();
	    this.headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json");
	    headers.add("Accept", "*/*");
	}
	
	public String GetServer() {
		return this.server;
	}
	public HttpHeaders GetHeaders() {
		return this.headers;
	}
	public RestTemplate GetRestTemplate() {
		return this.rest;
	}	
	public  String getUrlForRequest(String resource,HashMap<String,String> QueryParams) {
		
		StringBuilder ret = new StringBuilder();
		ret.append(this.server + "/" + resource);
		if(QueryParams.size() >0) {
			ret.append("?");
			int  i = 1;
			for(Map.Entry<String,String> parm:QueryParams.entrySet()) {				
				ret.append(parm.getKey()+"="+parm.getValue());	
				//Are you the last item 
				if(i !=  QueryParams.size()) {
					ret.append("&");
				}
				i ++;
			}
		}
		return ret.toString();
	}

}
