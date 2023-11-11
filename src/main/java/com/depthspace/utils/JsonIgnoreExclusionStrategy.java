package com.depthspace.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class JsonIgnoreExclusionStrategy implements ExclusionStrategy {

    private final boolean serialize;

    public JsonIgnoreExclusionStrategy(boolean serialize){
        this.serialize = serialize;
    }
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {

        JsonIgnore jsonIgnore = fieldAttributes.getAnnotation(JsonIgnore.class);
        if (jsonIgnore == null){
            return false;
        }
        return serialize ? jsonIgnore.serialize() : jsonIgnore.deserialize();
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
