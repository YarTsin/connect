package com.example.connect.service;

import com.example.connect.primary_repo.PrimaryRepo;
import com.example.connect.secondary_repo.SecondaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

  @Autowired
  private PrimaryRepo primaryRepo;

  @Autowired
  private SecondaryRepo secondaryRepo;

  public void test() {
    System.out.println("Данные первой базы данных");
    System.out.println(primaryRepo.findAll());
    System.out.println("Данные второй базы данных");
    System.out.println(secondaryRepo.findAll());
  }

}
