package com.erickson.lostandfound.Repositories;

import com.erickson.lostandfound.Models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long>
{
}
