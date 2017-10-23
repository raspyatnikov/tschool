package com.tsystems.tschool.logiweb.controllers;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class AjaxResponseBody {

    @JsonView
    String msg;

    @JsonView
    String code;

    @JsonView
    List<String> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
