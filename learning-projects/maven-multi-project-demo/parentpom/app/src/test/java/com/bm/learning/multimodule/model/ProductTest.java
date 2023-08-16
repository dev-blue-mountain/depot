package com.bm.learning.multimodule.model;

import org.junit.jupiter.api.Test;

class ProductTest {
    @Test
    void getProduct(){
        Product p = new Product("keyboard");
        System.out.println(p.getProductName());

    }


}