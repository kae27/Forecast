package com.example.dell.forecast;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Dell on 3/17/2017.
 */

public class LocationMapResponseModel
{

    public LocationMapResponseModel(String date, float wamDragCo, float wam_mPerio, float wam_pPerio, float wam_mFreqy, float wam_pFreqy, float wamUstar10, float wamWaveDir, float wamWaveHgt, float wamWaveSte, float wamWindDir, float wamWindSpd) {
        Date = date;
        WamDragCo = wamDragCo;
        Wam_mPerio = wam_mPerio;
        Wam_pPerio = wam_pPerio;
        Wam_mFreqy = wam_mFreqy;
        Wam_pFreqy = wam_pFreqy;
        WamUstar10 = wamUstar10;
        WamWaveDir = wamWaveDir;
        WamWaveHgt = wamWaveHgt;
        WamWaveSte = wamWaveSte;
        WamWindDir = wamWindDir;
        WamWindSpd = wamWindSpd;
    }

    public float getWamDragCo() {
        return WamDragCo;
    }

    public void setWamDragCo(float wamDragCo) {
        WamDragCo = wamDragCo;
    }

    public float getWam_mPerio() {
        return Wam_mPerio;
    }

    public void setWam_mPerio(float wam_mPerio) {
        Wam_mPerio = wam_mPerio;
    }

    public float getWam_pPerio() {
        return Wam_pPerio;
    }

    public void setWam_pPerio(float wam_pPerio) {
        Wam_pPerio = wam_pPerio;
    }

    public float getWam_mFreqy() {
        return Wam_mFreqy;
    }

    public void setWam_mFreqy(float wam_mFreqy) {
        Wam_mFreqy = wam_mFreqy;
    }

    public float getWam_pFreqy() {
        return Wam_pFreqy;
    }

    public void setWam_pFreqy(float wam_pFreqy) {
        Wam_pFreqy = wam_pFreqy;
    }

    public float getWamUstar10() {
        return WamUstar10;
    }

    public void setWamUstar10(float wamUstar10) {
        WamUstar10 = wamUstar10;
    }

    public float getWamWaveDir() {
        return WamWaveDir;
    }

    public void setWamWaveDir(float wamWaveDir) {
        WamWaveDir = wamWaveDir;
    }

    public float getWamWaveHgt() {
        return WamWaveHgt;
    }

    public void setWamWaveHgt(float wamWaveHgt) {
        WamWaveHgt = wamWaveHgt;
    }

    public float getWamWaveSte() {
        return WamWaveSte;
    }

    public void setWamWaveSte(float wamWaveSte) {
        WamWaveSte = wamWaveSte;
    }

    public float getWamWindDir() {
        return WamWindDir;
    }

    public void setWamWindDir(float wamWindDir) {
        WamWindDir = wamWindDir;
    }

    public float getWamWindSpd() {
        return WamWindSpd;
    }

    public void setWamWindSpd(float wamWindSpd) {
        WamWindSpd = wamWindSpd;
    }

    @SerializedName("date")
    String Date;

    @SerializedName("WAM_DRAG_CO")
    float WamDragCo;

    @SerializedName("WAM_M_PERIO")
    float Wam_mPerio;

    @SerializedName("WAM_P_PERIO")
    float Wam_pPerio;

    @SerializedName("WAM_M_FREQY")
    float Wam_mFreqy;

    @SerializedName("WAM_P_FREQY")
    float Wam_pFreqy;

    @SerializedName("WAM_USTAR10")
    float WamUstar10;

    @SerializedName("WAM_WAVEDIR")
    float WamWaveDir;

    @SerializedName("WAM_WAVEHGT")
    float WamWaveHgt;

    @SerializedName("WAM_WAVESTE")
    float WamWaveSte;

    @SerializedName("WAM_WINDDIR")
    float WamWindDir;

    @SerializedName("WAM_WINDSPD")
    float WamWindSpd;

    @SerializedName("success")
    int success;




    @SerializedName("tmd")
    ArrayList<LocationMapResponseModel> responseModels;



    public ArrayList<LocationMapResponseModel> getResponseModels() {
        return responseModels;
    }

    public void setResponseModels(ArrayList<LocationMapResponseModel> responseModels) {
        this.responseModels = responseModels;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }



    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
