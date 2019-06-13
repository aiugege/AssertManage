package com.zjhl.pad.presenter.okhttp;


import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.res.AsellasetsBaoliRes;
import com.zjhl.pad.moudle.entity.res.AsellasetsFuFeiTingRes;
import com.zjhl.pad.moudle.entity.res.AssertCommitRes;
import com.zjhl.pad.moudle.entity.res.BanckInstitutionRes;
import com.zjhl.pad.moudle.entity.res.BaojiatxRs;
import com.zjhl.pad.moudle.entity.res.BlockChainRes;
import com.zjhl.pad.moudle.entity.res.BohuiResonRes;
import com.zjhl.pad.moudle.entity.res.BusinessMessage;
import com.zjhl.pad.moudle.entity.res.CanselRes;
import com.zjhl.pad.moudle.entity.res.CheckBindPhoneNumberRes;
import com.zjhl.pad.moudle.entity.res.CheckCompaneyNameRes;
import com.zjhl.pad.moudle.entity.res.CheckControlRes;
import com.zjhl.pad.moudle.entity.res.CheckLetterRes;
import com.zjhl.pad.moudle.entity.res.CheckTransferLetterRes;
import com.zjhl.pad.moudle.entity.res.CheckUpdateAppRes;
import com.zjhl.pad.moudle.entity.res.DownCheckLetterRes;
import com.zjhl.pad.moudle.entity.res.EnterpriseRes;
import com.zjhl.pad.moudle.entity.res.FactoringPreferenceDetail;
import com.zjhl.pad.moudle.entity.res.ForfaitingPreferenceDetail;
import com.zjhl.pad.moudle.entity.res.ForgetPassNextRes;
import com.zjhl.pad.moudle.entity.res.ForgetPassSendCodeRes;
import com.zjhl.pad.moudle.entity.res.HandleFactoringDetailRes;
import com.zjhl.pad.moudle.entity.res.HandleFactoringDetailSearchRes;
import com.zjhl.pad.moudle.entity.res.HobbyBaoliRes;
import com.zjhl.pad.moudle.entity.res.HobbyDeleteRes;
import com.zjhl.pad.moudle.entity.res.HomeAllCountRes;
import com.zjhl.pad.moudle.entity.res.HomeBannerRes;
import com.zjhl.pad.moudle.entity.res.HomeIndexRes;
import com.zjhl.pad.moudle.entity.res.IssueFactoringRes;
import com.zjhl.pad.moudle.entity.res.IntegralRes;
import com.zjhl.pad.moudle.entity.res.LockRes;
import com.zjhl.pad.moudle.entity.res.LoginCheckUserPasswordRes;
import com.zjhl.pad.moudle.entity.res.LoginMsgCodeRes;
import com.zjhl.pad.moudle.entity.res.LoginRes;
import com.zjhl.pad.moudle.entity.res.LookBohuiReasonRs;
import com.zjhl.pad.moudle.entity.res.MarketFactoringDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketFactoringSellRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingDetailOldRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingDetailRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingOfferListRes;
import com.zjhl.pad.moudle.entity.res.MarketForfaitingSellRes;
import com.zjhl.pad.moudle.entity.res.MessageListDetailRes;
import com.zjhl.pad.moudle.entity.res.MessageListRes;
import com.zjhl.pad.moudle.entity.res.MineMsgRes;
import com.zjhl.pad.moudle.entity.res.MineStateRes;
import com.zjhl.pad.moudle.entity.res.ModeBanckRes;
import com.zjhl.pad.moudle.entity.res.MsgCountRes;
import com.zjhl.pad.moudle.entity.res.MyHobbyRes;
import com.zjhl.pad.moudle.entity.res.MyOfferBaoliRs;
import com.zjhl.pad.moudle.entity.res.MyOfferFufei;
import com.zjhl.pad.moudle.entity.res.MyQuotePriseListRes;
import com.zjhl.pad.moudle.entity.res.NoBanckInstitutionRes;
import com.zjhl.pad.moudle.entity.res.OrganizationmMsgRes;
import com.zjhl.pad.moudle.entity.res.QukuaiLianRes;
import com.zjhl.pad.moudle.entity.res.RegisterCheckEmailPhoneRes;
import com.zjhl.pad.moudle.entity.res.RegisterCodeRes;
import com.zjhl.pad.moudle.entity.res.RegisterCountryArearRes;
import com.zjhl.pad.moudle.entity.res.RegisterDictionaryRes;
import com.zjhl.pad.moudle.entity.res.RegisterRes;
import com.zjhl.pad.moudle.entity.res.ResetPasswordRes;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitLetterOnSaleListRes;
import com.zjhl.pad.moudle.entity.res.ReviewOfferSubmitOnSaleListRes;
import com.zjhl.pad.moudle.entity.res.TellerManagementRes;
import com.zjhl.pad.moudle.entity.res.TellerManagermentAddRes;
import com.zjhl.pad.moudle.entity.res.UploadFileRes;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @desc: ServerApi
 * @version: v1.0
 * @packagename: com.zjhl.pad.entity.base
 * @author: pluto
 * @create: 2018/4/18 11:54
 * @projectname: nnkj
 **/
