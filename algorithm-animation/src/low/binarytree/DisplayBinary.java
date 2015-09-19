package low.binarytree;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class DisplayBinary extends JApplet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DisplayBinary(){
		add(new BinaryUI(new BinaryTree<Integer>()));
	}
	
	public void start(){
		JFrame jFrame = new JFrame("二叉树插入与删除");
		jFrame.add(new DisplayBinary());
		jFrame.setSize(800,600);
		//jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
	
}
