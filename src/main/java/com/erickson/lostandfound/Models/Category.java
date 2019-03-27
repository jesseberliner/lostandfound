package com.erickson.lostandfound.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;

    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Item> items;

    //Getters and setters
    public long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(long categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public Set<Item> getItems()
    {
        return items;
    }

    public void setItems(Set<Item> items)
    {
        this.items = items;
    }
}
