package low.selection;

import javax.swing.JFrame;

public class SortClient {

	public static void main(String[] args) {
		SelectionSortUI ui = new SelectionSortUI();
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.show();
	}

}
