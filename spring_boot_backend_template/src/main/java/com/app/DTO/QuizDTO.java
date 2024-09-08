package com.app.DTO;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuizDTO {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long quizId;

	    @Column(name = "title")
	    private String title;

	    @Column(name = "description", length = 5000)
	    private String description;

	    @Column(name = "is_active")
	    private boolean isActive;

	    @Column(name = "num_of_questions")
	    private int numOfQuestions;

	    private int maxMarks;
	    
	    private Long categoryId;  
	    private Set<Long> questionIds;  
	    private List<Long> quizResultIds;
}