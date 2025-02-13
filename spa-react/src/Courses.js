import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Courses.css'; 

function Courses() {
  const [courses, setCourses] = useState([]); //State to store the list of courses
  const navigate = useNavigate(); //Hook to navigate programmatically

  //Fetch courses from the server when the component mounts
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
        console.log('API Response:', response.data); // Log theAPI response
        setCourses(response.data.data.allCourses); //Set the courses in state
      } catch (error) {
        console.error('Fehler beim Abrufen der Kurse:', error); //Log any errors
      }
    };

    fetchCourses();
  }, []); //Empty dependency array means this effect runs once when the component mounts

  const userId = localStorage.getItem("userId"); //Get the user ID from local storage

  return (
    <div className="courses-container">
      <h2 className="courses-title">Kurse:</h2>
      <div className="courses-buttons-container">
        {courses.length > 0 ? (
          courses.map(course => (
            <button 
              key={course.id} 
              onClick={() => navigate(`/course/${course.id}`)} //Navigate to course details page
              className="courses-button"
            >
              {course.name}
            </button>
          ))
        ) : (
          <p>Keine Kurse verfügbar</p> //Display a message if there are no courses
        )}
      </div>
      <button 
        onClick={() => navigate(`/dashboard/${userId}`)} //Navigate back to the dashboard
        className="dashboard-button"
      >
        Zurück zum Dashboard
      </button>
    </div>
  );
}

export default Courses;