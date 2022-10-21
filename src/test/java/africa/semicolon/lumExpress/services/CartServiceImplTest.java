package africa.semicolon.lumExpress.services;
//import uuid from 'react-uuid';

import africa.semicolon.lumExpress.data.dto.request.CartRequest;
import africa.semicolon.lumExpress.data.dto.request.GetAllItemsRequest;
import africa.semicolon.lumExpress.data.dto.response.CartResponse;
import africa.semicolon.lumExpress.data.models.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartServiceImplTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("test that cart can be created")
    void createCartTest() {
//        Cart cart = cartService.create();
//        assertThat(cart).isNotNull();
    }

    @Test
    void addProductToCart() {
        CartRequest request = CartRequest.builder()
                .cartId(
                        cartService.getCartList()
                                .get(cartService.getCartList().size()-1)
                                .getId()

                )
                .productId(
                        productService.getAllProducts(
                                new GetAllItemsRequest()
                        )
                                .getContent().get(productService.getAllProducts(new GetAllItemsRequest()).getNumberOfElements()-1
                                ).getId()
                ).build();
           CartResponse cartResponse = cartService.addProductToCart(request);

    }
}