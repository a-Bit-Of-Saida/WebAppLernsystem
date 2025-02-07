import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import './CourseDetails.css'; 

function CourseDetails() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [course, setCourse] = useState(null);

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
          variables: { id }
        },
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      );
      setCourse(response.data.data.courseById);
    } catch (error) {
      console.error('Fehler beim Abrufen der Kursdetails:', error);
    }
  };

  useEffect(() => {
    fetchCourseDetails();
  }, [id]);

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
        onClick={() => navigate('/courses')}
        className="course-details-button"
      >
        Zurück zu den Kursen
      </button>
    </div>
  );
}

export default CourseDetails;