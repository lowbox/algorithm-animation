package low.client;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import low.binarytree.DisplayBinary;
import low.bubble.BubbleSortUI;
import low.insert.InsertSortUI;
import low.selection.SelectionSortUI;

public class AnimationClient extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton bubbleButton;
	private JButton insertButton;
	private JButton selectButton;
	private JButton btreeButton;
	
	public static void main(String[] args) {
		AnimationClient ac = new AnimationClient();
		ac.addNotify();
	}
	
	
	
	
	
	public AnimationClient(){
		super.setTitle("算法动态演示");
		super.setSize(450,200);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(null);
		bubbleButton = new JButton("冒泡排序");
		insertButton = new JButton("插入排序");
		selectButton = new JButton("选择排序");
		btreeButton = new JButton("二叉树插入与删除");
		
		bubbleButton.setBounds(new Rectangle(50,50,150,30));
		insertButton.setBounds(new Rectangle(250,50,150,30));
		selectButton.setBounds(new Rectangle(50,100,150,30));
		btreeButton.setBounds(new Rectangle(250,100,150,30));
		
		this.add(bubbleButton);
		this.add(insertButton);
		this.add(selectButton);
		this.add(btreeButton);

		bubbleButton.addActionListener(new actionListener());
		insertButton.addActionListener(new actionListener());
		selectButton.addActionListener(new actionListener());
		btreeButton.addActionListener(new actionListener());
	}
	
	private class actionListener implements ActionListener{

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(bubbleButton)){
				BubbleSortUI ui = new BubbleSortUI();
				ui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ui.show();
			}else if(e.getSource().equals(insertButton)){
				InsertSortUI ui = new InsertSortUI();
				ui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ui.show();
			}else if(e.getSource().equals(selectButton)){
				SelectionSortUI ui = new SelectionSortUI();
				ui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ui.show();
			}else{
				DisplayBinary ui = new DisplayBinary();
				ui.start();
			}
			
		}
		
	}
	
}


