package main.helper;

import java.util.ArrayList;
import java.util.function.Function;

public class MyArrayList<T extends Identificator> extends ArrayList<T> {

    public int getIndexById(int id) {
        int index = -1;

        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getId() == id) {
                index = i;
            }
        }

        return index;
    }

    public T getElementById(int id) throws ElementNotFoundException {
        T element = null;

        for (T t : this) {
            if (t.getId() == id) {
                element = t;
                break;
            }
        }

        if(element == null) {
            throw new ElementNotFoundException("Element not found.");
        }

        return element;
    }

}
