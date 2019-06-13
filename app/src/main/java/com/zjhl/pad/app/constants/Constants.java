package com.zjhl.pad.app.constants;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;

/*
 * File: Constants.java
 * Author: DELL
 * Version: V1.0
 * Create: 2018/4/3 10:24
 * Changes (from 2018/4/3)
 */
public class Constants {

    //开发环境
    //root目录
//    public static final String API_ROOT = "http://139.199.116.36:8005/";
//    文件上传
//    public static final String API_ROOT1 = "http://139.199.116.36:8002/";


//    //测试环境
    //root目录
//    public static final String API_ROOT = "http://192.144.138.58:38080/";
//    文件上传
//    public static final String API_ROOT1 = "http://192.144.138.58:28080/";

    //    //UAT线上测试环境
    //root目录
//    public static final String API_ROOT = "http://123.207.151.28:38080/";
    //文件上传
//    public static final String API_ROOT1 = "http://123.207.151.28:28080/";

//    //生产环境
    //root目录
    public static final String API_ROOT = "https://trade.newnewchain.com/";
//    //文件上传
    public static final String API_ROOT1 = "https://assetx-API.newnewchain.com/";
    //问题列表
    public static final String API_ROOT2 = "https://assetx.newnewchain.com/";


    /**
     * 网络相关目录
     *
     * @author pluto
     */
    public static class NETPATH {
        //注册接口
        public static final String REGISTERPATH = "common/companyOrg/register";
        //获取注册验证码接口
        public static final String REGISTERCODEPATH = "common/companyOrg/getCode4Register";
        //异步校验手机号、邮箱唯一接口
        public static final String REGISTERCHECKEMAILPHONEPATH = "common/companyOrg/checkEailOrPhone";
        //异步校验企业信息
        public static final String CHECKCOMPNEYNAMEPHONEPATH = "common/companyOrg/checkCompanyName";
        //机构注册时生成图片验证码接口
        public static final String REGISTERIMGPATH = "common/companyOrg/getImgCode4Register";
        //获取机构类型接口
        public static final String REGISTERDICTIONPATH = "common/dictionary/getDictionaryList";
        //获取地区类型
        public static final String REGISTERAEARERPATH = "common/area/queryAllArea";
        //获取国家类型
        public static final String REGISTERCOUNTRYPATH = "common/country/queryAllCountry";
        //检查用户名密码接口
        public static final String LOGINCHECKUSERPASSWORDPATH = "common/user/findByNameAndPwd";
        //发送验证码接口
        public static final String LOGINMSGCODEPATH = "common/user/sendCode";
        //银行机构
        public static final String BANCKINPATH = "owner/companyOrg/mackupCompany";
        //企业
        public static final String ENTERPRISEPATH = "owner/companyOrg/mackupCompany";
        //非银行机构
        public static final String NOBANCKINPATH = "owner/companyOrg/mackupCompany";
        //登陆接口
        public static final String LOGINPATH = "common/user/login";
        //企业信息区块链接口
        public static final String QUKUAILIANPATH = "owner/blockChain/selectAgencyById";
        //我的报价 福费廷列表接口
        public static final String QUOTEPATH = "owner/assets/my/myOfferAssets";
        //我的偏好 福费廷列表接口
        public static final String MYHOBBYPATH = "owner/assetsBuying/read/myBuyingList";
        //我的偏好 保理列表接口
        public static final String BAOLIPATH = "owner/assetsBuying/read/myBuyingList";
        //待售资产 福费廷提交按钮列表接口
        public static final String ASSERTCOMMITPATH = "owner/assets/write/commit";
        //待售资产 保理经办提交按钮列表接口
        public static final String FACTORINGASSERTCOMMITPATH = "owner/factorying/write/commit";
        //待售资产 资产录入福费廷 复核岗审核通过、驳回、取消
        public static final String BOHUIPATH = "owner/assets/write/assetsReview";
        //我的报价 复核岗审核通过、驳回、取消
        public static final String OFFERBOHUIPATH = "owner/price/write/buyCancel";
        //资产录入保理 复核岗审核通过、驳回、取消 待售列表 复核岗审核通过、驳回、取消 （资产上线前的取消）
        public static final String FACTORINGOFFERBOHUIPATH = "owner/factorying/write/assetsReview";
        //待售资产 资产录入福费廷 复核岗审核通过、驳回、取消
//        public static final String DELETEPATH = "owner/assets/write/delete";
        //待售资产 福费廷查看驳回原因列表接口
        public static final String LOOKBOHUIPATH = "owner/assets/read/findAdvise";
        //待售资产 福费廷删除列表接口
        public static final String LOOKBOHUIDELETEPATH = "owner/assets/write/delete";
        //待售资产 保理删除列表接口
        public static final String FACTORINGLOOKBOHUIDELETEPATH = "owner/factorying/write/delete";
        //待售资产 福费廷列表接口
        public static final String ASELLASETSFUFEITINGPATH = "owner/assets/read/findAssetsForOnsaleList";
        //待售资产 保理列表接口
        public static final String ASSERTBAOLIPATH = "owner/factoring/read/findFactoringForOnsaleList";
        //待售资产 保理复核发布接口
        public static final String REVIEWASSERTBAOLIPATH = "owner/factorying/write/publish";
        //贴现率修改接口
        public static final String TIEXIANLVPATH = "owner/price/write/offerUpdate";
        //我的报价 保理列表接口
        public static final String OFFERBAOLIPATH = "owner/assets/my/myOfferAssets";
        //我的报价 福费廷复核提交接口
        public static final String REVIEWOFFERFORFAITINGCOMMITPATH = "owner/price/write/buyConfirm";
        //锁定接口
        public static final String LOCKPATH = "owner/user/lockCompanyUser";
        //注销接口
        public static final String NOMALPATH = "owner/user/cancelCompanyUser";
        //绑定手机号接口
        public static final String BINDPHONEPATH = "owner/companyOrg/valiCode";
        //我的-柜员修改
        public static final String MODEBANCK = "owner/user/modifyCompanyUser";
        //我的-验证码
        public static final String MINEPATH = "owner/companyOrg/sendCode";
        //我的接口
        public static final String LOGIN_MINE = "owner/user/mine";
        //企业信息接口
        public static final String ENTERPRISE_INFOMATION = "owner/companyOrg/getOrgByUserId";
        //找回密码 发送验证码接口
        public static final String FORGETPASSSENDCODE = "common/user/backPwd";
        //找回密码 下一步接口
        public static final String WUWUASSNEXT = "common/user/updatePwd";
        //找回密码 下一步接口
        public static final String FORGETPASSNEXT = "common/user/validBackPwdCode";
        //登录前最后一步修改密码
        public static final String UPDATAPWDPASSNEXT = "common/user/updatePwd";
        //登录后重置密码接口
        public static final String LOGINAFTERRESET = "owner/user/updatePwdById";
        //柜员添加接口
        public static final String TELLERMANAGERMENTADD = "owner/user/saveCompanyUser";
        //柜员列表接口
        public static final String TELLERMANAGERMENTList = "owner/user/findCompanyUserList";
        //积分列表接口
        public static final String INTEGRALList = "owner/integralRead/selectIntegralDetail";
        //首页banner接口
        public static final String HOMEBANNER = "common/banner/getList";
        //首页成交笔数 成交金额 注册人数接口
        public static final String HOMEBALLCOUNT = "common/home/getNumberList";
        //首页实时成交信息查询接口
        public static final String HOMEDEALMESSAGE = "assets/read/findRealTimeTran";
        //首页libor指数接口
        public static final String HOMEINDEX = "common/dictionary/getNumberList";
        //市场行情福费廷、在售列表接口
        public static final String MARKETFORFAITINGSELLLIST = "common/assets/read/findMarketAssets";
        //市场行情 保理-在售资产接口
        public static final String MARKETFACTORINGSELLLIST = "common/factoring/read/findMarketFactoring";
        //发布 福费廷发布接口
        public static final String ISSUEFORFAITING = "owner/assets/write/insert";
        //发布 保理发布接口
        public static final String ISSUEFACTORING = "owner/factorying/write/insert";
        //上传文件接口
        public static final String UPLOADFILE = "upload/file";
        //上传多文件接口
        public static final String UPLOADMOREFILE = "upload/file/multiple";
        //上传文件接口1
        public static final String UPLOADFILE1 = "upload/file/base";
        //ocr上传文件接口
        public static final String OCRUPLOADFILE = "ocr/upload/file";
        //市场 福费廷详情接口
        public static final String MARKETFORFAITINGDETAIL = "owner/assetsDetails/read/findDetails";
        //市场 福费廷 买家 提交报价接口
        public static final String MARKETFORFAITINGOFFER = "owner/price/write/insert";
        //市场 福费廷 买家 报价列表接口
        public static final String MARKETFORFAITINGOFFERLIST = "owner/price/read/ownPricesTransactorRead";
        //市场 福费廷 卖家 确认撮合接口
        public static final String MARKETFORFAITINGOFFERCONFIRM = "owner/price/write/confirmMatch";
        //市场 保理详情接口
        public static final String MARKETFACTORINGINGDETAIL = "owner/factoringDetails/read/findDetails";
        //市场 保理 买家 提交报价接口
//        public static final String MARKETFACTORINGOFFER = "owner/price/write/insert";
        //市场 保理 买家 报价列表接口
//        public static final String MARKETFACTORINGOFFERLIST = "owner/price/read/ownPricesTransactorRead";
        //市场 保理 卖家 确认撮合接口
//        public static final String MARKETFACTORINGOFFERCONFIRM = "owner/price/write/confirmMatch";
        //发布 偏好福费廷录入接口
        public static final String IUSSINGFORFAITINGENTRY = "owner/assetsBuying/write/insert";
        //发布 偏好保理录入接口
        public static final String IUSSINGFACTORINGENTRY = "owner/factoringBuying/write/insert";
        //我的资产 在售资产福费廷列表接口
        public static final String MINEFORFAITINGONSALELIST = "owner/assets/read/findAssetsForSellingList";
        //我的资产 在售资产保理列表接口
        public static final String MINEFACTORINGONSALELIST = "owner/factoring/read/findFactoringForSellingList";
        //我的资产 在售资产报价-经办列表接口
        public static final String HANDLEMINEOFFERLISTONSALELIST = "owner/price/read/ownPricesTransactorRead";
        //我的资产 在售资产报价-复核列表接口
        public static final String REVIEWMINEOFFERLISTONSALELIST = "owner/price/read/ownPricesRecheckRead";
        //我的资产 市场资产报价-复核列表接口
        public static final String MARKETMINEOFFERLISTONSALELIST = "owner/price/read/marketPriceRead";
        //我的资产 在售资产卖家复核确认提交接口
        public static final String REVIEWOFFERSUBMITONSALELIST = "owner/price/write/saleConfirm";
        //我的资产 在售资产资产卖家要约函发送，并确认提交。接口
        public static final String REVIEWOFFERSUBMILETTERTONSALELIST = "owner/assets/write/sendDocAndConfirm";
        //福费廷资产详情查询用于经办修改接口
        public static final String HANDLEFORFAITINGDETAIL = "owner/assets/read/detail";
        //保理资产详情查询用于经办修改接口
        public static final String HANDLEFACTORINGDETAIL = "owner/factoring/read/selectUpdateData";
        //我的资产福费廷更新接口
        public static final String HANDLEFORFAITINGDETAILUPDATE = "owner/assets/write/update";
        //我的资产保理更新接口
        public static final String HANDLEFACTORINGDETAILUPDATE = "owner/factorying/write/update";
        //资产福费廷卖家确认接口 提交让渡函
        public static final String REVIEWSUBMITLETTER = "owner/assets/write/sale/confirm";
        //资产保理卖家确认接口 提交让渡函
        public static final String REVIEWFACTORINGSUBMITLETTER = "owner/factorying/write/sale/confirm";
        //复核  福费廷发布接口
        public static final String REVIEWFORFAITINGIUSS = "owner/assets/write/publish";
        //复核  福费廷 卖家复核驳回接口
        public static final String REVIEWFORFAITINGREJECT = "owner/price/write/saleCancel";
        //复核  福费廷 卖家复核取消接口
        public static final String REVIEWFORFAITINGCANCELSELL = "owner/assets/write/sale/cancel";
        //复核  保理 卖家复核取消接口
        public static final String REVIEWFACTORINGCANCELSELL = "owner/factorying/write/sale/cancel";
        //复核  福费廷 买家复核取消接口
        public static final String REVIEWFORFAITINGCANCELBUY = "owner/assets/write/buy/cancel";
        //复核  保理 买家复核取消接口
        public static final String REVIEWFACTORINGCANCELBUY = "owner/factorying/write/buy/cancel";
        //复核  福费廷 买家复核确认接口
        public static final String REVIEWFORFAITINGSURE = "owner/assets/write/buy/confirm";
        //我的偏好————保理删除
        public static final String HOBBYDELETE = "owner/factoringBuying/write/delete";
        //消息红点数
        public static final String MSGREDNODECOUNT = "owner/message/msgCountNoRead";
        //我的偏好————福费廷删除
        public static final String FUFEITINGHOBBYDELETE = "owner/assetsBuying/write/delete";
        //复核  保理 买家复核确认接口
        public static final String REVIEWFACTORINGSURE = "owner/factorying/write/buy/confirm";
        //消息 客户服务政策列表接口
        public static final String CUSTOMERMESSAGELIST = "common/knowledge/selectAll";
        //消息 客户服务政策列表详情接口
        public static final String CUSTOMERMESSAGELISTDETAIL = "common/knowledge/selectById";
        //消息 业务消息列表接口
        public static final String BUSSINESSEMESSAGELIST = "owner/message/msgList";
        //消息 业务消息详情接口
        public static final String BUSSINESSMESSAGELISTDETAIL = "owner/message/msgOne";
        //消息 系统公告列表接口
        public static final String SYSTEMEMESSAGELIST = "common/knowledge/selectAll";
        //消息 系统公告详情接口
        public static final String SYSTEMMESSAGELISTDETAIL = "common/knowledge/selectById";
        //消息 消息状态更改为已读接口
        public static final String UPDATEMESSAGELIST = "owner/message/updateState";
        //消息 收藏接口
        public static final String COMMONMESSAGELISTDETAIL = "owner/message/msgStoreUp";
        //消息 取消收藏接口
        public static final String UNCOMMONMESSAGELISTDETAIL = "owner/message/rmStoreUp";
        //98.	区块链资产查询(多条)
        public static final String BLOCKCHAINBUSSINESS = "owner/blockChain/queryAssetsById";
        //99.	区块链资产查询(单条)接口
        public static final String BLOCKCHAINCOMPANY = "owner/blockChain/queryAssetById";
        //103.	区块链资产是否上链查询接口
        public static final String BLOCKCHAINTELLER = "owner/blockChain/isBlockChainByAssetIds";
        //查看要约函接口
        public static final String CHECKLETTER = "owner/pollicita/read/findByAssetsId";
        //福费廷资产再次发布接口
        public static final String FORFAITINGREPORT = "owner/assets/write/republish";
        //福费廷偏好查询详情接口
        public static final String FORFAITINGPREFERENCEDETAIL = "owner/assetsBuying/read/assetsBuyingDetail";
        //保理偏好查询详情接口
        public static final String FACTORINGPREFERENCEDETAIL = "owner/factoringBuying/read/factoringBuyingDetail";
        //福费廷偏好更新接口
        public static final String FORFAITINGPREFERENCEUPDATE = "owner/assetsBuying/write/update";
        //保理偏好更新接口
        public static final String FACTORINGPREFERENCEUPDATE = "owner/factoringBuying/write/update";
        //下载邀约函接口
        public static final String DOWNLOADCHECKLETTER = "owner/pollicita/read/downloadTransactorRead";
        //检查权限接口
        public static final String CHECKCONTROR = "owner/companyOrg/isEntryAuth";
        //根据报价查询报价流水信息（前五条）接口
        public static final String MYQUOTEPRISELIST = "owner/price/detail/selectByPriceIdFive";
        //让渡函查询接口
        public static final String CHECKTRANSFERLETTER = "owner/attachment/queryTransfer";
        //核人员查询已失效资产接口
        public static final String CHECKEXPIREDASSETS = "owner/forfeitingMyAsset/findByAssetsId";
        //重新发布已失效资产福费廷信息接口
        public static final String UPDATEEXPIREDASSETSFORFAITING = "owner/assets/write/updateDisabledById";
        //重新发布已失效资产保理信息接口
        public static final String UPDATEEXPIREDASSETSFACTORING = "owner/factorying/write/updateDisabledById";
        //退出登录接口
        public static final String USERLOGOUT = "owner/user/logout";
        //下载用户手册接口
        public static final String USERMANUAL = "owner/file/download";
        //更新app接口
        public static final String APPUPDATE = "common/appUpgrade/selectByType";
    }

