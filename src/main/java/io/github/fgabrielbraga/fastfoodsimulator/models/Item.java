package io.github.fgabrielbraga.fastfoodsimulator.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "table_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "Invalid code!")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    @NotNull(message = "The food field cannot be empty!")
    private Food food = new Food();

    @Column(nullable = false)
    @NotNull(message = "The quantity field cannot be empty!")
    @Min(value = 1, message = "The minimum quantity must be respected!")
    @Max(value = 100, message = "The maximum quantity must be respected!")
    private Integer quantity = 0;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getValue() {
        if(getQuantity() == null || getFood() == null) {
            return 0.0F;
        }
        else {
            return food.getPrice() * getQuantity();
        }
    }
}
