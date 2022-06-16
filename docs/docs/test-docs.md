# Testdokumentation

In der folgenden Dokumentation werden die für das Projekt durchgeführten Test beschrieben. Diese sind entweder manuell oder mit Hilfe von JUnit ausgeführt worden.  

</br>

| Name          | Sind Komponenten initialisiert                                |
| ------------- | ------------------------------------------------------------- |
| Anforderung   | Die ATM-Instanz soll einen screen und eine bankDatabase haben |
| Vorbedingung  | ATM-Instanz wird erzeugt                                      |
| Nachbedingung | Screen und bankDatabase des ATM sind initialisiert            |
| Testschritte  | Stelle sicher, dass Komponenten nicht null sind               |

</br>

| Name          | Wechsel in BALANCE Modus                                                    |
| ------------- | --------------------------------------------------------------------------- |
| Anforderung   | Mit dem Input "1" soll in den BALANCE Modus gewechselt werden               |
| Vorbedingung  | ATM-Instanz wird erzeugt</br>User loggt sich ein</br>Momentan im MENU Modus |
| Nachbedingung | Guthaben wird angezeigt</br>ATM im BALANCE Modus                            |
| Testschritte  | Funktion atm.atmEnterAction() wird mit Input "1" aufgerufen                 |

</br>

| Name          | Falscher Input in Menü                                                      |
| ------------- | --------------------------------------------------------------------------- |
| Anforderung   | Bei falschem Input soll ATM im selben Modus bleiben                         |
| Vorbedingung  | ATM-Instanz wird erzeugt</br>User loggt sich ein</br>Momentan im MENU Modus |
| Nachbedingung | ATM gibt Fehlermeldung, resettet das Textfeld und bleibt im selben Modus    |
| Testschritte  | Funktion atm.atmEnterAction() wird mit falschem Input aufgerufen            |

</br>

| Name          | "Back" Button                                                                                                                                                          |
| ------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Anforderung   | Der "Back" Button, soll den Modus zu MENU wechseln                                                                                                                     |
| Vorbedingung  | ATM-Instanz wird erzeugt</br>User loggt sich ein</br>Momentan im MENU Modus                                                                                            |
| Nachbedingung | ATM befindet sich wieder im MENU Modus                                                                                                                                 |
| Testschritte  | Wechsel in BALANCE Modus, Drücken auf "Back" Button</br>Wechsel in WITHDRAWAL Modus, Drücken auf "Back" Button</br>Wechsel in DEPOSIT Modus, Drücken auf "Back" Button |

</br>

| Name          | "Clear" Button                                                                |
| ------------- | ----------------------------------------------------------------------------- |
| Anforderung   | Bei Drücken auf den "Clear"-Button soll das Textfeld resettet werden          |
| Vorbedingung  | ATM-Instanz wird erzeugt</br>User loggt sich ein</br>Momentan im MENU Modus   |
| Nachbedingung | Das Textfeld ist leer                                                         |
| Testschritte  | Beliebiger Input wird in Textfeld eingegeben</br>"Clear"-Button wird gedrückt |

</br>

| Name          | Ungültiger Pin Input                                                                                                                               |
| ------------- | -------------------------------------------------------------------------------------------------------------------------------------------------- |
| Anforderung   | Bei falscher Pin soll eine LoginFailedException geworfen werden                                                                                    |
| Vorbedingung  | ATM-Instanz wird erzeugt</br>Ein neuer Account wird erstellt                                                                                       |
| Nachbedingung | ATM hat keinen Pin akzeptiert, da Pins aus 4 Ziffern bestehen müssen</br>ATM befindet sich noch im LOGIN Modus                                     |
| Testschritte  | Anmeldungsversuche mit verschieden ungültigen Pins</br>Zuerst ein Pin mit Buchstaben, dann ein Pin mit 5 Ziffern und zuletzt ein Pin mit 3 Ziffern |

</br>

| Name          | Neuen Account erstellen                                                                                                                                                         |
| ------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Anforderung   | In der AdminView soll ein neuer Account erstellt werden können                                                                                                                  |
| Vorbedingung  | ATM-Instanz wird erzeugt                                                                                                                                                        |
| Nachbedingung | Neuer Account wurde angelegt</br>ATM im ADMIN Modus                                                                                                                             |
| Testschritte  | Neuer Admin-Account wird erstellt und der Datenbank hinzugefügt</br>Der Admin loggt sich mit seiner Pin ein</br>Überprüfen, ob die Länge der Account Liste sich um 1 erhöht hat |

</br>

| Name          | Credit und Debit Funktion                                                                                                                                          |
| ------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Anforderung   | Credit Funktion soll das Guthaben um mitgegebenen Betrag erhöhen</br>Debit Funktion soll das Guthaben um mitgegebenen Wert verringern                              |
| Vorbedingung  | ATM-Instanz wird erzeugt</br>Neuer Account "a1" wird angelegt                                                                                                      |
| Nachbedingung | Guthaben ist gleich hoch wie vor der Durchführung des Tests                                                                                                        |
| Testschritte  | a1.credit(5) wird aufgerufen</br>Überprüfen, ob sich Guthaben um 5 erhöht hat</br>a1.debit(5) wird aufgerufen</br>Überprüfen, ob sich Guthaben um 5 verringert hat |
