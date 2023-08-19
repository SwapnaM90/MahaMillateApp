package com.mninetytechnology.mahamillateapp.models.viewmodelobj;

public class Questions {
    private String question_en;
    private String question_hd;
    private String question_mr;
    private Options options;
    private boolean isImage;
    private String answer;
    private String _id;

    public String getQuestion_en() {
        return question_en;
    }

    public void setQuestion_en(String question_en) {
        this.question_en = question_en;
    }

    public String getQuestion_hd() {
        return question_hd;
    }

    public void setQuestion_hd(String question_hd) {
        this.question_hd = question_hd;
    }

    public String getQuestion_mr() {
        return question_mr;
    }

    public void setQuestion_mr(String question_mr) {
        this.question_mr = question_mr;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
