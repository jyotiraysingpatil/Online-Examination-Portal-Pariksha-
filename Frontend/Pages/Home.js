import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css'; // Assuming you have a CSS file for styling

const Home = () => {
  return (
    <div className="home-container">
      <h1>Welcome to Pariksha</h1>
      <p>Your one-stop solution for online examinations</p>
      
      <div className="home-content">
        <div className="feature-card">
          <h2>Take a Quiz</h2>
          <p>Test your knowledge by taking one of our quizzes. Challenge yourself and see how much you know!</p>
          <Link to="/quizzes" className="btn">Start a Quiz</Link>
        </div>
        <div className="feature-card">
          <h2>Admin Panel</h2>
          <p>Admins can manage quizzes, categories, and view user scores.</p>
          <Link to="/admin" className="btn">Go to Admin Panel</Link>
        </div>
        <div className="feature-card">
          <h2>View Scorecard</h2>
          <p>Check your performance and track your progress over time.</p>
          <Link to="/scorecard" className="btn">View Scorecard</Link>
        </div>
      </div>
    </div>
  );
};

export default Home;
