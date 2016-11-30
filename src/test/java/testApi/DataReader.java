package testApi;

import java.io.*;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class DataReader {

	/* 
	LINES I WANT COMMENTED 
	Load data from OSM and save in XML 
	LINES I WANT COMMENTED 
	*/
	public static  String [] SERVER_ROOT_URI = {"http://overpass-api.de/api/interpreter","http://api.openstreetmap.fr/oapi/interpreter/"};
	
	public static void downloadSample(File file, String south, String west,String north,String east) throws IOException {
		
	
		// String SERVER_ROOT_URI1 = "http://api.openstreetmap.fr/oapi/interpreter/";
//		String query = "(node(51.80335,10.33210,51.80565,10.336);<;node[\"highway\"][\"highway\"!=\"footway\"][\"highway\"!=\"pedestrian\"][\"-highway\"!=\"path\"](51.80368908349047,10.332593321800232,51.806309539794675,10.335522294044495);way[\"highway\"][\"highway\"!=\"footway\"][\"highway\"!=\"pedestrian\"][\"-highway\"!=\"path\"](51.80368908349047,10.332593321800232,51.806309539794675,10.335522294044495);relation[\"highway\"][\"highway\"!=\"footway\"][\"highway\"!=\"pedestrian\"][\"-highway\"!=\"path\"](51.80368908349047,10.332593321800232,51.806309539794675,10.335522294044495););out meta;";
		String query_test = "node("+south+","+west+","+north+","+east+");way(bn);(._;>;);out;";
		WebResource resource = Client.create().resource(SERVER_ROOT_URI[0]);

		ClientResponse response = resource.accept(MediaType.APPLICATION_XML)

				.entity(query_test)

				.post(ClientResponse.class);

		InputStream in = response.getEntityInputStream();
		OutputStream out = new FileOutputStream(file);
		byte[] buffer = new byte[10000];
		try {
			int len = in.read(buffer);
			while (len > 0) {
				out.write(buffer, 0, len);
				len = in.read(buffer);
			}
		} finally {
			out.close();
			in.close();
		}

	}
}