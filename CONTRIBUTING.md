# Bidrag til WishList

Her står det, du skal vide for at komme i gang som nyt medlem i gruppen og for at lave ændringer, så resten af gruppen kan følge med.

## 1. Kom i gang

1. Sørg for, at du har fået og accepteret invitationen til GitHub-organisationen.
2. Installér **JDK 17 eller nyere**, **Git** og **IntelliJ IDEA**.
3. Log ind på GitHub i IntelliJ (**Settings > Version Control > GitHub**).
4. Klon repositoryet: **File > New > Project from Version Control**, vælg GitHub, og vælg organisationens repository. Kopiér adressen fra den grønne **Code**-knap på GitHub i stedet for at skrive den selv.
5. Lad IntelliJ indlæse Maven-projektet, og kør `WishListApplication`.
6. Åbn <http://localhost:8080> og tjek, at du ser oversigten over ønskelister.

Se [README.md](README.md) for forudsætninger og for, hvordan databasen fungerer.

## 2. Arbejdsgang

Vi arbejder med Scrum og feature-branches.

1. **Vælg en opgave** på Scrum boardet (GitHub Projects), og flyt den til *In progress*, så ingen andre tager den samtidig.
2. **Hent det nyeste:** skift til `main`, og tryk **Git > Pull**.
3. **Opret en branch** ud fra `main`. Én branch pr. user story eller opgave.
4. **Lav ændringerne** i små skridt, og commit ofte.
5. **Kør testene** (`mvn test`), og tjek, at de er grønne, før du pusher.
6. **Push** branchen, og **opret en pull request** til `main` på GitHub.
7. **Få en anden i gruppen til at reviewe.** Man godkender ikke sin egen pull request.
8. Når pull requesten er godkendt og testene kører grønt, **merges** den til `main`.
9. Flyt opgaven til *Done* på boardet, og slet den gamle branch.

**Vi committer aldrig direkte på `main`.**

### Navngivning af branches

Brug små bogstaver og bindestreger, uden mellemrum eller æ, ø og å:

```
feature/opret-oenskeliste
feature/rediger-oenske
fix/link-validering
docs/readme
```

Undgå en branch, der kun hedder `feature`. Git kan ikke have både `feature` og `feature/noget`.

### Commit-beskeder

Skriv, hvad ændringen gør, i en kort sætning i bydeform:

- God: `Tilføj validering af link på ønsker`
- Dårlig: `gg`, `test`, `Pushpush`, `rettelser`

### Pull requests

En pull request skal indeholde:
- en titel, der siger, hvad den gør
- en kort beskrivelse af, hvad der er lavet, og hvordan man kan teste det
- et link til den user story eller opgave, den hører til

Hold dem små, så de er nemme at gennemgå.

## 3. Kodestandard

**Pakker og navne**
- Pakker skrives med små bogstaver: `com.example.model`, ikke `com.example.Model`. Windows skelner ikke mellem store og små bogstaver i mapper, men Linux (GitHub Actions og Azure) gør.
- Klasser starter med stort bogstav: `WishService`. Filen hedder det samme som klassen.
- Getters og setters følger feltnavnet: feltet `createdAt` giver `getCreatedAt()`. Thymeleaf er afhængig af det.
- Tabeller og kolonner i databasen skrives med små bogstaver og underscore: `wishlist`, `created_at`.

**Lagene**
| Lag | Ansvar | Må ikke |
|---|---|---|
| `controller` | modtage requests, sende data til HTML, redirecte | indeholde regler eller SQL |
| `service` | validering og regler | kende til HTML eller skrive SQL |
| `repository` | SQL og adgang til databasen | indeholde regler eller validering |
| `model` | datamodel | indeholde logik |

**Regler vi holder os til**
- **Validering ligger i servicen.** Browserens `maxlength` er kun en hjælp, for den kan omgås.
- **Brug altid `?` i SQL** og send værdierne som parametre. Sæt aldrig brugerens tekst direkte ind i en SQL-streng.
- **Sletning og ændringer er `POST`**, aldrig et almindeligt link (`GET`). Sletning kræver bekræftelse.
- **Redirect efter en vellykket `POST`**, så en genindlæsning ikke sender formularen igen.
- **Links** på ønsker skal starte med `http://` eller `https://`. Eksterne links åbnes med `target="_blank" rel="noopener noreferrer"`.
- `UPDATE` og `DELETE` skal altid have en `WHERE`.
- Hver HTML-fil har kun ét `<!DOCTYPE html>`. Hvis IntelliJ lægger en skal ind, så ryd den, før du indsætter.

## 4. Database

- Ændringer i tabellerne laves i `src/main/resources/schema.sql`, og testdata i `data.sql`.
- Hvis du ændrer tabellerne, så opdatér også ER-modellen i `docs`, og giv gruppen besked.
- H2 ligger i hukommelsen, så ændringer i databasen forsvinder ved genstart. Det er `schema.sql`, der gælder.

## 5. Tests

- Nye regler i en service skal have en unittest i `src/test/java`, i samme pakkestruktur som koden.
- Test både det, der skal virke, og det, der skal afvises (tomt navn, for langt navn, ugyldig pris, ugyldigt link).
- Kør alle tests med `mvn test`, eller højreklik på `src/test/java` og vælg **Run 'All Tests'**.
- En pull request må ikke merges, hvis testene fejler.

## 6. Hvad vi ikke committer

- `.idea/`, `target/` og andre genererede filer. De står i `.gitignore`.
- **Adgangskoder, nøgler og andre hemmeligheder.** Brug miljøvariabler eller GitHub Secrets, især til Azure.
- Personlige indstillinger fra din egen computer.

## 7. Dokumentation

Hold dokumentationen i `docs` opdateret, når du ændrer noget, den beskriver:
- **ER-model:** når tabellerne ændres
- **Klassediagram:** når klasser eller deres ansvar ændres. Det tegnes i hånden og må ikke være autogenereret.
- **README:** når forudsætninger eller opstart ændres

## 8. Når du er i tvivl

Spørg i gruppen, før du laver store ændringer, fx nye tabeller, nye afhængigheder i `pom.xml` eller ændringer i mappestrukturen. Det er billigere at tale om det først end at løse en merge-konflikt bagefter.
