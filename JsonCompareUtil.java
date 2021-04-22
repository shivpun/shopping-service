package com.weboxapps.portal.sample.jackson;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.TextNode;

public class JsonCompareUtil {
	
	public static List<String> distinctFieldNames(JsonNode sourceA, JsonNode sourceB) {
		List<String> fieldNames = new ArrayList<String>();
		if(!isEmpty(sourceA)) {
			Iterable<String> itrA = () -> sourceA.fieldNames();
			StreamSupport.stream(itrA.spliterator(), false).distinct().forEach(name -> fieldNames.add(name));
		}
		if (!isEmpty(sourceB)) {
			Iterable<String> itrB = () -> sourceB.fieldNames();
			StreamSupport.stream(itrB.spliterator(), false).distinct().forEach(name -> fieldNames.add(name));
		}
		return fieldNames.stream().distinct().collect(Collectors.toList());
	}
	
	public static boolean isEqualObject(JsonNode sourceA, JsonNode sourceB) {
		if (isEmpty(sourceA) && isEmpty(sourceB)) {
			return true;
		}
		List<String> fieldNames = distinctFieldNames(sourceA, sourceB);
		boolean isEqual = true;
		for(String fieldName: fieldNames) {
			JsonNode nodeA = sourceA.findValue(fieldName);
			JsonNode nodeB = sourceB.findValue(fieldName);
			boolean isSourceAEmpty = isEmpty(nodeA), isSourceBEmpty = isEmpty(nodeB);
			if ((isSourceAEmpty && !isSourceBEmpty) || (isSourceBEmpty && !isSourceAEmpty)) {
				return false;
			}				
			isEqual = startCompareAsEqual(nodeA, nodeB);
			if (!isEqual) {
				break;
			}
		}
		return isEqual;
	}
	
	public static boolean isEqualPrimitive(JsonNode sourceA, JsonNode sourceB) {
		boolean isSourceAEmpty = isEmpty(sourceA), isSourceBEmpty = isEmpty(sourceB);
		if (isSourceAEmpty && isSourceBEmpty) {
			return true;
		}
		if ((isSourceAEmpty && !isSourceBEmpty) || (isSourceBEmpty && !isSourceAEmpty)) {
			return false;
		}
		System.out.println("Compare Primitive");
		return isPrimitiveWrapper(sourceA) && isPrimitiveWrapper(sourceB) && sourceA.asText().equals(sourceB.asText());
	}
	
	public static boolean startCompareAsEqual(JsonNode sourceA, JsonNode sourceB) {
		boolean isSourceAEmpty = isEmpty(sourceA), isSourceBEmpty = isEmpty(sourceB);
		if (isSourceAEmpty && isSourceBEmpty) {
			return true;
		}
		if ((isSourceAEmpty && !isSourceBEmpty) || (isSourceBEmpty && !isSourceAEmpty)) {
			return false;
		}
		boolean isEqual = false;
		switch (sourceA.getNodeType()) {
			case STRING:
				isEqual = isEqualText(sourceA, sourceB);
				break;
			case OBJECT:
				isEqual = isEqualObject(sourceA, sourceB);
				break;
			case ARRAY:
				isEqual = isEqualArray(sourceA, sourceB);
				break;
			default:
				isEqual = isEqualPrimitive(sourceA, sourceB);
				break;
		}
		return isEqual;
	}
	
	public static boolean isEqualText(JsonNode sourceA, JsonNode sourceB) {
		boolean isSourceAEmpty = isEmpty(sourceA), isSourceBEmpty = isEmpty(sourceB);
		if (isSourceAEmpty && isSourceBEmpty) {
			return true;
		}
		if ((isSourceAEmpty && !isSourceBEmpty) || (isSourceBEmpty && !isSourceAEmpty)) {
			return false;
		}
		return isText(sourceA, sourceB) && ((TextNode)sourceA).asText().equals( ((TextNode)sourceB).asText());
	}
	
	private static boolean isText(JsonNode sourceA, JsonNode sourceB) {
		return JsonNodeType.STRING.equals(sourceA.getNodeType()) && JsonNodeType.STRING.equals(sourceB.getNodeType());
	}
	
	public static boolean isEqualArray(JsonNode sourceA, JsonNode sourceB) {
		boolean isSourceAEmpty = isEmpty(sourceA), isSourceBEmpty = isEmpty(sourceB);
		if (isSourceAEmpty && isSourceBEmpty) {
			return true;
		}
		if ((isSourceAEmpty && !isSourceBEmpty) || (isSourceBEmpty && !isSourceAEmpty)) {
			return false;
		}
		if(sourceA.isArray() && sourceB.isArray()) {
			ArrayNode nodeA = (ArrayNode) sourceA;
			ArrayNode nodeB = (ArrayNode) sourceB;
			List<JsonNode> primitiveNodesA = new ArrayList<JsonNode>();
			List<JsonNode> primitiveNodesB = new ArrayList<JsonNode>();
			nodeB.forEach(jsonNode-> {
				if (isPrimitiveWrapper(jsonNode)) {
					primitiveNodesB.add(jsonNode);
				}
			});
			nodeA.forEach(jsonNode-> {
				if (isPrimitiveWrapper(jsonNode)) {
					primitiveNodesA.add(jsonNode);
				}
			});
			primitiveNodesB.addAll(primitiveNodesA);
			if (!CollectionUtils.isEmpty(primitiveNodesB)) {
				Predicate<JsonNode> predicate = (jsonNode) -> primitiveNodesA.stream().anyMatch(value -> isEqualPrimitive(value, jsonNode));
				List<JsonNode> allJsonNodes = primitiveNodesB.stream().distinct().collect(Collectors.toList());
				List<JsonNode> filerJsonNodes = allJsonNodes.stream().filter(predicate).collect(Collectors.toList());
				return allJsonNodes.size() == filerJsonNodes.size();
			}
		}
		return true;
	}
	
	public static boolean isPrimitiveWrapper(JsonNode source) {
		if (source == null) {
			return false;
		}
		boolean isPrimitive = source.isBoolean() || source.isDouble() || source.isShort() || source.isInt() || source.isLong() || source.isFloat();
		boolean isPrimitiveWrapper = source.isIntegralNumber() || source.isNumber() || source.isFloatingPointNumber() || source.isBigDecimal() || source.isBigInteger();
		return isPrimitive || isPrimitiveWrapper;
	}
	
	public static boolean isEmpty(JsonNode source) {
		if (source == null) {
			return true;
		}
		boolean emptyFlag = false;
		switch(source.getNodeType()) {
			case OBJECT:
				emptyFlag = source.isEmpty();
				break;
			case ARRAY:
				emptyFlag = source.isEmpty();
				break;
			case STRING:
				emptyFlag = !StringUtils.hasText(source.asText());
				break;
			case BOOLEAN:
				emptyFlag = !source.booleanValue();
				break;
			default:
				emptyFlag = false;
				break;
		}
		return emptyFlag;
	}
}
