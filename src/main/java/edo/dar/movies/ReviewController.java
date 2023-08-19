package edo.dar.movies;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {
    private ReviewService reviewService;



    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    //Creazione della recensione
    public ResponseEntity<Review> CreateReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")),
                HttpStatus.CREATED);
    }

    @PutMapping("/{reviewId}")
    // Aggiornamento della recensione
    public ResponseEntity<Review> updateReview(@PathVariable String reviewId,
            @RequestBody Map<String, String> payload) {
        return new ResponseEntity<>(reviewService.updateReview(reviewId, payload.get("reviewBody")),
                HttpStatus.OK);
    }
 @DeleteMapping("/{reviewId}")
    // Cancellazione della recensione
    public ResponseEntity<Review> deleteReview(@PathVariable String reviewId) {
        Review deletedReview = reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(deletedReview, HttpStatus.OK);
    }
}
