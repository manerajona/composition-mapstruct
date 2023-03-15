package com.manerajona.composition.jpa.entity;

import com.manerajona.composition.core.domain.OrderState;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "order_detail")
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public class OrderJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    private ConsumerJpaEntity consumer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    private Set<OrderItemJpaEntity> items;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Column(columnDefinition = "text")
    private String notes;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public void setConsumer(ConsumerJpaEntity consumer) {
        this.consumer = consumer;
        consumer.setOrder(this);
    }

    public void addItem(OrderItemJpaEntity item) {
        if (items == null) {
            items = new HashSet<>();
        }
        items.add(item);
        item.setOrder(this);
    }

}
