# Architekturdokumentation

## Beschreibung der Systemarchitektur

### Priorisierung der nicht funktionalen Anforderungen

> Beschreibt wie gut ein System/Produkt eine Funktion erfüllt

- Gute Benutzerfreundlichkeit
- Hohe Performance bei Operationen wie Guthaben abrufen, einzahlen und auszahlen
- Kurze Start-Zeit (Account-Initialisierung)

### Architekturprinzipien

> Nach welchen Kriterien soll das System in Komponenten unterteilt werden?
> Welche Aspekte sollen in Komponenten zusammengefasst werden?
> Welche Dienstleistungen sollen Komponenten nach außen an ihrer Schnittstelle anbieten, welche Aspekte müssen geschützt sein?
> Wie sollen die Komponenten miteinander interagieren?
> Wie sollen Komponenten strukturiert und verfeinert werden?

### Schnittstellen

- UI mit den Java-Swing GUI Bibliotheken
- `KeypadListener.java` für Kommunikationsschnittstelle zwischen dem Tastenfeld und dem Bildschirm Objekt
- `ATMListener.java` ist die Schnittstelle zum Haupt-ATM-Objekt, in der Aktionen wie ein Wechsel in einen anderen Modus oder das Betätigen der Enter-Taste behandelt werden

### Big Picture der Systemarchitektur


## Systementwurf

### Systemdekomposition

### Designalternativenund –Entscheidungen

### Cross-Cutting-Concerns, NFRs


## Mensch-Maschine-Schnittstelle

### Anforderungen an die Mensch-Maschine-Schnittstelle

Die Mensch-Maschine-Schnittstelle, oder auch Benutzerschnittstelle, bezieht sich auf die Kommunikation zwischen einem Nutzer (Mensch) und dem Geldautomaten (Maschine).

Der Mensch gibt mit seinen Aktoren (Händen) eine Eingabe-Information an die Peripherieeinheiten des Geldautomaten, welche eine digitale Information an die Recheneinheit des Geldautomaten weiterleiten. Die von der Recheneinheit entgegengenommene Information wird mittels der aufgespielten Software verarbeitet und eine Ausgabe-Information wird erzeugt. Die Recheneinheit steuert digital die Peripherieeinheiten des Geldautomaten an, welche eine optische (Bildschirm-Ausgabe) und mechanische Ausgabe Information (Geldauszahlung) erzeugen. Die Rückgabe-Informationen werden vom Menschen visuell (Bildschirm-Information) und haptisch (Annahme des ausgezahlten Geldes) verarbeitet.


### Gestaltungsprinzipien und Style-Guide

### Interatkionsmodellierung
