import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;


public class Simulation {

    
	private static ScheduledFuture<?> t; 
    int l,h;
    double probability;
    int [][] cases;
    Graph graph;
    int time=0;

    public Simulation(int l,int h, Coordinate [] startingFires, double probability) {
        this.l=l;
        this.h=h;
        this.probability=probability;
        this.cases= new int[l][h];
        for (int i=0;i<l;i++){
            for (int j=0;j<h;j++){
                this.cases[i][j]=0;
            }
        }
        for (Coordinate coord : startingFires){
            this.cases[coord.getX()][coord.getY()]=1;
        }
        this.graph = new Graph(this.cases, l, h);
        this.simulate();
    }
    
    public void simulate(){
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		t=executor.scheduleAtFixedRate(this.nextStep(), 0, 1000, TimeUnit.MILLISECONDS);
    }

    private Runnable nextStep() {
        int [][] copycases = this.cases.clone();
        boolean atLeastOne=false;
        for (int i=0;i<l;i++){
            for (int j=0;j<h;j++){
                if(copycases[i][j]==1){
                    if (!atLeastOne){atLeastOne=true;}
                    if (this.cases[i+1][j]==0 &&  new Random().nextDouble() < probability){
                        this.cases[i+1][j]=1;
                    }
                    if (this.cases[i-1][j]==0 &&  new Random().nextDouble() < probability){
                        this.cases[i-1][j]=1;
                    }
                    if (this.cases[i][j+1]==0 &&  new Random().nextDouble() < probability){
                        this.cases[i][j+1]=1;
                    }
                    if (this.cases[i][j-1]==0 &&  new Random().nextDouble() < probability){
                        this.cases[i][j-1]=1;
                    }
                    this.cases[i][j]=2;

                }
            }
        }
        if (!atLeastOne){
            JOptionPane.showMessageDialog(null, "End of simulation, no fires left");
        }
        this.graph.setCases(this.cases);
        this.graph.repaint();

        return null;

        
    }


}