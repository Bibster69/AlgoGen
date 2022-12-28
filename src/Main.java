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
        List<Double> slices = new ArrayList<>();
        double upperBoundSum = 0;
        for(int i = 0; i < population.getMembers().size(); i++){
            double upperBound = (population.getMembers().get(i).getGrade() / population.getPopulationGrade()) * 100;
            upperBoundSum += Math.abs(upperBound);
            slices.add(upperBoundSum);
        }

        population.print(1);
        System.out.println("Best osobnik: ");

        population.getBestSpecimen().print();
        System.out.println("slices:");


        System.out.println("UpperBoundSum:");
        System.out.println(upperBoundSum);
        for(int generationNumber = 0; generationNumber < numOfGenerations - 1; generationNumber++) {
            Population tmpPop = new Population(populationSize, generationNumber+2, false);
            for (int j = 0; j < populationSize; j++) {
                double randomSpecimen = 0 + (upperBoundSum - 0) * random.nextDouble();
                System.out.println("random specimen" + randomSpecimen);
                for (int z = 0; z < slices.size(); z++) {
                    if (randomSpecimen <= slices.get(z) && !tmpPop.getMembers().contains(population.getMembers().get(z))) {
                        System.out.println("tmpPop member");
                        population.getMembers().get(z).print();
                        tmpPop.addMember(population.getMembers().get(z));
                        break;
                    }
                }
            }
            int tmpPopSize = tmpPop.getMembers().size();
            System.out.println("Tmppopsize: " + tmpPopSize);
            int parent1Index = random.nextInt((tmpPopSize));
            int parent2Index = 0;
            if (parent1Index == tmpPopSize-1){
                parent2Index = parent1Index - 1;
            } else {
                parent2Index = parent1Index + 1;
            }
            Children children = new Children(tmpPop.getMembers().get(parent1Index), tmpPop.getMembers().get(parent2Index), random.nextInt((25 - 5) + 1) + 5, populationSize, generationNumber);
            if (populationSize - tmpPopSize > 2){
                tmpPop.addMember(children.child1);
                tmpPop.addMember(children.child2);
                tmpPopSize = tmpPop.getMembers().size();
                while (tmpPopSize < populationSize) {
                    tmpPop.generateMember(generationNumber, tmpPopSize);
                    tmpPopSize++;
                }
            } else if (populationSize - tmpPopSize == 2) {
                tmpPop.addMember(children.child1);
                tmpPop.addMember(children.child2);
            } else if (populationSize - tmpPopSize == 1) {
                tmpPop.addMember(children.child1);
                for (Specimen specimen : tmpPop.getMembers()){
                    if(specimen.getGrade() <= children.child2.getGrade()){
                        specimen = children.child2;
                        break;
                    }
                }
            } else if (populationSize  == tmpPopSize) {
                for (Specimen specimen : tmpPop.getMembers()){
                    if(specimen.getGrade() <= children.child1.getGrade()){
                        specimen = children.child1;
                        break;
                    }
                }
                for (Specimen specimen : tmpPop.getMembers()){
                    if(specimen.getGrade() <= children.child2.getGrade() && specimen.getGrade() != children.child1.getGrade()){
                        specimen = children.child2;
                        break;
                    }
                }
            }
            System.out.println("parent1 index " + parent1Index);
            System.out.println("parent2 index " + parent2Index);
        }
    }
    public static void main(String[] args) {
        roulette(8, 2, false);


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