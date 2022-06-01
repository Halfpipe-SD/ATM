# Delta-Dokumentation

## Verbesserungsvorschläge

- 4-stellige PIN
- "Exit" schließt das Programm
- Verbesserung des Event-Handlings mit Actionlisteners
- Verbesserung der Klassenstruktur (weniger Klassen?)
- Verbesserung des UI-Handlings mit JFrame und JPanel
- Verbesserung der CashDispenser-Funktion
- Bankautomat in Deutsch

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