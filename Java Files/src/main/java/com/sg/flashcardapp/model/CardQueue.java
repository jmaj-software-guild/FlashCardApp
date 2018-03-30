/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flashcardapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @authors Mike Betzler, Jacob Duerr, Anna Maxam, Jeff Peterson
 */
@Entity
public class CardQueue {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private int cardQueueId;

    @Column(nullable = false)
    private int cardId;

    @Column(nullable = false)
    private String cardName;

    @Column(nullable = false)
    private String cardChallenge;

    @Column(nullable = false)
    private String cardAnswer;

    @ManyToMany
    @JoinTable(name = "CardCategoryQueue",
            joinColumns = {
                @JoinColumn(name = "cardQueueId")},
            inverseJoinColumns = {
                @JoinColumn(name = "categoryId")}
    )
    private List<Category> categories = new ArrayList<>();

    public int getCardQueueId() {
        return cardQueueId;
    }

    public void setCardQueueId(int cardQueueId) {
        this.cardQueueId = cardQueueId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardChallenge() {
        return cardChallenge;
    }

    public void setCardChallenge(String cardChallenge) {
        this.cardChallenge = cardChallenge;
    }

    public String getCardAnswer() {
        return cardAnswer;
    }

    public void setCardAnswer(String cardAnswer) {
        this.cardAnswer = cardAnswer;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.cardQueueId;
        hash = 73 * hash + this.cardId;
        hash = 73 * hash + Objects.hashCode(this.cardName);
        hash = 73 * hash + Objects.hashCode(this.cardChallenge);
        hash = 73 * hash + Objects.hashCode(this.cardAnswer);
        hash = 73 * hash + Objects.hashCode(this.categories);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CardQueue other = (CardQueue) obj;
        if (this.cardQueueId != other.cardQueueId) {
            return false;
        }
        if (this.cardId != other.cardId) {
            return false;
        }
        if (!Objects.equals(this.cardName, other.cardName)) {
            return false;
        }
        if (!Objects.equals(this.cardChallenge, other.cardChallenge)) {
            return false;
        }
        if (!Objects.equals(this.cardAnswer, other.cardAnswer)) {
            return false;
        }
        if (!Objects.equals(this.categories, other.categories)) {
            return false;
        }
        return true;
    }

    
}
