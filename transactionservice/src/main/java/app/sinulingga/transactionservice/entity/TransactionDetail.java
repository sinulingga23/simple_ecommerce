package app.sinulingga.transactionservice.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Table(schema = "transactions", name = "transactions_detail")
@Entity(name = "transactions_detail")
public class TransactionDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = true)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;

    public TransactionDetail() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "TransactionDetail{" +
                "id=" + id +
                ", transaction=" + transaction +
                ", product=" + product +
                '}';
    }
}
