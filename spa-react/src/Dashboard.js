import React from 'react';
import { useNavigate, useParams } from 'react-router-dom';

function Dashboard() {
  const navigate = useNavigate();
  const { userId } = useParams();

  const handleLogout = () => {
    // Handle logout logic here (e.g., clear user data, redirect to login)
    navigate('/login');
  };

  return (
    <div style={{ 
      display: 'flex', 
      flexDirection: 'column', 
      alignItems: 'center', 
      justifyContent: 'center', 
      height: '100vh',
      backgroundColor: '#282c34', 
      color: 'white'
    }}>
      <h2>Dashboard für Benutzer {userId}</h2>
      <button 
        onClick={() => navigate('/courses')}
        style={{ margin: '10px', padding: '10px', fontSize: '16px' }}
      >
        Kurse
      </button>
      <button 
        onClick={() => navigate('/todo')}
        style={{ margin: '10px', padding: '10px', fontSize: '16px' }}
      >
        To-Do-Liste
      </button>
      <button 
        onClick={handleLogout}
        style={{ margin: '10px', padding: '10px', fontSize: '16px' }}
      >
        Abmelden
      </button>
    </div>
  );
}

export default Dashboard;