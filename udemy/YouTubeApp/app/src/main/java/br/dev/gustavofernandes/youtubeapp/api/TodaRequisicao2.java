package br.dev.gustavofernandes.youtubeapp.api;

public class TodaRequisicao2 {
    public class ListaVideosResponse {
        private String kind;
        private String etag;
        private String nextPageToken;
        private String regionCode;
        private PageInfo pageInfo;
        private Item[] items;

        public String getKind() {
            return kind;
        }

        public void setKind(String value) {
            this.kind = value;
        }

        public String getEtag() {
            return etag;
        }

        public void setEtag(String value) {
            this.etag = value;
        }

        public String getNextPageToken() {
            return nextPageToken;
        }

        public void setNextPageToken(String value) {
            this.nextPageToken = value;
        }

        public String getRegionCode() {
            return regionCode;
        }

        public void setRegionCode(String value) {
            this.regionCode = value;
        }

        public PageInfo getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfo value) {
            this.pageInfo = value;
        }

        public Item[] getItems() {
            return items;
        }

        public void setItems(Item[] value) {
            this.items = value;
        }
    }


    public class Item {
        private String kind;
        private String etag;
        private ID id;
        private Snippet snippet;

        public Snippet getSnippet() { return snippet; }
        public void setSnippet(Snippet value) { this.snippet = value; }

        public String getKind() {
            return kind;
        }

        public void setKind(String value) {
            this.kind = value;
        }

        public String getEtag() {
            return etag;
        }

        public void setEtag(String value) {
            this.etag = value;
        }

        public ID getID() {
            return id;
        }

        public void setID(ID value) {
            this.id = value;
        }
    }

    public class Snippet {
        private String publishedAt;
        private String channelID;
        private String title;
        private String description;
        private Thumbnails thumbnails;
        private String channelTitle;
        private String liveBroadcastContent;
        private String publishTime;

        public String getPublishedAt() { return publishedAt; }
        public void setPublishedAt(String value) { this.publishedAt = value; }

        public String getChannelID() { return channelID; }
        public void setChannelID(String value) { this.channelID = value; }

        public String getTitle() { return title; }
        public void setTitle(String value) { this.title = value; }

        public String getDescription() { return description; }
        public void setDescription(String value) { this.description = value; }

        public Thumbnails getThumbnails() { return thumbnails; }
        public void setThumbnails(Thumbnails value) { this.thumbnails = value; }

        public String getChannelTitle() { return channelTitle; }
        public void setChannelTitle(String value) { this.channelTitle = value; }

        public String getLiveBroadcastContent() { return liveBroadcastContent; }
        public void setLiveBroadcastContent(String value) { this.liveBroadcastContent = value; }

        public String getPublishTime() { return publishTime; }
        public void setPublishTime(String value) { this.publishTime = value; }
    }

    public class Thumbnails {
        private Default thumbnailsDefault;
        private Default medium;
        private Default high;

        public Default getThumbnailsDefault() { return thumbnailsDefault; }
        public void setThumbnailsDefault(Default value) { this.thumbnailsDefault = value; }

        public Default getMedium() { return medium; }
        public void setMedium(Default value) { this.medium = value; }

        public Default getHigh() { return high; }
        public void setHigh(Default value) { this.high = value; }
    }
    public class Default {
        private String url;
        private Long width;
        private Long height;

        public String getURL() { return url; }
        public void setURL(String value) { this.url = value; }

        public Long getWidth() { return width; }
        public void setWidth(Long value) { this.width = value; }

        public Long getHeight() { return height; }
        public void setHeight(Long value) { this.height = value; }
    }
    public class ID {
        private String kind;
        private String channelID;
        private String videoId;

        public String getKind() {
            return kind;
        }

        public void setKind(String value) {
            this.kind = value;
        }

        public String getChannelID() {
            return channelID;
        }

        public void setChannelID(String value) {
            this.channelID = value;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String value) {
            this.videoId = value;
        }
    }

    public class PageInfo {
        private long totalResults;
        private long resultsPerPage;

        public long getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(long value) {
            this.totalResults = value;
        }

        public long getResultsPerPage() {
            return resultsPerPage;
        }

        public void setResultsPerPage(long value) {
            this.resultsPerPage = value;
        }
    }
}