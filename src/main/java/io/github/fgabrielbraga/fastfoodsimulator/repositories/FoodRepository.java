package io.github.fgabrielbraga.fastfoodsimulator.repositories;

import io.github.fgabrielbraga.fastfoodsimulator.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
