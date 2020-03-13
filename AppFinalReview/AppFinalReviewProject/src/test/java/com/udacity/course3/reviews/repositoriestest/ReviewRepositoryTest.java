package com.udacity.course3.reviews.repositoriestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
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

import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.repositories.ProductRepository;
import com.udacity.course3.reviews.repositories.ReviewRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTest {

    /** The data source. */
    @Autowired
    private DataSource dataSource;

    /** The jdbc template. */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /** The entity manager. */
    @Autowired
    private EntityManager entityManager;

    /** The product repository. */
    @Autowired
    private ProductRepository productRepository;

    /** The review repository. */
    @Autowired
    private ReviewRepository reviewRepository;

    /** The test product. */
    private static Product testProduct;

    /** The test review. */
    private static Review testReview;

    /**
     * Inits the product and review for tests.
     */
    @BeforeClass
    public static void init() {
	testProduct = new Product();
	testProduct.setPrice(1000);
	testProduct.setName("Moto 360");
	testProduct.setabout("Moto 360 smart watch for smart generation.");

	testReview = new Review();
	testReview.setHeader("Excellent battery life");
	testReview.setContent("Moto 360 has an excellent battery life of 10 days per full charge.");
    }

    /**
     * Test injected components are not null.
     */
    @Test
    public void testInjectedComponentsAreNotNull() {
	assertThat(dataSource).isNotNull();
	assertThat(jdbcTemplate).isNotNull();
	assertThat(entityManager).isNotNull();
	assertThat(productRepository).isNotNull();
	assertThat(reviewRepository).isNotNull();
    }

    /**
     * Test save and find by id.
     */
    @Test
    public void testSaveAndFindById() {
	Product savedProduct = productRepository.save(testProduct);
	testReview.setProduct(savedProduct);

	Review expectedReview = reviewRepository.save(testReview);
	assertThat(expectedReview).isNotNull();

	Optional<Review> optionalReview = reviewRepository.findById(expectedReview.getId());
	Review actualReview = optionalReview.get();
	assertThat(actualReview).isNotNull();
	assertTrue(actualReview.getId().equals(expectedReview.getId()));
    }

    /**
     * Test find all review by product.
     */
    @Test
    public void testFindAllReviewByProduct() {
	Product savedProduct = productRepository.save(testProduct);
	testReview.setProduct(savedProduct);

	Review expectedReview = reviewRepository.save(testReview);
	assertThat(expectedReview).isNotNull();

	List<Review> actualList = (List<Review>) reviewRepository.findAll();
	assertFalse(actualList.size() == 0);
	assertTrue(actualList.size() == 1);

	Review actualReview = actualList.get(0);
	assertThat(expectedReview.getId().equals(actualReview.getId()));
    }

}
