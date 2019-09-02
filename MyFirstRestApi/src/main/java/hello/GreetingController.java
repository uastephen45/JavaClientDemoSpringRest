package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.Cat;
import util.httpClientPackets;
import util.jsonCat;
import util.jsonDog;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    
    
    @RequestMapping("/cats")
    public List<Cat> cats() {
    	List<Cat> ret = new ArrayList<Cat>();
    	httpClientPackets packet = new httpClientPackets();
    	ret = packet.getCats();
    	return ret;
    }
    @RequestMapping("/cat")
    public Cat cat() {
    	Cat ret = new Cat();
    	httpClientPackets packet = new httpClientPackets();
    	ret = packet.getCat(1);
    	return ret;
    }
    @RequestMapping("/updatecat")
    public Cat updates() {
    	Cat ret = new Cat();
    	httpClientPackets packet = new httpClientPackets();
    	ret.Age = 10;
    	ret.Color = "red";
    	ret = packet.updateCat(ret);
    	return ret;
    }
    
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody JSONObject  holder) throws JsonParseException, JsonMappingException, IOException {
    	ObjectMapper objectMapper = new ObjectMapper();
    	String mycatJson = JSONObject.toJSONString((Map) holder.get("cat"));
    	String mydogJson = JSONObject.toJSONString((Map) holder.get("dog"));
    	jsonCat cat = objectMapper.readValue(mycatJson,jsonCat.class);
    	jsonDog dog = objectMapper.readValue(mydogJson,jsonDog.class);
    	
    	int i = 0;
    	//if(dog == dog && cat == cat) {
    		
    	//}
    }
    
}
