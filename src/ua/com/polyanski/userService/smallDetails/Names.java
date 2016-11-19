package ua.com.polyanski.userService.smallDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadym on 19.11.2016.
 */
public class Names {
    List<Name> names;

    public Names () {
        this.names = new ArrayList<Name>();
    }

    public void add(Name name) {
        names.add(name);
    }

    public int size() {
        return names.size();
    }

    public void remove(int index) {
        names.remove(index);
    }

    public Name get(int index) {
        return names.get(index);
    }

    public void addAll(ArrayList<String> nameNames) {
        for (String nameName: nameNames) {
            names.add(new Name(nameName));
        }
    }
}
