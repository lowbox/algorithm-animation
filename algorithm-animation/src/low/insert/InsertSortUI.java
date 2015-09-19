package low.insert;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InsertSortUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Container contentpCane;
	

	private JButton stopButton = new JButton("暂停");
	public JButton startButton = new JButton("开始");
	public JButton freshButton = new JButton("随机生成数据");
	private JButton exitButton = new JButton("退出");
	private JTextField vField = new JTextField("速度： 3");
	private JSlider vSlider = new JSlider(1,5,3);
	private JPanel controlPanel = new JPanel();
	private InsertLogic logic;
	
	public InsertSortUI(){
		
		setTitle("插入排序");
		setSize(900,600);
		setResizable(false);

		controlPanel.add(freshButton);
		controlPanel.add(startButton);
		controlPanel.add(stopButton);
		controlPanel.add(exitButton);
		controlPanel.add(vSlider);
		controlPanel.add(vField);
		
		
		freshButton.addActionListener(new freshAction());
		stopButton.addActionListener(new StopAction());
		exitButton.addActionListener(new ExitAction());
		startButton.addActionListener(new StartSorting());
		vSlider.addChangeListener(new VAction());
		vSlider.setMajorTickSpacing(1);
		vSlider.setPaintTicks(true);
		vSlider.setPaintLabels(true);
		
		contentpCane = getContentPane();
		logic = new InsertLogic(this);
		logic.add(controlPanel, BorderLayout.PAGE_END);
		contentpCane.add(logic);
		stopButton.setEnabled(false);
		startButton.setEnabled(true);
	}
	
	private class VAction implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			int v = ((JSlider) e.getSource()).getValue();
			vField.setText("速度: " + v);
			logic.timer.setDelay(1000-200*(v - 1));
		}
		
	}
	
	private class freshAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			startButton.setEnabled(true);
			logic.setNumbers(logic.initialNum());
			logic.paint(getGraphics());
		}
		
	}
	
	private class StartSorting implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			startButton.setEnabled(false);
			freshButton.setEnabled(false);
			stopButton.setEnabled(true);
			logic.timer.start();
			if(logic.isCompleted()){
				logic.initSort();
			}
		}
		
	}
	private class ExitAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
		
	}

	private class StopAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("暂停")){
				logic.timer.stop();
				stopButton.setText("继续");
			}else {
				logic.timer.start();
				stopButton.setText("暂停");
			}
			
		}
		
	}
	
	
}
