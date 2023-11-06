import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ProductRepositoryTest {

    @InjectMocks
    private ProductRepository productRepository;

    @Mock
    private ProductRepository mockProductRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByCategory() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setCategory(ProductCategory.CATEGORY1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setCategory(ProductCategory.CATEGORY1);

        List<Product> products = Arrays.asList(product1, product2);

        when(mockProductRepository.findByCategory(ProductCategory.CATEGORY1)).thenReturn(products);

        List<Product> retrievedProducts = productRepository.findByCategory(ProductCategory.CATEGORY1);

        assertEquals(2, retrievedProducts.size());
    }

    @Test
    public void testFindByStockIdStock() {
        Long stockId = 1L;

        Product product1 = new Product();
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(2L);

        List<Product> products = Arrays.asList(product1, product2);

        when(mockProductRepository.findByStockIdStock(stockId)).thenReturn(products);

        List<Product> retrievedProducts = productRepository.findByStockIdStock(stockId);

        assertEquals(2, retrievedProducts.size());
    }
}

