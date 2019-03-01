package com.erickson.lostandfound.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryConfig
{
    private Cloudinary cloudinary;
    @Autowired
    public CloudinaryConfig(@Value("${cloudinary.apikey}") String key,
                            @Value("${cloudinary.apisecret}") String secret,
                            @Value("${cloudinary.cloudname}") String cloud)
    {
        cloudinary = Singleton.getCloudinary();
        cloudinary.config.cloudName = cloud;
        cloudinary.config.apiSecret = secret;
        cloudinary.config.apiKey = key;
    }

    public Map upload(Object file, Map options)
    {
        try{
            return cloudinary.uploader().upload(file, options);
        } catch (IOException e){
            System.out.println("IOException");
            e.printStackTrace();
            return null;
        }
    }

    public String createURL(String name, int width, int height, String action)
    {
        return cloudinary.url().transformation(new Transformation().width(width).height(height).border(
                "2px_solid_block").crop(action)).imageTag(name);
    }

    public String createSmallImage(String url, int width, int height)
    {
        //Creates a transformation from the URL provided
        //return cloudinary.url().transformation(new Transformation().width(width).height(height)).toString();
        System.out.println(url);
        System.out.println(cloudinary.url());
        System.out.println(cloudinary.url().toString());
        System.out.println(cloudinary.url().transformation());
        System.out.println(cloudinary.url().transformation().toString());
        System.out.println(cloudinary.url().transformation(new Transformation().width(100)));
        System.out.println(cloudinary.url().transformation(new Transformation().width(100)).toString());
        System.out.println(cloudinary.url().transformation(new Transformation().width(100)).type("fetch"));
        System.out.println(cloudinary.url().transformation(new Transformation().width(100)).type("fetch").toString());
        System.out.println(cloudinary.url().transformation(new Transformation().width(100)).generate(url));
        return cloudinary.url().transformation(new Transformation().width(width).height(height)).generate(url);

    }
}
