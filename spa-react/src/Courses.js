import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Courses.css'; 

function Courses() {
  const [courses, setCourses] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const response = await axios.post('http://localhost:8085/graphql', {
          query: `
            query {
              allCourses {
                id
                name
                description
                instructor
              }
            }
          `
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });
        console.log('API Response:', response.data); // Protokolliere die API-Antwort
        setCourses(response.data.data.allCourses);
      } catch (error) {
        console.error('Fehler beim Abrufen der Kurse:', error);
      }
    };

    fetchCourses();
  }, []);

  const userId = localStorage.getItem("userId");

  return (
    <div className="courses-container">
      <h2 className="courses-title">Kurse:</h2>
      <div className="courses-buttons-container">
        {courses.length > 0 ? (
          courses.map(course => (
            <button 
              key={course.id} 
              onClick={() => navigate(`/course/${course.id}`)}
              className="courses-button"
            >
              {course.name}
            </button>
          ))
        ) : (
          <p>Keine Kurse verfügbar</p>
        )}
      </div>
      <button 
        onClick={() => navigate(`/dashboard/${userId}`)}
        className="dashboard-button"
      >
        Zurück zum Dashboard
      </button>
    </div>
  );
}

export default Courses;