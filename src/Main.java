import java.util.*;

public class Main {

    public static void roulette(int populationSize, int numOfGenerations, boolean twoPointInheritance){
//        if (twoPointInheritance) {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Podaj pierwszy punkt przecięcia");
//            int point1 = scanner.nextInt();
//            System.out.println("Podaj drugi punkt przecięcia");
//            int point2 = scanner.nextInt();
//        } else if (!twoPointInheritance) {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Podaj pierwszy punkt przecięcia");
//            int point1 = scanner.nextInt();
//        }
        Random random = new Random();
        int bestPossibleGrade = (25 * 10) + (25 * 99);
        Population population = new Population(populationSize, numOfGenerations, true);
        TreeMap<Specimen, Double> slices = new TreeMap<>();
        double upperBoundSum = 0;
        for(int i = 0; i < population.getMembers().size(); i++){
            double upperBound = (population.getMembers().get(i).getGrade() / population.getPopulationGrade()) * 100;
            upperBoundSum += Math.abs(upperBound);
            slices.put(population.getMembers().get(i), upperBoundSum);
        }

        population.print(1);
        System.out.println("Best osobnik: ");
        population.getBestSpecimen().print();
        System.out.println("slices:");
//        slices.entrySet().forEach(entry -> {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        });

        System.out.println("UpperBoundSum:");
        System.out.println(upperBoundSum);
        for(int i = 0; i < numOfGenerations - 1; i++) {
            Population tmpPop = new Population(populationSize, i+2, false);
            for (int j = 0; j < populationSize; j++){
                double randomSpecimen = 0 + (upperBoundSum - 0) * random.nextDouble();
                slices.entrySet().forEach(entry -> {
                    if (randomSpecimen <= entry.getValue() && !tmpPop.getMembers().contains(entry.getKey())){
                        tmpPop.addMember(entry.getKey());
                    }
                });
                int parent1Index = random.nextInt(tmpPop.getMembers().size());
                int parent2Index = random.nextInt(tmpPop.getMembers().size());
                while (parent1Index == parent2Index){
                    parent2Index = random.nextInt(tmpPop.getMembers().size());
                }
            }

        }
    }
    public static void main(String[] args) {
        roulette(8, 1, false);


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