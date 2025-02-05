import React from 'react';
import { BrowserRouter as Router, Routes, Route, useNavigate } from 'react-router-dom';
import Courses from './Courses';
import CourseDetails from './CourseDetails';
import TodoList from './ToDoList';
import Dashboard from './Dashboard';
import Login from './Login';
import logo from './logo.svg'; // Import the logo image
import './App.css'; // Import the CSS file for styling

function Home() {
  const navigate = useNavigate();

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>Lernsystem🏫📝</p>
        <button className="login-button" onClick={() => navigate('/login')}>
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