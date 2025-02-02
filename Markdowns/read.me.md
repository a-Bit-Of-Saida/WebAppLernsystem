# SPA React Project

Dieses Projekt beinhaltet eine Single Page Application (SPA) mit React. Ein API Gateway wurde realisert für den Login-Service (REST), Course-Servie (GraphQL) sowie den Task-Service (GraphQL).

## Inhalte dieser read.me

- [Installation](#installation)
- [Nutzung](#Nutzung)
- [Funktionen](#Funktionen)
- [API Endpunkte](#API-Endpunkte)

## Installation

1. Lade das Projekt runter
2. Öffne das Projekt in VS Code 
3. Navigiere zum project directory:
   ```sh
   cd spa-react
   ```
4. Installiere die dependencies:
   ```sh
   npm install
   ```

## Nutzung

1. Starte alle Services

- TaskManagementApplication.java:
- LoginManagementApplication.java:
- CourseManagementApplication.java:
- ApiGatewayApplication.java:
  ```sh
  mvn spring-boot:run
  ```

1. Starte den development server:
   ```sh
   npm start
   ```
2. Öffne deinen browser und navigiere zu `http://localhost:3000`.

## Funktionen

- **User Authentication**: Nutzer können sich Ein- und Ausloggen.
- **Course Management**: User können eine Liste der Kurse und seine Inhalte sehen.
- **To-Do List**: Nutzer können ihre aktuellen To-Do's sehen, erstellen, bearbeiten, aktualisieren und löschen. 

## API Endpunkte

- **Login-Serivce**: `/auth/login`
- **Course-Service:** `/graphql`
- **Task-Service:** `/graphlq`  

***  

## Requests der Services

### Authentication

- **POST** `/auth/login`: User login

    ```json
    {
    "email": "email@student.com",
    "password": "password1234"
    }
    ```

---

### Courses

- **POST** `/graphql`: Fetch all courses
  ```json
    {
  "query": "query { allCourses { id name description instructor } }"
    }
    ```

- **POST** `/graphql`: Fetch course details by ID
  ```json
  {
    "query": "query ($id: ID!) { courseById(id: $id) { id name description instructor } }",
    "variables": { "id": "1" }
  }
  ```
***
### To-Do List

- **POST** `/graphql`: Fetch tasks by user ID (e.g. 1)
    ```json
    {
  "query": "query GetUserTasks($userId: ID!) { tasksByUserId(userId: $userId) { id title description assignee status dueDate } }",
  "variables": { "userId": "1" }
    }
    ```
- **POST** `/graphql`: Fetch tasks due today
    ```json
    {
  "query": "query { taskDueToday { id title description assignee status dueDate } }"
    }
    ```
- **POST** `/graphql`: Add a new task
    ```json
    {
  "query": "mutation AddTask($title: String!, $description: String!, $status: String!, $dueDate: String!, $assignee: String!) { addTask(title: $title, description: $description, status: $status, dueDate: $dueDate, assignee: $assignee) { id title description status dueDate } }",
  "variables": { "title": "New Task", "description": "Task description", "status": "offen", "dueDate": "2023-12-31", "assignee": "1" }
    }
    ```
- **POST** `/graphql`: Update a task
    ```json
    {
  "query": "mutation UpdateTask($id: ID!, $title: String, $description: String, $status: String, $dueDate: String) { updateTask(id: $id, title: $title, description: $description, status: $status, dueDate: $dueDate) { id title description status dueDate } }",
  "variables": { "id": "1", "title": "Updated Task", "description": "Updated description", "status": "in Bearbeitung", "dueDate": "2023-12-31" }
    }
    ```
- **POST** `/graphql`: Delete a task
    ```json
    {
  "query": "mutation DeleteTask($id: ID!) { deleteTask(id: $id) { id } }",
  "variables": { "id": "1" }
    }
    ```

