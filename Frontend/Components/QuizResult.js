import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const QuizResultPage = () => {
    const [quizResults, setQuizResults] = useState([]);
    const [quizResult, setQuizResult] = useState({
        userId: '',
        totalObtainedMarks: '',
        attemptDatetime: '',
        quizId: ''
    });
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchQuizResults = async () => {
            try {
                const response = await axios.get('http://localhost:8080/quizResult/getAll');
                setQuizResults(response.data);
            } catch (error) {
                console.error('There was an error fetching the quiz results!', error);
            }
        };
        fetchQuizResults();
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setQuizResult({ ...quizResult, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/quizResult/insert', quizResult);
            if (response.status === 200) {
                setMessage('Quiz Result added successfully!');
                setQuizResult({
                    userId: '',
                    totalObtainedMarks: '',
                    attemptDatetime: '',
                    quizId: ''
                });
                navigate('/quiz-results'); // Navigate to the list of quiz results
            }
        } catch (error) {
            console.error('Error adding quiz result:', error);
            setMessage('Error adding quiz result. Please try again later.');
        }
    };

    const handleDelete = async (quizResId) => {
        try {
            const response = await axios.delete(`http://localhost:8080/quizResult/delete/${quizResId}`);
            if (response.status === 200) {
                setMessage('Quiz Result deleted successfully!');
                setQuizResults(quizResults.filter(result => result.quizResId !== quizResId));
            }
        } catch (error) {
            console.error('Error deleting quiz result:', error);
            setMessage('Error deleting quiz result. Please try again later.');
        }
    };

    return (
        <div>
            <h2>Quiz Results</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="User ID"
                    name="userId"
                    value={quizResult.userId}
                    onChange={handleInputChange}
                />
                <input
                    type="text"
                    placeholder="Total Obtained Marks"
                    name="totalObtainedMarks"
                    value={quizResult.totalObtainedMarks}
                    onChange={handleInputChange}
                />
                <input
                    type="text"
                    placeholder="Attempt DateTime"
                    name="attemptDatetime"
                    value={quizResult.attemptDatetime}
                    onChange={handleInputChange}
                />
                <input
                    type="text"
                    placeholder="Quiz ID"
                    name="quizId"
                    value={quizResult.quizId}
                    onChange={handleInputChange}
                />
                <button type="submit">Add Quiz Result</button>
            </form>
            <p>{message}</p>

            <h3>All Quiz Results</h3>
            <ul>
                {quizResults.length ? (
                    quizResults.map(result => (
                        <li key={result.quizResId}>
                            User ID: {result.userId} | Marks: {result.totalObtainedMarks} | Quiz ID: {result.quizId}
                            <button onClick={() => handleDelete(result.quizResId)}>Delete</button>
                        </li>
                    ))
                ) : (
                    <p>No quiz results available</p>
                )}
            </ul>
        </div>
    );
};

export default QuizResultPage;
