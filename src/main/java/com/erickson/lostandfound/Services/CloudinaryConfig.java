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

        return cloudinary.url().transformation(new Transformation().width(width).height(height)).generate(url);

    }
    public String createUrl(String name) {
        //This method generates the URL for the actor's list
        //return cloudinary.url().transformation(new Transformation().width(100).height(100).crop("fill").radius(50).gravity("face")
        return cloudinary.url().transformation(new Transformation().effect("blackwhite").height(100).radius(25).width(100).crop("scale")).generate(name);


    }

    public String createUrl(String name, int width, int height) {
        //This method generates the URL for an image whose name is known and has been provided
        return cloudinary.url().transformation(new Transformation().width(width).height(height).crop("fill").radius(50).gravity("face")
        ).generate(name);

    }


}
