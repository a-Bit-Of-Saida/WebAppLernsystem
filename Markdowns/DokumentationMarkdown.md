## Projektdokumentation

### Einleitung

Diese Dokumentation beschreibt die Entwicklung einer webbasierten Lernanwendung, die Nutzern die Möglichkeit bietet, sich anzumelden, Kurse zu belegen und ihre Aufgaben in Form einer To-Do-Liste zu verwalten. Die Dokumentation wurde eingeteilt in verschiedene Kapitel, um die Entwicklung und Realsierung der Anwendung verständlich darzustellen. Da bereits Markdown-Dokumente zu den unterschiedlichen Inhalten erstellt wurden, wird diese Dokumentation grob gehalten und verweist bei Bedarf auf die entsprechenden Markdown-Dokumente.

### Projektbeschreibung

Das Lernsystem besteht aus drei Microservices, die jeweils spezifische Aufgaben erfüllen. Die Hauptkomponenten sind das API-Gateway, der Login-Service, der Course-Service und der Task-Service. Diese Komponenten arbeiten zusammen, um eine umfassende Lernplattform zu bieten, welche die Anmeldung, Kursverwaltung und Aufgabenverwaltung der Nutzer ermöglicht.
Das Hauptziel unserer Anwendung ist es, eine effiziente Plattform zu entwickeln, die den Benutzern das Lernen erleichtert und den Administratoren die Verwaltung von Kursen ermöglicht.

### Aufgabenstellung und Anforderung an das System

In der Projektanleitung wurden genaue Anforderungen an die zu entwickelnde Anwendung gestellt. Ziel war es, mindestens zwei Möglichkeiten der Anwendungsnutzung clientseitig sowie ein API-Gateway mit mindestens zwei API-Technologien zu realisieren.
Um dies zu realisieren, entschied die Gruppe sich für eine Single Page Application mit React und Postman. Als API-Technologien wurden die Rest API für den Login-Service und die GraphQL API für den Course- und Task-Service genutzt.
Die von der Gruppe entwickelten Anforderungen an das System wurden in den User-Stories innerhalb der Markdowns festgehalten. 

**Diese wurden zur Vereinfachung unten verlinkt:**

- [Markdown zum Login-Service](MarkdownLogin.md)
- [Markdown zum Course-Service](CourseManagement-Markdown.md)
- [Markdown zum Task-Service](TaskService-Markdown.md)

## Hauptfunktionen

Die Hauptfunktionen der Lernanwendung konzentrieren sich auf die Verwaltung von Benutzern, Kursen und Aufgaben. Diese Funktionen sind essenziell, um eine umfassende Anwendung zu bieten.
Grundlegende Funktionen

## Benutzerverwaltung:

Die Benutzerverwaltung ermöglicht dem Admin, das Erstellen u und d von neuen Benutzern. Registrierte Benutzer können sich durch den Login anmelden und auf das Lernsystem zugreifen.

## Kursverwaltung:

Durch die Kursverwaltung können Administratoren neue Kurse erstellen, bestehende Kurse aktualisieren und löschen. Zudem kann der Admin Dateien zur Verfügung stellen, die den entsprechenden Kursen hinzugefügt werden. Jeder Kurs enthält detaillierte Informationen wie Kursbeschreibung, Kursleiter und verfügbare Dateien. Benutzer können auf die Kurse zugreifen und die entsprechenden Dateien öffnen.

## Aufgabenverwaltung

Die Aufgabenverwaltung ermöglicht es den Benutzern, alle Crud-Operationen auszuführen. Zudem können Aufgaben nach Fälligkeitsdatum gefiltert werden, um anstehende Aufgaben anzuzeigen. Der Status der Aufgaben kann geändert werden, um die Aufgaben systematisch verwalten zu können.

## Erweiterte Funktionen

Neben den Hauptfunktionen sollen durch mehrere Zusatzfunktionen die Benutzererfahrung verbessert und die Verwaltung der Plattform erleichtert werden.

## API Gateway

Das API Gateway dient als zentraler Einstiegspunkt für alle Client-Anfragen und leitet diese an die entsprechenden Microservices weiter. Es wurde ein zentraler Controller implementiert, der für das Routing und die Weiterleitung aller Anfragen verantwortlich ist. Dieser Controller analysiert jede eingehende Anfrage und bestimmt, welcher Microservice die Anfrage bearbeiten soll. Da sowohl REST als auch GraphQL unterstützt werden, wird eine Flexibilität bei der Entwicklung und Nutzung geboten.
Eine genauere Beschreibung kann hier gefunden werden: 

## Benutzerfreundlichkeit

Die GUI der Anwendung (SPA) ist sehr einfach zu bedienen. Dadurch können Benutzer simpel durch die Anwendung geführt werden und sie sofort nutzen.

## Architektur des Systems

Die Systemarchitektur des Lernsystems besteht aus mehreren Backend-Services, die durch ein API-Gateway mit dem Client verbunden werden. Diese Architektur umfasst verschiedene Schichten, die jeweils spezifische Aufgaben erfüllen.
Die Systemarchitektur wurde visualisiert und in ihrer Markdown detailliert erklärt und festgehalten: 

## Technische Komponenten

## Spring Boot
Spring Boot ist das Framework, das für die Entwicklung der Backend-Services verwendet wird. Durch das Framework wurde eine schnelle Anwendungsentwicklung und eine einfache Konfiguration ermöglicht. Durch Spring Boot konnten die Microservices schnell entwickelt werden. Dabei ist zu beachten, dass jeder Microservice in der Anwendung, einschließlich des Login-Services, des Course-Services und des Task-Services als eigenständige Spring Boot-Anwendung implementiert ist.
Single Page Application (SPA)
Die GUI der Anwendung ist als Single Page Application mit React entwickelt. Die SPA bietet eine interaktive und flüssige Benutzererfahrung, da sie alle notwendigen Ressourcen, wie HTML, CSS, JavaScript, beim ersten Laden der Seite lädt und anschließend nur die benötigten Daten nachlädt. Die SPA kommuniziert mit den Backend-Services über das API-Gateway.
Zur Markdown der SPA kommt es hier: