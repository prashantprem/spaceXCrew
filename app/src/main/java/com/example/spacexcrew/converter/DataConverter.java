package com.example.spacexcrew.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class DataConverter implements Serializable {

    @TypeConverter
    public static  String fromClassInfoList(List<String> launches){
       /* if (categories==null){
            return (null);
        }*/
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.toJson(launches, type);
    }

    @TypeConverter
    public List<String> toClassInfoList(String launches){
        /*if (categoriesString==null){
            return (null);
        }*/
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(launches, type);
    }
}
