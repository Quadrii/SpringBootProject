package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dto.request.CreateProductRequest;
import africa.semicolon.lumExpress.data.dto.request.GetAllItemsRequest;
import africa.semicolon.lumExpress.data.dto.request.UpdateProductRequest;
import africa.semicolon.lumExpress.data.dto.response.CreateProductResponse;
import africa.semicolon.lumExpress.data.dto.response.UpdateProductResponse;
import africa.semicolon.lumExpress.data.models.Product;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    CreateProductResponse createProduct(CreateProductRequest productRequest) throws IOException;
//    UpdateProductResponse updateProductDetails(UpdateProductRequest updateProductRequest);

    UpdateProductResponse updateProductDetails(Long productId, JsonPatch patch);

    Product getProductById(Long id);
    Page<Product> getAllProducts(GetAllItemsRequest getAllItemsRequest);

//    List<Product> getAllProducts();
    String deleteProduct(Long id);
}
