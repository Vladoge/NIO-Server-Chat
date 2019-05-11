/**
 *
 *  @author Vladyslav Koval s17134
 *
 */
package zad1;
import java.net.InetSocketAddress;
import java.util.*;
import java.nio.channels.*;


public class Server {
	private ServerSocketChannel sscchannel;
	private Selector sl;

	public Server(){
		try {
			sl = Selector.open();
			sscchannel = ServerSocketChannel.open();
			sscchannel.configureBlocking(false);
			sscchannel.socket().bind(new InetSocketAddress("localhost", 2000));
			sscchannel.register(sl, SelectionKey.OP_ACCEPT);
		} catch(Exception exc) {
			exc.printStackTrace();
			System.exit(0);
		}
		try {
			serviceConnections();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void sendMessageToAllClients(String msg)throws Exception{
			sl.select();
			Set keys = sl.selectedKeys();
			Iterator it = keys.iterator();
			while(it.hasNext()) {
				SelectionKey key = (SelectionKey) it.next();
				if (key.isWritable()) {
					SocketChannel sc = (SocketChannel) key.channel();
					Socket.writeSocketChannel(msg, sc);
				}
			}
		}
	public void serviceConnections()throws Exception{
		while(true) {
				sl.select();
				Set keys = sl.selectedKeys();
				Iterator it = keys.iterator();
				while(it.hasNext()) {
					SelectionKey key = (SelectionKey) it.next();
					if (key.isAcceptable()) {
						SocketChannel sc = sscchannel.accept();
						if (sc != null){
							sc.configureBlocking(false);
							sc.register(sl, (SelectionKey.OP_READ | SelectionKey.OP_WRITE) );
						}
						continue;
					}

					if (key.isReadable()) {
						SocketChannel sc = (SocketChannel) key.channel();
						String result = Socket.readSocketChannel(sc);
						if(result.length() > 0){
							this.sendMessageToAllClients(result);
						}
						continue;
					}
				}
			
		}
	}
	private void accept(SelectionKey key) {

	}
	public static void main(String[] args) {
		new Server();
	}
}

 