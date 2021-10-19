import com.stelpolvo.factory.BeanFactory;
import com.stelpolvo.factory.proxy.ProxyFactory;
import com.stelpolvo.service.UserService;

public class Test {
    // 手动注入
    private static UserService userService = (UserService) BeanFactory.getBean("userService");
    public static void main(String[] args) {
        UserService proxy = (UserService) ProxyFactory.getProxy(userService);
        System.out.println(proxy.getUserById(2));
    }
}
