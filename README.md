![alt text](https://img.shields.io/badge/OpenWeather-Readme-orange)
# OpenWeather 
## Descrizione
Questo programma permette di visualizzare le previsioni meteo correnti e future relative ad una città.
Inoltre si può richiedere la stampa di alcune statistiche riguardanti la temperatura e la pressione in base a tre diverse fasce temporali (oraria,giornaliera e settimanale).
# Contenuti
- [Introduzione]
- [Installazione]
- [Rotte]
- [Test]
- [Autori]

## Rotte
| N° | Rotta   | Descrizione   |
|-----|:-------:|:-------------:|
| #1 | `/current` | Stampa le principali condizioni meteorologiche attuali della città richiesta. |
| #2 | `/forecast` | Stampa le condizioni meteorologiche per i successivi 5 giorni ad intervalli di 3 ore. |
| #3 | `/saveFile` | Consente di salvare un file in locale con estensione .json che si aggiorna, ad intervalli di un'ora, le condizioni meteo della città richiesta. |
| #4 | `/stats` | Visualizza le statistiche fatte sul file creato da `/saveFile` |
| #5 | `/errors` | Calcola e stampa la soglia di errore delle temperature tra forecast e attuale. |
| #6 | `/filters` | Permette di stampare solo le previsioni selezionate da un preciso momento *start* a *finish* .|
----------------------
## Eccezioni ed utilizzo
*Tutte le rotte hanno una eccezione in comune che si chiama **CityNotFoundException** se la città inserita non esiste.*
# Esempi di risultati da Postman
Di seguito gli esempi delle rotte sopra elencate:

### `/current?name=ancona` 

![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/responseCurrent.jpg)

### `/forecast?name=fano`   
*parte uno*
![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/responseForecast1.jpg)

*parte due*
![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/responseForecast2.jpg)

### `/saveFile?name=napoli`

Rotta di tipo GET.
*Questa rotta consente di salvare un file contenente le previsioni meteo ogni ora,
prendendo come parametro il **name** della città.
Restituisce un stringa contenente il percorso del file salvato, **path**.*

*Se viene passato come parametro un **name** inesistente, viene generata l'eccezione ***CityNotFoundException*** che chiede 
di inserire un nome della città corretto.*

![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/saveFile.jpg)

*esempio di stampa del file dopo essere stato in run per 2 ore*

![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/saveFileJsonEs.jpg)

### `/stats?name=napoli`

*Questa rotta consente di generare statistiche riguardanti valori massimi, minimi, media e varianza di pressioni e temperature.
Restituisce un JSONObject ***Statistics*** contenente le statistiche citate sopra, della città passata come parametro(**name**).*

*Questa rotta può generare l'eccezione ***IOException*** se il nome del file inserito è inesistente.*

![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/statsNapoli.jpg)


### `/errors?name=napoli`

*Questa rotta consente di visualizzare l'errore tra il forecast e l'attuale.
Restituisce un JSONObject contenente la differenza tra il current e il forecast della temperatura corrente, massima e minima 
della città passata come parametro(**name**).*

*Questa rotta può generare l'eccezione ***CityNotFoundException*** se il nome della città inserita è inesistente.*

![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/errors.jpg)

### `filters?start=18-01-2022&finish=19-01-2022&startTime=10:30&finishTime=12:30`
![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/filters1.jpg)

## **NOTE**
- Nel JAVADOC le lettere accentate non sono corrette ma abbiamo ritenuto corretto che nel programma i commenti fossero leggibili piuttosto che sul JDOC.
- 

## Link d'interesse
I link su cui ci siamo basati per la documentazione delle chiamate API sono:

[Five day weather forecast](https://openweathermap.org/forecast5#name5)

[Current weather data](https://openweathermap.org/current)
## Componenti del gruppo
Studenti: Sabatini Luca, Rachiglia Francesco

Progetto per l'esame di programmazione ad oggetti nella sessione di gennaio 2022

