package edo.dar.movies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/movies")
// link per testare http://localhost:8081/api/v1/movies

public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping
    // public ResponseEntity<String> allMovies() {
    // return new ResponseEntity<String>("Tutti i film", HttpStatus.OK);
    
    // }
    public ResponseEntity<List<Movie>> allMovies() {
        try {

            // Restituisci la risposta con i film recuperati
            return new ResponseEntity<List<Movie>>(movieService.getallMovies(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Movie>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable Object id) {
        return new ResponseEntity<Optional<Movie>>(movieService.getSingleMovie(id),HttpStatus.OK);
    }

}
