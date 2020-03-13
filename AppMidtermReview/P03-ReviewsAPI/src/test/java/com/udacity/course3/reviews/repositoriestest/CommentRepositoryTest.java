package com.udacity.course3.reviews.repositoriestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.repositories.CommentRepository;
import com.udacity.course3.reviews.repositories.ProductRepository;
import com.udacity.course3.reviews.repositories.ReviewRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CommentRepository commentRepository;

    private static Product testProduct;

    private static Review testReview;

    private static Comment testComment;

    @BeforeClass
    public static void init() {
	testProduct = new Product();
	testProduct.setPrice(1000);
	testProduct.setName("Moto 360");
	testProduct.setabout("Moto 360 smart watch for smart generation.");

	testReview = new Review();
	testReview.setHeader("Excellent battery life");
	testReview.setContent("Moto 360 has an excellent battery life of 10 days per full charge.");

	testComment = new Comment();
	testComment.setheader("Standby or regressive?");
	testComment.setContent("Is this battery life for standby mode or on regressive using?");
    }

    @Test
    public void testInjectedComponentsAreNotNull() {
	assertThat(dataSource).isNotNull();
	assertThat(jdbcTemplate).isNotNull();
	assertThat(entityManager).isNotNull();
    }

    @Test
    public void testSaveCommentInReview() {
	Product savedProduct = productRepository.save(testProduct);
	assertThat(savedProduct).isNotNull();

	testReview.setProduct(savedProduct);
	Review savedReview = reviewRepository.save(testReview);
	assertThat(savedReview).isNotNull();

	Optional<Review> optionalReview = reviewRepository.findById(savedReview.getId());
	Review actualReview = optionalReview.get();
	assertThat(actualReview).isNotNull();
	assertTrue(actualReview.getId().equals(savedReview.getId()));

	testComment.setReview(savedReview);

	Comment expectedComment = commentRepository.save(testComment);
	Comment actualComment = commentRepository.findById(expectedComment.getId()).get();
	assertTrue(actualComment.getId().equals(expectedComment.getId()));
    }

    @Test
    public void testFindAllCommentByReview() {
	Product savedProduct = productRepository.save(testProduct);
	assertThat(savedProduct).isNotNull();

	testReview.setProduct(savedProduct);
	Review savedReview = reviewRepository.save(testReview);
	assertThat(savedReview).isNotNull();

	Optional<Review> optionalReview = reviewRepository.findById(savedReview.getId());
	Review actualReview = optionalReview.get();

	assertThat(actualReview).isNotNull();
	assertTrue(actualReview.getId().equals(savedReview.getId()));

	testComment.setReview(savedReview);

	Comment expectedComment = commentRepository.save(testComment);
	List<Comment> actualCommentList = commentRepository.findAllByReview(savedReview);

	assertTrue(actualCommentList.size() == 1);
	assertTrue(actualCommentList.get(0).getId().equals(expectedComment.getId()));
    }

}
