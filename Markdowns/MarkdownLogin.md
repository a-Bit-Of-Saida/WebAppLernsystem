# Benutzeranmeldung und Authentifizierungsservice

In diesem Dokument werden drei Java-Klassen beschrieben, die zusammen einen Authentifizierungsservice bilden.

---

## 1. `User`-Klasse

Die `User`-Klasse enthält die Benutzerdaten und stellt Getter und Setter für diese Daten zur Verfügung. Sie wird in der `LogService`-Klasse zur Validierung von Anmeldeinformationen verwendet.

### Felder

| Feldname    | Typ     | Beschreibung                          |
|-------------|---------|--------------------------------------|
| `role`      | String  | Die Rolle des Benutzers  |
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

## 2. `LogService`-Klasse

Die `LogService`-Klasse ist verantwortlich für die Benutzeranmeldung und Validierung der Anmeldeinformationen.

### Felder

| Feldname | Typ                     | Beschreibung                           |
|----------|-------------------------|---------------------------------------|
| `users`  | `ArrayList<User>`        | Eine statische Liste, die Benutzer enthält. |

### Statische Initialisierung

Die `users`-Liste wird mit zwei Mock-Benutzern vorab befüllt:

| Rolle   | Vorname   | Nachname  | E-Mail               | Passwort       |
|---------|-----------|-----------|----------------------|----------------|
| Student | User1     | User1     | email@user1.com      | password1234  |
| Prof    | User2     | User2     | email@user2.com      | password5678  |

### Methoden

#### `login(String role, String firstName, String lastName, String email, String password)`

**Beschreibung**: Überprüft, ob die eingegebenen Anmeldeinformationen mit einem Benutzer in der `users`-Liste übereinstimmen.

- **Parameter**:
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

---

## 3. `Controller`-Klasse

Die `Controller`-Klasse stellt den REST-Endpunkt für die Benutzeranmeldung zur Verfügung. Sie verwendet den `LogService`, um die Anmeldeinformationen zu validieren.

### Annotationen

| Annotation         | Beschreibung                                                        |
|--------------------|--------------------------------------------------------------------|
| `@RestController`  | Kennzeichnet die Klasse als REST-Controller, der HTTP-Anfragen bearbeitet. |
| `@RequestMapping`  | Definiert die Basis-URL für alle Endpunkte in der Klasse.         |
| `@PostMapping`     | Gibt den HTTP-POST-Methode für den Login-Endpunkt an.             |
| `@RequestParam`    | Bindet Anforderungsparameter aus der URL oder dem Request-Body.  |
| `@Autowired`       | Ermöglicht das automatische Injektieren des `LogService`-Beans. |

### Felder

| Feldname     | Typ               | Beschreibung                                 |
|--------------|-------------------|---------------------------------------------|
| `authService`| `LogService`      | Der Authentifizierungsservice, der zur Validierung der Login-Daten verwendet wird. |

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
  2. Die Anmeldedaten werden an den `LogService` weitergegeben, um die Validität zu überprüfen.
  3. Gibt eine entsprechende Antwort zurück, je nachdem, ob die Anmeldeinformationen gültig sind oder nicht.

### Beispielanfragen und Antworten

1. **Erfolgreiche Anmeldung**:

    **Anfrage**:
    ```http
    POST /auth/login?role=Student&firstName=User1&lastName=User1&email=email@user1.com&password=password2403
    ```

    **Antwort**:
    ```text
    HTTP/1.1 200 OK
    login successful
    ```

2. **Fehlgeschlagene Anmeldung**:

    **Anfrage**:
    ```http
    POST /auth/login?role=Prof&firstName=User3&lastName=User3&email=email@user3.com&password=password1234
    ```

    **Antwort**:
    ```text
    HTTP/1.1 401 Unauthorized
    login invalid/unsuccessful
    ```

---

## HTTP Statuscodes

- **200 OK**: Erfolgreiche Anmeldung.
- **401 Unauthorized**: Ungültige Anmeldeinformationen.

---

## Verwendung

- Die `User`-Klasse wird für die Verwaltung von Benutzerdaten verwendet.
- Die `LogService`-Klasse verarbeitet die Anmeldung und prüft die Benutzerdaten.
- Die `Controller`-Klasse stellt einen REST-Endpoint zur Verfügung, um die Benutzerdaten über HTTP zu validieren und eine Antwort zurückzugeben.
