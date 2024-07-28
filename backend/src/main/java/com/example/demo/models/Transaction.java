package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
    @SequenceGenerator(name = "transaction_id_seq", sequenceName = "transaction_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "senderid")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiverid")
    private Account reciever;

}
