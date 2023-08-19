package edo.dar.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;


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

//     public Review creatReview(String ReviewBody, String imdbId) {
//         Review review = 
//         reviewRepository.insert(new Review(ReviewBody));
// mongoTemplate.update(Movie.class)
//         .matching(Criteria.where("imdbId").is(imdbId))
//         .apply(new Update().push("reviewIds").value(review))
//         .first();
//        return review; 
    
// };


public Review createReview(String reviewBody, String imdbId) {
        // Insert the new review into the "reviews" collection
        Review review = reviewRepository.insert(new Review(reviewBody));

        // Update the movie document in the "movies" collection to associate the review
        // with the movie
        Query query = new Query(Criteria.where("imdbId").is(imdbId));
        Update update = new Update().addToSet("reviewIds", review.getId());
        mongoTemplate.updateFirst(query, update, Movie.class);

        return review;
}

//Update
        public Review updateReview(String reviewId, String newReviewBody) {
        // Convert reviewId from String to ObjectId
        ObjectId objectIdReviewId = new ObjectId(reviewId);

        Review existingReview = reviewRepository.findById(objectIdReviewId).orElse(null);

        if (existingReview == null) {
                throw new IllegalArgumentException("Recensione non trovata con ID: " + reviewId);
        }

        existingReview.setReviewBody(newReviewBody);
        reviewRepository.save(existingReview);

        // Aggiornamento del campo reviewIds del film associato
        Update update = new Update();
        update.set("reviewIds.$[review].body", newReviewBody);
        update.filterArray(Criteria.where("review._id").is(objectIdReviewId));
        mongoTemplate.updateMulti(new Query(), update, Movie.class);

        return existingReview;
}
// Delete

public Review deleteReview(String reviewId) {
    // Convert reviewId from String to ObjectId
    ObjectId objectIdReviewId = new ObjectId(reviewId);

    // Recuperiamo la recensione esistente dal database
    Review existingReview = reviewRepository.findById(objectIdReviewId).orElse(null);

    if (existingReview == null) {
        throw new IllegalArgumentException("Recensione non trovata con ID: " + reviewId);
    }

    // Rimuoviamo la recensione dal database
    reviewRepository.delete(existingReview);

    // Rimuoviamo il riferimento alla recensione dal campo reviewIds del film associato
    Update update = new Update();
    update.pull("reviewIds", new BasicDBObject("_id", objectIdReviewId));
    mongoTemplate.updateMulti(new Query(), update, Movie.class);

    return existingReview;
}

   

    }

