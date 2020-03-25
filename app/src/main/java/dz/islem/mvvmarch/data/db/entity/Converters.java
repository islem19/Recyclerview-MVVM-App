package dz.islem.mvvmarch.data.db.entity;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {
        @TypeConverter
        public static List<String> listFromString(String value) {
            Type listType = new TypeToken<List<String>>() {}.getType();
            return new Gson().fromJson(value, listType);
        }

        @TypeConverter
        public static String stringFromList(List<String> list) {
            return new Gson().toJson(list);
        }
}
