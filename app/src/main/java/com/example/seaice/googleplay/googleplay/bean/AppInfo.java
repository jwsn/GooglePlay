package com.example.seaice.googleplay.googleplay.bean;

/**
 * Created by seaice on 2016/8/5.
 */
public class AppInfo {
    private long id;
    private String name;
    private String pkgName;
    private String iconUrl;
    private float star;
    private long size;
    private String downloadUrl;
    private String des;

    public AppInfo(long id, String name, String pkgName, String iconUrl, float star, long size, String downloadUrl, String des) {
        this.id = id;
        this.name = name;
        this.pkgName = pkgName;
        this.iconUrl = iconUrl;
        this.star = star;
        this.size = size;
        this.downloadUrl = downloadUrl;
        this.des = des;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pkgName='" + pkgName + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", star=" + star +
                ", size=" + size +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
