//package com.example.LinkingPGSQL.repositories;
//
//import com.example.LinkingPGSQL.TestDataUtil;
//import com.example.LinkingPGSQL.domain.entities.AuthorEntity;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class AuthorRepositoryIntegrationTest {
//
//    private AuthorRepository underTest;
//    @Autowired
//    public AuthorRepositoryIntegrationTest(AuthorRepository underTest)
//    {
//        this.underTest = underTest;
//    }
//    @Test
//    public void testThatAuthorCanBeCreatedAndRecalled()
//    {
//        AuthorEntity author = TestDataUtil.createTestAuthorA();
//        underTest.save(author);
//        Optional<AuthorEntity> result = underTest.findById(author.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(author);
//    }
//    @Test
//    public void testThatMultipleAuthorCanBeCreatedAndRecalled()
//    {
//        AuthorEntity authorA = TestDataUtil.createTestAuthorA();
//        underTest.save(authorA);
//        AuthorEntity authorB = TestDataUtil.createTestAuthorB();
//        underTest.save(authorB);
//        AuthorEntity authorC = TestDataUtil.createTestAuthorC();
//        underTest.save(authorC);
//        Iterable<AuthorEntity> result = underTest.findAll();
//        assertThat(result).hasSize(3).containsExactly(authorA, authorB, authorC);
//    }
//    @Test
//    public void testThatAuthorCanBeUpdated()
//    {
//        AuthorEntity author = TestDataUtil.createTestAuthorA();
//        underTest.save(author);
//        author.setName("Johnny Dark");
//        underTest.save(author);
//        Optional<AuthorEntity> result = underTest.findById(author.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(author);
//    }
//    @Test
//    public void testThatAuthorCanBeDeleted()
//    {
//        AuthorEntity author = TestDataUtil.createTestAuthorA();
//        underTest.save(author);
//        underTest.deleteById(author.getId());
//        Optional<AuthorEntity> result = underTest.findById(author.getId());
//        assertThat(result).isEmpty();
//    }
//    @Test
//    public void testThatAuthorWithAgeLessThan()
//    {
//        AuthorEntity author = TestDataUtil.createTestAuthorA();
//        underTest.save(author);
//        Iterable<AuthorEntity> result = underTest.ageLessThan(50);
//        assertThat(result).isEmpty();
//    }
//    @Test
//    public void testThatGetAuthorWithAgeGreaterThan()
//    {
//        AuthorEntity author = TestDataUtil.createTestAuthorA();
//        underTest.save(author);
//        Iterable<AuthorEntity> result = underTest.findAuthorWithAgeGreaterThan(50);
//        assertThat(result).containsExactly(author);
//    }
//}
