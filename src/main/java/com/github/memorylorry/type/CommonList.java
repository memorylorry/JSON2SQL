package com.github.memorylorry.type;

import java.util.ArrayList;
import java.util.Iterator;

public class CommonList<E> extends ArrayList<E> {
    public CommonList() {
    }

    public <T extends CommonList> T addList(CommonList<E> commonList, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        T res = this.copy(clazz);
        Iterator<E> var4 = commonList.iterator();

        while(var4.hasNext()) {
            E e = var4.next();
            res.add(e);
        }

        return res;
    }

    public <T extends CommonList> T copy(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T t = clazz.newInstance();
        Iterator<E> var3 = this.iterator();

        while(var3.hasNext()) {
            E e = var3.next();
            t.add(e);
        }

        return t;
    }
}