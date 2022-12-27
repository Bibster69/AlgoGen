import java.util.ArrayList;
import java.util.Random;

public class Specimen {
    public String name;
    public Double grade = 0.0;

    public String getName() {
        return name;
    }

    public ArrayList<Integer> chromosom;

    public Double getGrade() {
        return grade;
    }

    public ArrayList<Integer> getChromosom() {
        return chromosom;
    }

    public Specimen(Boolean empty, String name){
        this.name = name;
        Boolean dummy = empty;
        this.chromosom = new ArrayList<>();
    }

    public void countGrade(){
        for(int i = 0; i < 50; i++){
            if (i % 2 == 0){
                this.grade += this.chromosom.get(i);
            } else {
                this.grade -= this.chromosom.get(i);
            }
        }
        if (this.grade < 0) {
            this.grade = Math.abs(this.grade/100.0);
        }
    }
    public Specimen(String name) {
        Random random = new Random();
        this.name = name;
        this.chromosom = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            int gene = (random.nextInt((99 - 10) + 1) + 10);
            if (i % 2 == 0){
                this.grade += gene;
            } else {
                this.grade -= gene;
            }
            this.chromosom.add(gene);
        }
        if (this.grade < 0) {
            this.grade = Math.abs(this.grade/100.0);
        }
    }

    public void print(){
        System.out.println("osobnik + " + this.name);
        System.out.println("Grade: " + this.grade);
    }

}
