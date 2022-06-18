# Architekturdokumentation

## Beschreibung der Systemarchitektur

### Priorisierung der nicht funktionalen Anforderungen

#### Qualitätsanforderungen

**Änderbarkeit** und **Wiederverwendbarkeit** waren uns besonders wichtig, da wir zu Beginn Schwierigkeiten hatten, uns einen Überblick über den bestehenden Code zu verschaffen. Aus diesem Grund entschieden wir uns, den Code noch einmal von Grund auf neu zu erstellen.
Dadurch verbessert sich vor allem die **Brauchbarkeit** und **Wartbarkeit** des Codes.

#### Anforderungen an Lieferbestandteile

Eine vollständige Dokumentation in Form eines PDF Dokumentes und die Software bilden die Lieferbestandteile.

#### Anforderungen an die Benutzerschnittstelle

Eine weitere wichtige nicht funktionale Anforderung ist die **Bedienbarkeit** oder **Benutzerfreundlichkeit** des Programms. Da diese Anwendung für eine sehr große Menge an Benutzern ausgelegt ist, wurde die Bedienbarkeit und Benutzerfreundlichkeit des Programms auf eine höhere Priorität gesetzt. So wird gewährleistet, dass Benutzer aller Altersgruppen gut mit der Anwendung interagieren können.

### Architekturprinzipien

> Nach welchen Kriterien soll das System in Komponenten unterteilt werden?
> Wie sollen Komponenten strukturiert und verfeinert werden?

Das System wurde in verschiedene Komponenten unterteilt, die sich jeweils auf eine bestimmte Aufgabe beziehen, um eine enge Kopplung der Module untereinander zu reduzieren.
Der verschachtelte Aufbau der UI Komponenten bildet eine Struktur, die leicht erweitert werden kann.

> Welche Aspekte sollen in Komponenten zusammengefasst werden?

In der `ATM.java` Klasse werden die Änderungen von einem Modus in den Nächsten behandelt.
Dem entsprechend wird die `Screen.java` Klasse angesteuert, um die UI Elemente zu aktualisieren.

Die Klasse `Screen.java` beinhaltet alle Funktionen, die zum Ändern der UI Elemente benötigt werden. In ihr werden die Klassen `Keypad.java` und `SidePanel.java` verwendet.

> Welche Dienstleistungen sollen Komponenten nach außen an ihrer Schnittstelle anbieten?
> Wie sollen die Komponenten miteinander interagieren?

Die Komponente `Keypad.java` gibt über das `KeypadListener.java` Interface alle Events für Tastendrücke an die `Screen.java` Klasse weiter.
Die Komponente `Screen.java` gibt über das Interface `ATMListener.java` Events wie z.B. einen Modus-Wechsel oder das Betätigen der Enter-Taste an die `ATM.java` Klasse weiter.

### Schnittstellen

Hier werden alle Schnittstellen des Systems beschrieben.

- UI mit den Java-Swing GUI Bibliotheken
- `KeypadListener.java` für Kommunikationsschnittstelle zwischen dem Tastenfeld und dem Bildschirm Objekt
- `ATMListener.java` ist die Schnittstelle zum Haupt-ATM-Objekt, in der Aktionen, wie ein Wechsel in einen anderen Modus oder das Betätigen der Enter-Taste behandelt werden

### Big Picture der Systemarchitektur

Der Aufbau der Systemarchitektur ist weitestgehend modular gestaltet und ist hier in einem Klassendiagramm dargestellt.

![Klassendiagramm](images/class.png "Klassendiagramm")
![Exceptions](images/class2.png "Exceptions")

## Systementwurf

### Systemdekomposition

Im folgenden Abschnitt werden die einzelnen Komponenten des Systems und ihre Funktionen beschrieben.

Das System lässt sich hauptsächlich durch die Bestandteile `Guthaben anzeigen`, `Geld abheben` und `Geld einzahlen` beschreiben. Zusätzlich gibt es ein `Menü` eine `Admin-Ansicht` und eine `Login`, sowie eine `Logout` Funktion.

Vom Menü aus, ist es einem Benutzer möglich alle relevanten Funktionalitäten durch das Drücken einer Zahl zu erreichen. Die Funktion `atmSwitchModeAction()` wechselt nun, je nach eingegebener Zahl, in den entsprechenden Modus. Eine weitere wichtige Komponente des Systems ist das `Keypad`, welches die verschiedenen Knöpfe darstellt. Dieses befindet sich immer in der linken Hälfte des Fensters und hilft dem Nutzer bei der Bedienung des Automaten. Es wird in dem Konstruktor der Klasse `Screen.java` zusammen mit dem SidePanel initialisiert.

