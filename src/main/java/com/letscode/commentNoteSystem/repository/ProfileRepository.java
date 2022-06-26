package com.letscode.commentNoteSystem.repository;

import com.letscode.commentNoteSystem.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
