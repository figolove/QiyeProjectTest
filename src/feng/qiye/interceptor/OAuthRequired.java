package feng.qiye.interceptor;

import java.lang.annotation.*;
/**
 * 验证OAuth2注解
 * @author Sunlight
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OAuthRequired {
	
}
