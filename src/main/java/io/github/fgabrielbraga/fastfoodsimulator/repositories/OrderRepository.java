package io.github.fgabrielbraga.fastfoodsimulator.repositories;

import io.github.fgabrielbraga.fastfoodsimulator.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o ORDER BY o.client")
    public List<Order> findAllSortedByClient();
}
