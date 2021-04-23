package ru.aeon.test.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @NotNull
    private BigDecimal amount;

    /** Purpose of Transaction */
    private String details;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date transactionDate;

    public Transaction() {
    }

    public Transaction(Long id, User user, @NotNull BigDecimal amount, String details, Date transactionDate) {
        this.id = id;
        this.user = user;
        this.amount = amount;
        this.details = details;
        this.transactionDate = transactionDate;
    }
    public Transaction(TransactionBuilder builder) {
        id = builder.id;
        user = new User(builder.userId);
        amount = builder.amount;
        details = builder.details;
        transactionDate = builder.transactionDate;

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Transaction other = (Transaction) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    public static class TransactionBuilder {

        private Long id;
        private Long userId;
        private BigDecimal amount;
        private String details;
        private Date transactionDate;


        public TransactionBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public TransactionBuilder setUser(Long userId) {
            this.userId = userId;
            return this;
        }

        public TransactionBuilder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public TransactionBuilder setDetails(String details) {
            this.details = details;
            return this;
        }

        public TransactionBuilder setTransactionDate(Date transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }

    }
}
