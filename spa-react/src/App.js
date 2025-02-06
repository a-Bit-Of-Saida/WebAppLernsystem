import React from 'react';
import { BrowserRouter as Router, Routes, Route, useNavigate } from 'react-router-dom';
import Courses from './Courses';
import CourseDetails from './CourseDetails';
import TodoList from './ToDoList';
import Dashboard from './Dashboard';
import Login from './Login';
import newLogo from './new-logo.svg'; 
import './App.css';

function Home() {
  const navigate = useNavigate();

  const handleLoginClick = () => {
    const logo = document.querySelector('.App-logo');
    logo.classList.add('zoom-fade');
    setTimeout(() => {
      navigate('/login');
    }, 300); 
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={newLogo} className="App-logo" alt="logo" /> {/* Use the new logo with the App-logo class */}
        <p className="lernsystem-text">Lernsystem🏫📝</p> {/* Apply the new CSS class */}
        <button className="login-button" onClick={handleLoginClick}>
          Login
        </button>
      </header>
    </div>
  );
}

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/dashboard/:userId" element={<Dashboard />} />
        <Route path="/courses" element={<Courses />} />
        <Route path="/course/:id" element={<CourseDetails />} />
        <Route path="/todo" element={<TodoList />} />
      </Routes>
    </Router>
  );
}

export default App;