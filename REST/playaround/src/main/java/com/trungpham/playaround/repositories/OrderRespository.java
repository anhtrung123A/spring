package com.trungpham.playaround.repositories;

import com.trungpham.playaround.domains.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRespository extends CrudRepository<OrderEntity, Integer> {
}
