# Projektdokumentation


## Einleitung

Diese Dokumentation beschreibt die Entwicklung einer `webbasierten Lernanwendung`, die Nutzern die Möglichkeit bietet, sich anzumelden, Kurse zu belegen und ihre Aufgaben in Form einer To-Do-Liste zu verwalten. Die Dokumentation wurde eingeteilt in verschiedene Kapitel, um die Entwicklung und Realisierung der Anwendung verständlich darzustellen. Da bereits Markdown-Dokumente zu den unterschiedlichen Inhalten erstellt wurden, wird diese Dokumentation grob gehalten und verweist bei Bedarf auf die entsprechenden Markdown-Dokumente.

## Inhalte dieser Dokumentation

- [Kapitel 1: **Projektbeschreibung**](#projektbeschreibung)
- [Kapitel 2: **Aufgabenstellung und Anforderung an das System**](#aufgabenstellung-und-anforderung-an-das-system)
- [Kapitel 3: **Hauptfunktionen**](#hauptfunktionen)
- [Kapitel 4: **Erweiterte Funktionen**](#erweiterte-funktionen)
- [Kapitel 5: **Architektur des Systems**](#architektur-des-systems)
- [Kapitel 6: **Technische Komponenten**](#technische-komponenten)
- [Kapitel 7: **Bedienungsanleitung**](#bedienungsanleitung)
- [Kapitel 8: **Komplikationen**](#komplikationen)

## 1. Projektbeschreibung

Das Lernsystem besteht aus drei Microservices, die jeweils spezifische Aufgaben erfüllen. Die Hauptkomponenten sind das API-Gateway, der Login-Service, der Course-Service und der Task-Service. Diese Komponenten arbeiten zusammen, um eine umfassende Lernplattform zu bieten, welche die Anmeldung, Kursverwaltung und Aufgabenverwaltung der Nutzer ermöglicht.
Das Hauptziel unserer Anwendung ist es, eine effiziente Plattform zu entwickeln, die den Benutzern das Lernen erleichtert und den Administratoren die Verwaltung von Kursen ermöglicht.

## 2. Aufgabenstellung und Anforderung an das System

In der Projektanleitung wurden genaue Anforderungen an die zu entwickelnde Anwendung gestellt. Ziel war es, mindestens zwei Möglichkeiten der Anwendungsnutzung clientseitig, sowie ein API-Gateway mit mindestens zwei API-Technologien zu realisieren.
Um dies zu realisieren, entschied die Gruppe sich für eine Single Page Application mit React und Postman. Als API-Technologien wurden die Rest-API für den Login-Service und die GraphQL-API für den Course- und Task-Service genutzt.
Die von der Gruppe entwickelten Anforderungen an das System wurden in den User-Stories innerhalb der Markdowns festgehalten. 

**Diese wurden zur Vereinfachung unten verlinkt:**

- [Markdown zum Login-Service](MarkdownLogin.md)
- [Markdown zum Course-Service](CourseManagement-Markdown.md)
- [Markdown zum Task-Service](TaskService-Markdown.md)

## 3. Hauptfunktionen

Die Hauptfunktionen der Lernanwendung konzentrieren sich auf die Verwaltung von Benutzern, Kursen und Aufgaben. Diese Funktionen sind essenziell, um eine umfassende Anwendung zu bieten.

**Grundlegende Funktionen sind dabei die:**

#### Benutzerverwaltung:

Die Benutzerverwaltung ermöglicht dem Admin, das Erstellen, Aktualisieren und Löschen von Benutzern. Registrierte Benutzer können sich durch den Login anmelden und auf das Lernsystem zugreifen.

- [Markdown Benutzerverwaltung](MarkdownLogin.md)

#### Kursverwaltung:

Durch die Kursverwaltung können Administratoren neue Kurse erstellen und bestehende Kurse aktualisieren und löschen. Zudem kann der Admin Dateien zur Verfügung stellen, die den entsprechenden Kursen hinzugefügt werden. Jeder Kurs enthält detaillierte Informationen wie Kursbeschreibung, Kursleiter und verfügbare Dateien. Benutzer können auf die Kurse zugreifen und die entsprechenden Dateien öffnen.

- [Markdown Kursverwaltung](CourseManagement-Markdown.md)
#### Aufgabenverwaltung

Die Aufgabenverwaltung ermöglicht es den Benutzern, alle Crud-Operationen auszuführen. Zudem können Aufgaben nach Fälligkeitsdatum gefiltert werden, um anstehende Aufgaben anzuzeigen. Der Status der Aufgaben kann geändert werden, um die Aufgaben systematisch verwalten zu können.

- [Markdown Aufgabenverwaltung](TaskService-Markdown.md)
## 4. Erweiterte Funktionen

Neben den Hauptfunktionen sollen durch mehrere Zusatzfunktionen die Benutzererfahrung verbessert und die Verwaltung der Plattform erleichtert werden.

### API Gateway

Das **API Gateway** dient als zentraler Einstiegspunkt für alle Client-Anfragen und leitet diese an die entsprechenden Microservices weiter. Es wurde ein zentraler Controller implementiert, der für das Routing und die Weiterleitung aller Anfragen verantwortlich ist. Dieser Controller analysiert jede eingehende Anfrage und bestimmt, welcher Microservice die Anfrage bearbeiten soll. Da sowohl **REST** als auch **GraphQL** unterstützt werden, wird eine Flexibilität bei der Entwicklung und Nutzung geboten.

**Eine genauere Beschreibung kann hier gefunden werden:** [Markdown API-Gateway](APIGateway-Markdown.md)

### Benutzerfreundlichkeit

Die GUI der Anwendung (SPA) ist sehr einfach zu bedienen. Dadurch können Benutzer simpel durch die Anwendung geführt werden und sie sofort nutzen.

## 5. Architektur des Systems

Die Systemarchitektur des Lernsystems besteht aus mehreren Backend-Services, die durch ein API-Gateway mit dem Client verbunden werden. Diese Architektur umfasst verschiedene Schichten, die jeweils spezifische Aufgaben erfüllen.

**Die Systemarchitektur wurde visualisiert und in ihrer Markdown detailliert erklärt und festgehalten:** [Markdown der Systemarchitektur](MarkdownArchitektur.md)

## 6. Technische Komponenten

### Spring Boot
Spring Boot ist das Framework, das für die Entwicklung der Backend-Services verwendet wird. Durch das Framework wurde eine schnelle Anwendungsentwicklung und eine einfache Konfiguration ermöglicht. Durch Spring Boot konnten auch die Microservices schnell entwickelt werden. Dabei ist zu beachten, dass jeder Microservice in der Anwendung, einschließlich des Login-Services, des Course-Services und des Task-Services als eigenständige Spring Boot-Anwendung implementiert ist.

### Single Page Application (SPA) mit React
Die GUI der Anwendung ist als Single Page Application mit React entwickelt. Die SPA bietet eine interaktive und flüssige Benutzererfahrung, da sie alle notwendigen Ressourcen, wie HTML, CSS und JavaScript, beim ersten Laden der Seite lädt und anschließend nur die benötigten Daten nachlädt. Die SPA kommuniziert mit den Backend-Services über das API-Gateway.

**Zur Markdown der SPA kommt es hier:** [Markdown der SPA](SPA-React-Markdown.md)

### Postman

Postman wird als die Admin-Sicht der Anwendung genutzt. Sie ermöglicht es Benutzer, Kurse und Aufgaben zu erstellen, abzurufen, zu aktualisieren und zu löschen. Durch Postman können HTTP-Anfragen erstellt und versendet werden. Des Weiteren ermöglicht Postman es die API-Endpunkte der Microservices zu testen, um sicherzustellen, dass sie korrekt funktionieren. Es ermöglicht somit das Senden von Anfragen und das Anzeigen der Antworten, was die Entwicklung und das Debugging erleichtert.

### GraphQL

Durch GraphQL können Clients genau die Daten anfordern, die sie benötigen und Daten effizient vom Server abrufen. In der Lernsystem-Anwendung wird **GraphQL** für die Services Kursverwaltung und Aufgabenverwaltung genutzt. Es bietet alle CRUD-Operationen (Create, Read, Update, Delete) für Aufgaben und Kurse.

### REST

**REST** wird verwendet, um Ressourcen über HTTP-Methoden wie **GET, POST, PUT und DELETE** zu manipulieren. In der Lernsystem-Anwendung wird **REST** für die Benutzerverwaltung und seine CRUD-Operationen genutzt.

## 7. Bedienungsanleitung

### Vorraussetzungen

1. Spring-Boot
2. Java17
3. Node.js und npm
4. Herunterladen des Projekts
5. Öffnen des Projekts in VS Code und Navigation ins Projekt-Verzeichnis
6. Installieren der Dependencies

### Starten der Anwendung
1. Navigieren zu den Verzeichnissen der einzelnen Microservices und dem API-Gateway
2. Starten der einzelnen Application-Klassen aller Services
3. Starten der API-Gateway-Application-Klasse `mvn spring-boot:run`
4. Starten der Anwendung im Terminal `npm start`

## 8. Komplikationen

### **SwaggerUI**

Bei der Implementierung von **SwaggerUI** als clientseitige Anwendung, gab es Probleme über das API-Gateway auf Swagger zuzugreifen. Auch nach mehreren Debug-und Testversuchen konnte der Fehler nicht behoben beziehungsweise wirklich identifiziert werden. Aus diesem Grund entschied die Gruppe sich dazu, **Postman** als erste clientseitige Anwendung zu nutzen. 

### **GraphiQL**

Zu Beginn des Projekts funktionierte **GraphiQL** wie erwartet. Die verschiedenen Funktionen des Course- und Task Services funktionierten ohne das API-Gateway fehlerfrei. Auch die Mutationen und Queries wurden wie erwartet übernommen und umgesetzt. Erst nach Implementierung des API-Gateways fing es mit den Komplikationen an. Zunächst funktionierte der Task-Service nicht mehr. Es konnte nichts über **GraphiQL** eingesehen werden. Auch hier konnte der Fehler nach mehreren Versuchen und Änderungen nicht identifiziert werden. Dies führte zur Entscheidung der Gruppe eine **Single Page Application mit Angular** zu realisieren. 

### **Single Page Application mit Angular**

Zunächst versuchte die Gruppe sich mit einer Realisierung der Anwendung durch **SPA mit Angular** vertraut zu machen. Allerdings wurde insgesamt erkannt, dass auch mit Ausblick auf die restliche Zeit für das Projekt, die Realisierung zu kompliziert werden würde. Bereits bei den ersten Downloads kam es zu Problemen, wie Fehlermeldungen von VSC. Nach Vergleich mit React entschied die Gruppe sich die **SPA mit React** zu realisieren, da React einfacher zu verstehen war und auch beim Einrichten keine Probleme machte. Die Realisierung der **Single Page Application mit React** hatte deshalb zum Vorteil, dass sie nicht nur einfacher zu realisieren war, sondern es auch mehr Zeit zum Testen und Debuggen gab. Somit konnte die Gruppe sich stärker darauf fokussieren, eine **SPA** zu realisieren die ihren Anforderungen entspricht.

#### **API-Gateway**

Bereits zu Beginn des Projekts entstanden erste Komplikationen mit dem **API-Gateway**. Der Aufbau und die allgemeine Architektur des API-Gateways waren der Gruppe relativ unklar, weswegen jedes Teammitglied dazu aufgefordert war eine eigenständige Recherche durchzuführen, um sich mit den Inhalten vertraut zu machen. Dadurch, konnte insgesamt ein Wissen für die Implementierung und Konfiguration des Gateways entwickelt werden. 
Ein zentrales Problem, dass während der Implementierung des API-Gateways entstand, war das Routing der Anfragen an die korrekten Services. Dabei entstand die Idee der Methode `determineTargetUrl` für die GraphQL-Services. Anhand der übermittelten GraphQL-Query bzw. ihrer Notation, konnte das API-Gateway gezielt routen und das Problem behoben werden. 
Ein weiteres Problem bestand in der Rückgabe der Antworten vom Zielservice an das **API-Gateway**. Die Response-Formate waren fehlerhaft bzw. invalid. 
Deshalb wurden die drei CustomExceptions `BadRequestException`, `GlobalExceptionHandler`, `InternalServerErrorException` implementiert, durch die eine gezielte Fehlerbehandlung erreicht werden konnte.
Die Optimierung des **API-Gateways** führte letztendlich zu einer stabileren Architektur und einer zuverlässigeren Kommunikation zwischen Client und Backend-Services.

