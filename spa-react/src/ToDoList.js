import React, { useEffect, useState, useCallback } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function TodoList() {
  const [todos, setTodos] = useState([]);
  const [error, setError] = useState("");
  const [editingTask, setEditingTask] = useState(null);
  const [editedTask, setEditedTask] = useState({});
  const [newTask, setNewTask] = useState({ title: "", description: "", status: "offen", dueDate: "" });
  const [userId, setUserId] = useState(null);
  const navigate = useNavigate();
    
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

      setTodos(response.data.data.tasksByUserId || []);
    } catch (error) {
      console.error("❌ Fehler beim Abrufen der Aufgaben:", error);
      setError("Fehler beim Abrufen der Aufgaben");
    }
  }, []);

  const fetchTasksDueToday = async () => {
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
                assignee
                status
                dueDate
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
      console.log("Tasks due today fetched:", response.data.data.taskDueToday);
      setTodos(response.data.data.taskDueToday || []);
    } catch (error) {
      console.error("Fehler beim Abrufen der Aufgaben für heute:", error);
      setError('Fehler beim Abrufen der Aufgaben für heute');
    }
  };

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

      setTodos([...todos, response.data.data.addTask]);
      setNewTask({ title: "", description: "", status: "offen", dueDate: "" });
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

      setEditingTask(null);
    } catch (error) {
      console.error("❌ Fehler beim Aktualisieren der Aufgabe:", error);
      setError("Fehler beim Aktualisieren der Aufgabe: " + error.message);
    }
  };

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
      setTodos(todos.filter((todo) => todo.id !== taskId));
    } catch (error) {
      console.error("Fehler beim Löschen der Aufgabe:", error);
      setError('Fehler beim Löschen der Aufgabe');
    }
  };

  const getStatusColor = (status) => {
    return status === "offen" ? "green" : status === "in Bearbeitung" ? "orange" : "purple";
  };

  return (
    <div style={{ display: "flex", flexDirection: "column", alignItems: "center", minHeight: "100vh", backgroundColor: "#282c34", color: "white", padding: "20px" }}>
      <h2>To-Do Liste</h2>

      {userId && (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center", backgroundColor: "#333", padding: "10px", borderRadius: "5px", marginBottom: "20px", width: "100%", maxWidth: "600px" }}>
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

      <button onClick={fetchTasksDueToday} style={{ marginBottom: "20px", padding: "10px 20px", fontSize: "16px" }}>
        Heute fällige Aufgaben
      </button>

      <button onClick={() => fetchTodos(localStorage.getItem("userId"))} style={{ marginBottom: "20px", padding: "10px 20px", fontSize: "16px" }}>
        Alle Tasks
      </button>

      <button onClick={() => navigate(`/dashboard/${userId}`)} style={{ marginBottom: "20px", padding: "10px 20px", fontSize: "16px" }}>
        Zurück zum Dashboard
      </button>

      {error && <p style={{ color: "red" }}>{error}</p>}
      <ul style={{ width: "100%", maxWidth: "600px", listStyleType: "none", padding: "0" }}>
        {todos.map((todo) => (
          <li key={todo.id} style={{ margin: "10px 0", padding: "20px", backgroundColor: "#444", borderRadius: "5px", position: "relative", boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)" }}>
            {editingTask === todo.id ? (
              <>
                <input type="text" value={editedTask.title} onChange={(e) => setEditedTask({ ...editedTask, title: e.target.value })} />
                <textarea value={editedTask.description} onChange={(e) => setEditedTask({ ...editedTask, description: e.target.value })} />
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
              </>
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
