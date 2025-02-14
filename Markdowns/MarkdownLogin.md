# User Stories

### Benutzeranmeldung

Als Benutzer möchte ich mich mit meinen Anmeldeinformationen ( Rolle, Vorname und Nachname, E-Mail, Passwort) einloggen, um auf geschützte Bereiche der Anwendung zugreifen zu können.

### Fehlerhafte Anmeldeversuche
Als Benutzer möchte ich eine Fehlermeldung erhalten, wenn meine Anmeldeinformationen nicht korrekt sind, um zu wissen, dass ich die Eingaben überprüfen und korrigieren muss.


### Zielsetzung für den Service
Benutzeranmeldung validieren

Als System möchte ich die Anmeldeinformationen eines Benutzers mit den gespeicherten Daten vergleichen, um sicherzustellen, dass der Benutzer korrekt authentifiziert ist.

Fehlermeldungen im Falle von fehlerhaften Anmeldedaten

Antworten auf erfolgreiche oder fehlerhafte Anmeldungen

Als System möchte ich eine klare Bestätigung oder Fehlermeldung zurückgeben, damit der Benutzer weiß, ob die Anmeldung erfolgreich war oder nicht.

Als System möchte ich einen neuen Benutzer erstellen, damit neue Benutzer im System registriert werden können.

Als System möchte ich die Daten eines Benutzers anhand seiner ID abrufen, damit die Benutzerinformationen angezeigt werden können.

Als System möchte ich alle Benutzer abrufen, damit eine Liste aller registrierten Benutzer angezeigt werden kann.

Als System möchte ich die Daten eines bestehenden Benutzers aktualisieren, damit die Benutzerinformationen auf dem neuesten Stand gehalten werden können.

Als System möchte ich einen Benutzer anhand seiner ID löschen, damit der Benutzer aus dem System entfernt werden kann.

# Benutzeranmeldung und Authentifizierungsservice

In diesem Dokument werden drei Java-Klassen beschrieben, die zusammen einen Authentifizierungsservice bilden.

## 1. `User`-Klasse

Die `User`-Klasse enthält die Benutzerdaten und stellt Getter und Setter für diese Daten zur Verfügung. Sie wird in der `LogService`-Klasse zur Validierung von Anmeldeinformationen verwendet.

### Felder

| Feldname    | Typ     | Beschreibung                          |
|-------------|---------|--------------------------------------|
| `id`        | int      | Die ID des Benutzers                 |
| `role`      | String  | Die Rolle des Benutzers               |
| `firstName` | String  | Der Vorname des Benutzers             |
| `lastName`  | String  | Der Nachname des Benutzers            |
| `email`     | String  | Die E-Mail-Adresse des Benutzers      |
| `password`  | String  | Das Passwort des Benutzers            |

### Konstruktoren

- **Standardkonstruktor**: Erstellt ein leeres Benutzerobjekt.
- **Konstruktor mit Parametern**: Initialisiert ein Benutzerobjekt mit den angegebenen Werten.

### Methoden

- **Getter und Setter**: Ermöglichen den Zugriff und die Bearbeitung der privaten Felder.
  
- **`toString()`**: Gibt eine String-Darstellung des Benutzerobjekts aus.

---

## 2. `LoginService`-Klasse

Die `LoginService`-Klasse ist verantwortlich für die Benutzeranmeldung und Validierung der Anmeldeinformationen.

### Felder

| Feldname | Typ                     | Beschreibung                           |
|----------|-------------------------|---------------------------------------|
| `users`  | `ArrayList<User>`        | Eine statische Liste, die Benutzer enthält. |

### Statische Initialisierung

Die `users`-Liste wird mit drei Mock-Benutzern vorab befüllt:

| Rolle   | Vorname   | Nachname  | E-Mail               | Passwort       |
|---------|-----------|-----------|----------------------|----------------|
| Student | Merwe     | Ciceck    | email@student.com    | password1234   |
| Amin    | User2     | User2     | email@admin.com       | password5678   |
| Student | User3     | User3     | email@student2.com   | password9876   |

### Methoden

#### `login(String role, String firstName, String lastName, String email, String password)`

**Beschreibung**: Überprüft, ob die eingegebenen Anmeldeinformationen mit einem Benutzer in der `users`-Liste übereinstimmen.

- **Parameter**:
  - `id`: Die ID des Benutzers.
  - `role`: Die Rolle des Benutzers.
  - `firstName`: Der Vorname des Benutzers.
  - `lastName`: Der Nachname des Benutzers.
  - `email`: Die E-Mail-Adresse des Benutzers.
  - `password`: Das Passwort des Benutzers.

