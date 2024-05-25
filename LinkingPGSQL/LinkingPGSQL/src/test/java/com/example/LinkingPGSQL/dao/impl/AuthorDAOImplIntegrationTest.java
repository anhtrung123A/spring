package com.example.LinkingPGSQL.dao.impl;

import com.example.LinkingPGSQL.TestDataUtil;
import com.example.LinkingPGSQL.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDAOImplIntegrationTest {

    private AuthorDAOImpl underTest;
    @Autowired
    public AuthorDAOImplIntegrationTest(AuthorDAOImpl underTest)
    {
        this.underTest = underTest;
    }
    @Test
    public void testThatAuthorCanBeCreatedAndRecalled()
    {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }
    @Test
    public void testThatMultipleAuthorCanBeCreatedAndRecalled()
    {
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.create(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.create(authorB);
        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.create(authorC);
        List<Author> result = underTest.find();
        assertThat(result).hasSize(3).containsExactly(authorA, authorB, authorC);
    }
    @Test
    public void testThatAuthorCanBeUpdated()
    {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        author.setName("UPDATED");
        underTest.update(author.getId(), author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }
    @Test
    public void testThatAuthorCanBeDeleted()
    {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        underTest.delete(author.getId());
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isEmpty();
    }
}
