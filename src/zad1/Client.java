/**
 *
 *  @author Vladyslav Koval s17134
 *
 */
package zad1;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;

public class Client {

	public SocketChannel serverSocket;
	private Chat chat;

	public Client(String name){
		this.chat = new Chat(this);
		this.chat.setVisible(true);
		this.startClient(name);
	}
	public static void writeSocketChannel(String t, SocketChannel s){
			ByteBuffer bf = Charset.forName("UTF-8").encode(t);
			try {
				while(bf.hasRemaining() ){

					s.write(bf);
				}
			}catch (Exception e){

			}

	}
	public void sendMessage(String t){

		try{
		writeSocketChannel( "["+(chat.getNick()+"]"+ " " +"<<"+t+">>"), this.serverSocket);}
		catch (Exception e){

		}
	}
	public void startClient(String name){
		try {
			SocketChannel scCannel = SocketChannel.open();
			scCannel.connect(new InetSocketAddress("localhost", 2000));
			this.serverSocket = scCannel;

		while(true){
				String r  = Socket.readSocketChannel(this.serverSocket);
				if(r.length() > 0){
					chat.sendM(r);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


	}


}