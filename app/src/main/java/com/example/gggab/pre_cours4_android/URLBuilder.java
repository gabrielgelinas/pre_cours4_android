package com.example.gggab.pre_cours4_android;

/**
 * Created by gggab(Zombietux) on 2018-01-29.
 */

class URLBuilder {
    private int page;
    private String prefix;
    private String suffix;

    URLBuilder(int page, String prefix, String suffix) {
        this.page = page;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    String getURL(){
        return this.prefix+this.page+this.suffix;
    }
}

