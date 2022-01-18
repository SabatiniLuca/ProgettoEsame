![alt text](https://img.shields.io/badge/OpenWeather-Readme-orange)
# OpenWeather 

### INTRODUZIONE
Questo programma permette di visualizzare le previsioni meteo correnti e future relative ad una città.
Inoltre si può richiedere la stampa di alcune statistiche riguardanti la temperatura e la pressione in base a tre diverse fasce temporali (oraria,giornaliera e settimanale).

## Contenuti
- [Introduzione](https://github.com/SabatiniLuca/ProgettoEsame#descrizione)
- [Elenco Rotte](https://github.com/SabatiniLuca/ProgettoEsame#rotte)
  1. [`/current`](https://github.com/SabatiniLuca/ProgettoEsame#currentnameancona)
  2. [`/forecast`](https://github.com/SabatiniLuca/ProgettoEsame#forecastnamefano)
  3. [`/saveFile`](https://github.com/SabatiniLuca/ProgettoEsame#savefilenamenapoli)
  4. [`/stats`](https://github.com/SabatiniLuca/ProgettoEsame#statsnamenapoli)
  5. [`/errors`](https://github.com/SabatiniLuca/ProgettoEsame#errorsnamenapoli)
  6. [`/filters`](https://github.com/SabatiniLuca/ProgettoEsame#filtersstart18-01-2022finish19-01-2022starttime1030finishtime1230)
- [Test](https://github.com/SabatiniLuca/ProgettoEsame#test)
- [Autori](https://github.com/SabatiniLuca/ProgettoEsame#componenti-del-gruppo)

### ![alt text](https://img.shields.io/badge/OpenWeather-Rotte-blue) 
*Tutte le rotte sono di tipo GET*

| N° | Rotta   | Descrizione   | Parametri |
|-----|:-------:|:-------------:|:---------:|
| #1 | `/current` | Stampa le principali condizioni meteorologiche attuali della città richiesta. | *String* name |
| #2 | `/forecast` | Stampa le condizioni meteorologiche per i successivi 5 giorni ad intervalli di 3 ore. | *String* name |
| #3 | `/saveFile` | Consente di salvare un file in locale con estensione .json che si aggiorna, ad intervalli di un'ora, le condizioni meteo della città richiesta. | *String* name |
| #4 | `/stats` | Visualizza le statistiche fatte sul file creato da `/saveFile` | *String* name |
| #5 | `/errors` | Calcola e stampa la soglia di errore delle temperature tra forecast e attuale. | *String* name |
| #6 | `/filters` | Permette di stampare solo le previsioni selezionate da un preciso momento *start* a *finish* | *String* name, start, finish, startTime, finishTime |
----------------------

### `/current?name=ancona` 
Questa rotta fa stampare un JSONObject contenente due oggetti. Uno con le informazioni sul meteo e uno contenente informazioni della città richiesta. Se l'Url non è creato correttamente verrà stampata una eccezione.

*Funzionamento:* 
>Il JSONObject ottenuto dalla chiamata API viene elaborato, dalle informazioni che esso contiene viene creato un oggetto della classe [Weather](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/src/main/java/univpm/OpenWeather/Model/Weather.java). Questo oggetto è poi passato al metodo *printInfo* per creare un JSONObject da stampare.
![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/responseCurrent.jpg)

### `/forecast?name=fano`   
Questa rotta stampa un oggetto contenente le previsioni per i 5 giorni successivi al momento della richiesta.
Le previsioni sono fatte ad intervalli di tre ore l'una dall'altra. Se l'Url non è creato correttamente verrà stampata una eccezione.

*Funzionamento:* 
>Dal JSONObject ottenuto con la chiamata API viene selezionato un oggetto alla volta, all'interno del JSONArray contenente tutte le previsioni, e per ognuno di essi viene fatto un simile procedimento a quello usato con la rotta `/current`. 
>Si crea un'istanza della classe [Weather](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/src/main/java/univpm/OpenWeather/Model/Weather.java), poi la si stampa ma questa volta passando al metodo *printInfo* un valore *false* verranno stampate solo le informazioni relative al meteo  e non quelle relative alla città che invece verranno aggiunte solo dopo tutte le previsioni (come si può vedere nella seconda parte).

![alt text](https://img.shields.io/badge/OpenWeather-prima%20parte-yellowgreen)
![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/responseForecast1.jpg)

![alt text](https://img.shields.io/badge/OpenWeather-seconda%20parte-yellowgreen)
![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/responseForecast2.jpg)

### `/saveFile?name=napoli`

Rotta di tipo GET.
*Questa rotta salva un file contenente le previsioni meteo ogni ora, prendendo come parametro il **name** della città.
Restituisce un stringa contenente il percorso del file salvato, **path**.*

*Se viene passato come parametro un **name** inesistente, viene generata l'eccezione ***CityNotFoundException*** che chiede 
di inserire un nome della città corretto.*

*Funzionamento*
>Si crea inizialmente il percorso dove sarà salvato il file, viene preso il JSONObject del meteo attraverso una chiamata API e
>all'interno del metodo si manda in esecuzione un metodo runnable per salvare ogni ora nel file le informazioni aggiornate
>del meteo. La rotta restituisce il percorso del file salvato.
![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/saveFile.jpg)

![alt text](https://img.shields.io/badge/OpenWeather-esempio%20di%20stampa%20del%20file%20dopo%20essere%20stato%20in%20run%20per%202%20ore-yellowgreen)

![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/saveFileJsonEs.jpg)

### `/stats?name=napoli`

*Questa rotta consente di generare statistiche riguardanti valori massimi, minimi, media e varianza di pressioni e temperature.
Restituisce un JSONObject ***Statistics*** contenente le statistiche citate sopra, della città passata come parametro(**name**).*

*Questa rotta può generare l'eccezione ***IOException*** se il nome del file inserito è inesistente.*

*Funzionamento*
>Prende il file salvato con la rotta **saveFile** e genera le statistiche prendendo dai JSONObject salvati 
>i valori delle temperature e delle pressioni.
![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/statsNapoli.jpg)


### `/errors?name=napoli`

*Questa rotta consente di visualizzare l'errore tra il forecast e l'attuale.
Restituisce un JSONObject contenente la differenza tra il current e il forecast della temperatura corrente, massima e minima 
della città passata come parametro(**name**).*

*Questa rotta può generare l'eccezione ***CityNotFoundException*** se il nome della città inserita è inesistente.*

*Funzionamento*
>Prende il JSONObject del current e il primo JSONObject del forecast e confronta la temperatura corrente, massima e minima 
>e calcola l'errore tra le due.
![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/errors.jpg)

### `filters?start=18-01-2022&finish=19-01-2022&startTime=10:30&finishTime=12:30`

Questa rotta consente di visualizzare le previsioni filtrandole tramite un momento d'inizio e uno di fine.
Nota: è importante che il formato della data e del tempo richiesto siano corretti (dd-MM-yyyy e HH:mm) altrimenti verrà generata un'eccezione. Inoltre saranno generate eccezioni se il momento ***Finish*** è dopo più di tre giorni dal momento della richiesta oppure se tra il momento ***Start*** e ***Finish*** non ci sono previsioni disponibili.

*Funzionamento:*
>Questa rotta utilizza il metodo del service ([WeatherImpl](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/src/main/java/univpm/OpenWeather/Service/WeatherImpl.java)) getForecast per ottenere il JSONObject con le informazioni. Tramite un ciclo il metodo **FromStartToFinish** della classe [FiltersImpl](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/src/main/java/univpm/OpenWeather/Utils/FiltersImpl.java) aggiungerà al JSONObject da stampare solo le previsioni che sono contenute tra ***Start*** e ***Finish***.
![alt text](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/IMMgithub/filters1.jpg)

## Eccezioni ed utilizzo
*Tutte le rotte hanno una eccezione in comune che si chiama **CityNotFoundException** se la città inserita non esiste.*

# Test
[Utils](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/src/main/java/univpm/OpenWeather/Utils/Utils.java)
1. testSearchArray(): testa il funzionamento di un metodo implementato per cercare un valore all'interno di un array.
2. testDateConverter(): testa il funzionamento di un metodo implementato per convertire una data da testo (con formato specifico) ad un oggetto Date
3. testToDate(): testa il funzionamento di un metodo implementato per convertire una data da EpochTime a Date 
4. testTempConverter(): testa il funzionamento di un metodo implementato per convertire una temperatura da °K a °C
------
[Service](https://github.com/SabatiniLuca/ProgettoEsame/blob/main/OpenWeather.it/src/main/java/univpm/OpenWeather/Service/WeatherImpl.java)
1. testUrlBuilder(): testa che la costruzione dell'url per la chiamata sia corretta
2. testResetUrl(): testa il reset dell'url prima di ogni nuova chiamata
------
[Exception](https://github.com/SabatiniLuca/ProgettoEsame/tree/main/OpenWeather.it/src/main/java/univpm/OpenWeather/Exception)
1. testGetCurrent(): testa che venga lanciata l'eccezione **CityNotFound** quando viene inserita una città inesistente
2. testGetForecast():  testa che venga lanciata l'eccezione **CityNotFound** quando viene inserita una città inesistente
3. testSaveFile(): testa che venga lanciata l'eccezione **CityNotFound** quando viene inserita una città inesistente
4. testSetDate1(): testa che venga lanciata l'eccezione **ExeededDayException** quando le previsioni cercate sono più distanti di 5 giorni
5. testSetDate2(): testa che venga lanciata l'eccezione **ExeededDayException** quando le previsioni cercate sono troppo immediate
6. testFromStartToFinish(): testa che venga lanciata l'eccezione **WrongDayException** quando la data di inizio è successiva alla data di fine

## **NOTE**
- Nel JAVADOC le lettere accentate non sono corrette ma abbiamo ritenuto corretto che nel programma i commenti fossero leggibili piuttosto che sul JAVADOC.
- La suddivisione dei compiti nella realizzazione di questo programma è stata equa e c'è stata collaborazione tra i due studenti in caso di difficoltà. Ogni metodo o classe contiene il nome dell'autore.

# Link d'interesse
I link su cui ci siamo basati per la documentazione delle chiamate API sono:

[Five day weather forecast](https://openweathermap.org/forecast5#name5)

[Current weather data](https://openweathermap.org/current)

# Credits
- Eclipse(IDE)
- Springboot(Framework)
- Maven(Framework)
- J-Unit
- JSON(JSON-Simple)

## Componenti del gruppo
Studenti: Sabatini Luca, Rachiglia Francesco

Progetto per l'esame di programmazione ad oggetti nella sessione di gennaio 2022