    /**
     * shareprefeance常量
     *
     * @author pluto
     */
    public static class SPKEY {
        //
        public static final String SP_ROOT_KEY = "spzjhl";
        public static final String SP_MINE_ON_SALE_LIST_KEY = "mineOnSaleList";
        public static final String SP_MINE_SELL_ASSETS_LIST_KEY = "mineSellAssetsList";
        public static final String SP_MY_HOBBY_KEY = "myHobby";
        public static final String SP_MY_QUOTE_KEY = "myQuote";
        public static final String SP_MARKET_FORFAITING_KEY = "marketForfaiting";
        public static final String SP_MARKET_FACTORING_KEY = "marketFactoring";
    }

    /**
     * SD卡相关目录
     *
     * @author pluto
     */
    public static class SDPATH {
        public static final String ROOTPATH = "sdzjhl";
    }

    /**
     * 服务广播
     *
     * @author pluto
     */
    public static class SERVICE_ACTION {
        //系统服务
        public static final String ZJHL_ACTION = PACKAGE_NAME
                + ".SERVICE_ACTION.ZJHL_ACTION";
    }

    /**
     * 广播ACTIONS集合
     *
     * @author pluto
     */
    public static class BROADCAST_ACTION {

        // 网络广播
        public static final String NETWORK_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
        // 需要登录广播
        public static final String NEED_LOGIN_ACTION = PACKAGE_NAME
                + ".BROADCAST_ACTION.NEED_LOGIN_ACTION";
        // 需要更新用户信息
        public static final String UPDATE_LOGIN_MESSAGE_ACTION = PACKAGE_NAME
                + ".BROADCAST_ACTION.UPDATE_LOGIN_MESSAGE_ACTION";
        // 显示发布页面保存按钮
        public static final String SEND_VISABLE_BUTTON_ACTION = PACKAGE_NAME
                + ".BROADCAST_ACTION.SEND_VISABLE_BUTTON_ACTION";
        // 隐藏发布页面保存按钮
        public static final String SEND_GONE_BUTTON_ACTION = PACKAGE_NAME
                + ".BROADCAST_ACTION.SEND_GONE_BUTTON_ACTION";
        // 接收系统消息
        public static final String RESAVE_SYSTEM_MESSAGE = PACKAGE_NAME
                + ".BROADCAST_ACTION.RASAVE_SYSTEM_MASGE";

