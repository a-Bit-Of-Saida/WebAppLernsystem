# Markdown des Backends TaskWebService

## Übersicht
Das **Task-Management-Backend** ist zur Verwaltung von Aufgaben da. Es bietet dabei verschiedene Funktionen an, um einen genauen Überblick über geplante Aufgaben zu haben.  

## Inhalte dieser Markdown

- [Web-API-Technologie](#web-api-technologie)
- [Hauptfunktionen](#hauptfunktionen)
- [Erläuterung der einzelnen Klassen](#erläuterung-der-einzelnen-klassen)
- [Queries](#queries)
- [Mutationen](#mutationen)
- [User Stories](#user-stories)
- [Requests und Responses des Taskmanagement-Services](#requests-und-responses-des-taskmanagement-services)

## Web-API-Technologie

Es wird GraphQL genutzt, um eine flexible Schnittstelle für die Interaktion mit den Aufgaben anzubieten. Es werden Queries und Mutationen ermöglicht.

Dabei gibt es einen einzigen Endpoint : <mark>/graphql</mark>

## Hauptfunktionen

- Erstellen von Aufgaben
- Bearbeiten von Aufgaben
- Löschen von Aufgaben
- Aufrufen einer Aufgabe anhand ihrer ID
- Aktualisieren einer bestehenden Aufgabe




## Erläuterung der einzelnen Klassen

`InitData` : Erstellt und speichert Beispielaufgaben.

`GraphqlController`: Definiert GraphQL-Abfragen und Mutationen für die Aufgabenverwaltung.

`Task`: Repräsentiert eine Aufgabe mit allen relevanten Attributen.

`TaskRepository`: Dient als Speicher für Aufgaben und erweitert die Hash-Map, um Aufgaben mit ihren zugewiesenen IDs als Schlüssel zu speichern.

`TaskService`: Stellt die Logik für die Verwaltung der Aufgaben bereit. 

`TaskManagementApplication`: Startet als Einstiegspunkt die Spring Boot-Anwendung.


## Queries
| Query Name       | Beschreibung                          | Parameter          | Rückgabewert                |
|------------------|---------------------------------------|--------------------|-----------------------------|
| `ToDoList`     | Gibt eine Liste aller Aufgaben zurück    | -                  | `[Task]`                  |
| `TaskById`     | Gibt eine Aufgabe anhand ihrer ID zurück | `id: ID!`          | `Task`                    |
| `TaskDueToday`   | Gibt die heute fälligen Aufgaben zurück | -   | `Task`                    |

**TaskDueToday** nutzt folgende Methode, um das Fälligkeitsdatum mit dem heutigen Datum zu vergleichen : 

```java
  if (task.getdueDate() != null && LocalDate.parse(task.getdueDate(), formatter).equals(today)) {
                tasksDueToday.add(task);
```


## Mutationen

| Mutation Name        | Beschreibung                          | Parameter                                      | Rückgabewert                |
|----------------------|---------------------------------------|------------------------------------------------|-----------------------------|
| `addTask`          | Erstellt eine neue Aufgabe            | `title: String!, description: String!, assignee: Long!, status: String, dueDate: String` | `Task`                    |
| `updateTask`       | Aktualisiert eine vorhandene Aufgabe  | `id: ID!, title: String, description: String, assignee: Long, status: String, dueDate: String` | `Task`                    |
| `deleteTask`       | Löscht eine Aufgabe                     | `id: ID!`                                      | `Task`                    |

### Erklärung der Änderung des **Assignee-Attribut**
Das ``assignee``-Attribut in der ``Task``-Klasse ist jetzt ein **Long**, um die **ID** des zugewiesenen Benutzers zu speichern. Dadurch wird eine eindeutige Zuordnung der Aufgaben zu Benutzern ermöglicht und erleichtert somit die Verwaltung und Abfrage von Aufgaben basierend auf dem zugewiesenen **Benutzer**.

#### Beispiel für die Verwendung des ``assignee``-Attributs

``` java
Task task = new Task ();
task.setAssignee(1L); // Setzt die ID des zugewiesenen Benutzers
```

## User Stories

1. ***Aufgaben verwalten :***  
- Als **Benutzer** möchte ich eine neue Aufgabe **hinzufügen, aktualisieren oder löschen**, um **meine Aufgaben verwalten und verfolgen zu können**.
2. ***Anzeigen aller Aufgaben :*** 
- Als **Benutzer** möchte ich **alle Aufgaben anzeigen**, um **einen Überblick über meine Aufgaben zu erhalten**.
3. ***Aufgabe nach ID suchen :***  
- Als **Benutzer** möchte ich **eine Aufgabe nach ihrer ID suchen**, um **schnell spezifische Aufgaben zu finden**.
4. ***Anzeige von heute fälligen Aufgaben :***  
- Als **Benutzer** möchte ich **alle heute fälligen Aufgaben anzeigen**, um **meine dringenden Aufgaben zu priorisieren**.
5. ***Aktualisierung von Aufgaben :***  
- Als **Benutzer** möchte ich **den Status einer Aufgabe ändern**, um **den Fortschritt meiner Aufgaben zu beobachten**.
6. ***Anzeige persönlicher Aufgaben :***
- Als **Benutzer** möchte ich **nur meine eigenen Aufgaben angezeigt bekommen**, um **den Überblick zu behalten**.

## Requests und Responses des Taskmanagement-Services
>Alle POST-Operationen sind Requests (Anfragen) und die jeweiligen Responses (Antworten) sind direkt darunter angegeben.
- **POST** `/graphql`: Heutige fällige Aufgaben **abrufen**
    ```json
    {
  "query": "query { taskDueToday { id title description assignee status dueDate } }"
    }
    ```
**Antwort:**
```json
{
  "data": {
      "taskDueToday": [
          {
              "id": "5",
              "title": "New Task",
              "description": "Task description",
              "assignee": "1",
              "status": "offen",
              "dueDate": "2025-02-09"
          }
      ]
  }
}
```
- **POST** `/graphql`: Eine neue Aufgabe **hinzufügen**
    ```json
    {
  "query": "mutation AddTask($title: String!, $description: String!, $status: String!, $dueDate: String!, $assignee: String!) { addTask(title: $title, description: $description, status: $status, dueDate: $dueDate, assignee: $assignee) { id title description status dueDate } }",
  "variables": { "title": "New Task", "description": "Task description", "status": "offen", "dueDate": "2025-02-09", "assignee": "1" }
    }
    ```
  **Antwort:** 
    ```json
    {
    "data": {
        "addTask": {
            "id": "5",
            "title": "New Task",
            "description": "Task description",
            "status": "offen",
            "dueDate": "2025-02-09"
        }
      }
    }
    ```




- **POST** `/graphql`: Eine Aufgabe **aktualisieren**
    ```json
    {
  "query": "mutation UpdateTask($id: ID!, $title: String, $description: String, $status: String, $dueDate: String) { updateTask(id: $id, title: $title, description: $description, status: $status, dueDate: $dueDate) { id title description status dueDate } }",
  "variables": { "id": "5", "title": "New Task", "description": "Task description", "status": "offen", "dueDate": "2025-02-09" }
    }
    ```
    **Antwort:** 
    ```json
    {
    "data": {
        "updateTask": {
            "id": "5",
            "title": "New Task",
            "description": "Task description",
            "status": "offen",
            "dueDate": "2025-02-09"
        }
      }
    }
    ```

- **POST** `/graphql`: Eine Aufgabe **löschen**
    ```json
    {
  "query": "mutation DeleteTask($id: ID!) { deleteTask(id: $id) { id } }",
  "variables": { "id": "1" }
    }
    ```
     **Antwort:** 
    ```json
    {
    "data": {
        "deleteTask": {
            "id": "1"
        }
      }
    }
    ```



**Hinweis:** Die Dollarzeichen (`$`) in den GraphQL-Requests sind Platzhalter für Variablen, die dynamische Werte in Abfragen und Mutationen einfügen und so eine flexible Datenverarbeitung ermöglichen.




