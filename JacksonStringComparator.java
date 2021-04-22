package com.weboxapps.portal.sample.jackson;

import java.util.Comparator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class JacksonStringComparator implements Comparator<JsonNode> {

	@Override
	public int compare(JsonNode o1, JsonNode o2) {
		if (o1 instanceof TextNode && o2 instanceof TextNode) {
			String v1 = ((TextNode)o1).asText();
			String v2 = ((TextNode)o2).asText();
			return v1.compareTo(v2);
		}
		return 1;
	}
	
}
