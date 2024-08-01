package com.example.demo.repos;

import com.example.demo.dtos.StatisticsDTO;
import com.example.demo.models.Transaction;
import com.example.demo.models.projections.StatisticsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllBySenderId(Long senderId);

    List<Transaction> findAllByReceiverId(Long receiverId);

    List<Transaction> findAllByReceiverIdOrSenderId(Long senderId, Long receiverId);

    @Query(value =
            "SELECT" +
            "    SUM(CASE WHEN receiverid = ?1 THEN value ELSE 0 END) AS income, " +
            "    SUM(CASE WHEN senderid = ?1 THEN value ELSE 0 END) AS outcome, " +
            "    COUNT(*) AS count " +
            "FROM " +
            "    transaction " +
            "GROUP BY " +
            "    receiverid;",
            nativeQuery = true)
    StatisticsProjection findStats(Long id);
}
