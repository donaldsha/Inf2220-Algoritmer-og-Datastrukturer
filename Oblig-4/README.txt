1- Forklaring av algoritmen og hvordan den fungerer.

Algoritmen tok jeg fra forelesningen og endret den litt slik at den oppfyller de kravene som står i oppgaven og gjør den vi vil at den skal gjøre. I prinsippet er den veldig likt Boyer-Moore algortimen med noen små endringer og noen ting som måtte legges til, feks: vi måtte oppdatere bad_shift slik at den stemmer overens wildcard-ene som ble legget til.

a- først sjekker jeg om needle karakteren stemmer med haystackssin, ellers om needle er '_'(wildcard) så skal den stemme med sin tilsvarende haystack karakter

b- Den som stemmer settes(lagres) i ArrayListen

c- Fortsetter å lette etter nye overensstemninger ved å skippe framover med bruk av bad_character og returnerer hele listen når den er ferdig.

2- Kompiler oppgaven ved bruk av-->  javac *.java og kjør den ved bruk av:
java Oblig4 needle.txt haystack.txt (I stedet av needle og haystack kan settes inn de andre filene for å teste programmet med flere tilfeller).

3- Main metode er inkludert i Oblig4.java filen.

4- Ikke noe så viktig men bare antar at filene er ikke tomt.

5- Nei, ikke noe.

6- Hele oppgaven fungerer og jeg har ikke noen deler som fungerer ikke.

7-Koden min er basert mest på forelesning 10, som kan finnes her: http://www.uio.no/studier/emner/matnat/ifi/INF2220/h17/undervisningsmateriale/slidesuke10.pdf

--> Outputs som jeg får fra forskjellig filer hvor jeg tester de casene som er nevnt på obligen:

************************************

$ java Oblig4 needle.txt haystack.txt
Stemmer overens for needle: c_g

Stemmer overens  2 ganger
        På indeks: 0 med streng: cog
        På indeks: 8 med streng: cag

*************************************

$ java Oblig4 needle1.txt haystack1.txt
Stemmer overens for needle: _er

Stemmer overens  5 ganger
        På indeks: 2 med streng: ler
        På indeks: 7 med streng: ser
        På indeks: 12 med streng: ger
        På indeks: 18 med streng: ker
        På indeks: 25 med streng: ker


**************************************

$ java Oblig4 needle2.txt haystack2.txt
Stemmer overens for needle: _m__

Stemmer overens  2 ganger
        På indeks: 5 med streng: jmbc
        På indeks: 8 med streng: cmnx


*************************************


$ java Oblig4 needle3.txt haystack3.txt
Stemmer overens for needle: ___1

Stemmer overens  2 ganger
        På indeks: 2 med streng: 3451
        På indeks: 11 med streng: 6231



*************************************


$ java Oblig4 needle4.txt haystack4.txt
Stemmer overens for needle: __

Stemmer overens  10 ganger
        På indeks: 0 med streng: __
        På indeks: 1 med streng: __
        På indeks: 2 med streng: __
        På indeks: 3 med streng: __
        På indeks: 4 med streng: _a
        På indeks: 5 med streng: ah
        På indeks: 6 med streng: h_
        På indeks: 7 med streng: __
        På indeks: 8 med streng: __
        På indeks: 9 med streng: __














