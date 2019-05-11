package zad1;import java.nio.ByteBuffer;import java.nio.CharBuffer;import java.nio.channels.SocketChannel;import java.nio.charset.Charset;public class Socket {    public static final int BSIZE = 256;    public static String readSocketChannel(SocketChannel s)throws Exception{        if(!s.isOpen()) return "";        StringBuffer stBuffer = new StringBuffer();        stBuffer.setLength(0);        ByteBuffer bf = ByteBuffer.allocate(BSIZE);        bf.clear();        int count = 0;        label:        while (true) {            int n = s.read(bf);            if (n > 0) {                bf.flip();                CharBuffer charBuf = Charset.forName("UTF-8").decode(bf);                while(charBuf.hasRemaining()) {                    char c = charBuf.get();                    if (c == '\r' || c == '\n') break label;                    stBuffer.append(c);                }            }else{                count++;                if ( count > 1000){                    break label;                }            }        }        return stBuffer.toString();    }    public static void writeSocketChannel(String t, SocketChannel s)throws Exception{        t += "\n";        ByteBuffer bf = Charset.forName("UTF-8").encode(t);        while( bf.hasRemaining() ){            s.write(bf);        }    }}