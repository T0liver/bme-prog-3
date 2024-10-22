# lab6

Ez az egyetlen labor, amit Eclipse-ben kell csinálni, mert csak az támogatja, hogy futtasson code coverage-et ``.jar`` fálj-ra, mert minden más IDE felismerte, hogy ez egy felesleges dolog.

Én vscode-ban csináltam, de itt van, hogyan kell az ide feltöltött fájlokat átimportálni Eclipse-be, mert amúgy én se csináltam meg a saját gépemen.

<details>
<summary>Fun fact</summary>

Az *Eclipse nem tartalmaz uninstaller-t*. Tehát ha egyszer feltelepítetted, akkor nem tudod egy kattintással eltávolítani a gépedről. :)

Ez azért van a készítők szerint, mivel *,,Eclipse elvileg csak néhány olyan feladatot automatizál, amelyet korábban kézzel kellett elvégezni (például az Eclipse letöltése és kicsomagolása, valamint parancsikonok hozzáadása), így ezeket is vissza lehet csinálni kézzel. "*

[ide jöhet a bohóc emoji]

Ha el akarod távolítani mégis (mert rájöttél, hogy nem kell ez a szenny a gépedre), akkor itt egy kis segítség:

[StackOverflow](https://stackoverflow.com/questions/50854359/how-to-uninstall-eclipse#answer-50855742)

</details>

Szóval:

1. Létrehozol egy új projektet (File > New > Project) a munka mappába
2. Importálod a .zip fájlt (nekem nem sikerült .zip-ként, csak kicsomagolva, aztán *File system*-re kattintva)
3. A ``bank`` mappa mellé létrehozol egy ``test`` vagy hasonló nevűt.
4. Ide másolod be az itteni ``test`` mappából a fájlokat.
5. A fájlokat átteszed a junitlab.test package-be (mivel ez nem vscode, ez nem refaktorál autómatikusan...)
6. Létrehozol egy új JUnit futtatást (valahogy).
7. Futtatod boldogan (ki a világból ezt az egész #&@đĐ...)
8. Megmutatod boldogan a labvezednek, aki egyetért abban, hogy felesleges volt ez az egész, és reméli, hogy csak virtuális gépre telepítetted az Eclipse-t, és nem a saját gépedre!

<boldogság>

Ez a leírás lehet hiányos, én se csináltam teljesen végig, de próbálkozni lehet, menni fog az...

(Amúgy az egész projekt kiválóan fut vscode-ban, próbáld meg ott először, hátha úgy is elfogadják...)