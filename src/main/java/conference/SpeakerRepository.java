package conference;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Jeka
 * @since 07/10/2014
 */
//http://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
public interface SpeakerRepository extends PagingAndSortingRepository<Speaker, Long> {

    @FindByName

    List<Speaker> findByNameStartingWith(String name);

    List<Speaker> findPleeeeaseByNameEndingWith(String suffix);

    @Query(fields = "{talks.title:1}")
    List<Speaker>  findByTalksWhenBetween(Date from, Date to);

    Set<Speaker> findByTalksTitleContaining(String partOfTalkName);

    default List<Talk> findTalksBetween(Date from, Date to){
        List<Speaker> speakers = findByTalksWhenBetween(from, to);
        return speakers.stream().map(Speaker::getTalks).flatMap(Collection::stream).filter(t -> isBetween(from, to, t)).collect(Collectors.toList());
    }

    default boolean isBetween(Date from, Date to, Talk time) {
        return time.getWhen().after(from) && time.getWhen().before(to);
    }
}
