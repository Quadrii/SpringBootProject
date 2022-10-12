package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dto.request.CreateProductRequest;
import africa.semicolon.lumExpress.data.dto.request.GetAllItemsRequest;
import africa.semicolon.lumExpress.data.dto.request.UpdateProductRequest;
import africa.semicolon.lumExpress.data.dto.response.CreateProductResponse;
import africa.semicolon.lumExpress.data.dto.response.UpdateProductResponse;
import africa.semicolon.lumExpress.data.models.Category;
import africa.semicolon.lumExpress.data.models.Product;
import africa.semicolon.lumExpress.data.repositories.ProductRepository;
import africa.semicolon.lumExpress.exception.ProductNotFoundException;
import africa.semicolon.lumExpress.services.cloud.CloudService;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ModelMapper model = new ModelMapper();
    private final CloudService cloudService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public CreateProductResponse createProduct(CreateProductRequest productRequest) throws IOException {

        Product product = model.map(productRequest, Product.class);
        product.getCategory().add(Category.valueOf(productRequest.getProductCategory().toUpperCase()));
        var imageUrl = cloudService.upload(productRequest.getImage().getBytes(), ObjectUtils.emptyMap());
        product.setImageUrl(imageUrl);
       Product savedProduct =  productRepository.save(product);
       return buildCreateProductResponse(savedProduct);
    }

//    @Override
//    public UpdateProductResponse updateProductDetails(UpdateProductRequest updateProductRequest) {
//        return null;
//    }

    private CreateProductResponse buildCreateProductResponse(Product savedProduct){
        return CreateProductResponse.builder()
                .code(201)
                .productId(savedProduct.getId())
                .message("product created successfully")
                .build();
    }

    @Override
    public UpdateProductResponse updateProductDetails(Long productId, JsonPatch patch)  {
        //find product
        var foundProduct =
                productRepository.findById(productId)
                        .orElseThrow(()-> new ProductNotFoundException(
                                String.format("product with id %d not found",
                                        productId)
                        ));
        Product updatedProduct = applyPatchToProduct(patch, foundProduct);
        //save updated product
        var savedProduct=productRepository.save(updatedProduct);
        return buildUpdateResponse(savedProduct);
    }

    private Product applyPatchToProduct(JsonPatch patch, Product foundProduct)  {
        //convert found product to json node
        var productNode = objectMapper.convertValue(foundProduct, JsonNode.class);
        //apply patch to productNode
        JsonNode patchedProductNode;
        try {
            patchedProductNode = patch.apply(productNode);
            //convert patchedNode to product object
            var updatedProduct =
                    objectMapper.readValue(objectMapper.writeValueAsBytes(patchedProductNode),
                            Product.class);
            return updatedProduct;
        } catch (IOException | JsonPatchException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private static UpdateProductResponse buildUpdateResponse(Product savedProduct) {
        return UpdateProductResponse.builder()
                .productName(savedProduct.getName())
                .price(savedProduct.getPrice())
                .message("update successful")
                .statusCode(200)
                .build();
    }

    @Override
    public Product getProductById(Long id) {
        //method one
//        Optional<Product> foundProduct = productRepository.findById(id);
//        if (foundProduct.isPresent()) return foundProduct.get();
//        throw new ProductNotFoundException(
//                String.format("product with id %d not found", id)
//        );
        //method two
        return productRepository.findById(id).orElseThrow(
                ()->new ProductNotFoundException(
                        String.format("product with id %d not found", id)
                )
        );
    }

    @Override
    public Page<Product> getAllProducts(GetAllItemsRequest getAllItemsRequest) {
        Pageable pageable = PageRequest.of(getAllItemsRequest.getPageNumber()-1, getAllItemsRequest.getNumberOfItemsPerPage());
        Page<Product> products=productRepository.findAll(pageable);
        return products;
    }

//    @Override
//    public List<Product> getAllProducts() {
//        return null;
//    }

    @Override
    public String deleteProduct(Long id) {
        return null;
    }
}
