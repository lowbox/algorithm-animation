package low.bubble;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;


public class BubbleLogic extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String[] code = {
			"public static void bubbleSort(int[] a){ 			",
			"   for(int i = 0; i < a.length - 1; i++){			", 													
			"       for(int j = 0; j < a.length - i - 1; j++){	", 		
			"           if(a[j] > a[j + 1]){					", 			
			"               int t = a[j];						", 
			"               a[j] = a[j + 1];					", 		
			"               a[j + 1] = t;						", 
			"          }										", 						
			"      }											",
			"   }												",
			"}													"	
			};		

	private List<RectangleNum> numbers = initialNum();

	private boolean completed = false;
	private JList codeList = new JList(code);

	private int outerLoop = 0;
	private int innerLoop = 0;
	private int selectedIndex = 1;
	
	public TimerAction timerAction;
	public Timer timer;
	public BubbleSortUI ui;

	public BubbleLogic(BubbleSortUI logic) {
		timerAction = new TimerAction();
		timer = new Timer(1000, timerAction);
		
		codeList.setSelectedIndex(1);
		JScrollPane scrollPane = new JScrollPane(codeList);
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.EAST);
		this.ui = logic;
		
	}
	
	public void initSort(){
		completed = false;
		outerLoop = 0;
		innerLoop = 0;
		selectedIndex = 1; 
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < 20; i++) {
			numbers.get(i).draw(g2);
		}
	}


	private class TimerAction implements ActionListener, Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (completed) {
				return;
			}

			switch (selectedIndex) {
			case 1:
				if (outerLoop < 20) {
					innerLoop = 0;
					codeList.setSelectedIndex(selectedIndex++);
				} else {
					selectedIndex = 10;
				}
				break;
			case 2:
				if (innerLoop < 20 - outerLoop - 1) {
					numbers.get(innerLoop).setColor(Color.RED);
					numbers.get(innerLoop + 1).setColor(Color.BLUE);
					codeList.setSelectedIndex(selectedIndex++);
				} else {
					outerLoop++;
					selectedIndex = 1;
				}
				break;
			case 3:
				if (numbers.get(innerLoop).getValue() > numbers.get(
						innerLoop + 1).getValue()) {
					codeList.setSelectedIndex(selectedIndex++);
				} else {
					numbers.get(innerLoop).setColor(Color.GREEN);
					numbers.get(innerLoop + 1).setColor(Color.GREEN);
					innerLoop++;
					selectedIndex = 2;
				}
				break;
			case 4:
				codeList.setSelectedIndex(selectedIndex++);
				break;
			case 5:
				codeList.setSelectedIndex(selectedIndex++);
				break;
			case 6:
				codeList.setSelectedIndex(selectedIndex);
				int t1 = numbers.get(innerLoop).getValue();
				int t2 = numbers.get(innerLoop+1).getValue();
				numbers.get(innerLoop).setValue(t2);
				numbers.get(innerLoop+1).setValue(t1);
				numbers.get(innerLoop).setColor(Color.GREEN);
				numbers.get(innerLoop+1).setColor(Color.GREEN);
				selectedIndex = 2;
				innerLoop++;
				break;
			case 10:
				if(selectedIndex == 10){
					setCompleted(true);
					ui.freshButton.setEnabled(true);
					codeList.setSelectedIndex(selectedIndex);
				}
				break;
			default:
				break;
			}
			repaint();
		}

	}

	public List<RectangleNum> initialNum() {
		List<RectangleNum> list = new ArrayList<RectangleNum>();
		int[] arr = new int[101];
		for (int i = 1; i <= 100; i++){
			arr[i] = 0;
		}
		int k = 1;
		Random random = new Random();
		for (int i = 1; i <= 1000; i++) {
			int v = random.nextInt(30) + 1;
			if(arr[v] == 0 && v < 25){
				list.add(new RectangleNum(k++, 1, v, Color.GREEN));
				arr[v] = 1;
			}
			
		}
		return list;
	}

	public List<RectangleNum> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<RectangleNum> numbers) {
		this.numbers = numbers;
	}

	public boolean isCompleted() {
		if(completed)
			return true;
		return false;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
