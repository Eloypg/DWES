package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class StudentSubject {
    private int id_student;
    private int id_subject;
    private float calification;
    //private Student student;
    //private Subject subject;
}