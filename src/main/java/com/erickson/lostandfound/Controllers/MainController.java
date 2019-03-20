package com.erickson.lostandfound.Controllers;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import com.erickson.lostandfound.Models.Item;
import com.erickson.lostandfound.Models.User;
import com.erickson.lostandfound.Repositories.ItemRepository;
import com.erickson.lostandfound.Services.CloudinaryConfig;
import com.erickson.lostandfound.Services.CustomUserDetails;
import com.erickson.lostandfound.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class MainController
{

    @Autowired
    ItemRepository itemRepo;

    @Autowired
    private UserService userService;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping({"/index","/"})
    public String welcomePage()
    {
        return "index";
    }



    @RequestMapping({"/allItems"})
    public String allItems(Principal principal, Model model)
    {
        User myuser = ((CustomUserDetails)((UsernamePasswordAuthenticationToken)principal).getPrincipal()).getUser();
        model.addAttribute("myuser", myuser);
        model.addAttribute("allItems", itemRepo.findAllByItemIsDeletedIsFalse());
        return "allItems";
    }

    @GetMapping({"/addItem"})
    public String addItem(Model model)
    {
        model.addAttribute("item", new Item());
        return "addItem";
    }

    @PostMapping({"/addItem"})
//    public String addedItem(@Valid @ModelAttribute("item")Item item, BindingResult result,
//                            @RequestParam("file") MultipartFile file)
    public String addedItem(@ModelAttribute("item")Item item, @RequestParam("file")MultipartFile file, @RequestParam("file2")MultipartFile file2)
    {
        if(file.isEmpty())
        {
            return "redirect:/add";
        }
        try {


            /*
            public_id returns just the name of the image as an object
            url returns the entire url as an object
            */
            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            String transformedImage = cloudc.createUrl(uploadResult.get("public_id").toString());
            item.setItemPicture1(transformedImage);

            Map uploadResult2 = cloudc.upload(file2.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            String transformedImage2 = cloudc.createUrl(uploadResult2.get("public_id").toString());
            item.setItemPicture2(transformedImage2);

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
        itemRepo.save(item);
        return "redirect:/allItems";
    }
}