public interface ServerApi {
    // 登陆请求
    @POST(Constants.NETPATH.LOGINPATH)
    Observable<ResponseBean> login(@Body RequestBody requestBody);

    // 验证用户名密码请求
    @POST(Constants.NETPATH.LOGINCHECKUSERPASSWORDPATH)
    Call<LoginCheckUserPasswordRes> loginCheckUserPasswordRet(@Body RequestBody requestBody);

    // 登录发送验证码请求
    @POST(Constants.NETPATH.LOGINMSGCODEPATH)
    Call<LoginMsgCodeRes> loginMsgCodeRet(@Body RequestBody requestBody);

    // 我的里面发送验证码请求
    @POST(Constants.NETPATH.MINEPATH)
    Call<MineMsgRes> mineMsgCodeRet(@Body RequestBody requestBody);

    //机构信息认证资料补充请求
    @POST(Constants.NETPATH.ENTERPRISEPATH)
    Call<BanckInstitutionRes> banckinstituRet(@Body RequestBody requestBody);

    //企业接口请求
    @POST(Constants.NETPATH.BANCKINPATH)
    Call<EnterpriseRes> enterpriseRet(@Body RequestBody requestBody);

    //非银行机构请求
    @POST(Constants.NETPATH.NOBANCKINPATH)
    Call<NoBanckInstitutionRes> nobanckinstituRet(@Body RequestBody requestBody);

    // 登录请求
    @POST(Constants.NETPATH.LOGINPATH)
    Call<LoginRes> loginRet(@Body RequestBody requestBody);
    // 退出登录请求
    @POST(Constants.NETPATH.USERLOGOUT)
    Call<LoginRes> logoutRet(@Body RequestBody requestBody);

    // 企业信息区块链请求
    @POST(Constants.NETPATH.QUKUAILIANPATH)
    Call<QukuaiLianRes> qukuailianRet(@Body RequestBody requestBody);

    // 请求我的报价 福费廷列表
    @POST(Constants.NETPATH.QUOTEPATH)
    Call<MyOfferFufei> myassertRet(@Body RequestBody requestBody);


    // 请求我的偏好 福费廷列表
    @POST(Constants.NETPATH.MYHOBBYPATH)
    Call<MyHobbyRes> myhobbyRet(@Body RequestBody requestBody);


    // 请求我的偏好 保理列表
    @POST(Constants.NETPATH.BAOLIPATH)
    Call<HobbyBaoliRes> myhobbyboliRet(@Body RequestBody requestBody);

    // 请求我的报价 福费按钮提交廷列表
    @POST(Constants.NETPATH.BOHUIPATH)
    Call<AssertCommitRes> assertRet(@Body RequestBody requestBody);

    // 请求待售资产 驳回的接口
    @POST(Constants.NETPATH.BOHUIPATH)
    Call<BohuiResonRes> bohuiRet(@Body RequestBody requestBody);


