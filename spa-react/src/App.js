import React from 'react';
import { BrowserRouter as Router, Routes, Route, useNavigate } from 'react-router-dom';
import Courses from './Courses';
import CourseDetails from './CourseDetails';
import TodoList from './ToDoList';
import Dashboard from './Dashboard';
import Login from './Login';
import newLogo from './new-logo.svg'; 
import './App.css';


// Home component
function Home() {
  const navigate = useNavigate();

// Handle the click event on the login button
  const handleLoginClick = () => {
    const logo = document.querySelector('.App-logo');
    logo.classList.add('zoom-fade'); // Add animation class to logo
    setTimeout(() => { 
      navigate('/login'); // Navigate to login page after animation
    }, 300); //Delay for animation
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={newLogo} className="App-logo" alt="logo" /> {/*Logo of the Application */}
        <p className="lernsystem-text">Lernsystem🏫📝</p> {/* Application title */}
        <button className="login-button" onClick={handleLoginClick}>
          Login
        </button>
      </header>
    </div>
  );
}

// Main App component
function App() {
  return (
    <Router>
      <Routes>
      <Route path="/" element={<Home />} /> {/* Home route */}
        <Route path="/login" element={<Login />} /> {/* Login route */}
        <Route path="/dashboard/:userId" element={<Dashboard />} /> {/* Dashboard route with userId parameter */}
        <Route path="/courses" element={<Courses />} /> {/* Courses route */}
        <Route path="/course/:id" element={<CourseDetails />} /> {/* Course details route with course id parameter */}
        <Route path="/todo" element={<TodoList />} /> {/* Todo list route */}
      </Routes>
    </Router>
  );
}

export default App;