package com.example.connect.secondary_entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "public", name = "secondary_table")
public class SecondaryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;
}
