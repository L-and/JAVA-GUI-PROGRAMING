package graphic;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

class BarGraphs extends JPanel{
	int[] y;
	Color color;
	JCheckBox cb;
	BarGraphs(int[] y,Color color,JCheckBox cb){
		this.y=y;
		this.color=color;
		this.cb=cb;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D) g;
		g2.setColor(color);
		drawGraph(g2,y,cb);
	}

	void drawGraph(Graphics2D g2,int[] y,JCheckBox cb){
		int x=10;
		for(int i=0;i<y.length;i++){
			Shape shape=new Rectangle2D.Double(x+i*70, 250-y[i]*2, 50, y[i]*2);
			if(i==y.length-1)
				g2.setColor(Color.CYAN);
			g2.fill(shape);
			if(cb.isSelected())
				g2.drawString(Integer.toString(y[i]), x+i*70+15, 245-y[i]*2);
		}
	}
}
