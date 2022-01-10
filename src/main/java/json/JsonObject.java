package json;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final Map<String, Json> jsonFields;

    public JsonObject(JsonPair... jsonPairs) {
        List<JsonPair> pairs = Arrays.asList(jsonPairs);
        jsonFields = new HashMap<>();
        for (JsonPair jsonPair: pairs) {
            jsonFields.put(jsonPair.key, jsonPair.value);
        }
    }

    @Override
    public String toJson() {
        return "{" + getJsonObjectBody() + "}";
    }

    private String getJsonObjectBody() {
        StringBuilder jsonStr = new StringBuilder();

        Iterator<Map.Entry<String, Json>> iterator = jsonFields.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Json> jsonField = iterator.next();
            jsonStr.append("'").append(jsonField.getKey()).append("': ");
            jsonStr.append(jsonField.getValue().toJson());
            if (iterator.hasNext()) {
                jsonStr.append(", ");
            }
        }

//        Iterator<JsonPair> jsonIterator = jsonPairs.iterator();
//        while (jsonIterator.hasNext()) {
//            JsonPair jsonPair = jsonIterator.next();
//            jsonStr.append("'").append(jsonPair.key).append("': ");
//            jsonStr.append(jsonPair.value.toJson());
//            if (jsonIterator.hasNext()) {
//                jsonStr.append(", ");
//            }
//        }

        return jsonStr.toString();
    }

    public void add(JsonPair jsonPair) {
        jsonFields.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return jsonFields.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject newObject = new JsonObject();
        for (String key: names) {
            Json value = find(key);
            if (!Objects.isNull(value)) {
                newObject.add(new JsonPair(key, value));
            }
        }
        return newObject;
    }
}
