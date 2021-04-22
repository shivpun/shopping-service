package com.weboxapps.portal.sample.jackson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonMain {

	private static final String RESOURCE = "src/test/resources/";

	public static void main(String[] args) throws JsonProcessingException, IOException {
		JsonNode nodeA = readA("simple-a.json");
		JsonNode nodeB = readA("simple-b.json");
		JsonCompare jsonCompare = new JsonCompare();
		jsonCompare.setSourceA(nodeA);
		jsonCompare.setSourceB(nodeB);
		System.out.println(JsonCompareUtil.startCompareAsEqual(nodeA, nodeB));
	}

	public static ObjectMapper mapper() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}

	public static JsonNode readA(String fileName) throws JsonProcessingException, IOException {
		ObjectMapper mapper = mapper();
		File sa = new File(RESOURCE + fileName);
		return mapper.readTree(sa);
	}
}
