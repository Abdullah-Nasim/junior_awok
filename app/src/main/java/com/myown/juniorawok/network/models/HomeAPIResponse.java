package com.myown.juniorawok.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Abdullah on 2/13/2018.
 *
 * This is a model class for home products API.
 */

public class HomeAPIResponse {

    @SerializedName("STATUS")
    private STATUS sTATUS;
    @SerializedName("OUTPUT")
    private OUTPUT oUTPUT;
    @SerializedName("API")
    private API aPI;

    public STATUS getsTATUS() {
        return sTATUS;
    }

    public OUTPUT getoUTPUT() {
        return oUTPUT;
    }

    private class STATUS
    {
        @SerializedName("CODE")
        private int cODE;
        @SerializedName("MESSAGE")
        private String mESSAGE;
        @SerializedName("KEY")
        private String kEY;
        @SerializedName("LCR")
        private int lCR;
        @SerializedName("ILD")
        private String iLD;
        @SerializedName("NO_CACHE")
        private String nO_CACHE;
        @SerializedName("MSRV")
        private String mSRV;
    }

    public class IMAGE
    {
        @SerializedName("SRC")
        private String sRC;

        public String getsRC() {
            return sRC;
        }
    }

    public class PRICES
    {
        @SerializedName("PRICE_NEW")
        private String pRICE_NEW;
        @SerializedName("PRICE_OLD")
        private String pRICE_OLD;
        @SerializedName("DISCOUNT")
        private  String dISCOUNT;
        @SerializedName("PERCENT")
        private String pERCENT;

        public String getpRICE_NEW() {
            return pRICE_NEW;
        }

        public String getpRICE_OLD() {
            return pRICE_OLD;
        }
    }

    private class CARTDATA
    {
        @SerializedName("SKU")
        private  String sKU;
        @SerializedName("UID")
        private  String uID;
        @SerializedName("SSID")
        private  String sSID;
    }

    public class ITEM
    {
        @SerializedName("ID")
        private String iD;
        @SerializedName("LINKED_PROD_ID")
        private String lINKED_PROD_ID;
        @SerializedName("LINKED_PROD_CODE")
        private String lINKED_PROD_CODE;
        @SerializedName("LINKED_SECTION_CODE")
        private String lINKED_SECTION_CODE;
        @SerializedName("NAME")
        String nAME;
        @SerializedName("SORT")
        private String sORT;
        @SerializedName("IMAGE_FILE")
        private String iMAGE_FILE;
        @SerializedName("ACTIVE_FROM")
        private String aCTIVE_FROM;
        @SerializedName("ACTIVE_FROM_FORMATTED")
        private String aCTIVE_FROM_FORMATTED;
        @SerializedName("ACTIVE_TO")
        private String aCTIVE_TO;
        @SerializedName("DATE_DIFFERENCE")
        private String dATE_DIFFERENCE;
        @SerializedName("IMAGE")
        IMAGE iMAGE;
        @SerializedName("PRICES")
        PRICES pRICES;
        @SerializedName("CART_DATA")
        private CARTDATA cART_DATA;
        @SerializedName("PRODUCT_LINK")
        private String pRODUCT_LINK;

        public String getnAME() {
            return nAME;
        }

        public IMAGE getiMAGE() {
            return iMAGE;
        }

        public PRICES getpRICES() {
            return pRICES;
        }
    }

    public class DATA
    {
        @SerializedName("ITEMS")
        private List<ITEM> iTEMS;
        @SerializedName("TITLE")
        private String tITLE;
        @SerializedName("DOMAIN")
        private String dOMAIN;

        public List<ITEM> getiTEMS() {
            return iTEMS;
        }
    }

    public class NAVIGATION
    {
        @SerializedName("TOTAL")
        private int tOTAL;
        @SerializedName("COUNT")
        private int cOUNT;
        @SerializedName("PAGE")
        private int pAGE;
        @SerializedName("MAX_PAGES")
        int mAX_PAGES;

        public int getmAX_PAGES() {
            return mAX_PAGES;
        }
    }

    public class OUTPUT
    {
        @SerializedName("DATA")
        private DATA dATA;
        @SerializedName("NAVIGATION")
        private NAVIGATION nAVIGATION;

        public DATA getdATA() {
            return dATA;
        }

        public NAVIGATION getnAVIGATION() {
            return nAVIGATION;
        }
    }

    private class URI
    {
        @SerializedName("SOURCE")
        private String sOURCE;
        @SerializedName("PARSED")
        private String pARSED;
    }

    private class API
    {
        @SerializedName("MIN_APP_VER")
        private int mIN_APP_VER;
        @SerializedName("MIN_APP_VERSION")
        private int mIN_APP_VERSION;
        @SerializedName("VERSION")
        private double vERSION;
        @SerializedName("FORMAT")
        private String fORMAT;
        @SerializedName("LANG")
        private String lANG;
        @SerializedName("CURRENCY")
        private String cURRENCY;
        @SerializedName("CURRENCY_SYM")
        private String cURRENCY_SYM;
        @SerializedName("URI")
        private URI uRI;
    }
}