    //我的报价 复核岗审核通过、驳回、取消
    @POST(Constants.NETPATH.OFFERBOHUIPATH)
    Call<BohuiResonRes> offerBohuiRet(@Body RequestBody requestBody);

    //保理 复核岗审核通过、驳回、取消
    @POST(Constants.NETPATH.FACTORINGOFFERBOHUIPATH)
    Call<BohuiResonRes> factoringOfferBohuiRet(@Body RequestBody requestBody);

    // 请求待售资产 删除的接口
    @POST(Constants.NETPATH.LOOKBOHUIDELETEPATH)
    Call<BohuiResonRes> deleteRet(@Body RequestBody requestBody);

    // 保理待售资产 删除的接口
    @POST(Constants.NETPATH.FACTORINGLOOKBOHUIDELETEPATH)
    Call<BohuiResonRes> factoringDeleteRet(@Body RequestBody requestBody);

    // 请求待售资产 取消的接口
    @POST(Constants.NETPATH.BOHUIPATH)
    Call<CanselRes> cansalRet(@Body RequestBody requestBody);

    // 请求待售资产 查看驳回原因的接口
    @POST(Constants.NETPATH.LOOKBOHUIPATH)
    Call<LookBohuiReasonRs> lookBohuiResonRet(@Body RequestBody requestBody);


    // 请求待售 福费廷列表
    @POST(Constants.NETPATH.ASELLASETSFUFEITINGPATH)
    Call<AsellasetsFuFeiTingRes> asellasetfufeitingRet(@Body RequestBody requestBody);


    // 请求待售 保理列表
    @POST(Constants.NETPATH.ASSERTBAOLIPATH)
    Call<AsellasetsBaoliRes> asertbaoliRet(@Body RequestBody requestBody);


    // 修改贴现率
    @POST(Constants.NETPATH.TIEXIANLVPATH)
    Call<BaojiatxRs> tiexianlvRet(@Body RequestBody requestBody);

    // 请求我的报价 宝理列表
    @POST(Constants.NETPATH.OFFERBAOLIPATH)
    Call<MyOfferBaoliRs> myOfferbaoli(@Body RequestBody requestBody);

    // 锁定请求
    @POST(Constants.NETPATH.LOCKPATH)
    Call<LockRes> lockRet(@Body RequestBody requestBody);

    // 注销请求
    @POST(Constants.NETPATH.NOMALPATH)
    Call<LockRes> nomalRet(@Body RequestBody requestBody);


    // 绑定手机号请求
    @POST(Constants.NETPATH.BINDPHONEPATH)
    Call<CheckBindPhoneNumberRes> checkbindRet(@Body RequestBody requestBody);

    // 柜员修改请求
    @POST(Constants.NETPATH.MODEBANCK)
    Call<ModeBanckRes> modebanckRet(@Body RequestBody requestBody);

    // 我的请求
    @POST(Constants.NETPATH.LOGIN_MINE)
    Call<MineStateRes> mineRet(@Body RequestBody requestBody);

    // 企业信息请求
    @POST(Constants.NETPATH.ENTERPRISE_INFOMATION)
    Call<OrganizationmMsgRes> tellerManager(@Body RequestBody requestBody);

    // 注册请求
    @POST(Constants.NETPATH.REGISTERPATH)
    Call<RegisterRes> registerRet(@Body RequestBody requestBody);

    // 获取注册验证码接口
    @POST(Constants.NETPATH.REGISTERCODEPATH)
    Call<RegisterCodeRes> registerCodeRet(@Body RequestBody requestBody);

    // 异步校验手机号、邮箱唯一
    @POST(Constants.NETPATH.REGISTERCHECKEMAILPHONEPATH)
    Call<RegisterCheckEmailPhoneRes> registerCheckEmailPhoneRet(@Body RequestBody requestBody);

    // 异步校验企业名字
    @POST(Constants.NETPATH.CHECKCOMPNEYNAMEPHONEPATH)
    Call<CheckCompaneyNameRes> checkConpanyNameRet(@Body RequestBody requestBody);

