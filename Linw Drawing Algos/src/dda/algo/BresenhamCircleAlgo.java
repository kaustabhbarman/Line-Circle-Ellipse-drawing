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
public class BresenhamCircleAlgo extends JFrame {
    
    DataTable data = new DataTable(Integer.class, Integer.class);
    public BresenhamCircleAlgo(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        Scanner in = new Scanner(System.in);
        System.out.print("Enter x coordinate of centre: ");
        int X = in.nextInt();
        System.out.print("Enter y coordinate of centre: ");
        int Y = in.nextInt();
        System.out.print("Enter radius of circle: ");
        int R = in.nextInt();
        
        int P = 0;
        int Q = R;
        int D = 3-2*R;
        for (int i=X; i<Y; i++){
            drawCircle(X, Y, P, Q);
            P++;
            if(D<0)
                D = D + 4*X + 6;
            else {
                Y = Y + 1;
                D = D + 4*(X-Y) + 10;
            }
            drawCircle(X, Y, P, Q);
            X++;
        }
        
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderer(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);
        plot.getPointRenderer(data).setColor(color);
        plot.getLineRenderer(data).setColor(color);
    }
    
    public void drawCircle(int x,int y,int p,int q){
        data.add(x+p, y+q);
        data.add(x-p, y+q);
        data.add(x+p, y-q);
        data.add(x-p, y-q);
        data.add(x+q, y+x);
        data.add(x-p, y+x);
        data.add(x+q, y-x);
        data.add(x-q, y-x);
    }
    
    public static void main(String[] args){
        BresenhamCircleAlgo myframe = new BresenhamCircleAlgo();
        myframe.setVisible(true);
    }
    
}
