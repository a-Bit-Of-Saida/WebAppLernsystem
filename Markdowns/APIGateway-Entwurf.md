# API Gateway Entwurf

## Übersicht
Dieses Dokument beschreibt die grundlegende Struktur und Konfiguration eines API Gateways, das als Schnittstelle zwischen Clients und den  verschiedenen Microservices dient. Das Gateway leitet Anfragen an die entsprechenden Services weiter und bietet zusätzliche Funktionen wie Authentifizierung, Logging und Fehlerbehandlung.

## Benötigte Klassen
* Konfigurationsklasse `config`
* Controller für jeden Service
* Filter (für Header)

## Abhängigkeiten
Die folgenden Abhängigkeiten werden in der `pom.xml` Datei des Projekts definiert:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-webflux-ui</artifactId>
        <version>1.7.0</version>
    </dependency>
</dependencies>
```

## Beispiel Route (in der `ApiGatewayApplication`-Klasse)
```
@Bean
public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("course_service_route", r -> r.path("/api/courses/**")
            .filters(f -> f.rewritePath("/api/courses/(?<segment>.*)", "/graphql"))
            .uri("http://localhost:8082"))
        .build();
}
```
## Beispiel-Controller 

In diesem Beispiel wird die Anfrage für die Suche von Kursen nach ID bearbeitet. Dies kann als Vorlage benutzt werden und für jede beliebige Anfrage entsprechend angepasst werden.

    @RestController
    @RequestMapping("/api/courses")
    @Tag(name = "Courses API", description = "REST-zu-GraphQL API für Kurse")
    public class CourseGraphQLController {

        @Operation(summary = "Hole Kurs nach ID", description = "Holt einen Kurs basierend auf der ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Kurs gefunden"),
            @ApiResponse(responseCode = "404", description = "Kurs nicht gefunden"),
            @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
        })
        @PostMapping("/{id}")
        public ResponseEntity<?> getCourseById(
            @Parameter(description = "Die ID des Kurses", required = true)
            @PathVariable String id
        ) {
            String query = "query($id: ID!) { courseById(id: $id) { id name description instructor files { id name description } } }";
            Map<String, Object> variables = Map.of("id", id);

            ResponseEntity<?> response = forwardToGraphQLService(query, variables);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        }


Dieser Entwurf bietet eine grundlegende Struktur und Konfiguration für das API Gateway und kann nach Bedarf erweitert und angepasst werden.