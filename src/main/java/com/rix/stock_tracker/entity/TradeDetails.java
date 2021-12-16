package com.rix.stock_tracker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TRADEDETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "open_price")
    private double openPrice;

    @Column(name = "close_price")
    private double closePrice;

    @Column(name = "commission")
    private double commission;

    @Column(name = "profit")
    private double profit;

    @OneToOne(mappedBy = "tradeDetails")
    private Trade trade;

}
