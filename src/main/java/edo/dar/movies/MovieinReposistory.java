package edo.dar.movies;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieinReposistory extends MongoRepository<Movie, Object> {

}
