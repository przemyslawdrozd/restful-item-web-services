package com.example.demo.repository;

import com.example.demo.model.response.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findItemByName(String name);

    @Query("SELECT i.amount FROM Item i")
    List<Integer> getAllAmount();
}