package edo.dar.movies;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
        private ObjectId id;
        private String body;

        public Review(String body) {
            this.body = body;
            }
 
    public void setReviewBody(String body) {
        this.body = body;
    }
}
