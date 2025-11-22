package com.example.naega.repository.answer;

import com.example.naega.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answers,Long> {
    long countByUsersId(Long userId);
    @Query("SELECT a FROM Answers a JOIN FETCH a.question WHERE a.users.id = :userId")
    List<Answers> findAllByUserId(@Param("userId") Long userId);
}