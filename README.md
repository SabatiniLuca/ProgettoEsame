![alt text](https://img.shields.io/badge/OpenWeather-Readme-orange)
# OpenWeather 
## Descrizione
Questo programma permette di visualizzare le previsioni meteo correnti e future relative ad una città.

Inoltre si può richiedere la stampa di alcune statistiche riguardanti la temperatura e la pressione in base a tre diverse fasce temporali (oraria,giornaliera e settimanale).
## Utilizzo
| Tipo di risultato | Rotta   | Descrizione   |
|-------------------|:--------:|:-------------:|
| *JSONObject* |`/current`| Stampa le principali condizioni meteorologiche attuali della città richiesta. |
| *JSONObject* | `/forecast` | Stampa le condizioni meteorologiche per i successivi 5 giorni ad intervalli di 3 ore. |
| *JSONObject* | `/saveEveryHour` | Consente di salvare un file in locale con estensione .json che si aggiorna, ad intervalli di un'ora, le condizioni meteo della città richiesta. |
|  |     |       |


### Esempi di risultati da Postman
Di seguito gli esempi delle rotte sopra elencate:

`current?name=ancona`
![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/responseCurrent.jpg)

`/forecast?name=fano`   
*parte uno*
![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/responseForecast1.jpg)

*parte due*
![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/responseForecast2.jpg)
## Link d'interesse
I link su cui ci siamo basati per la documentazione delle chiamate API sono:

[Five day weather forecast](https://openweathermap.org/forecast5#name5)

[Current weather data](https://openweathermap.org/current)
### Componenti del gruppo
Studenti: Sabatini Luca, Rachiglia Francesco

Progetto per l'esame di programmazione ad oggetti nella sessione di gennaio 2022

