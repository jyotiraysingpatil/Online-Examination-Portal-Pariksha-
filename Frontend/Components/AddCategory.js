import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './AddCategory.css';

const AddCategory = () => {
    const [category, setCategory] = useState({
        title: '',
        description: '',
        adminId: '', // Admin ID to be set dynamically
        quizIds: [1]  // Pre-select quiz with id 1 by default
    });
    const [message, setMessage] = useState('');
    const [quizzes, setQuizzes] = useState([]);

    useEffect(() => {
        // Fetch quizzes from the backend to associate with the category
        axios.get('http://localhost:8080/quizzes')
            .then(response => {
                setQuizzes(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the quizzes!', error);
            });
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setCategory({ ...category, [name]: value });
    };

    const handleQuizSelection = (e) => {
        const selectedQuizIds = Array.from(e.target.selectedOptions, option => parseInt(option.value));
        setCategory({ ...category, quizIds: selectedQuizIds });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Set the adminId from localStorage or a similar method
        const adminId = localStorage.getItem('adminId');
        if (!adminId) {
            setMessage('Admin not logged in.');
            return;
        }

        // Add the adminId to the category object
        const categoryData = {
            ...category,
            adminId: parseInt(adminId) // Ensure adminId is a number
        };

        try {
            const response = await axios.post('http://localhost:8080/categories/insert', categoryData);
            if (response.status === 200 || response.status === 201) {
                setMessage('Category added successfully!');
                // Reset the form with quiz 1 selected by default
                setCategory({ title: '', description: '', quizIds: [1] });
            } else {
                setMessage('Failed to add category. Please try again.');
            }
        } catch (error) {
            setMessage('Failed to add category. Please try again.');
            console.error('There was an error adding the category!', error);
        }
    };

    return (
        <div className="add-category-container">
            <h2>Add New Category</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Category Title:</label>
                    <input
                        type="text"
                        name="title"
                        value={category.title}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div>
                    <label>Category Description:</label>
                    <input
                        type="text"
                        name="description"
                        value={category.description}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div>
                    <label>Select Quizzes:</label>
                    <select
                        multiple
                        onChange={handleQuizSelection}
                        value={category.quizIds}
                        size={5} // Optional: Adjust size for better visibility
                    >
                        {quizzes.map(quiz => (
                            <option key={quiz.quizId} value={quiz.quizId}>
                                {quiz.title}
                            </option>
                        ))}
                    </select>
                </div>
                <button type="submit">Add Category</button>
            </form>
            {message && <p className="message">{message}</p>}
        </div>
    );
};

export default AddCategory;
