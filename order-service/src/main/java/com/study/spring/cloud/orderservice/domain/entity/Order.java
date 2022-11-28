package com.study.spring.cloud.orderservice.domain.entity;

import com.study.spring.cloud.orderservice.domain.dto.OrderDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tn_order")
public class Order {

    @Id @GeneratedValue
    private Long orderId;

    private Long userId;

    private String catalogId;

    private Integer quantity;

    private Integer unitPrice;

    private Integer totalPrice;

    @Column(updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createDate;

    public static Order create(OrderDto orderDto) {
        return Order.builder()
                .userId(orderDto.getUserId())
                .catalogId(orderDto.getCatalogId())
                .quantity(orderDto.getQuantity())
                .unitPrice(orderDto.getUnitPrice())
                .totalPrice(orderDto.getTotalPrice())
                .build();
    }

}