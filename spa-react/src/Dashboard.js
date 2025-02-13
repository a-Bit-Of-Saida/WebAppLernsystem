import React from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import './Dashboard.css'; // Import the CSS file for styling

function Dashboard() {
  const navigate = useNavigate(); //Hook to navigate programmatically
  const { userId } = useParams(); //Get the user ID from the URL parameters

  const handleLogout = () => {
    // Handle logout logic here (e.g., clear user data, redirect to login)
    navigate('/login'); //Navigate to the login page
  };

  return (
    <div className="dashboard-container">
      <h2 className="dashboard-title">Dashboard für Benutzer {userId}</h2>
      <button 
        onClick={() => navigate('/courses')} //Navigate to the courses page
        className="dashboard-button"
      >
        Kurse
      </button>
      <button 
        onClick={() => navigate('/todo')} //Navigate to the to-do list page
        className="dashboard-button"
      >
        To-Do-Liste
      </button>
      <button 
        onClick={handleLogout} //Handle logout 
        className="dashboard-button"
      >
        Abmelden
      </button>
    </div>
  );
}

export default Dashboard;