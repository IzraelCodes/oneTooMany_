package oneTwoMany;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
public class CreatorChartPanel extends JPanel {

	private ArrayList<Creators> creatorList;
	
	public CreatorChartPanel(ArrayList<Creators> creatorList) {
		this.creatorList = creatorList;
		
		setBackground(Color.white);
		
		setPreferredSize(new Dimension(700,350));
	}
	public void setCreatorList(ArrayList<Creators> creatorList) {
		this.creatorList = creatorList;
		
		revalidate();
		repaint();
		
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(700, Math.max(350,100 + creatorList.size()* 75));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2 = (Graphics2D) g.create();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //GOOGLE
		
		g2.setColor(new Color(30,41,59));
		g2.setFont(new Font("Ariel", Font.BOLD, 20));
		g2.drawString("Average Views per Creator",25,35);
		
		// Check the creator list if its empty
		
		if(creatorList.isEmpty()) {
			g2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			g2.drawString("No creator data", 25, 75);
			g2.dispose(); return;
			
		}
		
		// looks for the highest viewer count
		double maxViews = 0;
		
		for(Creators creator : creatorList) {
			if(creator.getAverageViews() > maxViews) {
				maxViews = creator.getAverageViews();
			}
		}
		//Chart dimensions
		
		int startX = 25;
		int startY = 85;
		
		int barHeight = 25;
		int spacing = 75;
		
		int maxBarWidth = Math.max(0,getWidth()- 50);
		
		//makes each creator's bar
		
		for(Creators creator :creatorList) {
			double averageViews = creator.getAverageViews();
			int barWidth = 0;
			if(maxViews > 0) {
				barWidth = (int) ((averageViews / maxViews)*maxBarWidth);
			}
			
			//Creators names
			g2.setFont(new Font("Segue UI", Font.BOLD,14));
			g2.setColor(new Color(30,41,59));
			g2.drawString(creator.getName(), startX, startY);
			
			//Avg View Label
			
			String viewsText = NumberFormatter.formatNumber(averageViews);
			int textWidth = g2.getFontMetrics().stringWidth(viewsText);
			
			g2.drawString(viewsText, getWidth() - textWidth - 25, startY);
			
			//Background for the bar
			
			g2.setColor(new Color(226, 232, 240));
			g2.fillRoundRect(startX, startY + 10, maxBarWidth, barHeight, 12, 12);
			
			// actual bar
			
			g2.setColor(new Color(37,99,235));
			g2.fillRoundRect(startX, startY+10, barWidth, barHeight, 12, 12);
			
			startY += spacing;
			
		}
		g2.dispose();
		
	}
}
