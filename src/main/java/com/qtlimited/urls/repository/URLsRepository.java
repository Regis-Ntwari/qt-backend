package com.qtlimited.urls.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qtlimited.urls.domain.URLs;
import com.qtlimited.urls.domain.User;

import java.util.List;

@Repository
public interface URLsRepository extends JpaRepository<URLs, Long> {

    Boolean existsByShortCode(String shortCode);

    List<URLs> findByUser(User user);

    Optional<URLs> findByShortCodeAndUser(String shortCode, User user);
}
