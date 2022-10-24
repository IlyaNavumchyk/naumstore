package com.naumstore.controller.request;

import lombok.Getter;
import lombok.Setter;

/**
 * This request need to software remove or block an entity.
 * If the isDeleted parameter is null - temporary blocking;
 * true - software remove;
 * false - unblock.
 */
@Getter
@Setter
public class BlockRequest {

    Boolean isDeleted;
}