    // 机构注册时生成图片验证码
    @GET(Constants.NETPATH.REGISTERIMGPATH)
//    @FormUrlEncoded
    Call<ResponseBody> registerImgRet(@Query("imgCodeId") String imgCodeId);

    // 获取机构类型
    @POST(Constants.NETPATH.REGISTERDICTIONPATH)
    Call<RegisterDictionaryRes> registerDictionaryRet(@Body RequestBody requestBody);


    // 获取地区类型
    @POST(Constants.NETPATH.REGISTERAEARERPATH)
    Call<RegisterCountryArearRes> registerArearRet(@Body RequestBody requestBody);

    // 获取国家类型
    @POST(Constants.NETPATH.REGISTERCOUNTRYPATH)
    Call<RegisterCountryArearRes> registerCountryRet(@Body RequestBody requestBody);

    // 找回密码 发送验证码接口
    @POST(Constants.NETPATH.FORGETPASSSENDCODE)
    Call<ForgetPassSendCodeRes> forgetPassSendCodeRet(@Body RequestBody requestBody);

    // 找回密码 下一步保存接口
    @POST(Constants.NETPATH.UPDATAPWDPASSNEXT)
    Call<ForgetPassSendCodeRes> forgetNaxtSavePassCodeRet(@Body RequestBody requestBody);

    // 找回密码 下一步接口
    @POST(Constants.NETPATH.FORGETPASSNEXT)
    Call<ForgetPassNextRes> forgetPassNextRet(@Body RequestBody requestBody);

    // 登录后重置密码接口
    @POST(Constants.NETPATH.LOGINAFTERRESET)
    Call<ResetPasswordRes> resetPassword(@Body RequestBody requestBody);


    // 添加柜员接口
    @POST(Constants.NETPATH.TELLERMANAGERMENTADD)
    Call<TellerManagermentAddRes> tellermanageradd(@Body RequestBody requestBody);

    // 添加柜员列表接口
    @POST(Constants.NETPATH.TELLERMANAGERMENTList)
    Call<TellerManagementRes> tellermanagerlist(@Body RequestBody requestBody);

    // 我的积分接口
    @POST(Constants.NETPATH.INTEGRALList)
    Call<IntegralRes> integralRes(@Body RequestBody requestBody);


    // 首页banner接口
    @POST(Constants.NETPATH.HOMEBANNER)
    Call<HomeBannerRes> homeBannerRet(@Body RequestBody requestBody);

    // 首页成交笔数 成交金额 注册人数接口
    @POST(Constants.NETPATH.HOMEBALLCOUNT)
    Call<HomeAllCountRes> homeAllCountRet(@Body RequestBody requestBody);

    //首页libor SHIBOR HIBOR指数接口
    @POST(Constants.NETPATH.HOMEINDEX)
    Call<HomeIndexRes> homeIndexRet(@Body RequestBody requestBody);

    //市场行情福费廷、在售列表接口
    @POST(Constants.NETPATH.MARKETFORFAITINGSELLLIST)
    Call<MarketForfaitingSellRes> marketForfaitingSellRet(@Body RequestBody requestBody);

    //市场行情 保理-在售资产接口
    @POST(Constants.NETPATH.MARKETFACTORINGSELLLIST)
    Call<MarketFactoringSellRes> marketFactoringSellRet(@Body RequestBody requestBody);

    //发布 福费廷发布接口
    @POST(Constants.NETPATH.ISSUEFORFAITING)
    Call<MarketFactoringSellRes> issueForfaitingRet(@Body RequestBody requestBody);


    //发布 保理发布接口
    @POST(Constants.NETPATH.ISSUEFACTORING)
    Call<IssueFactoringRes> issueFactoringRet(@Body RequestBody requestBody);

    //市场 福费廷 详情接口
    @POST(Constants.NETPATH.MARKETFORFAITINGDETAIL)
    Call<MarketForfaitingDetailRes> marketForfaitingDetailRet(@Body RequestBody requestBody);

