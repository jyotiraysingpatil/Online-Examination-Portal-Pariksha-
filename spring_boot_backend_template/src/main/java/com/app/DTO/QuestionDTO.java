package com.app.DTO;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.app.entities.Quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

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

	  private Long quizId;
	  	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
