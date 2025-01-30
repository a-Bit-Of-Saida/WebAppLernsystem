# Markdown des API Gateways

## Übersicht
Das **API-Gateway** unserer Anwendung agiert als zentraler Einstiegspunkt für alle Client-Anfragen, indem sie diese an die entsprechenden Microservices (LoginWebService,TaskWebService, CoursesWebService) weiterleitet. Die Antworten der Microservices gelangen anschließend wieder über das API-Gateway zurück zum Client.

## Funktionsweise 
Das API Gateway empfängt Anfragen von Clients und leitet sie basierend auf vordefinierten Routing Regeln an die entsprechenden Microservices weiter.

## Implementierung
Das API Gateway wurde mit Spring Cloud Gateway implementiert. Es verwendet die Konfiguration in der ``application.properties`` Datei, um die Routing Regeln und andere Einstellungen zu definieren.

## Benötigte Klassen 

### ApiGatewayApplication.java
Startet die SpringBoot Anwendung.

### GatewayController.java
Die `GatewayController`-Klasse ist der zentrale Controller des API Gateways. Dabei werden die Methoden zur Weiterleitung von Anfragen des Clients
an die entsprechenden Microservices in einem Controller zusammengefasst.

### Application.properties
Enthält die Konfiguration für das API Gateway, einschließlich der Routing Regeln. 

## Wichtige Methoden

- ``forwardToGraphqlService``: Leitet Anfragen an den GraphQL-Service weiter.
 - ``determineTargetUrl``: Bestimmt die Ziel-URL basierend auf dem Inhalt der Anfrage.
 - ``forwardToLoginService``: Leitet Anfragen an den Login-Service weiter.
 - ``forwardRequest``: Führt die Weiterleitung der Anfrage an die Ziel-URL durch.

## Nutzung
Das API Gateway wird auf **Port 8085** ausgeführt und bietet Endpunkte für die verschiedenen Microservices.

## Ablauf des Routings
 **1. Empfangen der Anfrage:**
- Das API-Gateway empfängt eine Anfrage auf Port 8085

**2. Prüfen der Routing-Regeln:**
- Das API Gateway prüft die Routing Regeln in der application.properties-Datei, um zu bestimmen, an welchen Microservice die Anfrage weitergeleitet werden soll. 

**3. Weiterleiten der Anfrage:**
- Wenn die Bedingung (Path=/graphql/**)
erfüllt ist, wird die Anfrage an die entsprechende URL für den CourseService oder TaskService weitergeleitet, dies hängt von der angegebenen Query und ihrem enthaltenen Schlüsselwort ab. 

**4. Empfangen der Antwort:**
- Der Microservice verarbeitet die Anfrage und sendet eine Antwort zurück an das API Gateway.

**5. Zurückgeben der Antwort:**
- Das API Gateway empfängt die Antwort vom Microservice und leitet sie an den Client zurück.

## Routing-Regeln
```java

spring.cloud.gateway.routes[0].id=Course-Service
spring.cloud.gateway.routes[0].uri=http://localhost:8080
spring.cloud.gateway.routes[0].predicates[0]=Path=/graphql

spring.cloud.gateway.routes[1].id=Task-Service
spring.cloud.gateway.routes[1].uri=http://localhost:8081
spring.cloud.gateway.routes[1].predicates[0]=Path=/graphql/


spring.cloud.gateway.routes[2].id=Login-Service
spring.cloud.gateway.routes[2].uri=http://localhost:8082
spring.cloud.gateway.routes[2].predicates[0]=Path=/auth/login
```

#### Erklärung zu den Routing-Regeln:

***Course-Service:***
- ID: ``Couse-Service``
- URL:  ``http://localhost:8080``
- Prädikat:  ``Path=/graphql/**``
- Beschreibung: Leitet alle Anfragen, die mit  ``/graphql/**`` beginnen, an den Course-Service weiter.

***Task-Service:***
- ID: ``Task-Service``
- URL:  ``http://localhost:8081``
- Prädikat:  ``Path=/graphql/**``
- Beschreibung: Leitet alle Anfragen, die mit  ``/graphql/**`` beginnen, an den Task-Service weiter.

***Login-Service:***
- ID: ``Login-Service``
- URL:  ``http://localhost:8082``
- Prädikat:  ``Path=/auth/login``
- Beschreibung: Leitet alle Anfragen, die mit  ``/auth/login`` übereinstimmen, an den Login-Service weiter.


### Beispielanfragen
- **Login-Anfragen an den LoginService:**
  - URL: ``http://localhost:8085/auth/login``
  - Weiterleitung an: ``http://localhost:8082/auth/login``

- **GraphQL-Anfragen an den TaskService:**
  - URL:  ``http://localhost:8085/graphql``
  - Weiterleitung an: ``http://localhost:8081/graphql``

- **GraphQL-Anfragen an den Course-Service:**
  - URL: ``http://localhost:8085/graphql``
  - Weiterleitung: ``http://localhost:8080/graphql``
