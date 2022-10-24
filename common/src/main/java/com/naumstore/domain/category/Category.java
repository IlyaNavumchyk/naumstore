package com.naumstore.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.naumstore.domain.product.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
@EqualsAndHashCode(exclude = "products")
@JsonIgnoreProperties("products")
@Cacheable("categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "category_name")
    @Enumerated(value = EnumType.STRING)
    private ProductCategories categoryName;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<Product> products;
}
