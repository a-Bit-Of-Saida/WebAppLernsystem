import React, { useEffect, useState, useCallback } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import './ToDoList.css'; // Import the CSS file for styling

function TodoList() {
  const [todos, setTodos] = useState([]); // State to store the list of tasks
  const [error, setError] = useState(""); // State to store error messages
  const [editingTask, setEditingTask] = useState(null); // State to store the ID of the task being edited
  const [editedTask, setEditedTask] = useState({}); // State to store the details of the task being edited
  const [newTask, setNewTask] = useState({ title: "", description: "", status: "offen", dueDate: "" }); // State to store the details of the new task
  const [userId, setUserId] = useState(null); // State to store the user ID
  const navigate = useNavigate(); // Hook to navigate programmatically
    
  useEffect(() => {
    const storedUserId = localStorage.getItem("userId");
    if (storedUserId) {
      setUserId(parseInt(storedUserId, 10));
      fetchTodos(parseInt(storedUserId, 10));
    } else {
      setError("Benutzer nicht eingeloggt.");
    }
  }, []);

  const fetchTodos = useCallback(async (userId) => {
    if (!userId) return;

    try {
      const response = await axios.post(
        "http://localhost:8085/graphql",
        {
          query: `
            query GetUserTasks($userId: ID!) {
              tasksByUserId(userId: $userId) {
                id
                title
                description
                status
                dueDate
              }
            }
          `,
          variables: { userId },
        },
        { headers: { "Content-Type": "application/json" } }
      );

      setTodos(response.data.data.tasksByUserId || []); //Set the tasks in state
    } catch (error) {
      console.error("❌ Fehler beim Abrufen der Aufgaben:", error);
      setError("Fehler beim Abrufen der Aufgaben");
    }
  }, []);

  const fetchTasksDueToday = useCallback(async () => {
    try {
      const response = await axios.post(
        "http://localhost:8085/graphql",
        {
          query: `
            query {
              taskDueToday {
                id
                title
                description
                status
                dueDate
              }
            }
          `,
        },
        { headers: { "Content-Type": "application/json" } }
      );

      setTodos(response.data.data.taskDueToday || []); // Set the tasks due today in state
    } catch (error) {
      console.error("❌ Fehler beim Abrufen der heute fälligen Aufgaben:", error);
      setError("Fehler beim Abrufen der heute fälligen Aufgaben");
    }
  }, []);

  const addTask = async () => {
    if (!newTask.title || !newTask.description || !newTask.dueDate) {
      setError("Bitte alle Felder ausfüllen.");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8085/graphql",
        {
          query: `
            mutation AddTask($title: String!, $description: String!, $status: String!, $dueDate: String!, $assignee: String!) {
              addTask(title: $title, description: $description, status: $status, dueDate: $dueDate, assignee: $assignee) {
                id
                title
                description
                status
                dueDate
              }
            }
          `,
          variables: { ...newTask, assignee: userId.toString() },
        },
        { headers: { "Content-Type": "application/json" } }
      );

      setTodos([...todos, response.data.data.addTask]); // Add the new task to the list
      setNewTask({ title: "", description: "", status: "offen", dueDate: "" }); // Reset the new task form
    } catch (error) {
      console.error("❌ Fehler beim Hinzufügen der Aufgabe:", error);
      setError("Fehler beim Hinzufügen der Aufgabe: " + error.message);
    }
  };

  const updateTask = async (taskId, updatedTask) => {
    try {
      const response = await axios.post(
        "http://localhost:8085/graphql",
        {
          query: `
            mutation UpdateTask($id: ID!, $title: String, $description: String, $status: String, $dueDate: String) {
              updateTask(id: $id, title: $title, description: $description, status: $status, dueDate: $dueDate) {
                id
                title
                description
                status
                dueDate
              }
            }
          `,
          variables: { id: taskId, ...updatedTask },
        },
        { headers: { "Content-Type": "application/json" } }
      );

      setTodos((prevTodos) =>
        prevTodos.map((task) =>
          task.id === taskId ? { ...task, ...response.data.data.updateTask } : task
        )
      );

      setEditingTask(null); // Reset the editing state
    } catch (error) {
      console.error("❌ Fehler beim Aktualisieren der Aufgabe:", error);
      setError("Fehler beim Aktualisieren der Aufgabe: " + error.message);
    }
  };
  //Function to delete a task
  const deleteTask = async (taskId) => {
    try {
      await axios.post(
        "http://localhost:8085/graphql",
        {
          query: `
            mutation DeleteTask($id: ID!) {
              deleteTask(id: $id) {
                id
              }
            }
          `,
          variables: { id: taskId },
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      setTodos(todos.filter((todo) => todo.id !== taskId)); //Remove the deleted task from the list
    } catch (error) {
      console.error("Fehler beim Löschen der Aufgabe:", error);
      setError('Fehler beim Löschen der Aufgabe');
    }
  };

  //Function to get the color based on the task status
  const getStatusColor = (status) => {
    return status === "offen" ? "green" : status === "in Bearbeitung" ? "orange" : "purple";
  };

  return (
    <div className="todo-list-container">
      <h2 className="todo-list-title">To-Do Liste</h2>

      {userId && (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center", backgroundColor: "#555", padding: "10px", borderRadius: "5px", marginBottom: "20px", width: "100%", maxWidth: "600px" }}>
          <h3>Neue Aufgabe hinzufügen</h3>
          <input type="text" placeholder="Titel" value={newTask.title} onChange={(e) => setNewTask({ ...newTask, title: e.target.value })} />
          <textarea placeholder="Beschreibung" value={newTask.description} onChange={(e) => setNewTask({ ...newTask, description: e.target.value })} />
          
          <div style={{ marginBottom: "10px" }}>
            {["offen", "in Bearbeitung", "abgeschlossen"].map((status) => (
              <button
                key={status}
                onClick={() => setNewTask({ ...newTask, status })}
                style={{
                  margin: "5px",
                  padding: "5px 10px",
                  backgroundColor: newTask.status === status ? getStatusColor(status) : "#666",
                  color: "white",
                  border: "none",
                  cursor: "pointer",
                  borderRadius: "5px",
                }}
              >
                {status}
              </button>
            ))}
          </div>

          <input type="date" value={newTask.dueDate} onChange={(e) => setNewTask({ ...newTask, dueDate: e.target.value })} />
          <button onClick={addTask}>➕ Aufgabe hinzufügen</button>
        </div>
      )}

      <button 
      className="todo-list-button"
      onClick={fetchTasksDueToday} style={{ marginBottom: "20px", padding: "10px 20px", fontSize: "16px" }}>
        Heute fällige Aufgaben
      </button>

      <button 
      className="todo-list-button"
      onClick={() => fetchTodos(localStorage.getItem("userId"))} style={{ marginBottom: "20px", padding: "10px 20px", fontSize: "16px" }}>
        Alle Tasks
      </button>

      <button 
      className="todo-list-button"
      onClick={() => navigate(`/dashboard/${userId}`)} style={{ marginBottom: "20px", padding: "10px 20px", fontSize: "16px" }}>
        Zurück zum Dashboard
      </button>

      {error && <p style={{ color: "red" }}>{error}</p>}
      <ul style={{ width: "100%", maxWidth: "600px", listStyleType: "none", padding: "0" }}>
        {todos.map((todo) => (
          <li key={todo.id} style={{ margin: "10px 0", padding: "20px", backgroundColor: "#555", borderRadius: "5px", position: "relative", boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)" }}>
            {editingTask === todo.id ? (
              <div className="edit-task-form">
                <input type="text" placeholder="Titel" value={editedTask.title} onChange={(e) => setEditedTask({ ...editedTask, title: e.target.value })} />
                <textarea placeholder="Beschreibung" value={editedTask.description} onChange={(e) => setEditedTask({ ...editedTask, description: e.target.value })} />
                <input type="date" value={editedTask.dueDate} onChange={(e) => setEditedTask({ ...editedTask, dueDate: e.target.value })} />
                <select
                  value={editedTask.status}
                  onChange={(e) => setEditedTask({ ...editedTask, status: e.target.value })}
                >
                  <option value="offen">Offen</option>
                  <option value="in Bearbeitung">In Bearbeitung</option>
                  <option value="abgeschlossen">Abgeschlossen</option>
                </select>
                <button onClick={() => updateTask(todo.id, editedTask)}>✅ Speichern</button>
                <button onClick={() => setEditingTask(null)}>❌ Abbrechen</button>
              </div>
            ) : (
              <>
                <h4>{todo.title}</h4>
                <p><strong>Beschreibung:</strong> {todo.description}</p>
                <p><strong>Status:</strong> {todo.status}</p>
                <p><strong>Fälligkeitsdatum:</strong> {todo.dueDate}</p>
                <button onClick={() => { setEditingTask(todo.id); setEditedTask(todo); }}>📝 Bearbeiten</button>
                <button onClick={() => deleteTask(todo.id)}>❌ Löschen</button>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TodoList;