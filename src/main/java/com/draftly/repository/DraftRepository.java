package com.draftly.repository;

import com.draftly.model.Draft;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DraftRepository extends JpaRepository<Draft, Long> {
    List<Draft> findByUserId(Long userId);
}
