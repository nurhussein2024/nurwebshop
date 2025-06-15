package com.nurwebshop.exception;

// Anpassat undantag för när en produkt inte hittas
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}