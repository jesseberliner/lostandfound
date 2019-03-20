package com.erickson.lostandfound.Models;

import com.erickson.lostandfound.Services.UserService;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemId;

    private String itemName;

    private String itemDescription;

    private String itemFoundLocation;

    private String itemLocation;

    private Date itemFoundDate;

    private Date itemClaimedDeletedDate;

    private String itemPicture1;

    private String itemPicture2;

    private boolean itemIsClaimed;

    private boolean itemIsDeleted;

    private String itemClaimedBy;

    private String itemClaimedThrough;

    @ManyToOne(fetch = FetchType.LAZY)
    private User itemAddedBy;

    public Item()
    {
        this.itemFoundDate = new Date();
        this.itemIsClaimed = false;
        this.itemIsDeleted = false;
    }

    public long getItemId()
    {
        return itemId;
    }

    public void setItemId(long itemId)
    {
        this.itemId = itemId;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemDescription()
    {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }

    public String getItemFoundLocation()
    {
        return itemFoundLocation;
    }

    public void setItemFoundLocation(String itemFoundLocation)
    {
        this.itemFoundLocation = itemFoundLocation;
    }

    public String getItemLocation()
    {
        return itemLocation;
    }

    public void setItemLocation(String itemLocation)
    {
        this.itemLocation = itemLocation;
    }

    public Date getItemFoundDate()
    {
        return itemFoundDate;
    }

    public void setItemFoundDate(Date itemFoundDate)
    {
        this.itemFoundDate = itemFoundDate;
    }

    public Date getItemClaimedDeletedDate()
    {
        return itemClaimedDeletedDate;
    }

    public void setItemClaimedDeletedDate(Date itemClaimedDeletedDate)
    {
        this.itemClaimedDeletedDate = itemClaimedDeletedDate;
    }

    public String getItemPicture1()
    {
        return itemPicture1;
    }

    public void setItemPicture1(String itemPicture1)
    {
        this.itemPicture1 = itemPicture1;
    }

    public String getItemPicture2()
    {
        return itemPicture2;
    }

    public void setItemPicture2(String itemPicture2)
    {
        this.itemPicture2 = itemPicture2;
    }

    public boolean isItemIsClaimed()
    {
        return itemIsClaimed;
    }

    public void setItemIsClaimed(boolean itemIsClaimed)
    {
        this.itemIsClaimed = itemIsClaimed;
    }

    public boolean isItemIsDeleted()
    {
        return itemIsDeleted;
    }

    public void setItemIsDeleted(boolean itemIsDeleted)
    {
        this.itemIsDeleted = itemIsDeleted;
    }

    public User getItemAddedBy() {
        return itemAddedBy;
    }

    public void setItemAddedBy(User itemAddedBy) {
        this.itemAddedBy = itemAddedBy;
    }

    public String getItemClaimedBy() {
        return itemClaimedBy;
    }

    public void setItemClaimedBy(String itemClaimedBy) {
        this.itemClaimedBy = itemClaimedBy;
    }

    public String getItemClaimedThrough() {
        return itemClaimedThrough;
    }

    public void setItemClaimedThrough(String itemClaimedThrough) {
        this.itemClaimedThrough = itemClaimedThrough;
    }
}
