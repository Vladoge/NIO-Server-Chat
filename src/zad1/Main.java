/**
 *
 *  @author Vladyslav Koval s17134
 *
 */

package zad1;
public class Main {
	public static void main(String[] args) {
		try{
			new Thread()
			{public void run() {
					new Server();
				}
			}.start();
			Thread.sleep(1000);
			new Thread()
			{
				public void run() {
					new Client("");
				}
			}.start();
			new Thread(){
				@Override
				public void run() {
					new Client("");
				}
			}.start();
				}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}