package com.microservices.skeleton;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bike")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Bike {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = false, name = "coverage")
    @Min(value = 0)
    @Max(value = 3000)
    private Double coverage;

    @Column(nullable = false, unique = false, name = "risk")
    private double risk = 0.3;

    @Column(nullable = false, unique = false, name = "price")
    private double price;

}

