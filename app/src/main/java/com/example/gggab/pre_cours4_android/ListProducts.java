package com.example.gggab.pre_cours4_android;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by gggab(Zombietux) on 2018-01-30.
 */

class ListProducts extends ArrayList<Product> {

    @Override
    public boolean add(Product product) {
        for (Field field : product.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(product)!=null) {
                    if (Objects.equals(field.getType().getName(), "java.lang.String")){
                        try {
                            String temp = null;
                            try {
                                temp = field.get(product).toString().trim();
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            }

                            field.set(product,temp);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        return super.add(product);
    }
}
