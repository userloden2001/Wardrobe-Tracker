package com.example.wardeobetrackerthird.brand;
import com.example.wardeobetrackerthird.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name is required")
    @Size(max = 45, message = "Name cant be longer than 45 symbols")
    @Column(length=45, nullable=false, unique=true)
    private String name;
    @OneToMany
    @JoinColumn(name="brand_id")
    private List<Category> categories=new ArrayList<>();

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
    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