    //市场 福费廷 详情接口
    @POST(Constants.NETPATH.MARKETFORFAITINGDETAIL)
    Call<MarketForfaitingDetailOldRes> marketForfaitingDetailOldRet(@Body RequestBody requestBody);

    //市场 福费廷 买家提交报价接口
    @POST(Constants.NETPATH.MARKETFORFAITINGOFFER)
    Call<MarketForfaitingDetailRes> marketForfaitingOfferRet(@Body RequestBody requestBody);

    //市场 福费廷 买家报价列表接口
    @POST(Constants.NETPATH.MARKETFORFAITINGOFFERLIST)
    Call<MarketForfaitingOfferListRes> marketForfaitingOfferListRet(@Body RequestBody requestBody);

    //我的资产 市场资产报价-复核列表接口
    @POST(Constants.NETPATH.MARKETMINEOFFERLISTONSALELIST)
    Call<MarketForfaitingOfferListRes> marketForfaitingOfferList1Ret(@Body RequestBody requestBody);

    //市场 福费廷 卖家 确认撮合接口
    @POST(Constants.NETPATH.MARKETFORFAITINGOFFERCONFIRM)
    Call<MarketForfaitingDetailRes> marketForfaitingOfferConfirmRet(@Body RequestBody requestBody);

    //市场 保理 详情接口
    @POST(Constants.NETPATH.MARKETFACTORINGINGDETAIL)
    Call<MarketFactoringDetailRes> marketFactoringDetailRet(@Body RequestBody requestBody);

    //发布 偏好福费廷录入接口
    @POST(Constants.NETPATH.IUSSINGFORFAITINGENTRY)
    Call<MarketFactoringDetailRes> iussingForfaitingRet(@Body RequestBody requestBody);

    //发布 偏好保理录入接口
    @POST(Constants.NETPATH.IUSSINGFACTORINGENTRY)
    Call<MarketFactoringDetailRes> iussingFactoringRet(@Body RequestBody requestBody);


    //我的资产 在售资产列表福费廷接口
    @POST(Constants.NETPATH.MINEFORFAITINGONSALELIST)
    Call<MyOfferFufei> mineForfaitingOnSaleListRet(@Body RequestBody requestBody);

    //我的资产 在售资产列表保理接口
    @POST(Constants.NETPATH.MINEFACTORINGONSALELIST)
    Call<MyOfferBaoliRs> mineFactoringOnSaleListRet(@Body RequestBody requestBody);

    //我的资产 在售资产报价-经办列表接口
    @POST(Constants.NETPATH.HANDLEMINEOFFERLISTONSALELIST)
    Call<MarketForfaitingOfferListRes> handleOfferListOnSaleListRet(@Body RequestBody requestBody);

    //我的资产 在售资产报价-复核列表接口
    @POST(Constants.NETPATH.REVIEWMINEOFFERLISTONSALELIST)
    Call<MarketForfaitingOfferListRes> reviewOfferListOnSaleListRet(@Body RequestBody requestBody);

    //福费廷资产详情查询用于经办修改接口
    @POST(Constants.NETPATH.HANDLEFORFAITINGDETAIL)
    Call<HandleFactoringDetailRes> handleForfaitingDetailRet(@Body RequestBody requestBody);

    //保理资产详情查询用于经办修改接口
    @POST(Constants.NETPATH.HANDLEFACTORINGDETAIL)
    Call<HandleFactoringDetailSearchRes> handleFactoringDetailRet(@Body RequestBody requestBody);

    //我的资产更新接口
    @POST(Constants.NETPATH.HANDLEFORFAITINGDETAILUPDATE)
    Call<MarketFactoringSellRes> handleForfaitingDetailUpdateRet(@Body RequestBody requestBody);

    //我的资产保理更新接口
    @POST(Constants.NETPATH.HANDLEFACTORINGDETAILUPDATE)
    Call<MarketFactoringSellRes> handleFactoringDetailUpdateRet(@Body RequestBody requestBody);