- **Rückgabewert**:
  - `true`: Wenn die Anmeldeinformationen korrekt sind.
  - `false`: Wenn die Anmeldeinformationen nicht übereinstimmen.

- **Funktionsweise**:
  1. Die Methode durchläuft die `users`-Liste.
  2. Jeder Benutzer wird geprüft, ob alle Felder mit den eingegebenen Werten übereinstimmen.
  3. Gibt eine Erfolgsmeldung aus, wenn die Anmeldedaten gültig sind, andernfalls eine Fehlermeldung.

**`createUser`**: Erstellt einen neuen User. 

**`getUser`**: Gibt einen User anhand der ID aus.

**`getAllUsers`**: Gibt die Liste der vorhandenen User aus.

**`updateUser`**: Aktualisiert die Daten eines bestehenden Users.

**`deleteUser`**: Löscht einen User.

---

## 3. `Controller`-Klasse

Die `Controller`-Klasse stellt den REST-Endpunkt für die Benutzeranmeldung und für die CRUD-Operationen zur Verfügung. Sie verwendet den `LoginService`, um die Anmeldeinformationen zu validieren und die CRUD-Operationen durchzuführen.
### Annotationen

| Annotation         | Beschreibung                                                        |
|--------------------|--------------------------------------------------------------------|
| `@RestController`  | Kennzeichnet die Klasse als REST-Controller, der HTTP-Anfragen bearbeitet. |
| `@RequestMapping`  | Definiert die Basis-URL für alle Endpunkte in der Klasse.         |
| `@PostMapping`     | Gibt die HTTP-POST-Methode für den Login-Endpunkt und createUser-Endpunkt an.             |
| `@GetMapping`      | HTTP-GET-Anfragen an einen bestimmten Endpunkt zuordnen, der die Daten abruft.             |
| `@PutMapping`      | HTTP-PUT-Anfragen an einen bestimmten Endpunkt zuordnen, der die Daten aktualisiert.             |
| `@DeleteMapping`   | HTTP-DELETE-Anfragen an einen bestimmten Endpunkt zuordnen, der die Daten löscht.             |
| `@RequestParam`    | Bindet Anforderungsparameter aus der URL oder dem Request-Body.  |
| `@Autowired`       | Ermöglicht das automatische Injektieren des `LoginService`-Beans. |

### Felder

| Feldname     | Typ               | Beschreibung                                 |
|--------------|-------------------|---------------------------------------------|
| `authService`| `LoginService`      | Der Authentifizierungsservice, der zur Validierung der Login-Daten verwendet wird. |

### Endpunkte

#### `POST /auth/login`

**Beschreibung**: Überprüft die Login-Daten des Benutzers und gibt eine entsprechende HTTP-Antwort zurück.

- **Parameter**:
  - `role`,  `firstName`, `lastName`, `email`,`password`

- **Rückgabewert**:
  - **Status 200 (OK)**: Wenn die Anmeldeinformationen korrekt sind, wird eine Erfolgsmeldung zurückgegeben.
  - **Status 401 (Unauthorized)**: Wenn die Anmeldeinformationen falsch sind, wird eine Fehlermeldung zurückgegeben.

- **Funktionsweise**:
  1. Die `login`-Methode empfängt die Anmeldedaten als HTTP-Request-Parameter.
  2. Die Anmeldedaten werden an den `LoginService` weitergegeben, um die Validität zu überprüfen.
  3. Gibt eine entsprechende Antwort zurück, je nachdem, ob die Anmeldeinformationen gültig sind oder nicht.

#### `POST /auth/users`

**Beschreibung**: Erstellt einen neuen Benutzer.

- **Parameter**:
  - `role`,  `firstName`, `lastName`, `email`,`password`

- **Rückgabewert**:
  - **Status 201 (Created)**: Wenn der Benutzer erfolgreich erstellt wurde.
  - **Status 400 (Bad Request)**: Wenn die Anfrage fehlerhaft ist.

- **Funktionsweise**:
  1. Die `createUser`-Methode empfängt die Benutzerdaten als HTTP-Request-Parameter.
  2. Die Benutzerdaten werden an den `LoginService` weitergegeben, um den neuen Benutzer zu erstellen.
  3. Gibt eine entsprechende Antwort zurück, je nachdem, ob der Benutzer erfolgreich erstellt wurde oder nicht.

