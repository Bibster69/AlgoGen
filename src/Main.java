public class Main {
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("1111111", 2));


    }
}

//Program powinien mieć dwa tryby pracy: TrybA:
//Program powinien wygenerować dowolnej wielkości populację wybraną przez użytkownika,
//a następnie wykonać elementarny algorytm genetyczny z wyborem metody selekcji (koła ruletki,
//rankingowej, turniejowej). Algorytm powinien mieć możliwość wyboru krzyżowania (jednopunktowe,
//dwupunktowe – z ograniczeniem zakresu[1]). Populacja składa się z genów zapisanych jako liczby
//naturalne z przedziału 10-99 i każdy osobnik ma jeden chromosom a w nim 50 genów. Ocena polega
//na wybraniu takich osobników dla których parzyste miejsca w chromosomie mają największą wartość,
//a nieparzyste najmniejszą. Wynikiem działania powinien być najlepszy osobnik spełniający dane
//założenie.
//[1] Ograniczony zakres to np. dla chromosomu 111000 nie możemy wyciąć dwóch ostatnich oraz
//wycięty fragment nie może być większy niż połowa wielkości chromosomu. Czyli w tym wypadku
//wycięty fragment może być |111|000, 1|11|000. Natomiast nie może być: |11100|0