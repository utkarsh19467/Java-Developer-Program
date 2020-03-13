package com.udacity.course3.reviews.repositoriestest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
import com.udacity.course3.reviews.repositories.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

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

    /** The test product. */
    private static Product testProduct;

    /**
     * Inits the product for tests.
     */
    @BeforeClass
    public static void init() {
	testProduct = new Product();
	testProduct.setPrice(1000);
	testProduct.setName("Moto 360");
	testProduct.setabout("Moto 360 smart watch for smart generation.");
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
    }

    /**
     * Test save and find by id.
     */
    @Test
    public void testSaveAndFindById() {
	Product expected = productRepository.save(testProduct);
	assertThat(expected).isNotNull();

	Product actual = productRepository.findById(expected.getId()).get();
	assertTrue(expected.getId().equals(actual.getId()));
    }

    /**
     * Test find all products in repository.
     */
    @Test
    public void testFindAll() {
	Product expectedProduct = productRepository.save(testProduct);
	assertThat(expectedProduct).isNotNull();

	List<Product> actualList = (List<Product>) productRepository.findAll();
	assertFalse(actualList.size() == 0);
	assertTrue(actualList.size() == 1);

	Product actualProduct = actualList.get(0);
	assertTrue(expectedProduct.getId().equals(actualProduct.getId()));
    }

}
