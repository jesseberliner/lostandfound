package com.erickson.lostandfound.Repositories;


import com.erickson.lostandfound.Models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long>
{
}
