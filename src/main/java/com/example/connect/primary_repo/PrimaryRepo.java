package com.example.connect.primary_repo;

import com.example.connect.primary_entity.PrimaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimaryRepo extends JpaRepository<PrimaryEntity, Long> {
}
