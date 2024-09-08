import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './AddQuestion.css'; // Ensure this file contains styles for the component

const AddQuestion = () => {
    const [question, setQuestion] = useState({
        content: '',
        image: '',
        option1: '',
        option2: '',
        option3: '',
        option4: '',
        answer: '',
        quizId: 1  // Default quiz ID set to 1
    });
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setQuestion({ ...question, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/questions/insert', question, {
                headers: {
                    'Content-Type': 'application/json',
                }
            });
            if (response.status === 200) {
                setMessage('Question added successfully!');
                setQuestion({
                    content: '',
                    image: '',
                    option1: '',
                    option2: '',
                    option3: '',
                    option4: '',
                    answer: '',
                    quizId: 1  
                });
                navigate('/admin-dashboard'); // Navigate to Admin Dashboard after adding a question
            } else {
                setMessage('Failed to add question. Please try again.');
            }
        } catch (error) {
            setMessage('Failed to add question. Please try again.');
            console.error('There was an error adding the question!', error);
        }
    };

    return (
        <div className="add-question-container">
            <h2>Add New Question</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="content">Question Content:</label>
                    <textarea
                        id="content"
                        name="content"
                        value={question.content}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="image">Image URL (optional):</label>
                    <input
                        id="image"
                        type="text"
                        name="image"
                        value={question.image}
                        onChange={handleInputChange}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="option1">Option 1:</label>
                    <input
                        id="option1"
                        type="text"
                        name="option1"
                        value={question.option1}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="option2">Option 2:</label>
                    <input
                        id="option2"
                        type="text"
                        name="option2"
                        value={question.option2}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="option3">Option 3:</label>
                    <input
                        id="option3"
                        type="text"
                        name="option3"
                        value={question.option3}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="option4">Option 4:</label>
                    <input
                        id="option4"
                        type="text"
                        name="option4"
                        value={question.option4}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="answer">Answer:</label>
                    <input
                        id="answer"
                        type="text"
                        name="answer"
                        value={question.answer}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <button type="submit">Add Question</button>
            </form>
            {message && <p className="message">{message}</p>}
        </div>
    );
};

export default AddQuestion;
