package chat.client.frame;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chat.client.event.frame_event;
import chat.flatform.defs;
import chat.socket.http_socket;

@SuppressWarnings("serial")
public class frame extends JFrame
{
	public JTextArea recvText = new JTextArea();
	public JTextField sendText = new JTextField();
	
	//http Socket
	public http_socket httpSock = new http_socket();
	
	/**
	 * Create the dialog.
	 * @return 
	 */
	public frame()
	{
		try
		{
			make_pane();
			make_output_panel();
			make_input_panel();

			revalidate();
			repaint();

			httpSock.init();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void make_output_panel()
	{
		JPanel oPanel = new JPanel();

		oPanel.setLayout(null);
		oPanel.setSize(defs.DEFAULT_PANEL_WIDTH, 500);
		oPanel.setLocation(0, 0);
		oPanel.setBackground(defs.COLOR_BLUE);
		
		oPanel.add(new JLabel("iPanel"), JLabel.CENTER);
		
		//test
		JLabel oLabel = new JLabel("oPanel");
		{
			oLabel.setLayout(null);
			oLabel.setLocation(10, 10);
			oLabel.setSize(130, 20);

			oPanel.add(oLabel);
		}
		
		//recv Text Area
		{
			recvText.setLayout(null);
			recvText.setSize(defs.DEFAULT_PANEL_WIDTH - (defs.DEFAULT_MARGIN * 2), 450);
			recvText.setLocation(defs.DEFAULT_MARGIN, 30);
			recvText.setEditable(false);
			oPanel.add(recvText);
		}
		
		add(oPanel);
	}
	
	public void make_input_panel()
	{
		JPanel iPanel = new JPanel();

		iPanel.setLayout(null);
		iPanel.setSize(defs.DEFAULT_PANEL_WIDTH, 150);
		iPanel.setLocation(0, 500);
		iPanel.setBackground(defs.DEFAULT_BK_COLOR);
		
		//test
		JLabel iLabel = new JLabel("iPanel");
		{
			iLabel.setLayout(null);
			iLabel.setLocation(10, 10);
			iLabel.setSize(130, 50);

			iPanel.add(iLabel);
		}
		
		//send Text Area
		{
			sendText.setLayout(null);
			sendText.setSize(defs.DEFAULT_PANEL_WIDTH - 80 - ( defs.DEFAULT_MARGIN * 4 ), 30);
			sendText.setLocation(10, defs.INPUT_PANEL_HEIGHT - 30 - defs.DEFAULT_MARGIN );
			sendText.addKeyListener(new frame_event(this));
			
			iPanel.add(sendText);
		}
		
		//send Button
		JButton sendButton = new JButton(defs.DEFAULT_SEND_BT_COMMAND);
		{
			sendButton.setLayout(null);
			sendButton.setSize(80, 30);
			sendButton.setLocation(defs.DEFAULT_PANEL_WIDTH - 80 - (defs.DEFAULT_MARGIN  * 2), defs.INPUT_PANEL_HEIGHT - 30 - defs.DEFAULT_MARGIN);
			sendButton.addActionListener(new frame_event(this));
			
			iPanel.add(sendButton);
		}
		
		add(iPanel);
	}
	
	public void make_pane()
	{
		setLayout(null);
		
		setTitle(defs.DEFAULT_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(defs.DEFAULT_PANEL_WIDTH, defs.DEFAULT_PANEL_HEIGHT);
		
		setVisible(true);
	}
}
