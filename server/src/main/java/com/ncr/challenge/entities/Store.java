package com.ncr.challenge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "store")
public class Store extends BaseEntity {

  private Short number;
  private String name;

}
