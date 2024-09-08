import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const AddQuiz = () => {
    const [quiz, setQuiz] = useState({
        title: '',
        description: '',
        numOfQuestions: 0,
        maxMarks: 0,
        categoryId: '',
        questionIds: []
    });
    const [categories, setCategories] = useState([]);
    const [questions, setQuestions] = useState([]);
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        // Fetch categories from the backend
        axios.get('http://localhost:8080/categories/getAll')
            .then(response => {
                setCategories(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the categories!', error);
            });

        // Fetch questions from the backend
        axios.get('http://localhost:8080/questions/getAll')
            .then(response => {
                setQuestions(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the questions!', error);
            });
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setQuiz({ ...quiz, [name]: value });
    };

    const handleQuestionSelection = (e) => {
        const selectedQuestionIds = Array.from(e.target.selectedOptions, option => parseInt(option.value));
        setQuiz({ ...quiz, questionIds: selectedQuestionIds });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Prepare the data to match the expected DTO structure
        const quizData = {
            ...quiz,
            numOfQuestions: parseInt(quiz.numOfQuestions),
            maxMarks: parseInt(quiz.maxMarks),
            categoryId: parseInt(quiz.categoryId),
            questionIds: quiz.questionIds.map(id => parseInt(id))
        };

        try {
            const response = await axios.post('http://localhost:8080/quiz/insert', quizData);
            if (response.status === 200) {
                setMessage('Quiz added successfully!');
                setQuiz({
                    title: '',
                    description: '',
                    numOfQuestions: 0,
                    maxMarks: 0,
                    categoryId: '',
                    questionIds: []
                });
                navigate('/admin-dashboard'); // Redirect to the admin dashboard after adding the quiz
            }
        } catch (error) {
            console.error('Error adding quiz:', error);
            setMessage('Error adding quiz. Please try again later.');
        }
    };

    return (
        <div>
            <h2>Add New Quiz</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Title:</label>
                    <input
                        type="text"
                        name="title"
                        value={quiz.title}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div>
                    <label>Description:</label>
                    <textarea
                        name="description"
                        value={quiz.description}
                        onChange={handleInputChange}
                        required
                    ></textarea>
                </div>
                <div>
                    <label>Number of Questions:</label>
                    <input
                        type="number"
                        name="numOfQuestions"
                        value={quiz.numOfQuestions}
                        onChange={handleInputChange}
                        min="1"
                        required
                    />
                </div>
                <div>
                    <label>Maximum Marks:</label>
                    <input
                        type="number"
                        name="maxMarks"
                        value={quiz.maxMarks}
                        onChange={handleInputChange}
                        min="1"
                        required
                    />
                </div>
                <div>
                    <label>Category:</label>
                    <select
                        name="categoryId"
                        value={quiz.categoryId}
                        onChange={handleInputChange}
                        required
                    >
                        <option value="" disabled>Select a category</option>
                        {categories.map(category => (
                            <option key={category.catId} value={category.catId}>
                                {category.title}
                            </option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Questions:</label>
                    <select
                        name="questionIds"
                        multiple
                        value={quiz.questionIds}
                        onChange={handleQuestionSelection}
                    >
                        {questions.map(question => (
                            <option key={question.questionId} value={question.questionId}>
                                {question.content}
                            </option>
                        ))}
                    </select>
                </div>
                <button type="submit">Add Quiz</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
};

export default AddQuiz;
