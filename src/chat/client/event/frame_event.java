package chat.client.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import chat.client.frame.frame;
import chat.flatform.defs;
import chat.socket.http_socket;
import chat.stack.HttpManager;

public class frame_event implements ActionListener, KeyListener
{
	frame super_class;
	
	public void send()
	{
		String data;
		
		data = super_class.sendText.getText();
		
		if( data.isEmpty() == false )
		{
			super_class.recvText.append(data);
			super_class.recvText.append("\r\n");
			
			super_class.sendText.setText("");
			
			try
			{
				super_class.httpSock.request(data);
				//super_class.httpSock.sendPost();
				
				String responseData = super_class.httpSock.response();
				
				if( responseData != null )
				{
					HttpManager.httpManager.recvHttpSock(responseData);
				}
				
				//System.out.println(str);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("test event");

		String command;
		command = e.getActionCommand();
		
		if( command == defs.DEFAULT_SEND_BT_COMMAND )
		{
			send();
		}
	}

	public frame_event( frame _frame_class )
	{
		// TODO Auto-generated constructor stub
		this.super_class = _frame_class;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if( e.getKeyCode() == 10 )
			send();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
