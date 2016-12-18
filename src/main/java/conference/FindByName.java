package conference;/**
 * Created by Evegeny on 27/11/2016.
 */

import org.springframework.data.mongodb.repository.Query;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Query("{ 'name' : ?0 }")
public @interface FindByName {
}
