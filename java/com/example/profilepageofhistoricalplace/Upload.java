package com.example.profilepageofhistoricalplace;

public class Upload {
    private String imgName;
    private String imagDescription;
    private String imgUrl;

    public Upload() {
    }

    public String getImagDescription() {
        return imagDescription;
    }

    public void setImagDescription(String imagDescription) {
        this.imagDescription = imagDescription;
    }

    public Upload(String imgName, String imgUrl,String Imagedes) {
        if(imgName.trim().equals(""))
        {
            imgName="No name";
        }
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.imagDescription=Imagedes;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

