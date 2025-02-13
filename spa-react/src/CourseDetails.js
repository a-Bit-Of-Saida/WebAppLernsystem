import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import './CourseDetails.css'; 

function CourseDetails() {
  const { id } = useParams(); // Get the course ID from the URL parameters
  const navigate = useNavigate(); // Hook to navigate programmatically
  const [course, setCourse] = useState(null); // State to store course details

  // Function to fetch course details from the server
  const fetchCourseDetails = async () => {
    try {
      const response = await axios.post(
        'http://localhost:8085/graphql',
        {
          query: `
            query ($id: ID!) {
              courseById(id: $id) {
                id
                name
                description
                instructor
                files {
                  id
                  name
                  description
                }
              }
            }
          `,
          variables: { id } // Pass the course ID as a variable
        },
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      );
      setCourse(response.data.data.courseById); // Set the course details in state
    } catch (error) {
      console.error('Fehler beim Abrufen der Kursdetails:', error); // Log any errors
    }
  };

  // Fetch course details when the component mounts or the ID changes
  useEffect(() => {
    fetchCourseDetails();
  }, [id]);

  // Show a loading message while the course details are being fetched
  if (!course) {
    return <div>Loading...</div>;
  }

  return (
    <div className="course-details-container">
      <h2 className="course-details-title">{course.name}</h2>
      <div className="course-details-content">
        <p><strong>Beschreibung:</strong> {course.description}</p>
        <p><strong>Instructor:</strong> {course.instructor}</p>
        <h3>Files:</h3>
        <ul>
          {course.files.map(file => (
            <li key={file.id}>
              <strong>{file.name}</strong>: {file.description}
            </li>
          ))}
        </ul>
      </div>
      <button 
        onClick={() => navigate('/courses')} // Navigate back to the courses list
        className="course-details-button"
      >
        Zurück zu den Kursen
      </button>
    </div>
  );
}

export default CourseDetails;