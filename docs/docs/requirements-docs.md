# Anforderungsdokumentation


## Produktvision und Produktziele

### Produktvision

Eine regionale Bank hat unser externes Software-Entwicklerteam für einen Auftrag eingestellt. Bei dem uns übertragenem Projekt handelt es sich um die fehlerhafte Software einer ATM (Automated Teller Machine) zu deutsch Bankautomat. Der bereits existente Programmcode wurde von einem externen Unternehmen entwickelt, so dass der Kunde kein Expertenwissen zum Programm verfügt, außerdem fehlt auch die Dokumentation vollständig. 

Um dem Bankunternehmen nun die Verwendung des Systems zu ermöglichen, muss das Programm komplett überarbeitet werden, darüber hinaus soll eine detaillierte Dokumentation (vollständig in deutsch) für die Bank erstellt werden. Das Fehlerfreie Programm mit den bereits integrierten Features und einer strukturierten Dokumentation ist unser Basisfaktor. Das Programm ist für die Bankautomaten der Bank in Deutschland vorgesehen. Die Dokumentation soll die Entwicklung sowie die Funktionen der Software zusammenfassen und den zuständigen Mitarbeiter verständlich machen.

### Produktziele

Die Aufgabe unseres Teams ist es den bereits vorhandenen Code so zu überarbeiten, dass dieser voll funktionsfähig ist und eine sichere Laufzeit gewährleistet werden kann. Zur Entwicklung der Software ist eine vollständig deutsche Dokumentation vorgesehen mit **Anforderungs**-, **Architektur**-, **Test**-, **Abnahme**-, **Benutzer**-, **Projekt**-, und **Codedokumentation**.


## Rollen und Personas

### Rollen

| Rollen        | Beschreibung                                                                                |
| ------------- | ------------------------------------------------------------------------------------------- |
| Benutzer      | Die Benutzer sind Kunden der Bank, die den Geldautomaten zur Verfügung stellt               |
| Administrator | Administratoren des Bankautomatensystems, die Verwaltungsrechte über alle Benutzer besitzen |


### Personas

| Name                    | Gertrude Gabel                          |
| ----------------------- | --------------------------------------- |
| Rolle                   | Benutzer                                |
| Alter                   | 65                                      |
| Geschlecht              | weiblich                                |
| Tätigkeit               | Rentnerin                               |
| Familienstand           | verheiratet                             |
| Bildung                 | Mittelschule                            |
| Computerkenntnisse      | Keine                                   |
| Interessen und Hobbies  | Wandern, Kaffee trinken                 |
| Einstellung zum Produkt | "Eine tolle Maschine, tut was sie soll" |
| Wünsche                 | Einfache Bedienung, wenig zum Merken    |

<br>

| Name                    | Peter Pecks                                         |
| ----------------------- | --------------------------------------------------- |
| Rolle                   | Benutzer                                            |
| Alter                   | 38                                                  |
| Geschlecht              | männlich                                            |
| Tätigkeit               | Handwerker                                          |
| Familienstand           | verheiratet                                         |
| Bildung                 | Realschule                                          |
| Computerkenntnisse      | Grundkenntnisse                                     |
| Interessen und Hobbies  | Autos, Actionfilme, Fahrradfahren                   |
| Einstellung zum Produkt | "Hoffentlich werden die neuen Geldautomaten besser" |
| Wünsche                 | Nützliche Funktionen, Schnelle Bedienbarkeit        |

<br>

| Name                    | Andy Auman                                    |
| ----------------------- | --------------------------------------------- |
| Rolle                   | Administrator                                 |
| Alter                   | 29                                            |
| Geschlecht              | männlich                                      |
| Tätigkeit               | Systemadministrator                           |
| Familienstand           | ledig                                         |
| Bildung                 | Abitur                                        |
| Computerkenntnisse      | Fachkenntnisse                                |
| Interessen und Hobbies  | Programmierung, Netzwerke, Gaming             |
| Einstellung zum Produkt | ""                                            |
| Wünsche                 | Viele Funktionen, Wenig Konfigurationsaufwand |



## User Stories

> Als **[Rolle]** möchte ich **[Ziel/Wunsch]**, um **[Nutzen]**

1. Als **Benutzer** möchte ich **verschiedene Geldbeträge eingeben**, um diese abzuheben
2. Als **Benutzer** möchte ich **sehen, wie viel Geld auf meinem Konto** ist, um zu wissen, wie viel ich noch abheben kann
3. Als **Benutzer** möchte ich eine **vierstellige Pin zu meiner Karte eingeben müssen**, um zu wissen, dass mein Bankkonto sicher ist
4. Als **Benutzer** möchte ich eine **Stückelung auswählen** können, um gewünschte Scheine zu erhalten
5. Als **Benutzer** möchte ich mich **einloggen können**, um getätigte Transaktionen zu sehen
6. Als **Administrator** der Bank möchte ich eine **vollständige und detaillierte Dokumentation**, um im Fehlerfall schnell handeln zu können

  
## Aufgaben

coming soon

  
## Begriffslexikon

| **Begriff**        | **Bedeutung**                     |
| ------------------ | --------------------------------- |
| **Cash Dispenser** | Bargeld im ATM-Dispenser          |
| **Deposit Slot**   | Geldfach zum Ein- und Auszahlen   |
| **Balance**        | Ist-Saldo auf einem Account       |
| **Withdrawal**     | Geld abheben                      |
| **Account Pin**    | Geheimpin eines Accounts (unique) |
| **Account number** | Nummer eines Accounts (unique)    |
| **Credit**         | Gutschrift                        |
| **Debit**          | Lastschrift                       |

  
## Mengengerüst

Mengengerüst Stichpunkte:

- Pin-Länge: Immer genau 4 Stellen
- Maximaler Betrag pro Account an einem Tag abheben: 1000€?
- Maximale Nutzer gleichzeitig: ???
- Maximale Transaktionen pro Minute: ???
- Maximal registrierte Nutzer: ???

  
## Use Cases

coming soon
