package com.qtlimited.urls.service;

import com.qtlimited.urls.payload.BodyResponse;

public interface URLsServiceInterface {
    BodyResponse shortenUrl(String url) throws Exception;

    BodyResponse getUrls() throws Exception;

    BodyResponse getUrlsAnalytics(String url) throws Exception;
}
