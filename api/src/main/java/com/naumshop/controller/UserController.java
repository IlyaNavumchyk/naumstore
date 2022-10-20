package com.naumshop.controller;

import com.google.gson.Gson;
import com.naumshop.controller.converters.UserMapper;
import com.naumshop.controller.dto.order.OrderDTO;
import com.naumshop.controller.dto.order.OrderProductLinkDTO;
import com.naumshop.controller.dto.user.UserDTO;
import com.naumshop.controller.request.DeleteRequest;
import com.naumshop.domain.order.Order;
import com.naumshop.domain.order.OrderProductLinkEntity;
import com.naumshop.domain.product.Product;
import com.naumshop.domain.user.User;
import com.naumshop.exception.SystemStoreWorkException;
import com.naumshop.service.OrderService;
import com.naumshop.service.ProductService;
import com.naumshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.naumshop.controller.DefaultResponseTag.USER;
import static com.naumshop.controller.DefaultResponseTag.USERS;
import static com.naumshop.controller.DefaultResponseTag.ORDER;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    //
    private final OrderService orderService;

    //
    private final ProductService productService;

    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<Object> findAll() {

        return new ResponseEntity<>(
                Collections.singletonMap(USERS, userService.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") String id) {

        Long userId = Long.parseLong(id);

        return new ResponseEntity<>(
                Collections.singletonMap(USERS, userService.findById(userId)),
                HttpStatus.OK
        );
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserDTO userDTO) {

        User user = mapper.mapForCreate(userDTO);

        userService.create(user);

        return new ResponseEntity<>(
                Collections.singletonMap(USER, userService.findById(user.getId())),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id,
                                         @RequestBody UserDTO userDTO) {

        long userId = Long.parseLong(id);

        User user = userService.findById(userId);

        mapper.mapForUpdate(userDTO, user);

        userService.update(user);

        return new ResponseEntity<>(
                Collections.singletonMap(USER, user),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id,
                                         @RequestBody DeleteRequest isDel) {

        long userId = Long.parseLong(id);
        Boolean isDeleted = isDel.getIsDeleted();

        User user = userService.findById(userId);
        user.setIsDeleted(isDeleted);

        userService.update(user);

        return new ResponseEntity<>(
                Collections.singletonMap(USER, user),
                HttpStatus.OK
        );
    }

    @PostMapping("/{id}/orders")
    public ResponseEntity<Object> addOrder(@PathVariable("id") String id,
                                           @RequestBody OrderDTO request) {


        for (OrderProductLinkDTO lol : request.getOrders()) {
            System.out.println(lol.getId() + " " + lol.getCount() +
                    " " + lol.getPrice());
        }

        Long userId = Long.parseLong(id);

        User user = userService.findById(userId);

        Order order = new Order();
        order.setUser(user);

        orderService.create(order);

        List<OrderProductLinkEntity> products = new ArrayList<>();

        double totalPrice = 0;

        for (OrderProductLinkDTO link : request.getOrders()) {
            OrderProductLinkEntity product = new OrderProductLinkEntity();
            product.setOrder(order);

            Product tempProduct = productService.findById(link.getId());
            int count = tempProduct.getCount();

            if (count < link.getCount()) {
                throw new SystemStoreWorkException(String.format(
                        "The required count of the product \"%s\" exceeds " +
                                "the count of the product in stock.", tempProduct.getName()));
            }
            tempProduct.setCount(count - link.getCount());

           /* if (link.getPrice() != tempProduct.getPrice()) {
                throw new SystemStoreWorkException(String.format(
                        "The price \"%s\" was changed.", tempProduct.getName()));
            }*/

            totalPrice += link.getPrice() * link.getCount();

            product.setProduct(tempProduct);
            product.setProductCount(link.getCount());
            product.setProductPrice(link.getPrice());

            products.add(product);
        }

        orderService.saveAll(products);

        order.setTotalPrice(totalPrice);
        order.getProducts().addAll(products);

        orderService.create(order);

        return new ResponseEntity<>(
                Collections.singletonMap(ORDER, order),
                HttpStatus.OK
        );
    }
}
