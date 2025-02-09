# CourseWebService

## Überblick

Der `CourseWebService` ist eine Spring Boot Anwendung, die es ermöglicht, Kurse zu verwalten. Die Anwendung bietet Funktionen zum Erstellen, Abrufen, Aktualisieren und Löschen von Kursen sowie zum Hinzufügen und Entfernen von Dateien zu/aus Kursen. Die Schnittstellen sind als GraphQL-APIs implementiert.

## Inhalte dieser Markdown

- [Hauptfunktionen](#hauptfunktionen)
- [User Stories](#user-stories)
- [Schnittstellendefinition](#schnittstellendefinition)
- [Queries und Mutations im json-Format für Postman](#queries-und-mutations-im-json-format-für-postman)


## Funktionsbeschreibung

### Hauptfunktionen

1. **Kurse verwalten**
   - Erstellen eines neuen Kurses
   - Abrufen aller Kurse
   - Abrufen eines Kurses nach ID
   - Abrufen eines Kurses nach Name
   - Aktualisieren eines bestehenden Kurses
   - Löschen eines Kurses

2. **Dateien in Kursen verwalten**
   - Hinzufügen einer Datei in einen Kurs
   - Entfernen einer Datei von einem Kurs

## User Stories

### Kursverwaltung

1. **Kurs erstellen**
   - **Als** Dozent **möchte ich** einen neuen Kurs erstellen, **um** den Kursinhalt und die Teilnehmer zu verwalten.

2. **Alle Kurse anzeigen**
   - **Als** Student **möchte ich** eine Liste aller verfügbaren Kurse haben, **um** zu sehen, welche Kurse angeboten werden.

3. **Kurs nach ID suchen**
   - **Als** Student **möchte ich** einen Kurs anhand seiner ID suchen **um** schnell die Details eines bestimmten Kurses zu finden.

4. **Kurs nach Name suchen**
   - **Als** Student **möchte ich** einen Kurs anhand seines Namens suchen **um** schnell die Details eines bestimmten Kurses zu finden.

5. **Kurs aktualisieren**
   - **Als** Dozent **möchte ich** die Details eines bestehenden Kurses aktualisieren, **damit** ich die Kursinformationen auf dem neuesten Stand halten kann.

6. **Kurs löschen**
   - **Als** Dozent **möchte ich** einen bestehenden Kurs löschen, **um** nicht mehr benötigte Kurse zu entfernen.

### Dateiverwaltung

1. **Datei zu Kurs hinzufügen**
   - **Als** Dozent **möchte ich** eine Datei zu einem Kurs hinzufügen, **damit** ich den Teilnehmern zusätzliche Materialien zur Verfügung stellen kann.

2. **Datei von Kurs entfernen**
   - **Als** Dozent **möchte ich** eine Datei von einem Kurs entfernen, **damit** ich veraltete oder falsche Materialien löschen kann.

## Schnittstellendefinition

### GraphQL Queries

| Query Name       | Beschreibung                          | Parameter          | Rückgabewert                |
|------------------|---------------------------------------|--------------------|-----------------------------|
| `allCourses`     | Gibt eine Liste aller Kurse zurück    | -                  | `[Course]`                  |
| `courseById`     | Gibt einen Kurs anhand seiner ID zurück | `id: ID!`          | `Course`                    |
| `courseByName`   | Gibt einen Kurs anhand seines Namens zurück | `name: String!`   | `Course`                    |

### GraphQL Mutations

| Mutation Name        | Beschreibung                          | Parameter                                      | Rückgabewert                |
|----------------------|---------------------------------------|------------------------------------------------|-----------------------------|
| `addCourse`          | Erstellt einen neuen Kurs             | `name: String!, description: String!, instructor: String!` | `Course`                    |
| `updateCourse`       | Aktualisiert einen bestehenden Kurs   | `id: ID!, name: String, description: String, instructor: String` | `Course`                    |
| `deleteCourse`       | Löscht einen Kurs                     | `id: ID!`                                      | `Course`                    |
| `addFileToCourse`    | Fügt eine Datei zu einem Kurs hinzu   | `id: ID!, fileName: String!, fileDescription: String!` | `Course`                    |
| `deleteFileFromCourse` | Entfernt eine Datei von einem Kurs  | `id: ID!, fileId: ID!`                         | `Course`                    |


### Queries und Mutations im json-Format für Postman

#### Alle Kurse abrufen
```json
{
  "query": "{ allCourses { id name description instructor files { id name description } } }"
}
```
#### Response
```json
{
    "data": {
        "allCourses": [
            {
                "id": "2",
                "name": "Analysis",
                "description": "Mathe",
                "instructor": "Frau Schmidt",
                "files": [
                    {
                        "id": "3",
                        "name": "Mathe Skript",
                        "description": "Skript zur Vorlesung Mathematik"
                    }
                ]
            },
            {
                "id": "3",
                "name": "Business English",
                "description": "English",
                "instructor": "Frau Meyer",
                "files": []
            },
            {
                "id": "4",
                "name": "Spanish History",
                "description": "Spanish",
                "instructor": "Herr Gonzales",
                "files": [
                    {
                        "id": "2",
                        "name": "La Historia de España: Edad Moderna",
                        "description": "Este es un PDF sobre el tema de la Historia de España."
                    }
                ]
            },
            {
                "id": "5",
                "name": "Einführung in die Mathematik",
                "description": "Mathematik",
                "instructor": "Dr. Müller",
                "files": []
            }
        ]
    }
}
```

#### Kurs nach ID abrufen
```json
{
  "query": "{ courseById(id: \"1\") { id name description instructor files { id name description } } }"
}
```

#### Response
```json
{
    "data": {
        "courseById": {
            "id": "1",
            "name": "Algebra",
            "description": "Mathe",
            "instructor": "Herr Müller"
        }
    }
}
```

#### Kurs nach Name abrufen
```json
{
  "query": "{ courseByName(name: \"Algebra\") { id name description instructor files { id name description } } }"
}
```
#### Response
```json
{
    "data": {
        "courseByName": {
            "id": "1",
            "name": "Algebra",
            "description": "Mathe",
            "instructor": "Herr Müller",
            "files": [
                {
                    "id": "1",
                    "name": "Algebra Präsentation",
                    "description": "Dies ist eine Präsentation zum Thema Algebra."
                }
            ]
        }
    }
}
```

#### Kurs erstellen
```json
{
  "query": "mutation { addCourse(name: \"Mathematik\", description: \"Einführung in die Mathematik\", instructor: \"Dr. Müller\") { id name description instructor } }"
}
```
#### Response
```json
{
    "data": {
        "addCourse": {
            "id": "5",
            "name": "Einführung in die Mathematik",
            "description": "Mathematik",
            "instructor": "Dr. Müller"
        }
    }
}
```

#### Kurs aktualiseren
```json
{
  "query": "mutation { updateCourse(id: \"1\", name: \"Mathematik\", description: \"Fortgeschrittene Mathematik\", instructor: \"Dr. Müller\") { id name description instructor files { id name description } } }"
}
```
#### Response
```json
{
    "data": {
        "updateCourse": {
            "id": "1",
            "name": "Mathematik",
            "description": "Fortgeschrittene Mathematik",
            "instructor": "Dr. Müller",
            "files": [
                {
                    "id": "1",
                    "name": "Algebra Präsentation",
                    "description": "Dies ist eine Präsentation zum Thema Algebra."
                }
            ]
        }
    }
}
```

#### Kurs löschen
```json
{
  "query": "mutation { deleteCourse(id: \"1\") { id name description instructor files { id name description } } }"
}
```
#### Datei zu Kurs hinzufügen
```json
{
  "query": "mutation { addFileToCourse(id: \"2\", fileName: \"Mathe Skript\", fileDescription: \"Skript zur Vorlesung Mathematik\") { id name files { id name description } } }"
}
```

#### Response
```json
{
    "data": {
        "addFileToCourse": {
            "id": "2",
            "name": "Analysis",
            "files": [
                {
                    "id": "3",
                    "name": "Mathe Skript",
                    "description": "Skript zur Vorlesung Mathematik"
                }
            ]
        }
    }
}
```

#### Datei von Kurs entfernen
```json
{
  "query": "mutation { deleteFileFromCourse(id: \"1\", fileId: \"1\") { id name files { id name description } } }"
}
```