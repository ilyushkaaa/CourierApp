package com.example.deliverallapp;

public class PackageDocs extends Package{
    private String docsFrom;
    private String docsTo;
    public PackageDocs(String size, boolean is_fragile, boolean needCar, String docsFrom,
                       String docsTo){
        setSize(size);
        setFragile(is_fragile);
        setNeedCar(needCar);
        this.docsFrom = docsFrom;
        this.docsTo = docsTo;
        setType("docs");
    }

    public String getDocsFrom() {
        return docsFrom;
    }

    public void setDocsFrom(String docsFrom) {
        this.docsFrom = docsFrom;
    }

    public String getDocsTo() {
        return docsTo;
    }

    public void setDocsTo(String docsTo) {
        this.docsTo = docsTo;
    }
}
