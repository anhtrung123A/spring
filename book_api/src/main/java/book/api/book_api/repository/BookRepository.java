package book.api.book_api.repository;

import book.api.book_api.domain.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookEntity, Long> {
}
