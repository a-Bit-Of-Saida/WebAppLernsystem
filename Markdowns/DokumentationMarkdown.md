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