package com.cis385.mssu.catclickercitadel.ui.shop;

public class RefreshBool {
    private boolean refresh = false;
    private ChangeListener listener;

    public boolean isBoo() {
        return refresh;
    }

    public void setBoo(boolean boo) {
        this.refresh = boo;
        if (listener != null) listener.onChange();
    }

    public ChangeListener getListener() {
        return listener;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public interface ChangeListener {
        void onChange();
    }
}