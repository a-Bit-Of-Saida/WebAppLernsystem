# Lernsystem (SPA React Project mit API Gateway und drei Services)

Dieses Projekt beinhaltet eine Single Page Application (SPA) mit React. Ein API Gateway wurde realisert für den Login-Service (REST), Course-Servie (GraphQL) sowie den Task-Service (GraphQL).

## Inhalte dieser read.me

- [Installation](#installation)
- [Nutzung](#nutzung)
- [Funktionen](#funktionen)
- [API Endpunkte](#api-endpunkte)
- [Requests der Services](#requests-der-services)
  
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
 

## Requests der Services

### Authentication

- **POST** `/auth/login`: User login

    ```json
    {
    "email": "email@student.com",
    "password": "password1234"
    }
    ```


- **GET** `/auth/users`: All users

- **GET** `/auth/users/1`: User by ID
  
- **POST** `auth/users`: Create new user
  ```json
    {
  "role": "Student",
  "firstName": "Example",
  "lastName": "Example",
  "email": "123@example.com",
  "password": "password123"
   }
   ```
  
- **PUT** `auth/users/1`: Updates User
  ```json
    {
    "role": "Student",
    "firstName": "Updated",
    "lastName": "Example",
    "email": "123@example.com",
    "password": "password123"
   }
   ```
- **GET** `/auth/users/1`: Deletes User
---
### Courses

- **POST** `/graphql`: All courses
  ```json
    {
  "query": "query { allCourses { id name instructor } }"
   }
    ```

- **POST** `/graphql`: Course by name
  ```json
  {
    "query": "{ courseByName(name: \"Algebra\") { id name description instructor files { id name description } } }"
   }
  ```

- **POST** `/graphql`: Course by ID
  ```json
  {
    "query": "{ courseById(id: \"3\") { id name description instructor files { id name description } } }"
   }
  ```


- **POST** `/graphql`: Create course
  ```json
  {
    "query": "mutation { addCourse(name: \"Mathematik\", description: \"Einführung in die Mathematik\", instructor: \"Dr. Müller\") { id name description instructor files { id name description } } }"
   }
  ```

- **POST** `/graphql`: Update course
  ```json
    {
       "query": "mutation { updateCourse(id: \"1\", name: \"Mathematik\", description: \"Fortgeschrittene Mathematik\", instructor: \"Dr. Müller\") { id name description instructor files { id name description } } }"
   }
  ```

- **POST** `/graphql`: Delete course
  ```json
    {
    "query": "mutation { deleteCourse(id: \"1\") { id name description instructor files { id name description } } }"
   }
  ```

- **POST** `/graphql`: add file to course
  ```json
   {
    "query": "mutation { addFileToCourse(id: \"1\", fileName: \"Mathe Skript\", fileDescription: \"Skript zur Vorlesung Mathematik\") { id name files { id name description } } }"
   }
  ```

  - **POST** `/graphql`: delete file from course
  ```json
    {
       "query": "mutation { addFileToCourse(id: \"1\", fileName: \"Mathe Skript\", fileDescription: \"Skript zur Vorlesung Mathematik\") { id name files { id name description } } }"
   }
  ```
  ---
### To-Do List

- **POST** `/graphql`: Fetch tasks by user ID (e.g. 1)
    ```json
    {
    "query": "query GetUserTasks($userId: ID!) { tasksByUserId(userId: $userId) { id title description assignee status dueDate } }",
    "variables": { "userId": "1" }
   }
    ```
- **POST** `/graphql`: tasks due today
    ```json
    {
    "query": "{ taskDueToday { id title description assignee status dueDate } }"
   }
    ```
- **POST** `/graphql`: Add a new task
    ```json
   {
    "query": "mutation AddTask($title: String!, $description: String!, $assignee: String!, $status: String, $dueDate: String) { addTask(title: $title, description: $description, assignee: $assignee, status: $status, dueDate: $dueDate) { id title description assignee status dueDate } }",
    "variables": { "title": "New Task", "description": "Task description", "assignee": "1", "status": "offen", "dueDate": "2025-02-09" }
   }
    ```
- **POST** `/graphql`: Update a task
    ```json
    {
    "query": "mutation UpdateTask($id: ID!, $title: String, $description: String, $assignee: String, $status: String, $dueDate: String) { updateTask(id: $id, title: $title, description: $description, assignee: $assignee, status: $status, dueDate: $dueDate) { id title description assignee status dueDate } }",
    "variables": { "id": "1", "title": "Updated Task", "description": "Updated description", "assignee": "1", "status": "in Bearbeitung", "dueDate": "2025-02-09" }
   }
    ```
- **POST** `/graphql`: Delete a task
    ```json
    {
    "query": "mutation DeleteTask($id: ID!) { deleteTask(id: $id) { id } }",
    "variables": { "id": "1" }
   }
    ```

- **POST** `/graphql`: all tasks
    ```json
    {
  "query": "{ ToDoList { id title description assignee status dueDate } }"
   }
    ```



