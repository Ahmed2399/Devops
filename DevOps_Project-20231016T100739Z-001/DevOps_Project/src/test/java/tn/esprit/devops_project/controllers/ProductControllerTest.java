import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.devops_project.controllers.ProductController;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.services.Iservices.IProductService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private IProductService productService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testAddProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);

        when(productService.addProduct(any(Product.class), anyLong())).thenReturn(product);

        mockMvc.perform(post("/product/{idStock}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1, \"name\":\"Product1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testRetrieveProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);

        when(productService.retrieveProduct(1L)).thenReturn(product);

        mockMvc.perform(get("/product/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testRetreiveAllProduct() throws Exception {
        List<Product> products = Arrays.asList(new Product(), new Product());

        when(productService.retreiveAllProduct()).thenReturn(products);

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testRetreiveProductStock() throws Exception {
        List<Product> products = Arrays.asList(new Product(), new Product());

        when(productService.retreiveProductStock(1L)).thenReturn(products);

        mockMvc.perform(get("/product/stock/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testRetrieveProductByCategory() throws Exception {
        ProductCategory category = ProductCategory.CATEGORY1;
        List<Product> products = Arrays.asList(new Product(), new Product());

        when(productService.retrieveProductByCategory(category)).thenReturn(products);

        mockMvc.perform(get("/productCategoy/{category}", category))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/product/{id}", 1))
                .andExpect(status().isOk());
    }
}

