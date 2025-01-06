# Markdown der Systemarchitetkur

---
### Grafische Darstellung der Backends und ihrer Web-Services mit API's
![Vorläufige Systemarchitektur](Pictures/BildSystem.jpeg)

#### Geschäftsschicht:
Der Business Layer zeigt, wie Benutzer (Clients) mit dem System interagieren, z.B. über die Webanwendung oder die Konsole. Er beschreibt Rollen wie den optionalen Benutzer und stellt den Zugriff auf das System über Schnittstellen dar.

#### Anwendungsschicht:
Die Applikationsschicht modelliert die Softwarekomponenten wie das API-Gateway und die Backend-Systeme für Login, Aufgabenverwaltung und Kursverwaltung. Diese Komponenten stellen die benötigten Dienste zur Verfügung und ermöglichen die Umsetzung der fachlichen Anforderungen.

#### Technologie-Schicht:
Die Technologieschicht zeigt die physische Infrastruktur wie PCs, Webanwendungen, Swagger UI und externe Systeme, die den Betrieb der Anwendungen unterstützen. Er beschreibt, wie Geräte und Netzwerke die übergeordnete Softwareschicht ermöglichen.




### Backends und ihre zugehörigen Web-Services


| Benutzerverwaltung        | Aufgabenverwaltung           | Kursverwaltung  |
| ------------- |:-------------:| -----:|
| Login-Service: Benutzer können sich mit Nutzernamen und Passwort sicher anmelden     | Nutzer können Aufgaben erstellen und löschen (CRUD-Operationen) | Nutzer können neue Kurse erstellen, öffnen, anzeigen sowie Kursinhalte hochladen und löschen |


### Clientseitige Möglichkeiten der Anwendungsnutzung

Folgende clientseitigen Anwendungsnutzungen wurden gewählt, um auf das System und die API's zuzugreifen:

* Swagger UI
* Konsole

Die Gruppe entschied sich für Swagger UI und die Konsole, weil Swagger UI eine benutzerfreundliche Oberfläche bietet, um die API-Endpunkte einfach zu testen, während die Konsole eine effiziente Möglichkeit für automatisierte API-Interaktionen ermöglicht.


### Externes System
Die Integrierung eines externen Systems, ermöglicht es Drittanbietern, mit unserem Lernsystem zu interagieren.
Der Gedanke dabei ist: Ein externes System, wie ein Cloud-Speicher (z.B. Google Drive, OneDrive), könnte Zugriff auf Lernmaterialien wie PDF's, Videos oder Präsentationen haben.

### API Technologien
Das Team entschied sich für die API Technologien ***REST*** und ***GraphQL***. Für unsere Lernsystem-Anwendung nutzen wir REST nur für den Webservice des Login da es eine einfache Umsetzung für die Authentifizierung und Autorisierung von Benutzern bietet. GraphQL nutzen wir für die Webservices Aufgabenverwaltung und Kursverwaltung, da es eine effiziente Möglichkeit bietet, Datenoperationen wie Erstellen, Löschen und Aktualisieren durchzuführen.



