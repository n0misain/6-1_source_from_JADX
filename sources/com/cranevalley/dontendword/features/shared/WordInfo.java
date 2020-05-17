package com.cranevalley.dontendword.features.shared;

import com.google.gson.annotations.SerializedName;

public class WordInfo {
    @SerializedName("text")
    private String definition;
    @SerializedName("partOfSpeech")
    private String partOfSpeech;
    @SerializedName("word")
    private String word;

    public WordInfo(String word, String partOfSpeech, String definition) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
    }

    public String getWord() {
        return this.word;
    }

    @SerializedName("partOfSpeech")
    public String getPartOfSpeech() {
        return this.partOfSpeech;
    }

    @SerializedName("text")
    public String getDefinition() {
        return this.definition;
    }
}
