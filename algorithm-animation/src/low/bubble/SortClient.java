package low.bubble;

import javax.swing.JFrame;

public class SortClient {

	public static void main(String[] args) {
		BubbleSortUI ui = new BubbleSortUI();
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.show();
	}

}
