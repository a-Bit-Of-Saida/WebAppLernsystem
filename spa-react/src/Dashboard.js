import React from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import './Dashboard.css'; // Import the CSS file for styling

function Dashboard() {
  const navigate = useNavigate();
  const { userId } = useParams();

  const handleLogout = () => {
    // Handle logout logic here (e.g., clear user data, redirect to login)
    navigate('/login');
  };

  return (
    <div className="dashboard-container">
      <h2 className="dashboard-title">Dashboard für Benutzer {userId}</h2>
      <button 
        onClick={() => navigate('/courses')}
        className="dashboard-button"
      >
        Kurse
      </button>
      <button 
        onClick={() => navigate('/todo')}
        className="dashboard-button"
      >
        To-Do-Liste
      </button>
      <button 
        onClick={handleLogout}
        className="dashboard-button"
      >
        Abmelden
      </button>
    </div>
  );
}

export default Dashboard;