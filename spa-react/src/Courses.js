import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

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
        setCourses(response.data.data.allCourses);
      } catch (error) {
        console.error('Fehler beim Abrufen der Kurse:', error);
      }
    };

    fetchCourses();
  }, []);
  const userId = localStorage.getItem("userId");
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
      <h2>Kurse Seite</h2>
      {courses.map(course => (
        <button 
          key={course.id} 
          onClick={() => navigate(`/course/${course.id}`)}
          style={{ margin: '10px', padding: '10px', fontSize: '16px' }}
        >
          {course.name}
        </button>
      ))}
      <button 
        onClick={() => navigate(`/dashboard/${userId}`)}
        style={{ margin: '10px', padding: '10px', fontSize: '16px' }}
      >
        Zurück zum Dashboard
      </button>
    </div>
  );
}

export default Courses;