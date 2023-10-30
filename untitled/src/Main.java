import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main  {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("baidu.com");
        System.out.println(address);
    }
}