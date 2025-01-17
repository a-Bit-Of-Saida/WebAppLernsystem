# Markdown des Backends TaskWebService

## Übersicht
Das **Task-Management-Backend** ist zur Verwaltung von Aufgaben da. Es bietet dabei verschiedene Funktionen an, um einen genauen Überblick über geplante Aufgaben zu haben.  

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
| `addTask`          | Erstellt eine neue Aufgabe            | `title: String!, description: String!, assignee: String!, status: String, dueDate: String` | `Task`                    |
| `updateTask`       | Aktualisiert eine vorhandene Aufgabe  | `id: ID!, title: String, description: String, assignee: String, status: String, dueDate: String` | `Task`                    |
| `deleteTask`       | Löscht eine Aufgabe                     | `id: ID!`                                      | `Task`                    |

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






