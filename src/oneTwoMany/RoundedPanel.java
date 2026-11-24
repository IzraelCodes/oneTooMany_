package oneTwoMany;
import java.awt.*;
import javax.swing.*;
public class RoundedPanel extends JPanel{
private int radius;

public RoundedPanel(int radius) {
	this.radius = radius;
	setOpaque(false);
	}
@Override
protected void paintComponent(Graphics g) {
	
	Graphics2D g2 = (Graphics2D) g.create();
	
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g2.setColor(getBackground());
	
	g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
	
	g2.setColor(new Color(220, 225, 233));

	g2.drawRoundRect(
	    0, 0,
	    getWidth() - 1,
	    getHeight() - 1,
	    radius, radius
	    );
	g2.dispose();
	
	super.paintComponent(g);
	}
public RoundedPanel(int radius, LayoutManager layout) {
	super(layout);
	this.radius=radius;
	setOpaque(false);
}
}
