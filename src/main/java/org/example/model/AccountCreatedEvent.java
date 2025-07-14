package org.example.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountCreatedEvent implements Serializable {

    private String accountId;
    private Long userId;
    private BigDecimal initialBalance;

    public AccountCreatedEvent() {
        // Default constructor for deserialization
    }

    public AccountCreatedEvent(String accountId, Long userId, BigDecimal initialBalance) {
        this.accountId = accountId;
        this.userId = userId;
        this.initialBalance = initialBalance;
    }

    // === Getters & Setters ===

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    // === toString() ===

    @Override
    public String toString() {
        return "AccountCreatedEvent{" +
                "accountId='" + accountId + '\'' +
                ", userId=" + userId +
                ", initialBalance=" + initialBalance +
                '}';
    }
}
