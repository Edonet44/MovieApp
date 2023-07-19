package edo.dar.movies;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
 * MovieRepository è un'interfaccia che definisce i 
 * metodi per eseguire operazioni di accesso ai dati (lettura, scrittura, aggiornamento, cancellazione) relativi all'entità  Movie. 
 * 
 * 
 */


@Repository
public interface MovieinReposistory extends MongoRepository<Movie, Object> {
    Optional<Movie> findMovieByimdbId(String imdbId);
    //List<Movie> findByTitleContaining(String keyword);
}

