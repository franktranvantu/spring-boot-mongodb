package com.franktran.springboot.springbootmongodb;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
@RequiredArgsConstructor
@FieldNameConstants
public class Student {
  @Id
  private String id;
  private final String firstName;
  private final String lastName;
  @Indexed(unique = true)
  private final String email;
  private final Gender gender;
  private final Address address;
  private final List<String> favoriteSubjects;
  private final BigDecimal totalSpentInBooks;
  private final LocalDateTime created;
}
