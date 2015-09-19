package low.insert;

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

public class InsertLogic extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String[] code = {
			"public static void insertSort(int[] a){ 			",
			"    for(int i = 1; i < a.length; i++){				", 		
			"       int key = a[i];								",	
			"       int position = i;							",
			"       while(position > 0 && a[position -1]>key){  ",
			"           a[position] = a[position - 1]; 			",
			"           position--;								",
			"       }											",
			"       a[position] = key;							",
			"   }												",
			"}													"	
			};		

	private List<RectangleNum> numbers = initialNum();
	private boolean completed = false;
	private int outerLoop = 1;
	private int innerLoop = 0;
	private int selectedIndex = 1;
	private int temp = 0;
	
	private JList codeList = new JList(code);
	public TimerAction timerAction;
	public Timer timer;
	public InsertSortUI ui;

	public InsertLogic(InsertSortUI logic) {
		timerAction = new TimerAction();
		timer = new Timer(1000, timerAction);
		
		codeList.setSelectedIndex(1);
		JScrollPane scrollPane = new JScrollPane(codeList);
		scrollPane.setLocation(200, 300);
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.EAST);
		this.ui = logic;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < 10; i++) {
			numbers.get(i).draw(g2, null, -1);
		}
		numbers.get(12).draw(g2, "key = ", numbers.get(12).getValue());
		numbers.get(14).draw(g2, "position : ", numbers.get(14).getValue());
	}
	
	public void initSort(){
		outerLoop = 1;
		innerLoop = 0;
		selectedIndex = 1;
		temp = 0;
		completed = false;
	}
	
	private class TimerAction implements ActionListener, Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (isCompleted()) {
				return;
			}

			switch (selectedIndex) {
			case 1:
				if (outerLoop < 10) {
					codeList.setSelectedIndex(selectedIndex++);
				} else {
					selectedIndex = 10;
				}
				break;
			case 2:
				temp = numbers.get(outerLoop).getValue();
				// get something
				numbers.get(12).setValue(numbers.get(outerLoop).getValue());
				numbers.get(12).setColor(Color.BLACK);
				codeList.setSelectedIndex(selectedIndex++);
				break;
			case 3:
				innerLoop = outerLoop;
				numbers.get(14).setValue(outerLoop+1);
				codeList.setSelectedIndex(selectedIndex++);
				break;
			case 4:
				if(innerLoop > 0 && temp < numbers.get(innerLoop - 1).getValue()){
					codeList.setSelectedIndex(selectedIndex++);
				}else{
					codeList.setSelectedIndex(selectedIndex++);
					selectedIndex = 8;
				}
				break;
			case 5:
				codeList.setSelectedIndex(selectedIndex++);
				numbers.get(innerLoop).setColor(Color.RED);
				numbers.get(innerLoop).setValue(numbers.get(innerLoop-1).getValue());
				//numbers.get(innerLoop-1).setValue(0);
				break;
			case 6:
				codeList.setSelectedIndex(selectedIndex);
				numbers.get(innerLoop).setColor(Color.GREEN);
				innerLoop--;
				numbers.get(14).setValue(numbers.get(14).getValue()-1);
				selectedIndex = 4;
				break;
			case 8:
				codeList.setSelectedIndex(selectedIndex);
				numbers.get(innerLoop).setValue(temp);
				numbers.get(innerLoop).setColor(Color.green);
				selectedIndex = 1;
				outerLoop++;
				break;
			case 10:
				if(selectedIndex == 10){
					setCompleted(true);
					ui.freshButton.setEnabled(true);
					codeList.setSelectedIndex(selectedIndex);
					numbers.get(12).setValue(0);
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
		int k = 1;
		for (int i = 1; i <= 100; i++){
			arr[i] = 0;
		}
		Random random = new Random();
		for (int i = 1; i <= 1000; i++) {
			int v = random.nextInt(20) + 1;
			if(arr[v] == 0 && v < 17)
			{
				list.add(new RectangleNum(k++, 1, v, 1, Color.GREEN));
				arr[v] = 1;
			}
			
		}
		list.get(12).setValue(0);
		list.get(12).setType(2);
		list.get(14).setValue(1);
		list.get(14).setType(3);
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
