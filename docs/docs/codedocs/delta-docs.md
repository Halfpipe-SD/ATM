# Delta-Dokumentation

## Durchgeführte Veränderungen

- Änderung der PIN auf 4 Stellen
- Über das X kann das Programm beendet werden
- Über die Abbrechen-Funktion im Menu kann sich der Benutzer abmelden
- Die internen Klassen, die das Event-Handling übernahmen, wurden entfernt
- Event-Handling der UI Elemente werden mit zwei Interfaces umgesetzt
      - `KeypadListener.java` kommuniziert die Tastendrücke 
      - `ATMListener.java` kommuniziert einen Modus-Wechsel und das Betätigen der Enter-Taste
- Auslagerung der Admin-Ansicht in ein neues Fenster `AdminView.java`
- Die Sprache des Programms wurde auf Deutsch umgesetzt
- Verbessertes Error-Handling 
- Accounts werden mit einer .json Datei gelesen und gespeichert
- Geld wird in Scheinen aus dem Vorrat im Cash Dispenser ausgegeben
      - Es werden die höchstmöglichen Scheine gewählt

## Verbesserungsvorschläge

- Anbindung des Programms an eine Datenbank
- Besserer Algorithmus für die Ausgabe der Geldscheine aus dem Cash Dispenser
- Verfügbares Guthaben und gesamtes Guthaben sollten in Zukunft nicht gleich behandelt werden
- Zusätzliche Information auf den Oberflächen z.B. I-Icon mit Informationen zum Abheben