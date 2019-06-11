package com.mycompany.todoapp.util;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import com.mycompany.todoapp.entitys.Doing;
import com.mycompany.todoapp.entitys.User;

public class JsonUtil {

    public JsonObjectBuilder getJsonObject(User user) {
        JsonObjectBuilder userJson = Json.createObjectBuilder().add("userName", user.getName());
        return userJson;
    }

    public JsonObjectBuilder getJsonObject(Doing doing) {
        JsonObjectBuilder doingJson = Json.createObjectBuilder().add("doingId", doing.getId())
                    .add("doingText", doing.getText()).add("doingDate", doing.getDate())
                    .add("completed", doing.isComleted()).add("doingUserId", doing.getUserId());
        return doingJson;
    }

    public JsonObjectBuilder getJsonObject(List<Integer> counters) {
        JsonObjectBuilder counterJson = Json.createObjectBuilder().add("nad", counters.get(0))
                                .add("nncd", counters.get(1)).add("ncd", counters.get(2));
        return counterJson;
    }

    public JsonArrayBuilder getJsonArray(List<Doing> doings) {
        JsonArrayBuilder doingsJson = Json.createArrayBuilder();
        for (Doing doing : doings) {
            doingsJson.add(getJsonObject(doing));
        }
        return doingsJson;
    }    
}