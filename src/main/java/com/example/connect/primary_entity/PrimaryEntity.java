package com.example.connect.primary_entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "public", name = "primary_table")
public class PrimaryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;
}