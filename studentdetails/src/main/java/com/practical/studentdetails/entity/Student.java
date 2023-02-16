package com.practical.studentdetails.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private  String email;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private List<Result> results;
}
