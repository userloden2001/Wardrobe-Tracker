package com.example.wardeobetrackerthird.category;
import com.example.wardeobetrackerthird.brand.Brand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name is required")
    @Column(length=45, nullable=false, unique=true)
    private String name;

    @ManyToOne
    @JoinColumn(name="brand_id")
    private Brand brand;

    /*/public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }/*/

    public Category() {
    }
    public Category(Integer id) {
        this.id = id;
    }
    public Category(String name) {
        this.name = name;
    }
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
    @Override
    public String toString() {
        return name;
    }
}