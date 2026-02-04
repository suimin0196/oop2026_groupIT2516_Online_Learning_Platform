package com.company.models;

import java.util.List;

// Represents a paginated response
public class Page<T> {
    private List<T> items;
    private int totalItems;
    private int totalPages;
    private int currentPage;

    public Page(List<T> items, int totalItems, int totalPages, int currentPage) {
        this.items = items;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }

    // Getters
    public List<T> getItems() {
        return items;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}