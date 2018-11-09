package dmutils.com.dmutils.permission;

/**
 * Created by david on 9/23/16.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DMAfterPermissionGranted {

    int value();

}
