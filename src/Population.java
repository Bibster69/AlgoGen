import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Population {
    public ArrayList<Specimen> members;
    public Specimen bestSpecimen;
    public double populationGrade;

    public ArrayList<Specimen> getMembers() {
        return members;
    }

    public Specimen getBestSpecimen() {
        return bestSpecimen;
    }

    public double getPopulationGrade() {
        return populationGrade;
    }

    public Population(int populationSize, int generationNumber, boolean fill) {
        this.members = new ArrayList<>();
        if(fill){
            double maxGrade = 0;
            for (int i = 0; i < populationSize; i++) {
                Specimen tmp = new Specimen("Pokolenie " + Integer.toString(generationNumber) + " osobnik " + Integer.toString(i+1));
                if (tmp.getGrade() > maxGrade){
                    this.bestSpecimen = tmp;
                    maxGrade = tmp.getGrade();
                }
                this.members.add(tmp);
                this.populationGrade += tmp.getGrade();
            }
        } else {
            this.bestSpecimen = null;
            this.populationGrade = 0;
        }
    }

    public void addMember(Specimen member){
        this.members.add(member);
        System.out.println("dodawanie osobnika");
        //setBestSpecimen();
    }

    public void generateMember(int generationNumber, int specimenNumber){
        this.members.add(new Specimen("Pokolenie " + Integer.toString(generationNumber) + " osobnik " + Integer.toString(specimenNumber+1)));
    }



    public void GradePopulation(){
        for (Specimen specimen : this.members){
            this.populationGrade += specimen.getGrade();
        }
    }

    public void setBestSpecimen(){
        double bestGrade = 0;
        for (Specimen specimen : this.members){
            if (specimen.getGrade() > bestGrade) {
                this.bestSpecimen = specimen;
                bestGrade = specimen.getGrade();
            }
        }
    }

    public void print(int generationNumber){
        System.out.println("Pokolenie" + Integer.toString(generationNumber));
        for (Specimen specimen : this.members){
            specimen.print();
        }
    }

}
