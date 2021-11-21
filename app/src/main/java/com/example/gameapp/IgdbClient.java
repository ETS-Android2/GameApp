package com.example.gameapp;

import android.content.Context;
import android.util.Log;


import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.apicalypse.Sort;
import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.ProtoRequestKt;
import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.Endpoint;
import com.api.igdb.utils.Endpoints;
import com.api.igdb.utils.TwitchToken;
import com.api.igdb.*;
import com.codepath.asynchttpclient.BuildConfig;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.oauth.OAuthBaseClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import proto.Game;
import proto.GameResult;

public class IgdbClient{
    public static final String REST_CONSUMER_KEY = "c3vyvocsr3oyvgbip5bm3tp0mchc2w";
    public static final String REST_CONSUMER_SECRET = "n0v4v5a34pcu9aonyniyi69dbnlxdf";
    private String accessToken = "bearer ";

    public IgdbClient() {

    }


}
