package com.weboxapps.portal.sample.jackson;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonCompare {
	
	private JsonNode sourceA; 
	
	private JsonNode sourceB;
	
	private List<String> ignoreField;
	
	private List<JsonNode> modifiedField;

	public JsonNode getSourceA() {
		return sourceA;
	}

	public void setSourceA(JsonNode sourceA) {
		this.sourceA = sourceA;
	}

	public JsonNode getSourceB() {
		return sourceB;
	}

	public void setSourceB(JsonNode sourceB) {
		this.sourceB = sourceB;
	}

	public List<String> getIgnoreField() {
		return ignoreField;
	}

	public void setIgnoreField(List<String> ignoreField) {
		this.ignoreField = ignoreField;
	}

	public List<JsonNode> getModifiedField() {
		return modifiedField;
	}

	public void setModifiedField(List<JsonNode> modifiedField) {
		this.modifiedField = modifiedField;
	}
}
