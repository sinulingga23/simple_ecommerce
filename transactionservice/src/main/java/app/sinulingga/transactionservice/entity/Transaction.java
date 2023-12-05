package app.sinulingga.transactionservice.entity;

import app.sinulingga.transactionservice.definition.StatusPayment;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(schema = "transactions", name = "transactions")
@Entity(name = "transactions")
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column(name = "status_payment", length = 100, nullable = true)
    @Enumerated(EnumType.STRING)
    private StatusPayment statusPayment;

    @Column(name = "total_payment", nullable = true)
    private BigDecimal totalPayment;

    @Column(name = "transaction_time",  nullable = false)
    private LocalDateTime transactionTime;

    @OneToMany(mappedBy = "transaction")
    public List<TransactionDetail> transactionDetails;

    public Transaction() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StatusPayment getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public List<TransactionDetail> getTransactionDetails() {
        return transactionDetails;
    }

}
