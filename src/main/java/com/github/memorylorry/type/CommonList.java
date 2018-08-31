package com.github.memorylorry.type;

import java.util.ArrayList;

public class CommonList<E> extends ArrayList<E> {

    /**
     * Doesn't change data itself, return a new result
     * @param commonList
     * @return
     */
    public <T extends CommonList> T addList(CommonList<E> commonList,Class<T> clazz) throws InstantiationException, IllegalAccessException {
        T res = copy(clazz);
        for(E e:commonList){
            res.add(e);
        }
        return res;
    }

    /**
     * copy one new itself
     * @return
     */
    public <T extends CommonList> T copy(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T t = clazz.newInstance();
        for(E e:this){
            t.add(e);
        }
        return t;
    }

}
