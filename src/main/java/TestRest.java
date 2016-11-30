

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.ws.rs.core.MediaType;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

//import src.main.java.func.Functions;



//import com.google.gson.GsonBuilder;




/**
 * @author Masoud Gholami
 * 
 * A Class to test rest api
 *
 */
public class TestRest {

	private static final String SERVER_ROOT_URI = "http://localhost:7474/db/data/node/14/paths";
	//static final String SERVER_ROOT_URI_DIRECTED = "http://localhost:7474/graphAlgorithms/DirectedSimplePaths/post";
	//static final String SERVER_ROOT_URI_SIMPLE = "http://localhost:7474/graphAlgorithms/SimplePaths/post";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	
		String auth = "bmVvNGo6eA==";
		String json = "{"
				+ "\"to\": \"http://localhost:7474/db/data/node/44\","
				+ "\"max_depth\" : 100,"
				 + "\"limit\" : 10,"
				+ "\"order\" : \"depth_first\","
				+ "\"relationships\" : [{"
				+ 	"\"type\" : \"car\","
				+ 	"\"direction\" : \"out\""
				+ "}],"
				+ "\"algorithm\" : \"allSimplePaths\""
				+ "}";
//		String json = "{"
//				+ "\"startNode\" : 266624193,"
//				+ "\"endNode\" : 1118643961,"
//				+ "\"startTime\" : \"" + LocalDateTime.now().toString() + "\","
//				+ "\"maxHit\" : 100,"
//				+ "\"maxDepth\" : 20,"
//				+ "\"useDistance\" : \"true\""
//				+ "}";

		WebResource resource = Client.create()
		        .resource(SERVER_ROOT_URI);
		
//		resource = Client.create()
//		        .resource(SERVER_ROOT_URI_DIRECTED);

//		Duration directed = Duration.ZERO;
		//for (int i = 0; i < 100; i++) {
//			LocalTime time = LocalTime.now();
//		String j="hello world";
	//String result= resource.convertTogson();
	
	
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON)
	                .type(MediaType.APPLICATION_JSON)
	                .header("Authorization", "Basic " + auth)
	                .entity(json)  //
	                .post(ClientResponse.class);
			
System.out.println(response.getEntity(String.class));
System.out.println(SERVER_ROOT_URI);
//System.out.println("json= " +result);
//			directed = Duration.between(time, LocalTime.now()).plus(directed);
			response.close();
		//}

//		System.out.println("Directed: " + directed.toMillis());
		//System.out.println(response.getEntity(String.class));

	}

	
}
