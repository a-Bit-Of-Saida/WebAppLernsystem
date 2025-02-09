# Markdown der Single Page Application (SPA) mit React

## Übersicht
Diese Markdown beschreibt die oberflächliche Struktur, einzelne Funktionen der drei Services und die Implementierung der Single Page Application des Lernsystems.

## Inhalte dieser Markdown

- [Struktur und Komponenten](#strukturundkomponenten)
- [Technologische Basis](#technologische-basis)
- [API Gateway](#api-gateway)
- [Funktionen](#funktionen)

## Struktur und Komponenten
### 1. **Startbildschirm**
[Startbildschirm](Picture/Startbildschirm_SPA.png)
- Enthält das Logo des Lernsystems und eine Login-Schaltfläche.

### 2. **Login-Seite**
[Login-Seite][Picture/Login_SPA.png]
- Benutzer können sich mit ihrer E-Mail-Adresse und einem Passwort einloggen.

### 3. **Dashboard**
[Dashboard](Picture/Dashboard_SPA.png)
- Nach dem Login wird der Benutzer auf das Dashboard weitergeleitet.
- Enthält Schaltflächen für den Zugriff auf Kurse, To-Do-Listen und die Abmeldefunktion.

### 4. **Kursübersicht**
[Kursservice](Picture/Kursservice_SPA.png)
- Listet alle verfügbaren Kurse auf.
- Benutzer können einzelne Kurse auswählen.

### 5. **Kursinhalte**
[Kursinhalt](Picture/Kursinhalt_SPA.png)
- Zeigt detaillierte Informationen zu einem ausgewählten Kurs. ()
- Enthält eine Beschreibung, den Kursleiter und verfügbare Dateien.

### 6. **To-Do-Liste**
[To-Do-List](Picture/To-Do-List_SPA.png)
- Benutzer können Aufgaben hinzufügen, bearbeiten oder löschen.
- Enthält Statusoptionen (offen, in Bearbeitung, abgeschlossen).

### 7. **Fälligkeit für Aufgaben**
[Fälligkeit](Picture/FälligkeitTask_SPA.png)
- Zeigt die Fälligkeitsdaten für Aufgaben an.
- Integrierte Kalenderfunktion für Datumsauswahl.

## Technologische Basis
- **Frontend:** React.js (SPA-Architektur)
- **Styling:** CSS
- **API-Kommunikation:** REST / GraphQL

## API Gateway
Die Clientseitige Nutzung erfolgt durch die Integration des API Gateways, dieser fungiert als Schnittstelle zwischen dem Client (SPA) und den einzelnen Services.
## Funktionen
Jeder der drei Services bietet die CRUD Operationen an, hinzu kommen individuelle Funktionen jedes einzelnen Services. Beim Login- und CourseService sind die CRUD-Operationen nur über Postman verfügbar.
- **Benutzerauthentifizierung** (Login & Logout)
- **Kursverwaltung** (Anzeige von Kursen & Inhalten)
- **Aufgabenverwaltung** (Status einer Aufgabe (offen, in Bearbeitung und geschlossen), Datumswahl für Aufgabenfälligkeiten)



---
Diese SPA bietet eine zentrale Plattform für das Lernmanagement, optimiert für eine nahtlose Benutzerinteraktion und effiziente Verwaltung von Kursen und Aufgaben.