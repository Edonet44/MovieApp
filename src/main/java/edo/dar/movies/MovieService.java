package edo.dar.movies;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MovieService {
    @Autowired
    private MovieinReposistory movieinReposistory;

    public List<Movie> getallMovies() {
        return movieinReposistory.findAll();
    }
    
    public Optional<Movie> getSingleMovie(Object id) {
        return movieinReposistory.findById(id);
    }

}
