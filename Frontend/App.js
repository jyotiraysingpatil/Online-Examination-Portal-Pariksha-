import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Home from './Pages/Home';
import Login from './Components/Login';
import Register from './Components/Register';
import AdminDashboard from './Pages/AdminDashboard';
import UserDashboard from './Pages/UserDashboard';
import Navbar from './Components/Navbar';
import AdminForm from './Components/AdminForm';
import AddCategory from './Components/AddCategory';
import AddQuestion from './Components/AddQuestion';
import AddQuiz from './Components/AddQuiz';
import QuizResultPage from './Components/QuizResult'; // Import QuizResultPage

import './App.css';

const App = () => {
    const isAdminAuthenticated = !!localStorage.getItem('token');

    return (
        <Router>
            <div className="App">
                <Navbar />
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    <Route 
                        path="/admin-dashboard" 
                        element={isAdminAuthenticated ? <AdminDashboard /> : <Navigate to="/login" />} 
                    />
                    <Route 
                        path="/user-dashboard" 
                        element={<UserDashboard />} 
                    />
                    <Route 
                        path="/add-admin" 
                        element={isAdminAuthenticated ? <AdminForm /> : <Navigate to="/login" />} 
                    />
                    <Route 
                        path="/add-question" 
                        element={isAdminAuthenticated ? <AddQuestion /> : <Navigate to="/login" />} 
                    />
                    <Route 
                        path="/add-category" 
                        element={isAdminAuthenticated ? <AddCategory /> : <Navigate to="/login" />} 
                    />
                    <Route 
                        path="/admin/add-quiz" 
                        element={isAdminAuthenticated ? <AddQuiz /> : <Navigate to="/login" />} 
                    />
                    <Route 
                        path="/quiz-results" 
                        element={<QuizResultPage />} 
                    />

                </Routes>
            </div>
        </Router>
    );
}

export default App;
