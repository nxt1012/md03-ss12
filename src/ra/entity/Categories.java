package ra.entity;

import ra.IShop;

public class Categories implements IShop {
    //    1. Attributes
    private int catalogId;
    private String catalogName;
    private boolean status;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, boolean status) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.status = status;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData() {
    }

    @Override
    public void displayData() {
        System.out.println("catalogId=" + catalogId + "catalogName=" + catalogName  + "status=" + status);
    }
}
