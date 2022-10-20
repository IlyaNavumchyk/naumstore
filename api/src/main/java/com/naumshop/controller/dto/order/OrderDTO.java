package com.naumshop.controller.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {

    List<OrderProductLinkDTO> orders;
}
