package com.app.DTO;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.app.entities.Quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizResultDTO {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long quizResId;

	    @Column(name = "user_id")
	    private Long userId;

	    @Column(name = "total_obtained_marks")
	    private float totalObtainedMarks;

	    @Column(name = "attempt_datetime")
	    private String attemptDatetime;

	    
	    private Long quizId;
}
