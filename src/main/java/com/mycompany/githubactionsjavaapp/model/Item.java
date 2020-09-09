package com.mycompany.githubactionsjavaapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
public class Item {
    @Id
    private UUID id;
    private String name;
    private BigDecimal price;
}
