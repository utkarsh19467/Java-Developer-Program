package com.udacity.course3.reviews.repositoriestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.udacity.course3.reviews.entities.ReviewDocument;
import com.udacity.course3.reviews.repositories.ReviewMongoRepository;

/**
 * @author utkarsh
 *
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class ReviewMongoRepoTest {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private ReviewMongoRepository reviewMongoRepo;

    private static final String REVIEW_ID = "1";
    private static final String HEADLINE = "Best Product in 2019";
    private static final String CONTENT = "One plus 7 is the best phone in 2019. Here goes my value for money award to one plus 7.";
    private static ReviewDocument actualReview;

    @Before
    public void init() {
	actualReview = new ReviewDocument();
	actualReview.setReviewId(REVIEW_ID);
	actualReview.setheader(HEADLINE);
	actualReview.setContent(CONTENT);
    }

    @Test
    public void testTest() {
	DBObject objectToSave = BasicDBObjectBuilder.start().add("key", "value").get();

	// when
	mongoTemplate.save(objectToSave, "collection");

	// then
	assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key").containsOnly("value");
    }

    @Test
    public void saveReviewDocument() {
	// when
	reviewMongoRepo.save(actualReview);

	List<ReviewDocument> expectedReviewList = reviewMongoRepo.findAllByReviewId(REVIEW_ID);
	ReviewDocument expectedReview = expectedReviewList.get(0);

	// then
	assertThat(expectedReview.getheader().equals(HEADLINE));
	assertThat(expectedReview.getContent().equals(CONTENT));
    }

    @Test
    public void findAllReviewsUsingProductId() {
	actualReview.setProductId("1");

	// when
	reviewMongoRepo.save(actualReview);

	List<ReviewDocument> expectedReviews = reviewMongoRepo.findAllByProductId("1");

	// then
	assertTrue(expectedReviews.isEmpty() == false);
	assertThat(expectedReviews.get(0).getheader().equals(HEADLINE));
	assertThat(expectedReviews.get(0).getContent().equals(CONTENT));
    }

}
