package book.api.book_api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEntity {
    @Id
    private String bookId;
    private long rank;
    private String title;
    private List<String> series;
    private String numberOfSeries;
    private List<String> author;
    private String description;
    private String language;
    private List<String> genres;
    private List<String> characters;
    private String setting;
    private String coverImg;
    private String bookFormat;
    private String edition;
    private long pages;
    private String publisher;
    private long publishedYear;
    private long firstPublishYear;
    private List<String> awards;
    private double rating;
    private long numRatings;
    private String ISBN;
}
