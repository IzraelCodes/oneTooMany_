package oneTwoMany;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.*;
public class RoundedButton extends JButton{
	
	private Color buttonColor;
	private int radius = 20;
	
	public RoundedButton(String text, Color buttonColor) {
		super(text);
		this.buttonColor = buttonColor;
		
		setForeground(Color.white);
		setFont(new Font("Arial", Font.BOLD,13));
		
		setContentAreaFilled(false);
		setBorderPainted(false);
		setOpaque(false);
		
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//Change the color when its pressed or hovered over
		
		if (getModel().isPressed()) {
			g2.setColor(buttonColor.darker().darker());

        } else if (getModel().isRollover()) {

            g2.setColor(buttonColor.darker());
		} else {
			g2.setColor(buttonColor);
		}
		g2.fillRoundRect(
                0, 0, getWidth(), getHeight(), radius, radius);

        g2.dispose();

        super.paintComponent(g);
	}
}
