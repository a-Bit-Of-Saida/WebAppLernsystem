import axios from "axios";
import React, { useEffect, useState } from "react";

function Courses() {
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const response = await axios.post(
          "http://localhost:8085/graphql",
          {
            query: `
                query {
                  allCourses {
                    id
                    name
                    description
                    instructor
                  }
                }
              `,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
        setCourses(response.data.data.allCourses);
      } catch (error) {
        console.error("Fehler beim Abrufen der Kurse:", error);
      }
    };

    fetchCourses();
  }, []);
  const userId = localStorage.getItem("userId");
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
      <h1>Kurse</h1>
      <ul>
        {courses.map((course) => (
          <li key={course.id}>
            <h2>{course.name}</h2>
            <p>{course.description}</p>
            <p>Instructor: {course.instructor}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Courses;
