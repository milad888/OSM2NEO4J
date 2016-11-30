package testApi;

import java.io.File;
import java.util.Map;

//import org.neo4j.cypher.internal.frontend.v3_1.ast.Remove;
//import org.neo4j.cypher.internal.frontend.v3_1.ast.RemoveItem;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import scala.sys.process.ProcessBuilderImpl.OStreamBuilder;
import type.Graph;

public class GraphWriter {

	/**
	 * Graph database access service
	 */
	private GraphDatabaseService graphDb;

	/**
	 * Constructor, Creates connection to the graph database
	 * 
	 * @param dbPath
	 *            database path
	 */
	public GraphWriter(String dbPath) {
		
		
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File(dbPath));
		
//		registerShutdownHook(graphDb);
	}

	/**
	 * Stores the given graph to the database
	 * 
	 * @param graph
	 *            given graph
	 */
	public void storeGraph(Graph graph, boolean merge) {
		
		
		try (Transaction tx = graphDb.beginTx()) {
			
			for (int j = 0; j < graph.getGnode().size(); j++) {
				if (merge) {
					Node nodeMerge=graphDb.findNode(Labels.Place, "IDs", graph.getGnode().get(j).getnid().toString());
					if (nodeMerge!=null) {
						continue;
					}
				}
				Node node = graphDb.createNode(Labels.Place);
				node.setProperty("Name", graph.getGnode().get(j).getnName());
				node.setProperty("LATITUDE", graph.getGnode().get(j).getnLat());
				node.setProperty("LONGITUDE", graph.getGnode().get(j).getnLon());
				node.setProperty("IDs", graph.getGnode().get(j).getnid());
				

			}
			
//			GUI.osmServerTest.setText("inserting "+graph.getGnode().size()+ " Noodes is done");
			System.out.println("inserting "+graph.getGnode().size()+ " Noodes is done");

			for (int i = 0; i < graph.getGway().size(); i++) {
				
//				if (i % 500 == 0) {
//					System.out.println(i+" is done from "+size);
//					
//				}
				for (int j = 0; j < graph.getGway().get(i).getNode().size() - 1; j++) {

					Node firstNode = graphDb.findNode(Labels.Place, "IDs",
							graph.getGway().get(i).getNode().get(j).getnid().toString());
					Node secondNode = graphDb.findNode(Labels.Place, "IDs",
							graph.getGway().get(i).getNode().get(j + 1).getnid().toString());
					// System.out.println(firstNode.getProperty("Name"));

					double distance = Tool.distanceCal(firstNode.getProperty("LATITUDE").toString(),
							firstNode.getProperty("LONGITUDE").toString(),
							secondNode.getProperty("LATITUDE").toString(),
							secondNode.getProperty("LONGITUDE").toString());
					String query="match (n:Place)-[r:Road]-(m:Place) where id(n)="+firstNode.getId() + " and id(m)="+secondNode.getId()+ " return id(r) as id limit 1";
					Result result = graphDb.execute(query);
					String id=null;
					while (result.hasNext()) {
						Map<String, Object> row = result.next();
						id = row.get("id").toString();
					}
					if (id!= null) {
						continue;
						
					}else{
				if (graph.getGway().get(i).iseType()) {
						Relationship rel1 = firstNode.createRelationshipTo(secondNode, Relation.Road);
						rel1.setProperty("Name", graph.getGway().get(i).geteName());
						rel1.setProperty("Distance", distance);
						
					} else {
						
						Relationship rel1 = firstNode.createRelationshipTo(secondNode, Relation.Road);
						Relationship rel2 = secondNode.createRelationshipTo(firstNode, Relation.Road);
						rel1.setProperty("Name", graph.getGway().get(i).geteName());
						rel2.setProperty("Name", graph.getGway().get(i).geteName());
						rel1.setProperty("Distance", distance);
						rel2.setProperty("Distance", distance);
					}
				}
			}
			}
			System.out.println("Done");
			
			tx.success();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		graphDb.shutdown();
	}

//	private static void registerShutdownHook(final GraphDatabaseService graphDb) {
//		// Registers a shutdown hook for the Neo4j instance so that it
//		// shuts down nicely when the VM exits (even if you "Ctrl-C" the
//		// running application).
//		
//		Runtime.getRuntime().addShutdownHook(new Thread() {
//			@Override
//			public void run() {
//				graphDb.shutdown();
//			}
//		});
//
//	}

}