#### `GET /auth/users/{id}`

**Beschreibung**: Ruft einen Benutzer anhand seiner ID ab.

- **Parameter**:
  - `id` (Pfadparameter)

- **Rückgabewert**:
  - **Status 200 (OK)**: Wenn der Benutzer gefunden wurde.
  - **Status 404 (Not Found)**: Wenn der Benutzer nicht existiert.

- **Funktionsweise**:
  1. Die `getUser`-Methode empfängt die Benutzer-ID als Pfadparameter.
  2. Die Benutzer-ID wird an den `LoginService` weitergegeben, um die Benutzerdaten abzurufen.
  3. Gibt eine entsprechende Antwort zurück, je nachdem, ob der Benutzer gefunden wurde oder nicht.

#### `GET /auth/users`

**Beschreibung**: Ruft alle Benutzer ab.

- **Rückgabewert**:
  - **Status 200 (OK)**: Wenn die Benutzerliste erfolgreich abgerufen wurde.

- **Funktionsweise**:
  1. Die `getAllUsers`-Methode ruft alle Benutzer aus dem `LoginService` ab.
  2. Gibt die Liste der Benutzer zurück.
  
#### `PUT /auth/users/{id}`

**Beschreibung**: Aktualisiert die Daten eines Benutzers.

- **Parameter**:
  - `id` (Pfadparameter)
  - `role`, `firstName`, `lastName`, `email`, `password`

- **Rückgabewert**:
  - **Status 200 (OK)**: Wenn der Benutzer erfolgreich aktualisiert wurde.
  - **Status 404 (Not Found)**: Wenn der Benutzer nicht existiert.

- **Funktionsweise**:
  1. Die `updateUser`-Methode empfängt die Benutzer-ID als Pfadparameter und die neuen Benutzerdaten als HTTP-Request-Parameter.
  2. Die Daten werden an den `LoginService` weitergegeben, um den Benutzer zu aktualisieren.
  3. Gibt eine entsprechende Antwort zurück, je nachdem, ob der Benutzer erfolgreich aktualisiert wurde oder nicht.

#### `DELETE/auth/users/{id}`

**Beschreibung**: Löscht einen Benutzer anhand seiner ID.

- **Parameter**:
  - `id` (Pfadparameter)

- **Rückgabewert**:
  - **Status 200 (OK)**: Wenn der Benutzer erfolgreich gelöscht wurde.
  - **Status 404 (Not Found)**: Wenn der Benutzer nicht existiert.

- **Funktionsweise**:
  1. Die `deleteUser`-Methode empfängt die Benutzer-ID als Pfadparameter.
  2. Die Benutzer-ID wird an den `LoginService` weitergegeben, um den Benutzer zu löschen.
  3. Gibt eine entsprechende Antwort zurück, je nachdem, ob der Benutzer erfolgreich gelöscht wurde oder nicht.
  
---
## Endpunkte

#### Benutzeranmeldung
**POST /auth/login**

**Beschreibung:**
Überprüft die Anmeldedaten eines Benutzers.

**Request Body:**
```json
{
  "email": "email@student.com",
  "password": "password1234"
}
```

---

#### Alle Benutzer abrufen
**GET /auth/users**

**Beschreibung:**
Gibt eine Liste aller Benutzer zurück.

**Request Body:**
_None_

---

#### Benutzer nach ID abrufen
**GET /auth/users/{id}**

**Beschreibung:**
Ruft einen bestimmten Benutzer anhand seiner ID ab.

**Request Body:**
_None_

---

#### Neuen Benutzer erstellen
**POST /auth/users**

**Beschreibung:**
Erstellt einen neuen Benutzer.

**Headers:**
`Content-Type: application/json`

**Request Body:**
```json
{
  "role": "Student",
  "firstName": "Example",
  "lastName": "Example",
  "email": "123@example.com",
  "password": "password123"
}
```

---

#### Benutzer aktualisieren
**PUT /auth/users/{id}**

**Beschreibung:**
Aktualisiert die Daten eines bestehenden Benutzers.

**Headers:**
`Content-Type: application/json`

**Request Body:**
```json
{
  "role": "Student",
  "firstName": "Example",
  "lastName": "Example",
  "email": "123@example.com",
  "password": "password123"
}
```

---

#### Benutzer löschen
**DELETE /auth/users/{id}**

**Beschreibung:**
Löscht einen Benutzer anhand seiner ID.

**Request Body:**
_None_

---
