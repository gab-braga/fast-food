package io.github.fgabrielbraga.fastfoodsimulator.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "table_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "Invalid code!")
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "The client field cannot be empty!")
    @Size(min = 1, max = 100, message = "The client must respect the established length of 100 characters!")
    private String client = "";

    @Column(nullable = false)
    @NotNull(message = "The total field cannot be empty!")
    private Float total = 0.0F;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Valid
    private List<Item> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String cliente) {
        this.client = cliente;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void calculateTotal() {
        if(getItems().isEmpty()) {
            total = 0.0F;
        }
        else {
            setTotal(getItems().stream().map(i -> i.getValue()).reduce(0.0F, (subtotal, value) -> subtotal + value));
        }
    }
}
