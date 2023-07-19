package edo.dar.movies;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "movies")

/*
 *  
 * Movie è una classe che rappresenta l'entità dei film nel sistema. Questa
 * classe generalmente è una classe di modello e sarà annotata con le
 * annotazioni appropriate per la persistenza (ad esempio, con @Entity se si
 * utilizza un database relazionale o con annotazioni specifiche per MongoDB se
 * si utilizza un database NoSQL). Questa classe ha i campi (proprietà) che
 * rappresentano le informazioni relative al film, come il titolo, la
 * descrizione, l'IMDb ID, ecc.
 */


public class Movie {
    @Id
    

    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;
    @DocumentReference
    private List<Review> reviewIds;


}
