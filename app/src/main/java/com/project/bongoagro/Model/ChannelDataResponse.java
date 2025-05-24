package com.project.bongoagro.Model;


import java.util.List;

public class ChannelDataResponse {
    private Channel Channel;
    private List<Feed> Feeds;

    // Getters and Setters
    public Channel getChannel() {
        return Channel;
    }

    public void setChannel(Channel channel) {
        this.Channel = channel;
    }

    public List<Feed> getFeeds() {
        return Feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.Feeds = feeds;
    }
}