    //我的资产 在售资产卖家复核确认提交接口
    @POST(Constants.NETPATH.REVIEWOFFERSUBMITONSALELIST)
    Call<ReviewOfferSubmitOnSaleListRes> reviewOfferSubmitOnSaleListRet(@Body RequestBody requestBody);

    //我的资产 在售资产资产卖家要约函发送，并确认提交。接口
    @POST(Constants.NETPATH.REVIEWOFFERSUBMILETTERTONSALELIST)
    Call<ReviewOfferSubmitLetterOnSaleListRes> reviewOfferSubmitLetterOnSaleListRet(@Body RequestBody requestBody);

    //复核  福费廷发布接口
    @POST(Constants.NETPATH.REVIEWFORFAITINGIUSS)
    Call<ReviewOfferSubmitLetterOnSaleListRes> reviewForfaitingIussRet(@Body RequestBody requestBody);

    //资产卖家福费廷确认接口 提交让渡函  复用ReviewOfferSubmitLetterOnSaleListRes
    @POST(Constants.NETPATH.REVIEWSUBMITLETTER)
    Call<ReviewOfferSubmitLetterOnSaleListRes> reviewSubmitLetterOnSaleListRet(@Body RequestBody requestBody);

    //资产卖家保理确认接口 提交让渡函  复用ReviewOfferSubmitLetterOnSaleListRes
    @POST(Constants.NETPATH.REVIEWFACTORINGSUBMITLETTER)
    Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSubmitLetterOnSaleListRet(@Body RequestBody requestBody);

    //我的报价 福费廷复核提交接口
    @POST(Constants.NETPATH.REVIEWOFFERFORFAITINGCOMMITPATH)
    Call<ReviewOfferSubmitLetterOnSaleListRes> reviewOfferForfaitingCommitRet(@Body RequestBody requestBody);

    //待售资产 福费廷提交按钮列表接口
    @POST(Constants.NETPATH.ASSERTCOMMITPATH)
    Call<ReviewOfferSubmitLetterOnSaleListRes> handleForfaitingCommitRet(@Body RequestBody requestBody);

    //待售资产 保理经办提交按钮列表接口
    @POST(Constants.NETPATH.FACTORINGASSERTCOMMITPATH)
    Call<ReviewOfferSubmitLetterOnSaleListRes> handleFactoringCommitRet(@Body RequestBody requestBody);

    //待售资产 保理复核发布接口
    @POST(Constants.NETPATH.REVIEWASSERTBAOLIPATH)
    Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringCommitCommitRet(@Body RequestBody requestBody);

    //复核  福费廷 卖家复核驳回接口
    @POST(Constants.NETPATH.REVIEWFORFAITINGREJECT)
    Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringRejectRet(@Body RequestBody requestBody);


    //消息 客户服务列表接口
    @POST(Constants.NETPATH.CUSTOMERMESSAGELIST)
    Call<MessageListRes> customerMessageListRet(@Body RequestBody requestBody);

    //消息 客户服务列表详情接口
    @POST(Constants.NETPATH.CUSTOMERMESSAGELISTDETAIL)
    Call<MessageListDetailRes> customerMessageListDetailRet(@Body RequestBody requestBody);

    //消息 业务消息列表接口
    @POST(Constants.NETPATH.BUSSINESSEMESSAGELIST)
    Call<BusinessMessage> bussinessMessageListRet(@Body RequestBody requestBody);

    //消息 业务消息详情接口
    @POST(Constants.NETPATH.BUSSINESSMESSAGELISTDETAIL)
    Call<MessageListDetailRes> bussinessMessageListDetailRet(@Body RequestBody requestBody);

    //消息 系统公告消息列表接口
    @POST(Constants.NETPATH.SYSTEMEMESSAGELIST)
    Call<MessageListRes> systemMessageListRet(@Body RequestBody requestBody);

    //消息 系统公告消息详情接口
    @POST(Constants.NETPATH.SYSTEMMESSAGELISTDETAIL)
    Call<MessageListDetailRes> systemMessageListDetailRet(@Body RequestBody requestBody);

