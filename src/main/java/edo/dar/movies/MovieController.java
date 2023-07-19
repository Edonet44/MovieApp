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
// link per testare http://localhost:8082/api/v1/movies


/*
 * MovieController è una classe di controller che gestisce le richieste HTTP
 * relative ai film. Questa classe dovrebbe essere annotata con @RestController
 * per dichiararla come un controller REST e può essere inclusa nel contesto
 * dell'applicazione Spring grazie all'annotazione @ComponentScan (solitamente
 * presente nell'applicazione principale).
 * 
 * Il controller utilizza MovieService per ottenere i dati necessari e
 * restituirli in risposta alle richieste HTTP. Le
 * annotazioni @GetMapping, @PostMapping, @DeleteMapping, ecc. vengono
 * utilizzate per mappare le richieste HTTP ai metodi del controller.
 * 
 * 
 */

public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping
    
    public ResponseEntity<List<Movie>> allMovies() {
        try {

            // Restituisci la risposta con i film recuperati
            return new ResponseEntity<List<Movie>>(movieService.getallMovies(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Movie>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId) {
        return new ResponseEntity<Optional<Movie>>(movieService.getSingleMovie(imdbId),HttpStatus.OK);
    }

}
