package com.franktran.springboot.springbootmongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootMongodbApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootMongodbApplication.class, args);
  }

  @Bean
  public CommandLineRunner runner(
      StudentRepository repository,
      MongoTemplate mongoTemplate) {
    return args -> {
      Address address = new Address("England", "London", "NE9");
      String email = "jahmed@gmail.com";
      Student student = new Student(
          "Jamila",
          "Ahmed",
          email,
          Gender.FEMALE,
          address,
          Arrays.asList("Computer Science"),
          BigDecimal.TEN,
          LocalDateTime.now()
      );

      Query query = Query.query(Criteria.where(Student.Fields.email).is(email));
      List<Student> students = mongoTemplate.find(query, Student.class);
      if (students.size() > 0) {
        throw new IllegalStateException(String.format("Email %s already exist", email));
      }
      repository.save(student);
    };
  }
}