        // 刷新消息數量
        public static final String RESAVE_SYSTEM_MESSAGE_COUNT = PACKAGE_NAME
                + ".BROADCAST_ACTION.RASAVE_SYSTEM_MASGE_COUNT";
        // 柜员添加广播
        public static final String RESAVE_ADD_MESSAGE = PACKAGE_NAME
                + ".BROADCAST_ACTION.RASAVE_ADD_NAME";
        // 刷新首页
        public static final String SEND_REFERSH_HOME_ACTION = PACKAGE_NAME
                + ".BROADCAST_ACTION.SEND_REFERSH_HOME_ACTION";
        // 刷新待售资产（编辑完之后）
        public static final String SEND_REFERSH_SELL_ASSERT = PACKAGE_NAME
                + ".BROADCAST_ACTION.SEND_REFERSH_SELL_ASSERT";
    }

    /**
     * 小米推送相关常量
     *
     * @author pluto
     */
    public static class MIPUSH {
        //小米推送app id
        public static final String MIPUSH_APP_ID = "";
        //小米推送app key
        public static final String MIPUSH_APP_KEY = "";
        //小米推送DEBUG app id
        public static final String MIPUSH_DEBUG_APP_ID = "";
        //小米推送DEBUG app key
        public static final String MIPUSH_DEBUG_APP_KEY = "";
    }

