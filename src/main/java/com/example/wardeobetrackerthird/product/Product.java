package com.example.wardeobetrackerthird.product;

import com.example.wardeobetrackerthird.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required")  ///imie musi byc
    @Column(length = 128, nullable = false, unique = true)
    private String name;

    @DecimalMin(value = "0.0", message = "Price must be greater than or equal to 0") /////// walidacja zeby nie bylo minusowych
    @Digits(integer = 10, fraction = 0, message = "Price must be a valid number")
    private float price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @OneToMany(mappedBy="product", cascade=CascadeType.ALL)
    private List<ProductDetails> details = new ArrayList<>();



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void addDetail(String name, String value) {
        this.details.add(new ProductDetails(name,value,this));
    }

    public List<ProductDetails> getDetails() {
        return details;
    }

    public void setDetails(List<ProductDetails> details) {
        this.details = details;
    }
    public void setDetail(Integer id, String name, String value) {
        this.details.add(new ProductDetails(id,name,value,this));
    }
}