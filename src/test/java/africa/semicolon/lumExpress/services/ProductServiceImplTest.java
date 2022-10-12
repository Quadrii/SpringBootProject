package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dto.request.CreateProductRequest;
import africa.semicolon.lumExpress.data.dto.request.GetAllItemsRequest;
import africa.semicolon.lumExpress.data.dto.request.UpdateProductRequest;
import africa.semicolon.lumExpress.data.dto.response.CreateProductResponse;
import africa.semicolon.lumExpress.data.dto.response.UpdateProductResponse;
import africa.semicolon.lumExpress.data.models.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.ReplaceOperation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    CreateProductResponse productResponse;

    CreateProductRequest createProductRequest;
    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths.get("/home/quadri/Desktop/testProduct.jpg");
        MultipartFile file = new MockMultipartFile("peak", Files.readAllBytes(path));
        createProductRequest = CreateProductRequest.builder()
                .productCategory("fashion")
                .name("designer")
                .image(file)
                .price(BigDecimal.valueOf(30.00))
                .build();
        productResponse = productService.createProduct(createProductRequest);
    }

    @Test
    void createProductTest() throws IOException {
        assertThat(productResponse).isNotNull();
        assertThat(productResponse.getProductId()).isGreaterThan(0L);
        assertThat(productResponse.getMessage()).isNotNull();
        assertThat(productResponse.getCode()).isEqualTo(201);
//        assertThat(productResponse.getCode()).isEqualTo(HttpStatus.CREATED);
    }


//    void updateProductRequestTest(){
//        UpdateProductRequest updateProductRequest = UpdateProductRequest.builder()
//                .description("new design")
//                .price(BigDecimal.valueOf(30.00))
//                .quantity(4)
//                .productId(1L)
//                .build();
//        var updateResponse = productService.updateProductDetails(updateProductRequest);
//        assertThat(updateResponse).isNotNull();
//        assertThat(updateResponse).isEqualTo(201);
//    }
    @Test
    void updateProductRequestTest() {
        ObjectMapper mapper = new ObjectMapper();
        UpdateProductResponse updateResponse=null;
        try {
            JsonNode value = mapper.readTree("50.00");
            JsonPatch patch =
                    new JsonPatch(List.of(new ReplaceOperation(new JsonPointer("/price"), value)));
            updateResponse = productService.updateProductDetails(1L, patch);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        Assertions.assertThat(updateResponse).isNotNull();
        Assertions.assertThat(updateResponse.getStatusCode())
                .isEqualTo(200);
        Assertions.assertThat(productService.getProductById(1L).getName())
                .isEqualTo("designer");
    }

    @Test
    void getProductByIdTest() throws IOException {
       CreateProductResponse response =  productService.createProduct(createProductRequest);
       Product foundProduct =productService.getProductById(response.getProductId());
       assertThat(foundProduct).isNotNull();
       assertThat(foundProduct.getId()).isEqualTo(response.getProductId());
    }

    @Test
    void getAllProductsTest() throws IOException {
        productService.createProduct(createProductRequest);
        var getItemRequest = buildGetItemsRequest();
        Page<Product>productPage=productService.getAllProducts(getItemRequest);
        assertThat(productPage).isNotNull();
        assertThat(productPage.getTotalElements()).isGreaterThan(0);
    }
    private GetAllItemsRequest buildGetItemsRequest(){
        return GetAllItemsRequest.builder()
                .numberOfItemsPerPage(5)
                        .pageNumber(1)
                .build();
    }

    private CreateProductRequest buildAddProductRequest(MultipartFile file) {
        return CreateProductRequest.builder()
                .name("Milk")
                .productCategory("Beverages")
                .price(BigDecimal.valueOf(30.00))
                .quantity(10)
                .image(file)
                .build();
    }

    private UpdateProductRequest buildUpdateRequest() {
        return UpdateProductRequest.builder()
                .price(BigDecimal.valueOf(40.00))
                .productId(1L)
                .description("its just fashion")
                .quantity(10)
                .build();
    }

}