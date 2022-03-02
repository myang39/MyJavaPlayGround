package amplitude;

import org.json.JSONObject;

import java.util.*;


class Solution {
  public static String jsonStringify(Object obj) {
    StringBuilder sb = new StringBuilder();
    if (obj == null) {
      return sb.toString();
    }
    jsonStringify(obj, sb);

    return sb.toString();
  }

  public static void jsonStringify(Object obj, StringBuilder sb) {
    if (obj == null) {
      sb.append("null");
    }
    if (obj instanceof Boolean) {
      booleanType((Boolean)obj, sb);
    } else if (obj instanceof Map) {
      hashMapType((HashMap<String, Object>)obj, sb);
    } else if (obj instanceof ArrayList) { // array
      arrayType((ArrayList)obj, sb);
    } else if (obj instanceof String) { // string
      stringType((String)obj, sb);
    } else if (obj instanceof Integer) { // integer
      integerType((Integer)obj, sb);
    }
  }

  private static void booleanType(Boolean b, StringBuilder sb) {
    sb.append(b + ",");
  }

  private static void hashMapType(HashMap<String, Object> map, StringBuilder sb) {
    sb.append("{");
    for (HashMap.Entry<String, Object> e : map.entrySet()) {
      // System.out.println("debug hashmap:" + e.getKey() + " " + e.getValue());
      sb.append("\"" + e.getKey() + "\"" + ":");
      jsonStringify(e.getValue(), sb);
      sb.append(",");
    }
    sb.deleteCharAt(sb.length() - 1);
    sb.append("}");
  }

  private static void arrayType(ArrayList<Object> obj, StringBuilder sb) {
    // System.out.println("debug array: here");
    sb.append("[");
    for (Object o : obj) {
      // System.out.println("debug array: " + o);
      jsonStringify(o, sb);
      sb.append(",");
    }
    sb.deleteCharAt(sb.length() - 1);
    sb.append("]");
  }

  private static void stringType(String b, StringBuilder sb) {
    sb.append("\"" + b + "\"");
  }

  private static void integerType(Integer b, StringBuilder sb) {
    sb.append(b);
  }

  public static void main(String[] args) {
    // Initialize our object to serialize
    HashMap<String, Object> obj = new HashMap<>();
    obj.put("title", "Event Segmentation");
    obj.put("project", 114);
    HashMap<String, Object> params = new HashMap<>();
    obj.put("params", params);
    List<HashMap<String, Object>> eventList = new ArrayList<>();
    params.put("events", eventList);

    HashMap<String, Object> event1 = new HashMap<>();
    event1.put("type", null);
    HashMap<String, Object> event2 = new HashMap<>();
    event2.put("type", "click");
    eventList.add(event1);
    eventList.add(event2);
    obj.put("params2", params);


    // org.json.simple.JSONObject
    System.out.println("org.json.simple.JSONObject serializer:");
    System.out.println(new JSONObject(obj));

    // our serialiser
    System.out.println("\nOur serializer:");
    System.out.println(jsonStringify(obj));
  }
}

/*
{
  "project":114,
  "title":"Event Segmentation",
  "params":{
    "events":[
      {
        "type":null
      },
      {
        "type":"click"
      }
    ]
  },
  "params2":{
    "events":[
      {
        "type":null
      },
      {
        "type":"click"
      }
    ]
  }
}
*/
