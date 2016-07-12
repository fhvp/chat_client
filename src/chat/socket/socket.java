package chat.socket;

import java.io.IOException;

public interface socket
{
	public void init();
	
	public void request( String data ) throws IOException;

	public String response() throws IOException;
}
