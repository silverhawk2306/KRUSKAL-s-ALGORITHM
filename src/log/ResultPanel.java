package log;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ResultPanel extends JPanel{
	public LogPanel logPanel;
	public ResultPanel() {
		// TODO Auto-generated constructor stub
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		logPanel = new LogPanel();
		add(logPanel);
	}
}
