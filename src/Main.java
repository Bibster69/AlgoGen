import java.util.*;

public class Main {

    public static Specimen roulette(int populationSize, int numOfGenerations, boolean twoPointInheritance){
        Random random = new Random();
        int bestPossibleGrade = (25 * 10) + (25 * 99);
        Population population = new Population(populationSize, 1, true);
        List<Double> slices = new ArrayList<>();
        double upperBoundSum = 0;
        for(int i = 0; i < population.getMembers().size(); i++){
            double upperBound = (population.getMembers().get(i).getGrade() / population.getPopulationGrade()) * 100;
            upperBoundSum += Math.abs(upperBound);
            slices.add(upperBoundSum);
        }

        for(int generationNumber = 0; generationNumber < numOfGenerations - 1; generationNumber++) {
            int genNum = generationNumber + 2;
            Population tmpPop = new Population(populationSize, genNum , false);
            for (int j = 0; j < populationSize; j++) {
                double randomSpecimen = 0 + (upperBoundSum - 0) * random.nextDouble();
                //System.out.println("random specimen" + randomSpecimen);
                for (int z = 0; z < slices.size(); z++) {
                    if (randomSpecimen <= slices.get(z) && !tmpPop.getMembers().contains(population.getMembers().get(z))) {
                       //System.out.println("tmpPop member");
//                        population.getMembers().get(z).print();
                        tmpPop.addMember(population.getMembers().get(z));
                        break;
                    }
                }
            }
            int tmpPopSize = tmpPop.getMembers().size();
            //System.out.println("Tmppopsize: " + tmpPopSize);
            int parent1Index = random.nextInt((tmpPopSize));
            int parent2Index = 0;
            if (parent1Index == tmpPopSize-1){
                parent2Index = parent1Index - 1;
            } else {
                parent2Index = parent1Index + 1;
            }
            Children children = null;
            if(twoPointInheritance) {
                int mutPoint1 = random.nextInt((25 - 5) + 1) + 5;
                int mutPoint2 = mutPoint1 + random.nextInt(20);
                children = new Children(tmpPop.getMembers().get(parent1Index), tmpPop.getMembers().get(parent2Index), mutPoint1, mutPoint2, tmpPopSize, genNum);
            } else if (!twoPointInheritance) {
                children = new Children(tmpPop.getMembers().get(parent1Index), tmpPop.getMembers().get(parent2Index), random.nextInt((25 - 5) + 1) + 5, tmpPopSize, genNum);
            }
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
            population = tmpPop;
            population.setBestSpecimen();
            if(population.getBestSpecimen().getGrade() == bestPossibleGrade){
                return population.getBestSpecimen();
            }
        }
        population.setBestSpecimen();
        return population.getBestSpecimen();

    }

    public static Specimen tournament(int populationSize, int numOfGenerations, boolean twoPointInheritance){
        Random random = new Random();
        int bestPossibleGrade = (25 * 10) + (25 * 99);
        Population population = new Population(populationSize, 1, true);
        int teamSize = random.nextInt(((populationSize / 2) - 2) + 1) + 2;
        int teamCount = random.nextInt((10 - 2) + 1) + 2;
        int hottiesCounter = 0;
        ArrayList<Specimen> parents = new ArrayList<>();
        for(int generationNumber = 0; generationNumber < numOfGenerations - 1; generationNumber++){
            int genNum = generationNumber + 2;
            Specimen[] winners = new Specimen[teamCount];
            for (int i = 0; i < teamCount; i++) {
                double winningGrade = 0;
                for (int j = 0; j < teamSize; j++) {
                    if (population.getMembers().get(j).getGrade() > winningGrade){
                        winningGrade = population.getMembers().get(j).getGrade();
                        winners[i] = population.getMembers().get(j);
                    }
                }
            }

            Specimen hottie = winners[0];
            for(int i = 1; i < winners.length; i++){
                if (winners[i].getGrade() > hottie.getGrade()){
                    hottie = winners[i];
                }
            }
            parents.add(hottie);
            hottiesCounter++;
            if(hottiesCounter == 2){

            }
        }
    }
    public static void main(String[] args) {
        Specimen test = roulette(8, 6, true);
        test.print();


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