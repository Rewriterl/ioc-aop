import com.stelpolvo.factory.BeanFactory;
import com.stelpolvo.proxy.ProxyManager;
import com.stelpolvo.service.UserService;

public class Test {
    // 手动注入
//    private static UserService userService = (UserService) BeanFactory.getBean("userService");

    public static void main(String[] args) {
//        System.out.println(userService.getUserById(2));
        System.out.println(ProxyManager.getProxy().getUserById(2));
    }
}
