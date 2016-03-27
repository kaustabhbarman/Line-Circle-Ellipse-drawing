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
public class BresenhamsAlgo extends JFrame{
    public BresenhamsAlgo(){
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
        
        int dy = y2-y1;
        int p = 2*dy-(x2-x1);
        data.add(x1,y1);
        System.out.println(x1+", "+y1);
        for(int x=x1; x<=x2; x++){
            if(p<0){
                System.out.println("sub zero p");
                data.add(x+1,y1);
                p = p+(2*dy);
            }
            else{
                System.out.println("not sub zero p");
                data.add(x+1, y1+1);
                p = p + (2*dy) - 2*(x2-x1);
                y1++;
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
    public static void main(String[] args){
        BresenhamsAlgo myframe = new BresenhamsAlgo();
        myframe.setVisible(true);
    }
}
