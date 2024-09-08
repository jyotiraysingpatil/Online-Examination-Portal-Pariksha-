import React from 'react';
import { Link } from 'react-router-dom';

const AdminDashboard = () => {
    return (
        <div>
            <h1>Admin Dashboard</h1>
            <ul>
                <li><Link to="/add-category">Add Category</Link></li>
                <li><Link to="/add-question">Add Question</Link></li>
                {/* Add more links as needed */}
            </ul>
        </div>
    );
};

export default AdminDashboard;
