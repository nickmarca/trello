package com.nickmarca.trello.repository;

import com.nickmarca.trello.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {
}
