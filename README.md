# WishList: digital ønskeseddel

Webbaseret databaseapplikation, hvor man kan oprette ønskelister og tilføje ønsker til dem. Udviklet som miniprojekt på 2. semester af Datamatikeruddannelsen (KEA).

## Link til den kørende applikation

> **Udfyldes, når Azure-deploy er på plads:** `https://<jeres-app>.azurewebsites.net`

## Funktioner

**Implementeret**
- Oprette, se, omdøbe og slette ønskelister
- Tilføje, redigere og slette ønsker på en ønskeliste
- Hvert ønske har et navn, en valgfri pris og et valgfrit link til produktet
- Validering af input: navn må ikke være tomt eller for langt, pris skal være et ikke-negativt tal (komma accepteres), og link skal starte med `http://` eller `https://`
- Bekræftelse, før noget slettes, og en "Fortryd"-knap ved redigering

**Ikke implementeret (endnu)**
- Deling af ønskelister med andre
- Reservation af ønsker
- Brugere og login

## Teknologi

| Del | Valg |
|---|---|
| Sprog | Java (projektet bygges til Java 17) |
| Framework | Spring Boot 4.1.2 |
| Byggeværktøj | Maven |
| Skabeloner | Thymeleaf |
| Databaseadgang | Spring JDBC (`JdbcTemplate`) |
| Database | H2 (i hukommelsen) |
| Test | JUnit 5 |

## Softwaremæssige forudsætninger

- **JDK 17 eller nyere** (projektet er udviklet og kørt med JDK 25)
- **Git**
- **Maven** (følger med IntelliJ IDEA, ellers installeres separat)
- Anbefalet udviklingsmiljø: **IntelliJ IDEA**
- Der skal ikke installeres en separat database. H2 starter inde i applikationen.

## Kom i gang

1. Klon repositoryet:
   ```
   git clone https://github.com/WishListDoublegg12/<repository-navn>.git
   ```
2. Åbn mappen i IntelliJ. Hvis IntelliJ spørger, så vælg at indlæse Maven-projektet (**Load Maven Changes**).
3. Tjek under **File > Project Structure**, at der er valgt en JDK på version 17 eller nyere.
4. Kør klassen `WishListApplication` med den grønne play-knap, eller kør i terminalen:
   ```
   mvn spring-boot:run
   ```
5. Åbn <http://localhost:8080> i browseren.

### Databasen

Applikationen bruger en **H2-database i hukommelsen**. Tabellerne oprettes af `src/main/resources/schema.sql`, og testdata indsættes af `src/main/resources/data.sql`, hver gang applikationen starter. Det betyder, at **data forsvinder, når applikationen lukkes**.

Du kan se i databasen via H2-konsollen, mens applikationen kører:

- Adresse: <http://localhost:8080/h2-console>
- JDBC URL: `jdbc:h2:mem:wishlist`
- Brugernavn: `sa`
- Password: tomt

## Test

Kør alle tests:

```
mvn test
```

Unittests ligger i `src/test/java/com/example/service` og tester valideringen i `WishService` og `WishlistService` uden database.

## Projektstruktur

```
src/main/java/com/example
├── WishListApplication.java      starter applikationen
├── controller                    modtager requests og vælger HTML-side
├── service                       forretningsregler og validering
├── repository                    SQL og adgang til databasen
└── model                         Wishlist og Wish
src/main/resources
├── templates                     Thymeleaf-sider (HTML)
├── schema.sql                    opretter tabellerne
├── data.sql                      indsætter testdata
└── application.properties        indstillinger
src/test/java                     tests
docs                              dokumentation (se nedenfor)
```

Lagene arbejder i rækkefølgen **controller → service → repository → database**. Et lag taler kun med laget lige under sig.

## Dokumentation

Dokumentationen ligger i mappen `docs`:

| Dokument |
|---|
| ER-model |
| Klassediagram|
| UX/UI: wireframes eller papirprototype |
| UX/UI: usability test |
| UX/UI: heuristisk evaluering |
| Qodana-kodeinspektion (skærmbillede) |

## CI/CD

Projektet bruger GitHub Actions. Workflow-filerne ligger i `.github/workflows`.

### Continuous Integration (`ci.yml`)

| Hvornår | Hvad sker der |
|---|---|
| Push til en anden branch end `main` (fx en feature-branch) | **Build:** projektet bygges med Maven og Java 17 |
| Pull request til `main` | **Build** kører først. Når den er grøn, kører **Test**, som afvikler alle unittests og integrationstests med `mvn verify`. Testrapporterne gemmes som artifact |

Hvis en ny kørsel starter på den samme branch, mens en gammel stadig kører, bliver den gamle stoppet.

### Statisk kodeanalyse (`qodana_code_quality.yml`)

Qodana analyserer koden og kører ved pull requests, ved push til `main` og manuelt fra fanen *Actions*. Resultatet kommer som kommentar og annotationer på pull requesten og kan ses i Qodana Cloud. Qodana bruger en hemmelig nøgle, `QODANA_TOKEN`, som ligger som repository secret i GitHub. Et skærmbillede af kodeinspektionen ligger i `docs/qodana`.

### Continuous Deployment

> **Udfyldes, når Azure-deploy er på plads:** Beskriv, at en workflow bygger jar-filen og deployer til Azure App Service, når en pull request er merget til `main`, og hvor linket til appen findes.

### Arbejdsgang

Al kode går via en feature-branch og en pull request til `main`. En pull request skal have grønne checks (Build, Test og Qodana) og et review, før den merges. Se [CONTRIBUTING.md](CONTRIBUTING.md).

## Kendte begrænsninger

- Der er ingen brugere eller login, så alle kan se og ændre alle ønskelister.
- Data i H2 forsvinder ved genstart.
- Linket på et ønske valideres kun for, at det starter med `http://` eller `https://`. Det tjekkes ikke, om adressen findes.

## Gruppen

| Gruppenavn | Gruppe 4       |
|------------|----------------|
| Iddi       | idju1000       |
| Wail       | wail-2002      |
| Simon      | DrOfori        |
| Rawnd      | Rata10         |
| Mustafa    | digitalworld24 |
## Bidrag til projektet

Se [CONTRIBUTING.md](CONTRIBUTING.md).
