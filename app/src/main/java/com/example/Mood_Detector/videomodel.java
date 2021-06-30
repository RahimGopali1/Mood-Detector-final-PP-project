package com.example.Mood_Detector;

public class videomodel
{
    String videoName,videoUri;

    public videomodel(String videoName, String videoUri) {
    }
    videomodel()
    {

    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }
}
