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
public class MidpointEllipseDrawing extends JFrame{
    DataTable data = new DataTable(Integer.class, Integer.class);
    
    public MidpointEllipseDrawing(int xCentre, int yCentre, int Rx, int Ry){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        int Rx2 = Rx*Rx;
        int Ry2 = Ry*Ry;
        int twoRx2 = 2*Rx2;
        int twoRy2 = 2*Ry2;
        float p;
        int x = 0;
        int y = Ry;
        int px = 0;
        int py = twoRx2 * y;
        
        ellipsePlotPoints(xCentre, yCentre, x, y);
        
        //Region 1
        p = Math.round(Ry2-(Rx2*Ry)+(0.25*Rx2));
        while(px<py){
            x++;
            px += twoRy2;
            if(p<0)
                p += Ry2 + px;
            else{
                y--;
                py -= twoRx2;
                p += Ry2 + px - py;
            }
            ellipsePlotPoints(xCentre, yCentre, x, y);
        }
        
        //Region 2
        p = Math.round(Ry2*(x+0.5) * (x+0.5)+ Rx2*(y-1) * (y- 1 ) - Rx2*Ry2);
        while (y > 0 ) {
            y--;
            py -= twoRx2;
            if (p > 0)
                p += Rx2 - py;
            else {
                x++;
                px += twoRy2;
                p += Rx2 - py + px;
            }
            ellipsePlotPoints(xCentre, yCentre, x, y);
        }
        
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderer(data, lines);
        Color color = new Color(0.0f, 0.3f, 1.0f);
        plot.getPointRenderer(data).setColor(color);
        plot.getLineRenderer(data).setColor(color);
    }
    
    public void ellipsePlotPoints(int xCentre, int yCentre, int x, int y){
        data.add(xCentre+x, yCentre+y);
        data.add(xCentre-x, yCentre+y);
        data.add(xCentre+x, yCentre-y);
        data.add(xCentre-x, yCentre-y);
    }
    
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter xCentre: ");
        int xCentre = in.nextInt();
        System.out.print("Enter yCentre: ");
        int yCentre = in.nextInt();
        System.out.print("Enter Rx: ");
        int rx = in.nextInt();
        System.out.print("Enter Ry: ");
        int ry = in.nextInt();
        
        MidpointEllipseDrawing myframe = new MidpointEllipseDrawing(xCentre, yCentre, rx, ry);
        myframe.setVisible(true);
    }
    
}