    //消息 消息状态更改为已读接口
    @POST(Constants.NETPATH.UPDATEMESSAGELIST)
    Call<MessageListRes> updateMessageListRet(@Body RequestBody requestBody);

    //消息 收藏接口
    @POST(Constants.NETPATH.COMMONMESSAGELISTDETAIL)
    Call<ReviewOfferSubmitLetterOnSaleListRes> commonMessageListRet(@Body RequestBody requestBody);

    //消息 取消收藏接口
    @POST(Constants.NETPATH.UNCOMMONMESSAGELISTDETAIL)
    Call<ReviewOfferSubmitLetterOnSaleListRes> unCommonMessageListRet(@Body RequestBody requestBody);

    //区块链资产查询接口
    @POST(Constants.NETPATH.BLOCKCHAINBUSSINESS)
    Call<BlockChainRes> blockChainBussinessRet(@Body RequestBody requestBody);

    //区块链机构查询接口
    @POST(Constants.NETPATH.BLOCKCHAINCOMPANY)
    Call<BlockChainRes> blockChainCompanyRet(@Body RequestBody requestBody);

    //区块链柜员查询接口
    @POST(Constants.NETPATH.BLOCKCHAINTELLER)
    Call<BlockChainRes> blockChainTellerRet(@Body RequestBody requestBody);

    //复核  福费廷 卖家复核取消接口 返回复用BlockChainRes
    @POST(Constants.NETPATH.REVIEWFORFAITINGCANCELSELL)
    Call<BlockChainRes> reviewForfaitingCancelSellRet(@Body RequestBody requestBody);

    //复核  保理 卖家复核取消接口 返回复用BlockChainRes
    @POST(Constants.NETPATH.REVIEWFACTORINGCANCELSELL)
    Call<BlockChainRes> reviewFactoringCancelSellRet(@Body RequestBody requestBody);

    //复核  福费廷 买家复核取消接口 返回复用BlockChainRes
    @POST(Constants.NETPATH.REVIEWFORFAITINGCANCELBUY)
    Call<BlockChainRes> reviewForfaitingCancelBuyRet(@Body RequestBody requestBody);

    //复核 保理 买家复核取消接口 返回复用BlockChainRes
    @POST(Constants.NETPATH.REVIEWFACTORINGCANCELBUY)
    Call<BlockChainRes> reviewFactoringCancelBuyRet(@Body RequestBody requestBody);

    //复核  福费廷 买家复核确认接口 复用 ReviewOfferSubmitLetterOnSaleListRes
    @POST(Constants.NETPATH.REVIEWFORFAITINGSURE)
    Call<ReviewOfferSubmitLetterOnSaleListRes> reviewForfaitingSureRet(@Body RequestBody requestBody);

    //我的偏好 福费廷删除
    @POST(Constants.NETPATH.FUFEITINGHOBBYDELETE)
    Call<HobbyDeleteRes> hobbyRet(@Body RequestBody requestBody);

    //我的偏好 保理删除
    @POST(Constants.NETPATH.HOBBYDELETE)
    Call<HobbyDeleteRes> hobbybaoliRet(@Body RequestBody requestBody);

    //消息红点数
    @POST(Constants.NETPATH.MSGREDNODECOUNT)
    Call<MsgCountRes> newnoderedRet(@Body RequestBody requestBody);

    //复核  保理 买家复核确认接口 复用 ReviewOfferSubmitLetterOnSaleListRes
    @POST(Constants.NETPATH.REVIEWFACTORINGSURE)
    Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSureRet(@Body RequestBody requestBody);

    //查看要约函
    @POST(Constants.NETPATH.CHECKLETTER)
    Call<CheckLetterRes> checkLetterRet(@Body RequestBody requestBody);


    //福费廷资产再次发布接口
    @POST(Constants.NETPATH.FORFAITINGREPORT)
    Call<CheckLetterRes> forfaitingReportRet(@Body RequestBody requestBody);


