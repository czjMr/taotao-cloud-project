package com.taotao.cloud.sensitive.sensitive.sensitive.model.bugs;


import com.taotao.cloud.sensitive.sensitive.sensitive.annotation.SensitiveEntry;

public class Father {

    @SensitiveEntry
    private Child child;

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
