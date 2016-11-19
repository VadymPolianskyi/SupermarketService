package ua.com.polyanski.userService.smallDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadym on 19.11.2016.
 */
public class Models {
    List<Model> models;

    public Models () {
        this.models = new ArrayList<Model>();
    }

    public void add(Model model) {
        models.add(model);
    }

    public int size() {
        return models.size();
    }

    public void remove(int index) {
        models.remove(index);
    }

    public Model get(int index) {
        return models.get(index);
    }

    public void addAll(ArrayList<String> modelNames) {
        for (String modelName: modelNames) {
            models.add(new Model(modelName));
        }
    }
}
