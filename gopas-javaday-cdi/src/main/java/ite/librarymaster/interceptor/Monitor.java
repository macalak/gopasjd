package ite.librarymaster.interceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.*;

import javax.interceptor.InterceptorBinding;

@InterceptorBinding
@Retention(RUNTIME)
@Target({METHOD,TYPE})
public @interface Monitor {

}
