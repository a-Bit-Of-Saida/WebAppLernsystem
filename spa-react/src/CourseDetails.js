import axios from "axios";
import React, { useEffect, useState } from "react";

function CourseDetails() {
  const [course, setCourse] = useState(null);

  const fetchCourseDetails = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8085/graphql",
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
          variables: { id }, // Dynamische ID hier übergeben
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      setCourse(response.data.data.courseById);
    } catch (error) {
      console.error("Fehler beim Abrufen der Kursdetails:", error);
    }
  };

  useEffect(() => {
    fetchCourseDetails();
  }, [id]);

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        height: "100vh",
        backgroundColor: "#282c34",
        color: "white",
      }}
    >
      <h2>{course?.name}</h2>
      <p>{course?.description}</p>
      <p>Instructor: {course?.instructor}</p>
      <h3>Files:</h3>
      <ul>
        {/* Ensure that course.files exists */}
        {course?.files?.length > 0 ? (
          course.files.map((file) => (
            <li key={file.id}>
              <h4>{file.name}</h4>
              <p>{file.description}</p>
            </li>
          ))
        ) : (
          <p>Keine Dateien vorhanden.</p>
        )}
      </ul>
    </div>
  );
}

export default CourseDetails;
