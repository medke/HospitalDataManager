import javax.swing.JOptionPane;
import javax.swing.JWindow;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
 
 
public class Splashscr extends JWindow
{
 
Image img=Toolkit.getDefaultToolkit().getImage(".//splash.png");
 
ImageIcon imgicon=new ImageIcon(img);
 
	public Splashscr()
	{
		try
		{
     
			setSize(633,400);
			setLocationRelativeTo(null);
			show();
			Thread.sleep(2500);
			dispose();
		}
		catch(Exception exception)
		{
			JOptionPane.showMessageDialog(
					null,"Error"+exception.getMessage(), "Error:",
					JOptionPane.DEFAULT_OPTION);
		}
	}
 
public void paint(Graphics g)
{
g.drawImage(img,0,0,this);
}
 

}


