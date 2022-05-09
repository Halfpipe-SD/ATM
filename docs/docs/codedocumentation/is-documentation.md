# Code Ist-Documentation

## Klassen

`ATMCaseStudy.java`

- Erstellt eine ATM Instanz und startet diese, wenn noch keine vorhanden


`ATM.java`

- Stellt die Hauptklasse des ATMs dar
- Initialisiert UI mit Keypad, CashDispenser, DepositSlot und Bankdatabase
- Es gibt viele unbenutzte konstante int Variablen
- Sobald Enter betätigt wird, wird die PIN überprüft (login)
- Wenn man eingeloggt ist, wird das Menü angezeigt, wenn man als Admin eingeloggt ist, wird das Admin-Menü angezeigt
- Im Menü kann man nun zwischen Funktionen wählen:
    - `balance`: Eigenes Guthaben anzeigen
    - `withdrawal`: Geld abheben, indem man die Scheine einzeln wählt
    - `deposit`: Geld einzahlen. Geld ist erst verfügbar, wenn überprüft.
    - `exit`: Führt Login erneut aus, öffnet allerdings neues Fenster
- Sollte man als Admin angemeldet sein, öffnet sich die Adminoberfläche mit diesen Funktionen: - Kontostand jedes Nutzers einsehen - Accounts löschen - Neue Accounts hinzufügen


`Transaction.java`

- Abstrakte Klasse, die mit einer AccountNummer, Dem Screen-Objekt und dem BankDatabase-Objekt initialisiert wird.


`BalanceInquiry.java`

- Erbt von Transactions und überschreibt die Execute-Funktion
- Die Execute-Funktion gibt den Kontostand auf dem Screen aus


`Withdrawal.java`

- Erbt von Transactions und überschreibt die Execute-Funktion
- Die Execute-Funktion zeigt die Buttons zur Scheinauswahl an
- Die Transaction-Funktion ermöglicht das abheben von Geld, wenn noch genügend auf dem Konto und im CashDispenser verfügbar ist.
- Man kann nur in 20er Scheinen abheben


`Deposit.java`

- Erbt von Transactions und überschreibt die Execute-Funktion
- Die Execute-Funktion zeigt UI zum Geldeinzahlen an
- Beim Geldeinzahlen wird geprüft, ob das Geld eingezahlt wurde


`DepositSlot.java`

- Klasse ist nicht vorhanden.
- Hier sollte überprüft werden, ob das Geld vorhanden ist


`CashDispenser.java`

- Startet mit 500 20$ Scheinen


`BankDatabase.java`

- Initialisiert alle Accounts
- Authentifiziert Nutzer anhand der PIN
- Funktionen um anhand der AccountNumber Daten über den Account abzurufen
- ruft


## Verbesserungen

- Pin-Authentifizierung beim Login
    - Login mit Accountnummer?
    - Länge des PIN`s?
- "Exit" schließt das Programm
- Verbesserung des Event-Handlings mit Actionlisteners
- Verbeserung der Klassenstruktur (weniger Klassen?)
- Verbesserung des UI-Handlings mit JFrame und JPanel
- Verbesserung der CashDispenser-Funktion
- Bankautomat in Deutsch oder Englisch
