import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProduct() {
        Stock stock = new Stock();
        stock.setIdStock(1L);

        Product product = new Product();
        product.setId(1L);
        product.setStock(stock);

        Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.addProduct(product, 1L);

        assertEquals(1L, savedProduct.getId().longValue());
        assertEquals(stock, savedProduct.getStock());
    }

    @Test
    public void testRetrieveProduct() {
        Product product = new Product();
        product.setId(1L);

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product retrievedProduct = productService.retrieveProduct(1L);

        assertEquals(1L, retrievedProduct.getId().longValue());
    }

    @Test
    public void testRetreiveAllProduct() {
        Product product1 = new Product();
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(2L);

        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.retreiveAllProduct();

        assertEquals(2, products.size());
    }

    @Test
    public void testRetrieveProductByCategory() {
        ProductCategory category = ProductCategory.CATEGORY1;

        Product product1 = new Product();
        product1.setId(1L);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setCategory(category);

        Mockito.when(productRepository.findByCategory(category)).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.retrieveProductByCategory(category);

        assertEquals(2, products.size());
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct(1L);

        Mockito.verify(productRepository).deleteById(1L);
    }

    @Test
    public void testRetreiveProductStock() {
        Long stockId = 1L;

        Product product1 = new Product();
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(2L);

        Mockito.when(productRepository.findByStockIdStock(stockId)).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.retreiveProductStock(stockId);

        assertEquals(2, products.size());
    }
}
