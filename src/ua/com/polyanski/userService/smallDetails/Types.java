package ua.com.polyanski.userService.smallDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadym on 19.11.2016.
 */
public class Types {
    List<Type> types;

    public Types () {
        this.types = new ArrayList<Type>();
    }

    public void add(Type type) {
        types.add(type);
    }

    public int size() {
        return types.size();
    }

    public void remove(int index) {
        types.remove(index);
    }

    public Type get(int index) {
        return types.get(index);
    }

    public void addAll(ArrayList<String> typeNames) {
        for (String typeName: typeNames) {
            types.add(new Type(typeName));
        }
    }
}
