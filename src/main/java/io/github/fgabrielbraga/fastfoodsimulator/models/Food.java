package io.github.fgabrielbraga.fastfoodsimulator.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "table_food")
public class Food {

    @Id
    @NotNull(message = "The code field cannot be empty!")
    @Min(value = 1, message = "Invalid code!")
    private Long code;

    @Column(nullable = false)
    @NotEmpty(message = "The description field cannot be empty!")
    @Size(min = 1, max = 100, message = "The description must respect the established length of 100 characters!")
    private String description = "";

    @Column(nullable = false)
    @NotEmpty(message = "The category field cannot be empty!")
    @Size(min = 1, max = 100, message = "The category must respect the established length of 100 characters!")
    private String category = "";

    @Column(nullable = false)
    @NotNull(message = "The price field cannot be empty!")
    @DecimalMin(value = "0", message = "The minimum price value must be respected!")
    @DecimalMax(value = "10000", message = "The maximum price value must be respected!")
    private Float price = 0.0F;

    @Column(nullable = false)
    @NotEmpty(message = "The image field cannot be empty!")
    @Size(min = 1, max = 255, message = "The image must respect the established length of 100 characters!")
    private String image = "";

    public Food() {
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
