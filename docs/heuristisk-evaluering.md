
# Heuristisk analyse – Wishlist

## 1. Formål

Formålet med analysen er at evaluere brugervenligheden i vores Wishlist-applikation ud fra Jakob Nielsens 10 usability-heuristikker.

## 2. Metode

Vi har gennemgået applikationen og vurderet dens design, navigation og funktioner.

Problemer vurderes på en skala fra 0 til 4:

- **0:** Intet problem
- **1:** Kosmetisk problem
- **2:** Mindre problem
- **3:** Større problem
- **4:** Kritisk problem

## 3. Evaluering

| Heuristik | Observation | Alvorlighed | Forbedringsforslag |
|---|---|---|---|
| 1. Synlig systemstatus | Brugeren får ikke en bekræftelse efter oprettelse, men skal bekræfte, når et ønske eller en ønskeliste slettes. | 2 | Tilføj en bekræftelsesbesked efter oprettelse. |
| 2. Match med virkeligheden | Ord som "Opret", "Omdøb" og "Slet" er nemme at forstå. | 0 | Behold de enkle betegnelser. |
| 3. Brugerkontrol | Applikationen har ingen fortryd-funktion. Brugeren kan dog slette et ønske og oprette et nyt. | 2 | Tilføj mulighed for at fortryde handlinger. |
| 4. Konsistens | Knapper, kort og farver har et ensartet design på forsiden. | 0 | Bevar samme stil på alle sider. |
| 5. Fejlforebyggelse | Systemet kræver bekræftelse før sletning og validerer navn, pris og link. Linkvalideringen kontrollerer dog ikke, om linket faktisk er gyldigt. | 2 | Forbedr linkvalideringen, så ugyldige links ikke accepteres. |
| 6. Genkendelse frem for hukommelse | Oprettelse og listehandlinger er synlige. Det er dog ikke helt tydeligt, hvordan man åbner en ønskeliste. | 2 | Gør kortene klikbare og tilføj en tydelig "Åbn"-knap. |
| 7. Fleksibilitet og effektivitet | Oprettelse af ønskelister kan ske direkte på forsiden. | 0 | Bevar den korte arbejdsgang. |
| 8. Æstetik og minimalistisk design | Siden har et enkelt layout med god luft og tydelig opdeling. | 0 | Bevar det overskuelige design. |
| 9. Hjælp ved fejl | Systemet viser fejlbeskeder ved tomme navne, ugyldige tegn i priser, priser over 8 cifre og links uden `http://` eller `https://`. Punktum og komma accepteres i prisfeltet. | 2 | Behold fejlbeskederne, men forbedr valideringen af links. |
| 10. Hjælp og dokumentation | Der er ingen synlig hjælpefunktion på forsiden. | 1 | Tilføj eventuelt korte forklaringer til nye brugere. |

## 4. Konklusion

Applikationen har et enkelt og overskueligt design med tydelige funktioner og ensartede knapper. Systemet forebygger flere brugerfejl gennem inputvalidering, fejlbeskeder og bekræftelse før sletning.

Der er dog plads til forbedringer, især når det gælder linkvalidering, feedback efter oprettelse og muligheden for at fortryde handlinger. Disse forbedringer kan gøre applikationen mere brugervenlig og pålidelig.
