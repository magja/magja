package com.google.code.magja.soap;

import java.util.HashMap;
import java.util.Map;

public class ArrayItemMap {

    Map<Object, Object> items = new HashMap<Object, Object>();

    public void add(Object key, Object value) {
        this.items.put(key, value);
    }

    public Map<Object, Object> getItems() {
        return items;
    }

    public void setItems(Map<Object, Object> items) {
        this.items = items;
    }
}