    //福费廷偏好查询详情接口
    @POST(Constants.NETPATH.FORFAITINGPREFERENCEDETAIL)
    Call<ForfaitingPreferenceDetail> forfaitingPrefenceDetailRet(@Body RequestBody requestBody);

    //保理偏好查询详情接口
    @POST(Constants.NETPATH.FACTORINGPREFERENCEDETAIL)
    Call<FactoringPreferenceDetail> factoringPrefenceDetailRet(@Body RequestBody requestBody);

    //福费廷偏好更新接口
    @POST(Constants.NETPATH.FORFAITINGPREFERENCEUPDATE)
    Call<CheckLetterRes> forfaitingPrefenceUpdateRet(@Body RequestBody requestBody);

    //保理偏好更新接口
    @POST(Constants.NETPATH.FACTORINGPREFERENCEUPDATE)
    Call<CheckLetterRes> factoringPrefenceUpdateRet(@Body RequestBody requestBody);

    //下载邀约函接口
    @POST(Constants.NETPATH.DOWNLOADCHECKLETTER)
    Call<DownCheckLetterRes> downCheckLetterRet(@Body RequestBody requestBody);

    //检查权限接口
    @POST(Constants.NETPATH.CHECKCONTROR)
    Call<CheckControlRes> checkControlRet(@Body RequestBody requestBody);

    //根据报价查询报价流水信息（前五条）接口
    @POST(Constants.NETPATH.MYQUOTEPRISELIST)
    Call<MyQuotePriseListRes> myQuotePriseListRet(@Body RequestBody requestBody);

    //查询让渡函接口
    @POST(Constants.NETPATH.CHECKTRANSFERLETTER)
    Call<CheckTransferLetterRes> checkTransferLetterRet(@Body RequestBody requestBody);

    //复核人员查询已失效资产接口
    @POST(Constants.NETPATH.CHECKEXPIREDASSETS)
    Call<CheckTransferLetterRes> checkExpiredAssetsRet(@Body RequestBody requestBody);

    //重新发布已失效资产福费廷信息接口
    @POST(Constants.NETPATH.UPDATEEXPIREDASSETSFORFAITING)
    Call<CheckTransferLetterRes> updateExpiredAssetsForfaitingRet(@Body RequestBody requestBody);

    //重新发布已失效资产保理信息接口
    @POST(Constants.NETPATH.UPDATEEXPIREDASSETSFACTORING)
    Call<CheckTransferLetterRes> updateExpiredAssetsFactoringRet(@Body RequestBody requestBody);

    //app更新接口
    @POST(Constants.NETPATH.APPUPDATE)
    Call<CheckUpdateAppRes> checkAppUpdateRet(@Body RequestBody requestBody);

    //上传图片
//    @POST(Constants.NETPATH.ISSUEFORFAITING)
//    Call<MarketFactoringSellRes> issueForfaitingRet(@Body RequestBody requestBody);
    @Multipart
    @POST("")
    Call<UploadFileRes> uploadFileRet(@Url String url, @Part("channel") RequestBody requestBody, @Part("token") RequestBody requestBody1,
                                      @Part MultipartBody.Part file);

    @Multipart
    @POST("")
    Call<UploadFileRes> uploadFileParamsRet(@Url String url, @PartMap Map<String, RequestBody> params,
                                            @Part MultipartBody.Part file);;
//上传多文件
    @Multipart
    @POST("")
    Call<UploadFileRes> uploadMoreFileParamsRet(@Url String url, @PartMap Map<String, RequestBody> params,
                                            @Part List<MultipartBody.Part> file);

    @Multipart
    @POST("")
    Call<UploadFileRes> uploadOCRFileParamsRet(@Url String url, @PartMap Map<String, RequestBody> params,
                                               @Part MultipartBody.Part file);

    @GET("/resource/example.zip")
    Call<ResponseBody> downloadFileWithFixedUrl();

    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);


    // 下载用户手册
    @POST(Constants.NETPATH.USERMANUAL)
    Call<ResponseBody> userManualRet(@Body RequestBody requestBody);

}
