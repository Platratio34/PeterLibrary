package cheapLogger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CheapLogger extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6511431248033182780L;

	private JPanel panel;
	
	private Listen list;
	private JLabel label;
	
	public CheapLogger(LogEvent event) {
		super("CheapLogger");
//		System.out.println(event);
		panel = new JPanel();
		label = new JLabel("Log;");
		list = new Listen(label, event);
		panel.add(label);
		add(panel);
		addKeyListener(list);
        setSize(200, 100);
        setVisible(true);
	}
	
//	public CheapLogger(cheapLogger.LogEvent logEvent) {
//		// TODO Auto-generated constructor stub
//	}

	public class Listen implements KeyListener {
		
		private JLabel label;
		private LogEvent event;
		
		public Listen(JLabel label, LogEvent event) {
			this.label = label;
			this.event = event;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
//			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String s = event.onLog();
			System.out.println(s);
			label.setText(s);
//	        }
		}
	}
}