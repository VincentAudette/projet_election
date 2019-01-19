import javax.swing.UIManager;


/**
 * Programme pour suivre les élections du Québec en 2018
 * 
 * @author Pierre Bélisle
 * @student Vincent Audette
 * @version Copyright A2018
 */
public class DemarrerElection2018Etud{


	public static void main(String[] args) {

		preparerPourMac();
        new Thread(new CadreElection()).start();
							
	}
	
	/*
	 * Nécessaire pour le JOptionPane sur un Mac
	 */
	public static void preparerPourMac() {

		try {
			
	         UIManager.setLookAndFeel(
	        		 UIManager.getCrossPlatformLookAndFeelClassName());
	         
	      } catch (Exception e) {  
	         e.printStackTrace();
	      }
	}

}
