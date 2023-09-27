import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guoweijie
 * @version 1.0
 * @description: TODO
 * @date 2023/9/26 11:09
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class TestController {

    @Value("${user.name}")
    private String userName;

    @Value("${user.age}")
    private int userAge;

    @Value("${user.pass}")
    private int userPass;


    @RequestMapping("/get")
    public String get() {
        System.out.println(userName);
        System.out.println(userAge);
        System.out.println(userPass);
        System.out.println(userPass);
        return userName;
    }
}