Das `SidePanel` hat, wie das Keypad, eine eigene Klasse. Es befindet sich auf der rechten Hälfte des Fensters und beinhaltet unter anderem einen „Back-Button“. Mit diesem kann zurück in den „Menü-Modus“ gewechselt werden. In dem SidePanel befindet sich außerdem das Textfeld, in welchem die Benutzereingabe angezeigt wird, sowie ein JLabel. Dieses zeigt, je nach Modus, zum Beispiel das verfügbare Geld, oder die verschiedenen Optionen mit entsprechender Eingabe an.

Eine weitere Funktionalität ist die `Admin-Ansicht`. Loggt sich ein Admin ein, öffnet sich ein neues Fenster. In diesem können die Daten der Benutzer geändert und anschließend gespeichert werden.

### Designalternativen und –Entscheidungen

Es wurde sich dazu entschieden die einzelnen Funktionalitäten mit Hilfe von verschiedenen Modi zu implementieren. Der Bankautomat befindet sich zu jedem Zeitpunkt in einem bestimmten Modus und reagiert, je nach Modus, unterschiedlich auf bestimmte Eingaben. Dieser Ansatz unterscheidet sich von der ursprünglichen Version des Automaten. Hier gab es keine Modi und die verschiedenen Funktionen, wie das Geldabheben, wurden von eigenen Klassen übernommen.

In der alten Version des Bankautomaten, konnte ein Admin mit Hilfe eines Iterators auf die einzelnen Benutzer zugreifen. In dem überarbeiteten Modell ist es möglich, aus einer Liste von Benutzern den gewünschten per Mausklick auszuwählen. Dies ermöglicht eine einfachere und schnellere Bearbeitung.

Zudem wird das Speichern der verschiedenen Benutzer nicht mehr innerhalb einer Java-Klasse übernommen, sondern außerhalb in einer JSON-Datei. Die Benutzerdaten werden mit Hilfe der Klasse `BankDatabase.java` in diese Datei übertragen.

### Cross-Cutting-Concerns, NFRs

Nun werden kurz die Cross-Cutting-Concerns des Systems, sowie der Umgang mit diesen, vorgestellt.

Ein Benutzer soll in jedem Modus eine Eingabe tätigen können. Daher wurde das Keypad und ein entsprechendes Textfeld so implementiert, dass diese Komponenten stets sichtbar und verfügbar sind. Andere Komponenten werden teilweise unsichtbar gemacht, da diese nicht in jedem Modus gebraucht werden.

Ein weiterer Cross-Cutting-Concern ist das Geben von passendem Feedback an den Benutzer. Hier soll dem Benutzer, unabhängig von dem aktuellen Modus, stets mitgeteilt werden, wenn er eine ungültige Eingabe getätigt hat. Für diese Art von Fehlermeldungen wurde im untersten Bereich des Fensters ein Textfeld angelegt, welches die jeweilige Nachricht in roter Farbe anzeigt.

Außerdem ist die Validierung des Inputs bei einem Bankautomaten äußerst wichtig. Deshalb werden die Eingaben stets auf Richtigkeit überprüft. So wird beispielsweise sichergestellt, dass das eingezahlte Geld keinen Maximalwert überschreitet. Ebenso muss sichergestellt werden, dass ein Benutzer nicht mehr Geld abheben kann, als gerade für ihn verfügbar ist.

Bezüglich der Nicht-funktionalen-Anforderungen wurde auf eine hohe Performance und Bedienbarkeit geachtet. Dem Benutzer wird das Bedienen des Automaten durch ein intuitives Interface leichtgemacht. Die Wartezeiten sind kurz, da die Funktionen zur Berechnung von Überweisungen und Kontoständen eine geringe Laufzeit aufweisen.

## Mensch-Maschine-Schnittstelle

### Anforderungen an die Mensch-Maschine-Schnittstelle

Die Mensch-Maschine-Schnittstelle, oder auch Benutzerschnittstelle, bezieht sich auf die Kommunikation zwischen einem Nutzer (Mensch) und dem Geldautomaten (Maschine).
Der Mensch gibt mit seinen Aktoren (Händen) eine Eingabe-Information an die Peripherieeinheiten des Geldautomaten, welche eine digitale Information an die Recheneinheit des Geldautomaten weiterleiten. Die von der Recheneinheit entgegengenommene Information wird mittels der aufgespielten Software verarbeitet und eine Ausgabe-Information wird erzeugt. Die Recheneinheit steuert digital die Peripherieeinheiten des Geldautomaten an, welche eine optische (Bildschirm-Ausgabe) und mechanische Ausgabe Information (Geldauszahlung) erzeugen. Die Rückgabe-Informationen werden vom Menschen visuell (Bildschirm-Information) und haptisch (Annahme des ausgezahlten Geldes) verarbeitet.

