package com.naumstore.controller;

import com.naumstore.controller.converter.OrderMapper;
import com.naumstore.controller.entity_request.OrderProductLinkEntityRequest;
import com.naumstore.controller.entity_request.OrderRequest;
import com.naumstore.domain.order.Order;
import com.naumstore.domain.order.OrderProductLinkEntity;
import com.naumstore.domain.product.Product;
import com.naumstore.domain.user.User;
import com.naumstore.exception.SystemStoreWorkException;
import com.naumstore.service.OrderService;
import com.naumstore.service.ProductService;
import com.naumstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static com.naumstore.controller.DefaultResponseTag.ORDER;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;

    private final OrderMapper orderMapper;

    @PostMapping("/{id}/orders")
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public ResponseEntity<Object> addOrder(@PathVariable("id") String id,
                                           @RequestBody @Valid OrderRequest orderRequest) {

        long userId = Long.parseLong(id);

        User user = userService.findById(userId);

        Order order = new Order();
        order.setUser(user);
        order.setProducts(new HashSet<>());

        orderService.create(order);

        List<OrderProductLinkEntity> products = new ArrayList<>();

        double totalPrice = 0;

        for (OrderProductLinkEntityRequest linkEntityRequest : orderRequest.getOrders()) {
            OrderProductLinkEntity linkEntity = new OrderProductLinkEntity();
            linkEntity.setOrder(order);

            Product product = productService.findById(linkEntityRequest.getId());

            int count = product.getCount();
            if (count < linkEntityRequest.getCount()) {
                throw new SystemStoreWorkException(String.format(
                        "The required count of the product \"%s\" exceeds " +
                                "the count of the product in stock.", product.getName()));
            }
            product.setCount(count - linkEntityRequest.getCount());

            if (linkEntityRequest.getPrice() != product.getPrice()) {
                throw new SystemStoreWorkException(String.format(
                        "The price \"%s\" was changed.", product.getName()));
            }

            totalPrice += linkEntityRequest.getPrice() * linkEntityRequest.getCount();

            linkEntity.setProduct(product);
            linkEntity.setProductCount(linkEntityRequest.getCount());
            linkEntity.setProductPrice(linkEntityRequest.getPrice());

            products.add(linkEntity);
        }

        orderService.saveAll(products);

        order.setTotalPrice(totalPrice);
        order.getProducts().addAll(products);

        orderService.create(order);

        return new ResponseEntity<>(
                Collections.singletonMap(ORDER, orderMapper.mapToResponse(order)),
                HttpStatus.OK
        );
    }
}
