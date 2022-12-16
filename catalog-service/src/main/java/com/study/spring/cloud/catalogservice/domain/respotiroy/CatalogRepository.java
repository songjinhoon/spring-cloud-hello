package com.study.spring.cloud.catalogservice.domain.respotiroy;

import com.study.spring.cloud.catalogservice.domain.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CatalogRepository extends JpaRepository<Catalog, UUID> {
}
