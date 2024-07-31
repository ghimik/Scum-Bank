package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "friendpair")
public class FriendsPair {

    @Id
    @GeneratedValue(generator = "fp_id_seq")
    @SequenceGenerator(name = "fp_id_seq", sequenceName = "friendpair_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "first")
    private Account first;

    @ManyToOne
    @JoinColumn(name = "second")
    private Account second;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getFirst() {
        return first;
    }

    public void setFirst(Account first) {
        this.first = first;
    }

    public Account getSecond() {
        return second;
    }

    public void setSecond(Account second) {
        this.second = second;
    }
}
