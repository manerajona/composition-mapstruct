package com.manerajona.composition.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "order_item")
@Access(AccessType.FIELD)
public class OrderItemJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "item_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private OrderJpaEntity order;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer quantity;
}
