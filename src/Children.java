public class Children {
    public Specimen child1;
    public Specimen child2;

    public Specimen getChild1() {
        return child1;
    }

    public void setChild1(Specimen child1) {
        this.child1 = child1;
    }

    public Specimen getChild2() {
        return child2;
    }

    public void setChild2(Specimen child2) {
        this.child2 = child2;
    }

    /**
     * Krzyżowania zrealizowane jako przeładowane konstruktory,
     * w zależności od ilości parametrów zwracają obiekt potomstwa
     * z krzyżowania jedno lub dwu punktowego
     */

    public Children(Specimen parent1, Specimen parent2, int mutationPoint, int specimenrNumber, int generationNumber){
        if (mutationPoint >= 48 || mutationPoint > 25) {
            System.out.println("Ograniczony zakres to np. dla chromosomu 111000 nie możemy wyciąć dwóch ostatnich oraz\n" +
                    "//wycięty fragment nie może być większy niż połowa wielkości chromosomu");
            return;
        }
        this.child1 = new Specimen(true, "Skrzyrzowany osobnik nr " + specimenrNumber + " z pokolenia " + generationNumber);
        this.child2 = new Specimen(true, "Skrzyrzowany osobnik nr " + Integer.toString(specimenrNumber + 1) + " z pokolenia " + generationNumber);
        for (int i = 0; i < mutationPoint; i++){
            this.child1.chromosom.add(parent1.chromosom.get(i));
            this.child2.chromosom.add(parent2.chromosom.get(i));
        }
        for (int i = mutationPoint; i < 50; i++){
            this.child1.chromosom.add(parent2.chromosom.get(i));
            this.child2.chromosom.add(parent1.chromosom.get(i));
        }
        this.child1.countGrade();
        this.child2.countGrade();
    }

    public Children(Specimen parent1, Specimen parent2, int mutationPoint1, int mutationPoint2, int specimenrNumber, int generationNumber){

        if ((mutationPoint1 >= 48) || (mutationPoint1 > 25) || (mutationPoint2 >= 48) || (Math.abs(mutationPoint1 - mutationPoint2) > 25)) {
            System.out.println("Ograniczony zakres to np. dla chromosomu 111000 nie możemy wyciąć dwóch ostatnich oraz\n" +
                    "//wycięty fragment nie może być większy niż połowa wielkości chromosomu");
            return;
        }

        if (mutationPoint1 > mutationPoint2){
            int tmp = mutationPoint1;
            mutationPoint1 = mutationPoint2;
            mutationPoint2 = tmp;
        }

        this.child1 = new Specimen(true, "Skrzyrzowany osobnik nr " + specimenrNumber + " z pokolenia " + generationNumber);
        this.child2 = new Specimen(true, "Skrzyrzowany osobnik nr " + Integer.toString(specimenrNumber + 1) + " z pokolenia " + generationNumber);
        for (int i = 0; i < mutationPoint1; i++){
            this.child1.chromosom.add(parent1.chromosom.get(i));
            this.child2.chromosom.add(parent2.chromosom.get(i));
        }
        for (int i = mutationPoint1; i < mutationPoint2; i++){
            this.child1.chromosom.add(parent2.chromosom.get(i));
            this.child2.chromosom.add(parent1.chromosom.get(i));
        }
        for (int i = mutationPoint2; i < 50; i++){
            this.child1.chromosom.add(parent1.chromosom.get(i));
            this.child2.chromosom.add(parent2.chromosom.get(i));
        }
        this.child1.countGrade();
        this.child2.countGrade();
    }
}
