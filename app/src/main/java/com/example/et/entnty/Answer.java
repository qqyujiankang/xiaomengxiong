package com.example.et.entnty;

/**
 * 预约答案
 */
public class Answer {
    private boolean isChecked;
    private String string;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Answer(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
