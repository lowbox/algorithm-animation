package low.selection;

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


public class SelectionLogic extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String[] code = {
			"public void selectionSort(int[] a){	        ",
			"   int min = 0;								",
			"   for(int i = 0; i < a.length; i++){			",
			"       min = i;								",
			"       for(int j = i+1; j<a.length; j++){		",
			"           if(a[i] > a[j])						",
			"               min = j;						",
			"       }										",
			"       if(min != i){							",
			"           int t = a[i];						",	
			"           a[i] = a[min];						",
			"           a[min] = t;							",
			"       }										",
			"   }											",
			"}												"
		};		

	private List<RectangleNum> numbers = initialNum();
	private int outerLoop = 0;
	private int innerLoop = 0;
	private int selectedIndex = 3;
	private int minIndex = 0;
	private boolean completed = false;
	private int preMin = 0;
	private JList codeList = new JList(code);

	public TimerAction timerAction;
	public Timer timer;
	public SelectionSortUI ui;

	public SelectionLogic(SelectionSortUI logic) {
		timerAction = new TimerAction();
		timer = new Timer(1000, timerAction);
		
		codeList.setSelectedIndex(1);
		JScrollPane scrollPane = new JScrollPane(codeList);
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.EAST);
		this.ui = logic;
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < 10; i++) {
			numbers.get(i).draw(g2, null);
		} 
		numbers.get(12).draw(g2, "min = ");
	}

	public void initSort(){
		completed = false;
		outerLoop = 0;
		innerLoop = 0;
		selectedIndex = 3;
		minIndex = 0;
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
			case 3:
				if(outerLoop < 9){
					codeList.setSelectedIndex(selectedIndex++);
					numbers.get(outerLoop).setColor(Color.RED);
					innerLoop = outerLoop + 1;
					minIndex = outerLoop;
					numbers.get(12).setValue(minIndex);
				}else{
					selectedIndex = 14;
				}
				break;
			case 4:
				codeList.setSelectedIndex(selectedIndex++);
				break;
			case 5:
				if (innerLoop < 10) {
					codeList.setSelectedIndex(selectedIndex++);
					numbers.get(innerLoop).setColor(Color.BLUE);
				} else {
					selectedIndex = 10;
				}
				break;
			case 6:
				numbers.get(innerLoop).setColor(Color.GREEN);
				if(numbers.get(minIndex).getValue() > numbers.get(innerLoop).getValue()){
					codeList.setSelectedIndex(selectedIndex++);
				}else{
					codeList.setSelectedIndex(selectedIndex++);
					innerLoop++;
					selectedIndex = 5;
				}
				break;
			case 7:
				preMin = minIndex;
				minIndex = innerLoop;
				numbers.get(12).setValue(minIndex);
				numbers.get(minIndex).setColor(Color.BLACK);
				if(numbers.get(preMin).getColor() == Color.BLACK)
					numbers.get(preMin).setColor(Color.GREEN);
				innerLoop++;
				selectedIndex = 5;
				break;
			case 10:
				codeList.setSelectedIndex(selectedIndex++);
				break;
			case 11:
				codeList.setSelectedIndex(selectedIndex++);
				break;
			case 12:
				numbers.get(innerLoop - 1).setColor(Color.GREEN);
				codeList.setSelectedIndex(selectedIndex);
				int t1 = numbers.get(outerLoop).getValue();
				int t2 = numbers.get(minIndex).getValue();
				numbers.get(outerLoop).setValue(t2);
				numbers.get(minIndex).setValue(t1);
				numbers.get(outerLoop).setColor(Color.GREEN);
				numbers.get(minIndex).setColor(Color.GREEN);
				selectedIndex = 3;
				outerLoop++;
				break;	
			case 14:
				completed = true;
				setCompleted(true);
				ui.freshButton.setEnabled(true);
				codeList.setSelectedIndex(selectedIndex);
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
			int v = random.nextInt(20) + 1;
			if(arr[v] == 0 && v < 17){
				list.add(new RectangleNum(k++, 1, v, Color.GREEN));
				arr[v] = 1;
			}
			
		}
		
		list.get(12).setValue(0);
		
		return list;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public List<RectangleNum> getNumbers() {
		return numbers;
	}
	public void setNumbers(List<RectangleNum> numbers) {
		this.numbers = numbers;
	}

}
