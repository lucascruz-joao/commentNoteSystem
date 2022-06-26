package com.letscode.commentNoteSystem.repository;

import com.letscode.commentNoteSystem.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
}