| Ein-/Ausgabe | **Mensch Schnittstelle** | **Hardware Schnittstelle**                | **Software Schnittstelle** |
| ------------ | ------------------------ | ----------------------------------------- | -------------------------- |
| **Eingabe**  | Hände                    | Encrypting PIN Pad                        | Tastenabfrage              |
|              | Augen                    | ID-Kartenleser, Softkeys oder Touchscreen | Touchbildschirm Abfrage    |
| **Ausgabe**  | Hände                    | Bildschirm                                | Grafikausgabe              |
|              | Augen                    | Auszahlmodul                              | Peripherie Ansteuerung     |

### Gestaltungsprinzipien und Style-Guide

Im Folgenden wurden Design-Mockups erstellt die die Ansichten für den Benutzer und den Administrator Repräsentieren.

Style-Guide Für den Benutzer:
![Style-Guide](images/Benutzer_Mockup.png "Style-Guide")

Style-guide für die Adminview:
![Style-Guide](images/Admin_Mockup.png "Style-Guide")

#### Gestaltprinzipien

Gestaltprinzipien, auch bekannt als Gestaltgesetzte je nach Literatur, helfen ein ansprechendes und verständliches design zu erstellen. Es handelt sich hier um psychologische Ansätze, um zu verstehen wie das menschliche Gehirn visuelle Informationen wahrnimmt und ordnet. Folgende Prinzipien wurden in diesem Projekt beachtet:

##### 1. Prinzip der Ähnlichkeit

Elemente die eine ähnliche bzw. gleiche Funktionen haben wurden gleich gestaltet. Wie zum Beispiel die Eingabe Nummernfeld Null bis Neun. Sie haben alle den gleichen design. Die knöpfe “Enter”, “Clear” und “OK” haben den gleichen design aber führen unterschiede Funktionen aus.

![Gestaltprinzipien](images/Prinzip_aehnlichkeit.png "Gestaltprinzipien")

##### 2. Prinzip der Nähe

Elemente die die gleichen Funktionen ausführen oder dazu helfen, dass diese Funktion ausgeführt werden kann, wurden räumlich nah platziert. Wie zum Beispiel die Eingabe Zifferknöpfe Null bis Neun, sie wurden links vom Display nah an einander  platziert, sowie auch die knöpfe “Enter” und “Clear” da sie die Zifferneingabe bestätigen oder korrigieren.  

![Gestaltprinzipien](images/Prinzip_naehe.png "Gestaltprinzipien")

##### 3. Prinzip der Prägnanz

Interaktionselemente wurden hervorgehoben. Wie im Falle der Eingabeknöpfe Null bis Neuen. Aber auch das Ausgabefenster wurde quadratisch und mit weißem Hintergrund gestaltet.

![Gestaltprinzipien](images/Prinzip_praegnanz.png "Gestaltprinzipien")

##### 4. Prinzip der gemeinsamen Region

Eingabe Elemente werden links vom Display platziert und das Ausgabefenster rechts vom Display. Die Regionen sind mit einer unterschiedlichen Hintergrundfarbe getrennt.

![Gestaltprinzipien](images/Prinzip_region.png "Gestaltprinzipien")

##### 5. Prinzip der Erfahrung

Elemente werden so gestaltet wie sie sich in voriger Praxis schon bewiesen haben oder aus Kulturellersicht angenommen wurden. Zum Beispiel in unseren Fall ist es kultureller standard das nummerische Tastenfelder in einer Matrix 3x4 angezeigt werden sollten.

![Gestaltprinzipien](images/Prinzip_erfahrung.png "Gestaltprinzipien")

### Interatkionsmodellierung

Im Folgenden wurde die Interaktion zwischen den Benutzern und dem Geldautomaten modelliert und in einem UML-Aktivitätsdiagramm
dargestellt.

#### Adminansicht

![InterkationsDiagramm](images/AdminView.png "InterkationsDiagramm")

#### Benutzeransicht

![InterkationsDiagramm](images/BenutzerView.png "InterkationsDiagramm")
