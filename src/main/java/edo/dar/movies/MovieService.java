package edo.dar.movies;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * 
 * MovieService è una classe di servizio che contiene la logica di business per la gestione dei film. 
 * Questa classe dovrebbe essere annotata con  @Service per consentire a Spring di gestirla come un componente gestito. 
   La classe  MovieService si avvale del  MovieRepository per eseguire operazioni di accesso ai dati e fornisce metodi per recuperare i film e svolgere altre operazioni sui film, se necessario. 
 */

@Service
public class MovieService {
    @Autowired
    private MovieinReposistory movieinReposistory;

    public List<Movie> getallMovies() {
        return movieinReposistory.findAll();
    }
    
    public Optional<Movie> getSingleMovie(String imdbId) {
        return movieinReposistory.findMovieByimdbId(imdbId);
    }

}
