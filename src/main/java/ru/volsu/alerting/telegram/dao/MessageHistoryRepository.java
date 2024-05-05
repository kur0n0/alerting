package ru.volsu.alerting.telegram.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volsu.alerting.telegram.model.MessageHistory;

@Repository
public interface MessageHistoryRepository extends JpaRepository<MessageHistory, Integer> {
}