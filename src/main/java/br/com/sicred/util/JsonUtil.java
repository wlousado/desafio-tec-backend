package br.com.sicred.util;

import com.google.gson.GsonBuilder;

import java.util.Objects;

public class JsonUtil {
    private static GsonBuilder gsonBuilder = null;

    private JsonUtil() {
        gsonBuilder = new GsonBuilder();
    }

    public static String convertToJson(Object obj) {
        if(Objects.isNull(gsonBuilder)) {
            new JsonUtil();
        }
        return gsonBuilder.create().toJson(obj);
    }

    public static <T> T convertFromJson(String json, Class<T> type) {
        if(Objects.isNull(gsonBuilder)) {
            new JsonUtil();
        }
        return gsonBuilder.create().fromJson(json, type);
    }

}
