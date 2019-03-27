package com.erickson.lostandfound.Repositories;

import com.erickson.lostandfound.Models.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ItemRepository extends CrudRepository<Item, Long>
{
    Set<Item> findAllByItemIsDeletedIsFalse();
}
