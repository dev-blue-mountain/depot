package com.bm.learning.multimodule.model;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

class ProductTest {
    @Test
    void getProduct(){
        Product p = new Product("keyboard");
        System.out.println(p.getProductName());

    }

    @Test
    void streamTest(){
        String[] strArray = { "x", "y", "xyz", "abc"};
        Object[] outputStrArray = Stream.of(strArray)
                .map(item-> item.toUpperCase())
                .filter(elem-> elem.length() ==1)
                .sorted()
                .toArray();
        System.out.println(outputStrArray);
    }


}