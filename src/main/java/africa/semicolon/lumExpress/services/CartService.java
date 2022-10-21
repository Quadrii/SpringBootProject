package africa.semicolon.lumExpress.services;

import africa.semicolon.lumExpress.data.dto.request.CartRequest;
import africa.semicolon.lumExpress.data.dto.response.CartResponse;
import africa.semicolon.lumExpress.data.models.Cart;

import java.util.List;

public interface CartService {
    CartResponse addProductToCart(CartRequest cartRequest);
    List<Cart> getCartList();
}
