/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dda.algo;

import java.util.Scanner;
import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
//suitable inputs - 12, 23, 15, 29

/**
 *
 * @author kaustabh
 */
public class DDAAlgo extends JFrame {
    public DDAAlgo(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
         Scanner in = new Scanner(System.in);
        DataTable data = new DataTable(Integer.class, Integer.class);
        System.out.print("Enter value of x1: ");
        int x1 = in.nextInt();
        System.out.print("Enter value of y1: ");
        int y1 = in.nextInt();
        System.out.print("Enter value of x2: ");
        int x2 = in.nextInt();
        System.out.print("Enter value of y2: ");
        int y2 = in.nextInt();
        
        float slope = (y2 - y1)/(x2 - x1);
        
        if(slope < 1){
        int x = x1;
        float y = y1;
        data.add(x,Math.round(y));
        int f = x2-x1;
        for (int i=0; i<f; i++){
            x++;
            y = y+slope; 
            data.add(x,Math.round(y));
        }
        }
        else if(slope > 1){
            float x = x1;
            int y = y1;
            data.add(Math.round(x),y);
            int f = y2-y1;
            for (int i=0; i<f; i++){
            y++;    
            x = x+(1/slope); 
            data.add(Math.round(x),y);
        }
        }
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderer(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);
        plot.getPointRenderer(data).setColor(color);
        plot.getLineRenderer(data).setColor(color);
    }

    /**
     * @param args the command line arguments
     */
    
   
    
    public static void main(String[] args) {
        // TODO code application logic here
       DDAAlgo myframe = new DDAAlgo();
       myframe.setVisible(true);
      
    }
    
    
    
}
