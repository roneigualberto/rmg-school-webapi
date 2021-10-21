package com.gualberto.ronei.rmgschoolapi.domain.course.quiz;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Optional<Quiz> findBySectionId(Long sectionId);
}
