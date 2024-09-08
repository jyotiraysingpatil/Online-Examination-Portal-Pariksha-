package com.app.entities;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quesId;

    @Column(name = "question", length = 5000)
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "option1")
    private String option1;

    @Column(name = "option2")
    private String option2;

    @Column(name = "option3")
    private String option3;

    @Column(name = "option4")
    private String option4;

    @Column(name = "answer")
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")  // Ensure this column matches your DB schema
    private Quiz quiz;  // Use Quiz entity instead of Long quizId

}
