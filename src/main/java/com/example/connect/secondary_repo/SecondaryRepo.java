package com.example.connect.secondary_repo;

import com.example.connect.secondary_entity.SecondaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondaryRepo extends JpaRepository<SecondaryEntity, Long> {
}
