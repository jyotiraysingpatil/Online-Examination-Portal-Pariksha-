import React, { useState } from 'react';
import axios from 'axios';

const AdminForm = () => {
    const [admin, setAdmin] = useState({
        firstName: '',
        lastName: '',
        username: '',
        password: '',
        isActive: true,
        categories: [{ title: '', description: '' }]
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setAdmin({ ...admin, [name]: value });
    };

    const handleCategoryChange = (index, e) => {
        const { name, value } = e.target;
        const categories = [...admin.categories];
        categories[index][name] = value;
        setAdmin({ ...admin, categories });
    };

    const addCategory = () => {
        setAdmin({ ...admin, categories: [...admin.categories, { title: '', description: '' }] });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/admin/insert', admin);
            console.log('Admin added:', response.data);
            alert('Admin and Category added successfully!');
        } catch (error) {
            console.error('There was an error adding the admin!', error);
        }
    };

    return (
        <div>
            <h2>Add Admin</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>First Name:</label>
                    <input type="text" name="firstName" value={admin.firstName} onChange={handleInputChange} required />
                </div>
                <div>
                    <label>Last Name:</label>
                    <input type="text" name="lastName" value={admin.lastName} onChange={handleInputChange} required />
                </div>
                <div>
                    <label>Username:</label>
                    <input type="text" name="username" value={admin.username} onChange={handleInputChange} required />
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" name="password" value={admin.password} onChange={handleInputChange} required />
                </div>
                <div>
                    <label>Active:</label>
                    <input type="checkbox" name="isActive" checked={admin.isActive} onChange={(e) => setAdmin({ ...admin, isActive: e.target.checked })} />
                </div>

                <h3>Categories</h3>
                {admin.categories.map((category, index) => (
                    <div key={index}>
                        <div>
                            <label>Category Title:</label>
                            <input type="text" name="title" value={category.title} onChange={(e) => handleCategoryChange(index, e)} required />
                        </div>
                        <div>
                            <label>Category Description:</label>
                            <input type="text" name="description" value={category.description} onChange={(e) => handleCategoryChange(index, e)} required />
                        </div>
                    </div>
                ))}
                <button type="button" onClick={addCategory}>Add Another Category</button>

                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default AdminForm;
