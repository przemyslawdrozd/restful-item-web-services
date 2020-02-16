package com.example.demo.repository;

import com.example.demo.model.response.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findItemByName(String name);

    boolean existsByName(String name);

    @Query("SELECT i.price FROM Item i WHERE i.name = :name")
    Double getPrice(@Param("name") String name);

    @Query("SELECT i.amount FROM Item i")
    List<Integer> getAllAmount();

    @Transactional @Modifying
    @Query("UPDATE Item i SET i.price = :price WHERE i.name = :name")
    void changePriceForItem(@Param("price") Double price,
                            @Param("name") String name);
}