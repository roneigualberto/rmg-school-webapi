package com.gualberto.ronei.rmgschoolapi.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {


    Optional<SubCategory> findByCategoryAndName(Category category, String name);

    List<SubCategory> findByCategoryId(Long categoryId);

}
