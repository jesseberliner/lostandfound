package com.erickson.lostandfound.Controllers;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import com.erickson.lostandfound.Models.Item;
import com.erickson.lostandfound.Repositories.ItemRepository;
import com.erickson.lostandfound.Services.CloudinaryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class MainController
{

    @Autowired
    ItemRepository itemRepo;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping({"/index","/"})
    public String welcomePage()
    {
        System.out.println("index");
        return "index";
    }

    @RequestMapping({"/allItems"})
    public String allItems(Model model)
    {
        System.out.println("all");
        model.addAttribute("allItems", itemRepo.findAll());
        return "allItems";
    }

    @GetMapping({"/addItem"})
    public String addItem(Model model)
    {
        model.addAttribute("item", new Item());
        return "addItem";
    }

    @PostMapping({"/addItem"})
//    public String addedItem(@Valid @ModelAttribute("newItem")Item item,
//                            @RequestParam("file") MultipartFile file, BindingResult result)
    public String addedItem(@ModelAttribute("item")Item item, @RequestParam("file")MultipartFile file)
    {
        if(file.isEmpty())
        {
            //model.addAttribute("dish", dish);
            return "redirect:/add";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));


            String name = uploadResult.get("public_id").toString();
            String test = cloudc.createSmallImage(uploadResult.get("url").toString(), 120, 120);
            System.out.println("test:" +test);

            item.setItemPicture1(uploadResult.get("url").toString());
            itemRepo.save(item);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/add";
        }
        return "addedItem";
    }


    @RequestMapping("/detail/{id}")
    public String showItem(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("item", itemRepo.findById(id).get());
        return "showItem";
    }

    @RequestMapping("/update/{id}")
    public String updateItem(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("item", itemRepo.findById(id).get());
        return "addItem";
    }

    @RequestMapping("/delete/{id}")
    public String delItem(@PathVariable("id") long id)
    {
        Item item = itemRepo.findById(id).get();
        item.setItemIsDeleted(true);
        return "redirect:/allItems";
    }
}
