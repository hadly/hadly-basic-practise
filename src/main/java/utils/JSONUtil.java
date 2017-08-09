///*
// * JSONUtil.java
// *
// * Copyright (C) KAI Square Pte Ltd
// */
//package utils;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.JsonNodeFactory;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// * JSON utility methods.
// *
// * @author Tan Yee Fan
// */
//public class JSONUtil {
//
//    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
//
//    private JSONUtil() {
//    }
//
//    /**
//     * Transforms a JSON string into a map. If this cannot be done, then
//     * {@code null} is returned.
//     *
//     * @param json
//     * @return
//     */
//    public static Map<String, Object> readJSON(String json) {
//	try {
//	    TypeReference ref = new TypeReference<LinkedHashMap<String, Object>>() {
//	    };
//	    Map<String, Object> map = JSON_MAPPER.readValue(json, ref);
//	    return map;
//	} catch (IOException e) {
//	    return null;
//	}
//    }
//
//    /**
//     * Transforms an object into a JSON string. If this cannot be done,
//     * then {@code null} is returned.
//     *
//     * @param object
//     * @return
//     */
//    public static String writeJSON(Object object) {
//	try {
//	    return JSON_MAPPER.writeValueAsString(object);
//	} catch (IOException e) {
//	    return null;
//	}
//    }
//
//    /**
//     * Transforms an object into a JSON string. If this cannot be done,
//     * then {@code null} is returned.
//     *
//     * Note that the returned JSON string will be formatted.
//     * Example, format {"name":"zhangsan","age":"20"} as
//     * {
//     * "age" : "20",
//     * "name" : "zhangsan"
//     * }
//     *
//     * @param object
//     * @return
//     */
//    public static String writeJSONWithPrettyPrinter(Object object) {
//	try {
//	    return JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
//	} catch (IOException e) {
//	    return null;
//	}
//    }
//
//    /**
//     * Get String from map. If not found, return {@code "null"}.
//     *
//     * @param map
//     * @param key
//     * @return
//     */
//    public static String getJSONString(Map<String, Object> map, String key) {
//	Object value = map.get(key);
//	return String.valueOf(value);
//    }
//
//    /**
//     * Get String from map. If not found or if the value is null, return {@code default_}.
//     *
//     * @param map
//     * @param key
//     * @param default_
//     * @return
//     */
//    public static String getJSONString(Map<String, Object> map, String key, String default_) {
//	String str = getJSONString(map, key);
//	if ("null".equals(str)) {
//	    return default_;
//	}
//	return str;
//    }
//
//    /**
//     * Get Integer from map. If not found, return {@code null}.
//     *
//     * @param map
//     * @param key
//     * @return
//     */
//    public static Integer getJSONInteger(Map<String, Object> map, String key) {
//	Object value = map.get(key);
//	if (value == null) {
//	    return null;
//	}
//	if (value instanceof Number) {
//	    Number number = (Number) value;
//	    return Integer.valueOf(number.intValue());
//	} else {
//	    String string = value.toString();
//	    if (string != null) {
//		try {
//		    return Integer.valueOf(string);
//		} catch (NumberFormatException e) {
//		    return null;
//		}
//	    } else {
//		return null;
//	    }
//	}
//    }
//
//    /**
//     * Get Long from map. If not found, return {@code null}.
//     *
//     * @param map
//     * @param key
//     * @return
//     */
//    public static Long getJSONLong(Map<String, Object> map, String key) {
//	Object value = map.get(key);
//	if (value == null) {
//	    return null;
//	}
//	if (value instanceof Number) {
//	    Number number = (Number) value;
//	    return Long.valueOf(number.longValue());
//	} else {
//	    String string = value.toString();
//	    if (string != null) {
//		try {
//		    return Long.valueOf(string);
//		} catch (NumberFormatException e) {
//		    return null;
//		}
//	    } else {
//		return null;
//	    }
//	}
//    }
//
//    /**
//     * Get Float from map. If not found, return {@code null}.
//     *
//     * @param map
//     * @param key
//     * @return
//     */
//    public static Float getJSONFloat(Map<String, Object> map, String key) {
//	Object value = map.get(key);
//	if (value == null) {
//	    return null;
//	}
//	if (value instanceof Number) {
//	    Number number = (Number) value;
//	    return Float.valueOf(number.floatValue());
//	} else {
//	    String string = value.toString();
//	    if (string != null) {
//		try {
//		    return Float.valueOf(string);
//		} catch (NumberFormatException e) {
//		    return null;
//		}
//	    } else {
//		return null;
//	    }
//	}
//    }
//
//    /**
//     * Get Double from map. If not found, return {@code null}.
//     *
//     * @param map
//     * @param key
//     * @return
//     */
//    public static Double getJSONDouble(Map<String, Object> map, String key) {
//	Object value = map.get(key);
//	if (value == null) {
//	    return null;
//	}
//	if (value instanceof Number) {
//	    Number number = (Number) value;
//	    return Double.valueOf(number.doubleValue());
//	} else {
//	    String string = value.toString();
//	    if (string != null) {
//		try {
//		    return Double.valueOf(string);
//		} catch (NumberFormatException e) {
//		    return null;
//		}
//	    } else {
//		return null;
//	    }
//	}
//    }
//
//    /**
//     * Get a string list from map. If not found, return {@code null}.
//     * If the original list contains null, then a "null" will be contained in the
//     * returned string list.
//     *
//     * @param map
//     * @param key
//     * @return
//     */
//    public static List<String> getJSONListOfString(Map<String, Object> map, String key) {
//	if (null == map) {
//	    return null;
//	}
//	Object value = map.get(key);
//	if (value == null) {
//	    return null;
//	}
//	if (value instanceof List) {
//	    List list = (List) value;
//	    List<String> result = new ArrayList<String>(list.size());
//	    for (Object object : list) {
//		result.add(String.valueOf(object));
//	    }
//	    return result;
//	} else {
//	    return null;
//	}
//    }
//
//    private static ObjectMapper getJsonMapper() {
//	return JSON_MAPPER;
//    }
//
//    /**
//     * Parse a JSON dictionary into java.util.Map. If cannot be parsed, a
//     * empty map is returned.
//     *
//     * @param data Data to be parsed.
//     * @return Map<String, Object> if successful.
//     */
//    public static Map<String, Object> parseStringToMap(String data) {
//	ObjectMapper om = getJsonMapper();
//	try {
//	    data = cleanJsonString(data);
//	    Map<String, Object> map = (Map<String, Object>) om.readValue(data, LinkedHashMap.class);
//	    return map;
//	} catch (IOException ex) {
////			logger.error("Unable to parse JSON: " + data, ex);
//	    return new LinkedHashMap<String, Object>();
//	} catch (NullPointerException ex) {
////			logger.error("Unable to parse JSON: " + data, ex);
//	    return new LinkedHashMap<String, Object>();
//	}
//    }
//
//    /**
//     * Cleans a JSON string so that it can be parsed by a JSON parser. At
//     * present, this method escapes some illegal characters found within
//     * quoted strings.
//     *
//     * @param data JSON to be cleaned.
//     * @return
//     */
//    public static String cleanJsonString(String data) {
//	if (data == null) {
//	    return null;
//	}
//	StringBuilder builder = new StringBuilder();
//	boolean insideQuote = false;
//	for (int i = 0; i < data.length(); i++) {
//	    char c = data.charAt(i);
//	    switch (c) {
//		case '\"':
//		    insideQuote = !insideQuote;
//		    builder.append(c);
//		    break;
//		case '\n':
//		    if (insideQuote) {
//			builder.append("\\n");
//		    } else {
//			builder.append(c);
//		    }
//		    break;
//		case '\r':
//		    if (insideQuote) {
//			builder.append("\\r");
//		    } else {
//			builder.append(c);
//		    }
//		    break;
//		case '\t':
//		    if (insideQuote) {
//			builder.append("\\t");
//		    } else {
//			builder.append(c);
//		    }
//		    break;
//		default:
//		    builder.append(c);
//		    break;
//	    }
//	}
//	return builder.toString();
//    }
//
//    /**
//     * Get Integer from a map. If not found, return {@code default_}.
//     *
//     * @param map
//     * @param key
//     * @param default_
//     * @return
//     */
//    public static Integer getInteger(Map<String, Object> map, String key, Integer default_) {
//	Integer val = null;
//	if (map != null) {
//	    Object obj = map.get(key);
//	    val = ObjectUtil.asInteger(obj);
//	}
//	if (null == val) {
//	    val = default_;
//	}
//	return val;
//    }
//
////    public static Integer getInteger(Map<String, Object> map, String key) {
////	return getInteger(map, key, null);
////    }
//    /**
//     * Get Boolean from a map. If not found, return {@code default_}.
//     *
//     * @param map
//     * @param key
//     * @param default_
//     * @return
//     */
//    public static Boolean getBoolean(Map<String, Object> map, String key, Boolean default_) {
//	Boolean val = null;
//	if (null != map) {
//	    Object obj = map.get(key);
//	    val = ObjectUtil.asBoolean(obj);
//	}
//	if (null == val) {
//	    val = default_;
//	}
//
//	return val;
//    }
//
//    /**
//     *
//     * @param map
//     * @param key
//     * @return
//     */
////    public static List<String> getListOfStrings(Map<String, Object> map, String key) {
////	List<String> val = null;
////	if (map != null) {
////	    Object obj = map.get(key);
////	    if (obj instanceof List) {
////		List list = (List) obj;
////		val = new ArrayList<String>(list.size());
////		for (Object o : list) {
////		    String v = ObjectUtil.asString(o);
////		    if (v != null) {
////			val.add(v);
////		    }
////		}
////	    }
////	}
////	return val;
////    }
//    /**
//     * Get an empty JSON string
//     *
//     * @return
//     */
//    public static String getEmptyJsonString() {
//	return "{}";
//    }
//
//    /**
//     * Format JSON string with pretty printer.
//     *
//     * Example, format {"name":"zhangsan","age":"20"} as
//     * {
//     * "age" : "20",
//     * "name" : "zhangsan"
//     * }
//     *
//     * @param jsonString
//     * @return
//     */
//    public static String formatWithPrettyPrinter(String jsonString) {
//	String result = JSONUtil.getEmptyJsonString();
//	if (null != jsonString) {
//	    Map<String, Object> map = JSONUtil.readJSON(jsonString);
//	    result = JSONUtil.writeJSONWithPrettyPrinter(map);
//	}
//	return result;
//    }
//
////The JSON string maybe like below:
////[
////   {
////      "channel" : 0,
////      "device-id" : 12,
////      "sessions" : [
////         {
////            "create-time" : "20160408164404",
////            "ip" : "10.101.0.207",
////         }
////      ],
////      "video-codec" : "H.264"
////   }
////]
//    /**
//     * Get JSON list from a JSON array.
//     *
//     * @param json the JSON array, such as [{"create-time" : "20160408164404", "ip" : "10.101.0.207"}]
//     * @return
//     */
//    public static List<String> getJSONListOfString(String json) {
//	List<String> list = new ArrayList<>();
//	try {
//	    JsonNode node = JSON_MAPPER.readTree(json);
//	    Iterator<JsonNode> it = node.iterator();
//	    while (it.hasNext()) {
//		JsonNode eachNode = it.next();
//		list.add(eachNode.toString());
//	    }
//	} catch (IOException ex) {
//	    ex.printStackTrace();
//	}
//	return list;
//    }
//
//    /**
//     * Get JSON list from a JSON string.
//     *
//     * @param json the JSON string, such as {"sessions":[{"create-time" : "20160408164404", "ip" : "10.101.0.207"}]}
//     * @param key the key of the JSON array
//     * @return
//     */
//    public static List<String> getJSONListOfString(String json, String key) {
//	try {
//	    JsonNode node = JSON_MAPPER.readTree(json);
//	    JsonNode s = node.get(key);
//	    return getJSONListOfString(s.toString());
//	} catch (IOException ex) {
//	    ex.printStackTrace();
//	}
//	return new ArrayList<>();
//    }
//
//    public static String test(Map<String, String> strMap){
//	Map<String, JsonNode> map = new HashMap<>();
//	for (Map.Entry<String, String> entry: strMap.entrySet()){
//	    String key = entry.getKey();
//	    String value = entry.getValue();
//	    try {
//		JsonNode node = JSON_MAPPER.readTree(value);
//		map.put(key, node);
//	    } catch (IOException ex) {
//		Logger.getLogger(JSONUtil.class.getName()).log(Level.SEVERE, null, ex);
//	    }
//	}
//
//	ObjectNode on = JSON_MAPPER.createObjectNode();
//	on.putAll(map);
//
//
////	return formatWithPrettyPrinter(on.toString());
//	return on.toString();
//    }
//}
