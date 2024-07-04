package com.ncr.challenge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(
  name = "custom_user",
  uniqueConstraints = {
    @UniqueConstraint(name = "user_unique_user_idx", columnNames = {"userName"}),
    @UniqueConstraint(name = "user_unique_mail_idx", columnNames = {"mail"})
  }
)
public class User extends BaseEntity {
  
  private String name;
  private String lastName;
  private String userName;
  private String mail;
  private String password;
  private Short profile;
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Store store;

}
