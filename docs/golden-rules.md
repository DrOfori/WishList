
# Evaluering af Shneidermans 8 Golden Rules – Wishlist

## 1. Formål

Formålet er at evaluere brugervenligheden i vores Wishlist-applikation ud fra Shneidermans 8 Golden Rules.

## 2. Metode

Vi tager udgangspunkt i vores heuristiske analyse og vurderer, hvordan applikationen lever op til de 8 Golden Rules.

Vi fokuserer på design, navigation, feedback, fejlforebyggelse og brugerkontrol.

## 3. Evaluering

| Golden Rule | Observation | Forbedringsforslag |
|---|---|---|
| 1. Konsistens | Knapper, kort og farver er ensartede på forsiden. | Bevar samme design på alle sider. |
| 2. Universel brugervenlighed | Applikationen har et enkelt layout og forståelige funktioner som "Opret", "Omdøb" og "Slet". Tilgængelighed er dog ikke undersøgt. | Test mobilvisning og tastaturnavigation. |
| 3. Informativ feedback | Systemet viser fejlbeskeder ved ugyldige indtastninger, men giver ikke en bekræftelse efter oprettelse. | Tilføj en tydelig besked efter oprettelse. |
| 4. Tydelig afslutning | Brugeren kan oprette og slette ønsker, men får ikke en tydelig bekræftelse efter oprettelse. | Vis en besked, når en handling er gennemført. |
| 5. Fejlforebyggelse | Systemet validerer navn, pris og link samt kræver bekræftelse før sletning. Linkvalideringen accepterer dog ugyldige links med `http://` eller `https://` foran. | Forbedr linkvalideringen, så ugyldige links afvises. |
| 6. Fortryd handlinger | Applikationen har ingen fortryd-funktion. Brugeren kan dog slette et ønske og oprette et nyt. | Tilføj mulighed for at fortryde handlinger, især sletning. |
| 7. Brugerens kontrol | Brugeren kan oprette, redigere og slette ønsker. Det er dog ikke helt tydeligt, hvordan man åbner en ønskeliste. | Gør kortene klikbare eller tilføj en tydelig "Åbn"-knap. |
| 8. Reducér hukommelsesbelastning | Funktionerne er synlige og har forståelige navne, så brugeren ikke behøver huske komplicerede trin. | Bevar den enkle navigation, og gør det tydeligere, hvordan ønskelister åbnes. |

## 4. Konklusion

Wishlist følger flere af Shneidermans 8 Golden Rules, især inden for konsistens, enkel navigation og fejlforebyggelse.

De vigtigste forbedringsmuligheder er tydeligere feedback efter oprettelse, bedre linkvalidering og mulighed for at fortryde handlinger. Disse forbedringer vil gøre applikationen mere brugervenlig og pålidelig.