    /**
     * 用户相关常量
     *
     * @author pluto
     */
    public static class USERINFO {
        //用户登陆状态 false未登录 true登陆
        public static final String USERINFO_STATUS = PACKAGE_NAME + ".USERINFO_STATUS";
        //token
        public static final String USERINFO_TOKEN = PACKAGE_NAME + ".USERINFO_TOKEN";
        //currentid
        public static final String USERINFO_CURRENTID = PACKAGE_NAME + ".USERINFO_CURRENTID";
        //用户邮箱
        public static final String USERINFO_EMAIL = PACKAGE_NAME + ".USERINFO_EMAIL";
        //系统语言
        public static final String USERINFO_LANGUAGE = PACKAGE_NAME + ".USERINFO_LANGUAGE";
        //登录返回对象
        public static final String USERINFO_OBJECT = PACKAGE_NAME + ".USERINFO_OBJECT";
        //登录返回companyId
        public static final String USERINFO_COMPANYID = PACKAGE_NAME + ".USERINFO_COMPANYID";
        //登录返回userType
        public static final String USERINFO_TYPE = PACKAGE_NAME + ".USERINFO_TYPE";
        //检查福费廷权限
        public static final String USERINFO_FORFAITING = PACKAGE_NAME + ".USERINFO_FORFAITING";
        //检查保理权限
        public static final String USERINFO_FACTORING = PACKAGE_NAME + ".USERINFO_FACTORING";
        //公司名字
        public static final String USERINFO_COMPANYNAME = PACKAGE_NAME + ".USERINFO_COMPANEYNAME";
        //用户姓名
        public static final String USERINFO_USERNAME = PACKAGE_NAME + ".USERINFO_USERNAME";
    }


}
