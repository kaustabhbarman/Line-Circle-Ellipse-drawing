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
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author kaustabh
 */
public class MidpointCircleAlgo extends JFrame {
    DataTable data = new DataTable(Integer.class, Integer.class);
    public MidpointCircleAlgo(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        Scanner in = new Scanner(System.in);
        System.out.print("Enter x coordinate of centre: ");
        int X = in.nextInt();
        System.out.print("Enter y coordinate of centre: ");
        int Y = in.nextInt();
        System.out.print("Enter radius of circle: ");
        int R = in.nextInt();
        
        int P = 1 - R;
        int x=0, y = R;
        
        plotPoints(X, Y, x, y);
        
        while (x < y) {
            x++ ;
            if (P < 0)
                P = P + 2*x + 1;
            else{
                y--;
                P = P + 2 *(x - Y) + 1;
            }
            plotPoints(X, Y, x, y);
        }
        
        
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderer(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);
        plot.getPointRenderer(data).setColor(color);
        plot.getLineRenderer(data).setColor(color);
        
    }
    
    public void plotPoints(int Xcenter, int Ycenter, int x, int y){
       data.add(Xcenter + x, Ycenter + y ) ;
        data.add (Xcenter - x, Ycenter + y);
        data.add (Xcenter + x, Ycenter - y);
        data.add (Xcenter - x, Ycenter - y ) ;
        data.add (Xcenter + y, Ycenter + x);
        data.add (Xcenter - y , Ycenter + x);
        data.add (Xcenter + y , Ycenter - x);
        data.add (Xcenter - y , Ycenter - x);
    }
    public static void main(String[] args){
        MidpointCircleAlgo myframe = new MidpointCircleAlgo();
        myframe.setVisible(true);
    }
    
}
