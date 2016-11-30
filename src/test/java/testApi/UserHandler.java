package testApi;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import type.Node;
import type.Way;
import type.Graph;

/**
*
*rewrite DefaultHandler to create a graph of all data 
*
*/

public class UserHandler extends DefaultHandler {

	boolean bway = false;
	boolean btag = false;
	Node node;
	Way way;
	Graph graph = new Graph();

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		// * add all Node to graph
		if (qName.equalsIgnoreCase("node")) {
			btag = true;
			node = new Node();
			graph.getGnode().add(node);
			node.setnLat(attributes.getValue("lat"));
			node.setnLon(attributes.getValue("lon"));
			node.setnid(attributes.getValue("id"));
		} else if (qName.equalsIgnoreCase("tag") && btag) {
			String keyname = attributes.getValue("k");

			if ("name".equals(keyname) && qName.equalsIgnoreCase("tag"))
				node.setnName(attributes.getValue("v").toString());

			// System.out.println("Node id : " + node.getnName() + "\nlat: " +
			// node.getnLat() + "\nlon: " + node.getnLon());

			// * write edge in graph with there Noodes

		} else if (qName.equalsIgnoreCase("way")) {
			bway = true;
			way = new Way();
			graph.getGway().add(way);
			way.seteName(attributes.getValue("id"));
//			int arraySize = graph.getGway().size();
//			System.out.println("\nway id : " + graph.getGway().get(arraySize - 1).geteName());

		}

		else if (qName.equalsIgnoreCase("nd") && bway) {
			for (int i = 0; i < graph.getGnode().size(); i++) {
				if (attributes.getValue("ref").equals(graph.getGnode().get(i).getnid())) {
					way.getNode().add(graph.getGnode().get(i));

				}
			}

		} else if (qName.equalsIgnoreCase("tag") && bway) {
			String key = attributes.getValue("k");
			if ("highway".equals(key) && qName.equalsIgnoreCase("tag")) {
//				String t1 = attributes.getValue("v");
//				if ("footway".equals(t1)) {
//					graph.getGway().remove(way);
//
//				}

			}
			if ("oneway".equals(key)) {

				String t = attributes.getValue("v");

				way.seteType(t == "yes");
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		if (qName.equalsIgnoreCase("way"))
			bway = false;
		if (qName.equalsIgnoreCase("node"))
			btag = false;
	}

}
