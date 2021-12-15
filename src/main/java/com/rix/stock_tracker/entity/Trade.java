package com.rix.stock_tracker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRADE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_time")
    private LocalDateTime creationTimestamp;

    @Column(name = "update_time")
    private LocalDateTime updateTimestamp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trade_details_id")
    private TradeDetails tradeDetails;

    @PrePersist
    public void beforeSave() {
        creationTimestamp = LocalDateTime.now();
        updateTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    public void beforeUpdate() {
        updateTimestamp = LocalDateTime.now();
    }

}
