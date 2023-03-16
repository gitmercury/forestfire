import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class Graph extends JPanel{
    public int[][] cases;
    private int l;
    private int h;

    public Graph(int[][] cases, int l, int h){
        super();
        this.cases=cases;
        this.l=l;this.h=h;
        this.setSize(600,600);
    }

    protected void paintComponent(Graphics g) {
        int size;
        if (this.l>this.h){
            size=600/l;
        }
        else{
            size=600/h;
        }
    
        for (int i=0;i<this.l;i++){
            for (int j=0;j<this.h;j++){
                if (this.cases[i][j]==0){
                    g.setColor(Color.green);
                }
                else if (this.cases[i][j]==1){
                    g.setColor(Color.orange);
                }
                else {
                    g.setColor(Color.black);
                }
                g.fillRect((i+1)*size, (j+1)*size, size, size);
            }
        }
    }

    public void setCases(int[][] cases) {
        this.cases=cases;
    }
        
}