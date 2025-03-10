package com.qtlimited.urls.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qtlimited.urls.domain.URLs;

@Repository
public interface URLsRepository extends JpaRepository<URLs, Long> {

    Boolean existsByShortCode(String shortCode);
}
