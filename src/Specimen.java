import java.util.ArrayList;
import java.util.Random;

public class Specimen {
    public int grade;
    public ArrayList<Integer> chromosom;

    public int getGrade() {
        return grade;
    }

    public ArrayList<Integer> getChromosom() {
        return chromosom;
    }

    public Specimen(Boolean empty){
        Boolean dummy = empty;
        this.chromosom = new ArrayList<>();
        this.grade = 0;
    }

    public void countGrade(){
        for(int i = 0; i < 50; i++){
            if (i % 2 == 0){
                this.grade += this.chromosom.get(i);
            }
        }
    }
    public Specimen() {
        Random random = new Random();
        this.chromosom = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            int gene = (random.nextInt((99 - 10) + 1) + 10);
            if (i % 2 == 0){
                this.grade += gene;
            }
            this.chromosom.add(gene);
        }
    }


    public static ArrayList<Specimen> generatePopulation(int populationSize){
        ArrayList<Specimen> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(new Specimen());
        }
        return population;
    }
}
