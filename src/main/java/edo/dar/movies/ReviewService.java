package edo.dar.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
@Autowired
private ReviewRepository reviewRepository;
@Autowired
private MongoTemplate mongoTemplate;

/*
 * reviewRepository.insert(review);: 
 * Questa riga inserisce l'oggetto review nel database MongoDB utilizzando 
 * il repository reviewRepository. Si presume che reviewRepository sia un'interfaccia o una classe che estende 
 * un repository di Spring Data MongoDB e fornisce metodi per l'accesso ai dati delle recensioni.

mongoTemplate.update(Movie.class): 
Questa riga indica che si desidera aggiornare un documento nella collezione "movies" 
nel database MongoDB. mongoTemplate è un'istanza di MongoTemplate, una classe fornita da Spring Data MongoDB 
per l'accesso avanzato al database MongoDB.

.matching(Criteria.where("imdbId").is(imdbId)): 
Questa riga specifica il criterio per selezionare il documento da aggiornare. 
Si sta cercando un documento nel quale il campo "imdbId" corrisponda al valore specificato imdbId. 
Criteria è un oggetto utilizzato per costruire condizioni di query complesse in MongoDB.

.apply(new Update().push("reviewIds").value(review)): 
Questa riga definisce l'operazione di aggiornamento.
 In particolare, utilizza l'operatore $push di MongoDB per aggiungere l'ID della recensione all'array reviewIds
  all'interno del documento del film corrispondente. In questo modo, si sta associando 
  la recensione al film tramite l'ID della recensione.

.first();: Questa riga specifica che verrà eseguita solo il primo documento corrispondente al criterio di selezione. Poiché un film dovrebbe avere un solo documento nel database con un dato ID IMDb, questa riga assicura che l'aggiornamento venga applicato solo al primo documento corrispondente.

};: Questo segna la fine del metodo  createReview. 
 * 
 */

    public Review creatReview(String ReviewBody, String imdbId) {
        Review review = 
        reviewRepository.insert(new Review(ReviewBody));
mongoTemplate.update(Movie.class)
        .matching(Criteria.where("imdbId").is(imdbId))
        .apply(new Update().push("reviewIds").value(review))
        .first();
       return review; 
    
    };
    }

