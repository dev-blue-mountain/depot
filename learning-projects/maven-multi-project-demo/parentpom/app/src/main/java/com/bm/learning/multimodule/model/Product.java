package com.bm.learning.multimodule.model;

import java.util.Objects;

public class Product {
    private String productName;

    public Product(String productName) {
        this.productName = productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName);
    }

    public String getProductName() {
        return productName;
    }
}
