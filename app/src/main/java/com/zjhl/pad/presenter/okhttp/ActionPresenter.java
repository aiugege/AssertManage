package com.zjhl.pad.presenter.okhttp;

import com.zjhl.pad.app.constants.Constants;
import com.zjhl.pad.moudle.entity.base.ResponseBean;
import com.zjhl.pad.moudle.entity.req.AssertCommitReq;
import com.zjhl.pad.moudle.entity.req.AssertFufeitingDeleteRq;
import com.zjhl.pad.moudle.entity.req.AssertsBaoliReq;
import com.zjhl.pad.moudle.entity.req.BanckInstitutionalReq;
import com.zjhl.pad.moudle.entity.req.BaojiatxReq;
import com.zjhl.pad.moudle.entity.req.BaoliAssertReq;
import com.zjhl.pad.moudle.entity.req.BlockChainReq;
import com.zjhl.pad.moudle.entity.req.BohuiResonReq;
import com.zjhl.pad.moudle.entity.req.CansalResonReq;
import com.zjhl.pad.moudle.entity.req.CheckBindPhoneNumberReq;
import com.zjhl.pad.moudle.entity.req.CheckCompaneyNameReq;
import com.zjhl.pad.moudle.entity.req.CheckUpdateAppReq;
import com.zjhl.pad.moudle.entity.req.EnterpriseReq;
import com.zjhl.pad.moudle.entity.req.ForfaitingRportReq;
import com.zjhl.pad.moudle.entity.req.ForgetPassSendCodeReq;
import com.zjhl.pad.moudle.entity.req.HobbyDeleteReq;
import com.zjhl.pad.moudle.entity.req.HobbyReq;
import com.zjhl.pad.moudle.entity.req.IssueFactoringReq;
import com.zjhl.pad.moudle.entity.req.IssueForfaitingReq;
import com.zjhl.pad.moudle.entity.req.LockReq;
import com.zjhl.pad.moudle.entity.req.IussingFactoringEntryReq;
import com.zjhl.pad.moudle.entity.req.IussingForfaitingEntryReq;
import com.zjhl.pad.moudle.entity.req.LoginCheckUserPasswordReq;
import com.zjhl.pad.moudle.entity.req.LoginReq;
import com.zjhl.pad.moudle.entity.req.LookBohuiReasonReq;
import com.zjhl.pad.moudle.entity.req.MarketFactoringSellReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingDetailReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferListReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingOfferReq;
import com.zjhl.pad.moudle.entity.req.MarketForfaitingSellReq;
import com.zjhl.pad.moudle.entity.req.MessageListReq;
import com.zjhl.pad.moudle.entity.req.MineForfaitingOnSaleListReq;
import com.zjhl.pad.moudle.entity.req.MineMsgReq;
import com.zjhl.pad.moudle.entity.req.ModeBanckReq;
import com.zjhl.pad.moudle.entity.req.MyHobbyReq;
import com.zjhl.pad.moudle.entity.req.MyassetsReq;
import com.zjhl.pad.moudle.entity.req.NoBanckInstitutionReq;
import com.zjhl.pad.moudle.entity.req.NomalReq;
import com.zjhl.pad.moudle.entity.req.QukuailianReq;
import com.zjhl.pad.moudle.entity.req.RegisterCheckEmailPhoneReq;
import com.zjhl.pad.moudle.entity.req.RegisterCodeReq;
import com.zjhl.pad.moudle.entity.req.RegisterCounrtyArear;
import com.zjhl.pad.moudle.entity.req.RegisterDictionaryReq;
import com.zjhl.pad.moudle.entity.req.RegisterReq;
import com.zjhl.pad.moudle.entity.req.RegisterSelectCountry;
import com.zjhl.pad.moudle.entity.req.RepublishExpiredAssetsReq;
import com.zjhl.pad.moudle.entity.req.ResetPasswordReq;
import com.zjhl.pad.moudle.entity.req.ReviewCheckTransferReq;
import com.zjhl.pad.moudle.entity.req.ReviewOfferSubmitLetterOnSaleListReq;
import com.zjhl.pad.moudle.entity.req.ReviewSubmitLetterReq;
import com.zjhl.pad.moudle.entity.req.TellerManagementReq;
import com.zjhl.pad.moudle.entity.req.TellerManagermentAddReq;
import com.zjhl.pad.moudle.entity.req.UploadFileReq;
import com.zjhl.pad.moudle.entity.req.UserManualReq;
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
import com.zjhl.pad.moudle.entity.res.IntegralRes;
import com.zjhl.pad.moudle.entity.res.IssueFactoringRes;
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

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @desc: ActionPresenter
 * @version: v1.0
 * @packagename: com.zjhl.pad.okhttp
 * @author: pluto
 * @create: 2018/4/18 13:43
 * @projectname: nnkj
 **/
public class ActionPresenter extends AppPresenter {
    private static volatile ActionPresenter mInstance;

    public static ActionPresenter getInstance() {
        if (mInstance == null) {
            synchronized (ActionPresenter.class) {
                if (mInstance == null) {
                    mInstance = new ActionPresenter();
                }
            }
        }
        return mInstance;
    }

    /**
     * 登录的接口
     */
    public void login(LoginCheckUserPasswordReq data, Observer<ResponseBean> observer) {
        Observable<ResponseBean> loginobservable = mApi.login(createRequestBody(data));
        // 转换
//        convert(loginobservable, observer);
        execute(loginobservable, observer);
    }

    /**
     * 验证用户名密码的接口
     */
    public Call<LoginCheckUserPasswordRes> loginCheckUserPasswordRet(LoginCheckUserPasswordReq data) {
        Call<LoginCheckUserPasswordRes> loginCheckUserPasswordRet = mApi.loginCheckUserPasswordRet(createRequestBody(data));
        return loginCheckUserPasswordRet;
    }


    /**
     * 登录发送验证码的接口
     */
    public Call<LoginMsgCodeRes> loginMsgCodeRet(LoginCheckUserPasswordReq data) {
        Call<LoginMsgCodeRes> loginMsgCodeRet = mApi.loginMsgCodeRet(createRequestBody(data));
        return loginMsgCodeRet;
    }

    /**
     * 我的里面发送验证码的接口
     */
    public Call<MineMsgRes> mineMsgCodeRet(MineMsgReq data) {
        Call<MineMsgRes> mineMsgCodeRet = mApi.mineMsgCodeRet(createRequestBody(data));
        return mineMsgCodeRet;
    }

    /**
     * 机构信息认证资料补充的接口
     */
    public Call<BanckInstitutionRes> BanckInstitutionRet(BanckInstitutionalReq data) {
        Call<BanckInstitutionRes> banckinstitutionRet = mApi.banckinstituRet(createRequestBody(data));
        return banckinstitutionRet;
    }

    /**
     * 企业
     *
     * @param data
     * @return
     */
    public Call<EnterpriseRes> enterRet(EnterpriseReq data) {
        Call<EnterpriseRes> enterpriseRet = mApi.enterpriseRet(createRequestBody(data));
        return enterpriseRet;
    }

    /**
     * 非银行金融机构的接口
     */
    public Call<NoBanckInstitutionRes> NoBanckInstitutionRet(NoBanckInstitutionReq data) {
        Call<NoBanckInstitutionRes> nobanckinstitutionRet = mApi.nobanckinstituRet(createRequestBody(data));
        return nobanckinstitutionRet;
    }

    /**
     * 企业信息区块链的接口
     */
    public Call<QukuaiLianRes> qukuailianRet(QukuailianReq data) {
        Call<QukuaiLianRes> qukuailianret = mApi.qukuailianRet(createRequestBody(data));
        return qukuailianret;
    }


    /**
     * 登录的接口
     */
    public Call<LoginRes> loginRet(LoginReq data) {
        Call<LoginRes> loginret = mApi.loginRet(createRequestBody(data));
        return loginret;
    }


    /**
     * 退出登录的接口
     */
    public Call<LoginRes> logoutRet(LoginReq data) {
        Call<LoginRes> logoutRet = mApi.logoutRet(createRequestBody(data));
        return logoutRet;
    }

    /**
     * 待售资产 福费廷 移动查询接口
     */
    public Call<AsellasetsFuFeiTingRes> asserfufeitingRet(MyassetsReq data) {
        Call<AsellasetsFuFeiTingRes> myassertsret = mApi.asellasetfufeitingRet(createRequestBody(data));
        return myassertsret;
    }

    /**
     * 待售资产 保理 移动查询接口
     */
    public Call<AsellasetsBaoliRes> asserfufeitingRet(AssertsBaoliReq data) {
        Call<AsellasetsBaoliRes> assertbaoliret = mApi.asertbaoliRet(createRequestBody(data));
        return assertbaoliret;
    }

    /**
     * 我的报价 福费廷列表的接口
     */
    public Call<MyOfferFufei> myasserRet(MyassetsReq data) {
        Call<MyOfferFufei> myassertsret = mApi.myassertRet(createRequestBody(data));
        return myassertsret;
    }


    /**
     * 我的偏好 福费廷列表的接口
     */
    public Call<MyHobbyRes> myHobbyRet(MyHobbyReq data) {
        Call<MyHobbyRes> myassertsret = mApi.myhobbyRet(createRequestBody(data));
        return myassertsret;
    }

    /**
     * 我的偏好 保理列表的接口
     */
    public Call<HobbyBaoliRes> myHobbyRet(HobbyReq data) {
        Call<HobbyBaoliRes> baolisret = mApi.myhobbyboliRet(createRequestBody(data));
        return baolisret;
    }

    /**
     * 我的报价 福费按钮提交的接口
     */
    public Call<AssertCommitRes> asserRet(AssertCommitReq data) {
        Call<AssertCommitRes> assertscommitret = mApi.assertRet(createRequestBody(data));
        return assertscommitret;
    }


    /**
     * //我的报价 复核岗审核通过、驳回、取消
     *
     * @POST(Constants.NETPATH.OFFERBOHUIPATH) Call<BohuiResonRes> offerBohuiRet(@Body RequestBody requestBody);
     */
    public Call<BohuiResonRes> offerBohuiRet(AssertCommitReq data) {
        Call<BohuiResonRes> offerBohuiRet = mApi.offerBohuiRet(createRequestBody(data));
        return offerBohuiRet;
    }

    /**
     * //我的报价 复核岗审核通过、驳回、取消
     *
     * @POST(Constants.NETPATH.OFFERBOHUIPATH) Call<BohuiResonRes> factoringOfferBohuiRet(@Body RequestBody requestBody);
     */
    public Call<BohuiResonRes> factoringOfferBohuiRet(AssertCommitReq data) {
        Call<BohuiResonRes> factoringOfferBohuiRet = mApi.factoringOfferBohuiRet(createRequestBody(data));
        return factoringOfferBohuiRet;
    }

    /**
     * 待售资产 驳回的接口
     */
    public Call<BohuiResonRes> bohuiRet(BohuiResonReq data) {
        Call<BohuiResonRes> bohuiret = mApi.bohuiRet(createRequestBody(data));
        return bohuiret;
    }

    /**
     * 待售资产 删除的接口
     */
    public Call<BohuiResonRes> deleteRet(AssertFufeitingDeleteRq data) {
        Call<BohuiResonRes> bohuiret = mApi.deleteRet(createRequestBody(data));
        return bohuiret;
    }

    /**
     * // 保理待售资产 删除的接口
     *
     * @POST(Constants.NETPATH.FACTORINGLOOKBOHUIDELETEPATH) Call<BohuiResonRes> factoringDeleteRet(@Body RequestBody requestBody);
     */
    public Call<BohuiResonRes> factoringDeleteRet(AssertFufeitingDeleteRq data) {
        Call<BohuiResonRes> factoringDeleteRet = mApi.factoringDeleteRet(createRequestBody(data));
        return factoringDeleteRet;
    }

    /**
     * 待售资产 取消的接口
     */
    public Call<CanselRes> cansalRet(CansalResonReq data) {
        Call<CanselRes> cansalret = mApi.cansalRet(createRequestBody(data));
        return cansalret;
    }

    /**
     * 待售资产 查看驳回意见的接口
     */
    public Call<LookBohuiReasonRs> lookcansalRet(LookBohuiReasonReq data) {
        Call<LookBohuiReasonRs> lookcansalret = mApi.lookBohuiResonRet(createRequestBody(data));
        return lookcansalret;
    }

    /**
     * 修改贴现率
     *
     * @param data
     * @return
     */
    public Call<BaojiatxRs> myasserRet(BaojiatxReq data) {
        Call<BaojiatxRs> baojiaret = mApi.tiexianlvRet(createRequestBody(data));
        return baojiaret;
    }

    /**
     * 我的报价 保理列表的接口
     */
    public Call<MyOfferBaoliRs> myasserRet(BaoliAssertReq data) {
        Call<MyOfferBaoliRs> tiexianlv = mApi.myOfferbaoli(createRequestBody(data));
        return tiexianlv;
    }

    /**
     * 锁定的接口
     */
    public Call<LockRes> lockRet(LockReq data) {
        Call<LockRes> lockret = mApi.lockRet(createRequestBody(data));
        return lockret;
    }

    /**
     * 注销的接口
     */
    public Call<LockRes> nomalRet(NomalReq data) {
        Call<LockRes> lockret = mApi.nomalRet(createRequestBody(data));
        return lockret;
    }

    /**
     * 绑定手机号
     *
     * @param data
     * @return
     */
    public Call<CheckBindPhoneNumberRes> checkbindphoneRet(CheckBindPhoneNumberReq data) {
        Call<CheckBindPhoneNumberRes> checkbindret = mApi.checkbindRet(createRequestBody(data));
        return checkbindret;
    }

    /**
     * 柜员修改
     *
     * @param data
     * @return
     */
    public Call<ModeBanckRes> modeBanckRet(ModeBanckReq data) {
        Call<ModeBanckRes> modebanckret = mApi.modebanckRet(createRequestBody(data));
        return modebanckret;
    }

    /**
     * 我的接口
     */
    public Call<MineStateRes> mineRet() {
        Call<MineStateRes> loginret = mApi.mineRet(createRequestBody(""));
        return loginret;
    }

    /**
     * 企业信息接口
     */
    public Call<OrganizationmMsgRes> enterRet() {
        Call<OrganizationmMsgRes> OrganizationmMsgRes = mApi.tellerManager(createRequestBody(""));
        return OrganizationmMsgRes;
    }

    /**
     * 注册的接口
     */
    public Call<RegisterRes> registerRet(RegisterReq data) {
        Call<RegisterRes> registerRet = mApi.registerRet(createRequestBody(data));
        return registerRet;
    }

    /**
     * 获取注册验证码接口
     */
    public Call<RegisterCodeRes> registerCodeRet(RegisterCodeReq data) {
        Call<RegisterCodeRes> registerCodeRet = mApi.registerCodeRet(createRequestBody(data));
        return registerCodeRet;
    }

    /**
     * 异步校验手机号、邮箱唯一
     */
    public Call<RegisterCheckEmailPhoneRes> registerCheckEmailPhoneRet(RegisterCheckEmailPhoneReq data) {
        Call<RegisterCheckEmailPhoneRes> registerCheckEmailPhoneRet = mApi.registerCheckEmailPhoneRet(createRequestBody(data));
        return registerCheckEmailPhoneRet;
    }


    /**
     * 异步校验企业信息是否正确
     */
    public Call<CheckCompaneyNameRes> checkcompaneyRet(CheckCompaneyNameReq data) {
        Call<CheckCompaneyNameRes> checkcompaneyRet = mApi.checkConpanyNameRet(createRequestBody(data));
        return checkcompaneyRet;
    }

    /**
     * 机构注册时生成图片验证码
     */
    public Call<ResponseBody> registerImgRet(String imgCodeId) {
        Call<ResponseBody> registerImgRet = mApi.registerImgRet(imgCodeId);
        return registerImgRet;
    }

    /**
     * 获取机构类型
     */
    public Call<RegisterDictionaryRes> registerDictionaryRet(RegisterDictionaryReq data) {
        Call<RegisterDictionaryRes> registerDictionaryRet = mApi.registerDictionaryRet(createRequestBody(data));
        return registerDictionaryRet;
    }

    /**
     * 获取国家类型
     */
    public Call<RegisterCountryArearRes> registerDictionaryRet(RegisterSelectCountry data) {
        Call<RegisterCountryArearRes> registercountryRet = mApi.registerCountryRet(createRequestBody(data));
        return registercountryRet;
    }

    /**
     * 获取地区
     *
     * @param data
     * @return
     */
    public Call<RegisterCountryArearRes> registerDictionaryArearRet(RegisterCounrtyArear data) {
        Call<RegisterCountryArearRes> registerDictionaryRet = mApi.registerArearRet(createRequestBody(data));
        return registerDictionaryRet;
    }

    /**
     * 找回密码 发送验证码接口
     */
    public Call<ForgetPassSendCodeRes> forgetPassSendCodeRet(ForgetPassSendCodeReq data) {
        Call<ForgetPassSendCodeRes> registerDictionaryRet = mApi.forgetPassSendCodeRet(createRequestBody(data));
        return registerDictionaryRet;
    }

    /**
     * 找回密码 下一步保存接口
     */
    public Call<ForgetPassSendCodeRes> forgetNextSaveCodeRet(ForgetPassSendCodeReq data) {
        Call<ForgetPassSendCodeRes> registerDictionaryRet = mApi.forgetNaxtSavePassCodeRet(createRequestBody(data));
        return registerDictionaryRet;
    }

    /**
     * @param data
     * @return
     */
    public Call<ForgetPassSendCodeRes> forgeSaveCodeRet(ResetPasswordReq data) {
        Call<ForgetPassSendCodeRes> registerDictionaryRet = mApi.forgetNaxtSavePassCodeRet(createRequestBody(data));
        return registerDictionaryRet;
    }

    /**
     * 未登录——修改密码最后一个接口
     *
     * @param data
     * @return
     */
    public Call<ForgetPassSendCodeRes> SavegudingRet(ResetPasswordReq data) {
        Call<ForgetPassSendCodeRes> registerDictionaryRet = mApi.forgetNaxtSavePassCodeRet(createRequestBody(data));
        return registerDictionaryRet;
    }


    /**
     * 找回密码 修改密码保存接口
     */
    public Call<ForgetPassSendCodeRes> saveforgetPasswordRet(ResetPasswordReq data) {
        Call<ForgetPassSendCodeRes> registerDictionaryRet = mApi.forgetNaxtSavePassCodeRet(createRequestBody(data));
        return registerDictionaryRet;
    }


    /**
     * 找回密码 下一步接口
     */
    public Call<ForgetPassNextRes> forgetPassNextRet(ForgetPassSendCodeReq data) {
        Call<ForgetPassNextRes> registerDictionaryRet = mApi.forgetPassNextRet(createRequestBody(data));
        return registerDictionaryRet;
    }

    /**
     * 登录后重置密码
     */
    public Call<ResetPasswordRes> LoginAfterResetPassword(ResetPasswordReq data) {
        Call<ResetPasswordRes> resetPassword = mApi.resetPassword(createRequestBody(data));
        return resetPassword;
    }

    /**
     * 添加柜员
     */
    public Call<TellerManagermentAddRes> AddTellerManager(TellerManagermentAddReq data) {
        Call<TellerManagermentAddRes> resetPassword = mApi.tellermanageradd(createRequestBody(data));
        return resetPassword;
    }

    /**
     * 首页banner接口
     */
    public Call<HomeBannerRes> homeBannerRet(Object data) {
        Call<HomeBannerRes> homeBannerRet = mApi.homeBannerRet(createRequestBody(data));
        return homeBannerRet;
    }

    /**
     * 首页成交笔数 成交金额 注册人数接口
     */
    public Call<HomeAllCountRes> homeAllCountRet(Object data) {
        Call<HomeAllCountRes> homeAllCountRet = mApi.homeAllCountRet(createRequestBody(data));
        return homeAllCountRet;
    }

    /**
     * //首页libor SHIBOR HIBOR指数接口
     * Call<HomeIndexRes> homeIndexRet
     */
    public Call<HomeIndexRes> homeIndexRet(Object data) {
        Call<HomeIndexRes> homeIndexRet = mApi.homeIndexRet(createRequestBody(data));
        return homeIndexRet;
    }


    /**
     * //市场行情福费廷、在售列表接口
     *
     * @POST(Constants.NETPATH.MARKETFORFAITINGSELLLIST) Call<MarketForfaitingSellRes> MarketForfaitingSellRet
     */
    public Call<MarketForfaitingSellRes> marketForfaitingSellRet(MarketForfaitingSellReq data) {
        Call<MarketForfaitingSellRes> marketForfaitingSellRet = mApi.marketForfaitingSellRet(createRequestBody(data));
        return marketForfaitingSellRet;
    }

    /**
     * //市场行情 保理-在售资产接口
     *
     * @POST(Constants.NETPATH.MARKETFACTORINGSELLLIST) Call<MarketFactoringSellRes> marketFactoringSellRet
     */
    public Call<MarketFactoringSellRes> marketFactoringSellRet(MarketFactoringSellReq data) {
        Call<MarketFactoringSellRes> marketFactoringSellRet = mApi.marketFactoringSellRet(createRequestBody(data));
        return marketFactoringSellRet;
    }

    /**
     * //发布 福费廷发布接口
     *
     * @POST(Constants.NETPATH.ISSUEFORFAITING) Call<MarketFactoringSellRes> issueForfaitingRet(@Body RequestBody requestBody);
     */
    public Call<MarketFactoringSellRes> issueForfaitingRet(IssueForfaitingReq data) {
        Call<MarketFactoringSellRes> issueForfaitingRet = mApi.issueForfaitingRet(createRequestBody(data));
        return issueForfaitingRet;
    }

    /**
     * //发布 保理发布接口
     *
     * @POST(Constants.NETPATH.ISSUEFACTORING) Call<IssueFactoringRes> issueFactoringRet(@Body RequestBody requestBody);
     */
    public Call<IssueFactoringRes> issueFactoringRet(IssueFactoringReq data) {
        Call<IssueFactoringRes> issueFactoringRet = mApi.issueFactoringRet(createRequestBody(data));
        return issueFactoringRet;
    }

    /**
     * //市场 福费廷 详情接口
     *
     * @POST(Constants.NETPATH.MARKETFORFAITINGDETAIL) Call<MarketForfaitingDetailRes> marketForfaitingDetailRet(@Body RequestBody requestBody);
     */
    public Call<MarketForfaitingDetailRes> marketForfaitingDetailRet(MarketForfaitingDetailReq data) {
        Call<MarketForfaitingDetailRes> marketForfaitingDetailRet = mApi.marketForfaitingDetailRet(createRequestBody(data));
        return marketForfaitingDetailRet;
    }

    /**
     * //市场 福费廷 详情接口
     *
     * @POST(Constants.NETPATH.MARKETFORFAITINGDETAIL) Call<MarketForfaitingDetailRes> marketForfaitingDetailRet(@Body RequestBody requestBody);
     */
    public Call<MarketForfaitingDetailOldRes> marketForfaitingDetailOldRet(MarketForfaitingDetailReq data) {
        Call<MarketForfaitingDetailOldRes> marketForfaitingDetailOldRet = mApi.marketForfaitingDetailOldRet(createRequestBody(data));
        return marketForfaitingDetailOldRet;
    }

    /**
     * //市场 保理 详情接口
     *
     * @POST(Constants.NETPATH.MARKETFACTORINGINGDETAIL) Call<MarketForfaitingDetailRes> marketFactoringDetailRet(@Body RequestBody requestBody);
     */
    public Call<MarketFactoringDetailRes> marketFactoringDetailRet(MarketForfaitingDetailReq data) {
        Call<MarketFactoringDetailRes> marketFactoringDetailRet = mApi.marketFactoringDetailRet(createRequestBody(data));
        return marketFactoringDetailRet;
    }

    /**
     * //市场 福费廷 买家提交报价接口
     *
     * @POST(Constants.NETPATH.MARKETFORFAITINGOFFER) Call<MarketForfaitingDetailRes> marketForfaitingOfferRet(@Body RequestBody requestBody);
     */
    public Call<MarketForfaitingDetailRes> marketForfaitingOfferRet(MarketForfaitingOfferReq data) {
        Call<MarketForfaitingDetailRes> marketForfaitingOfferRet = mApi.marketForfaitingOfferRet(createRequestBody(data));
        return marketForfaitingOfferRet;
    }

    /**
     * //市场 福费廷 买家报价列表接口
     *
     * @POST(Constants.NETPATH.MARKETFORFAITINGOFFERLIST) Call<MarketForfaitingOfferListRes> marketForfaitingOfferListRet(@Body RequestBody requestBody);
     */
    public Call<MarketForfaitingOfferListRes> marketForfaitingOfferList1Ret(MarketForfaitingOfferListReq data) {
        Call<MarketForfaitingOfferListRes> marketForfaitingOfferList1Ret = mApi.marketForfaitingOfferList1Ret(createRequestBody(data));
        return marketForfaitingOfferList1Ret;
    }

    /**
     * //在售 福费廷 买家报价列表接口
     *
     * @POST(Constants.NETPATH.MARKETFORFAITINGOFFERLIST) Call<MarketForfaitingOfferListRes> marketForfaitingOfferListRet(@Body RequestBody requestBody);
     */
    public Call<MarketForfaitingOfferListRes> marketForfaitingOfferListRet(MarketForfaitingOfferListReq data) {
        Call<MarketForfaitingOfferListRes> marketForfaitingOfferListRet = mApi.marketForfaitingOfferListRet(createRequestBody(data));
        return marketForfaitingOfferListRet;
    }

    /**
     * //市场 福费廷 卖家 确认撮合接口
     *
     * @POST(Constants.NETPATH.MARKETFORFAITINGOFFERCONFIRM) Call<MarketForfaitingDetailRes> marketForfaitingOfferConfirmRet(@Body RequestBody requestBody);
     */
    public Call<MarketForfaitingDetailRes> marketForfaitingOfferConfirmRet(MarketForfaitingOfferReq data) {
        Call<MarketForfaitingDetailRes> marketForfaitingOfferConfirmRet = mApi.marketForfaitingOfferConfirmRet(createRequestBody(data));
        return marketForfaitingOfferConfirmRet;
    }


    /**
     * //发布 偏好福费廷录入接口
     *
     * @POST(Constants.NETPATH.IUSSINGFORFAITINGENTRY) Call<MarketFactoringDetailRes> iussingForfaitingRet(@Body RequestBody requestBody);
     */
    public Call<MarketFactoringDetailRes> iussingForfaitingRet(IussingForfaitingEntryReq data) {
        Call<MarketFactoringDetailRes> iussingForfaitingRet = mApi.iussingForfaitingRet(createRequestBody(data));
        return iussingForfaitingRet;
    }


    /**
     * //我的资产 在售资产列表接口
     *
     * @POST(Constants.NETPATH.MINEONSALELIST) Call<MyOfferFufei> mineOnSaleListRet(@Body RequestBody requestBody);
     */
    public Call<MyOfferFufei> mineForfaitingOnSaleListRet(MineForfaitingOnSaleListReq data) {
        Call<MyOfferFufei> mineForfaitingOnSaleListRet = mApi.mineForfaitingOnSaleListRet(createRequestBody(data));
        return mineForfaitingOnSaleListRet;
    }


    /**
     * //我的资产 在售资产列表保理接口
     *
     * @POST(Constants.NETPATH.MINEONSALELIST) Call<MyOfferBaoliRs> mineFactoringOnSaleListRet(@Body RequestBody requestBody);
     */
    public Call<MyOfferBaoliRs> mineFactoringOnSaleListRet(MineForfaitingOnSaleListReq data) {
        Call<MyOfferBaoliRs> mineFactoringOnSaleListRet = mApi.mineFactoringOnSaleListRet(createRequestBody(data));
        return mineFactoringOnSaleListRet;
    }


    /**
     * //发布 偏好保理录入接口
     *
     * @POST(Constants.NETPATH.IUSSINGFACTORINGENTRY) Call<MarketFactoringDetailRes> iussingFactoringRet(@Body RequestBody requestBody);
     */
    public Call<MarketFactoringDetailRes> iussingFactoringRet(IussingFactoringEntryReq data) {
        Call<MarketFactoringDetailRes> iussingFactoringRet = mApi.iussingFactoringRet(createRequestBody(data));
        return iussingFactoringRet;
    }

    /**
     * 柜员列表
     *
     * @param data
     * @return
     */
    public Call<TellerManagementRes> TellerManagementRet(TellerManagementReq data) {
        Call<TellerManagementRes> tellerManagementRet = mApi.tellermanagerlist(createRequestBody(data));
        return tellerManagementRet;
    }

    /**
     * 我的积分
     *
     * @param
     * @return
     */
    public Call<IntegralRes> MyIntegral(TellerManagementReq data) {
        Call<IntegralRes> integraltRet = mApi.integralRes(createRequestBody(data));
        return integraltRet;
    }


    /**
     * //我的资产 在售资产报价-经办列表接口
     *
     * @POST(Constants.NETPATH.HANDLEMINEOFFERLISTONSALELIST) Call<MarketForfaitingOfferListRes> handleOfferListOnSaleListRet(@Body RequestBody requestBody);
     */
    public Call<MarketForfaitingOfferListRes> handleOfferListOnSaleListRet(MarketForfaitingOfferListReq data) {
        Call<MarketForfaitingOfferListRes> handleOfferListOnSaleListRet = mApi.handleOfferListOnSaleListRet(createRequestBody(data));
        return handleOfferListOnSaleListRet;
    }

    /**
     * //我的资产 在售资产报价-复核列表接口
     *
     * @POST(Constants.NETPATH.REVIEWMINEOFFERLISTONSALELIST) Call<MarketForfaitingOfferListRes> reviewOfferListOnSaleListRet(@Body RequestBody requestBody);
     */
    public Call<MarketForfaitingOfferListRes> reviewOfferListOnSaleListRet(MarketForfaitingOfferListReq data) {
        Call<MarketForfaitingOfferListRes> reviewOfferListOnSaleListRet = mApi.reviewOfferListOnSaleListRet(createRequestBody(data));
        return reviewOfferListOnSaleListRet;
    }

    /**
     * //福费廷资产详情查询用于经办修改接口
     *
     * @POST(Constants.NETPATH.HANDLEFORFAITINGDETAIL) Call<MarketFactoringSellRes> handleForfaitingDetailRet(@Body RequestBody requestBody);
     */
    public Call<HandleFactoringDetailRes> handleForfaitingDetailRet(MarketForfaitingOfferReq data) {
        Call<HandleFactoringDetailRes> handleForfaitingDetailRet = mApi.handleForfaitingDetailRet(createRequestBody(data));
        return handleForfaitingDetailRet;
    }

    /**
     * //保理资产详情查询用于经办修改接口
     *
     * @POST(Constants.NETPATH.HANDLEFACTORINGDETAIL) Call<HandleFactoringDetailRes> handleFactoringDetailRet(@Body RequestBody requestBody);
     */
    public Call<HandleFactoringDetailSearchRes> handleFactoringDetailRet(MarketForfaitingOfferReq data) {
        Call<HandleFactoringDetailSearchRes> handleFactoringDetailRet = mApi.handleFactoringDetailRet(createRequestBody(data));
        return handleFactoringDetailRet;
    }


    /**
     * //我的资产 在售资产卖家复核确认提交接口
     * 请求参数只有id所以 复用MarketForfaitingOfferReq 请求对象
     *
     * @POST(Constants.NETPATH.REVIEWOFFERSUBMITONSALELIST) Call<ReviewOfferSubmitOnSaleListRes> reviewOfferSubmitOnSaleListRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitOnSaleListRes> reviewOfferSubmitOnSaleListRet(MarketForfaitingOfferReq data) {
        Call<ReviewOfferSubmitOnSaleListRes> reviewOfferSubmitOnSaleListRet = mApi.reviewOfferSubmitOnSaleListRet(createRequestBody(data));
        return reviewOfferSubmitOnSaleListRet;
    }


    /**
     * //我的资产 在售资产资产卖家要约函发送，并确认提交。接口
     *
     * @POST(Constants.NETPATH.REVIEWOFFERSUBMILETTERTONSALELIST) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewOfferSubmitLetterOnSaleListRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitLetterOnSaleListRes> reviewOfferSubmitLetterOnSaleListRet(ReviewOfferSubmitLetterOnSaleListReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> reviewOfferSubmitLetterOnSaleListRet = mApi.reviewOfferSubmitLetterOnSaleListRet(createRequestBody(data));
        return reviewOfferSubmitLetterOnSaleListRet;
    }


    /**
     * //我的资产更新接口
     *
     * @POST(Constants.NETPATH.HANDLEFORFAITINGDETAILUPDATE) Call<MarketFactoringSellRes> handleForfaitingDetailUpdateRet(@Body RequestBody requestBody);
     */
    public Call<MarketFactoringSellRes> handleForfaitingDetailUpdateRet(IssueForfaitingReq data) {
        Call<MarketFactoringSellRes> handleForfaitingDetailUpdateRet = mApi.handleForfaitingDetailUpdateRet(createRequestBody(data));
        return handleForfaitingDetailUpdateRet;
    }

    /**
     * //我的资产保理更新接口
     *
     * @POST(Constants.NETPATH.HANDLEFACTORINGDETAILUPDATE) Call<MarketFactoringSellRes> handleFactoringDetailUpdateRet(@Body RequestBody requestBody);
     */
    public Call<MarketFactoringSellRes> handleFactoringDetailUpdateRet(IssueFactoringReq data) {
        Call<MarketFactoringSellRes> handleFactoringDetailUpdateRet = mApi.handleFactoringDetailUpdateRet(createRequestBody(data));
        return handleFactoringDetailUpdateRet;
    }

    /**
     * //资产卖家福费廷确认接口 提交让渡函
     *
     * @POST(Constants.NETPATH.REVIEWSUBMITLETTER) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewSubmitLetterOnSaleListRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitLetterOnSaleListRes> reviewSubmitLetterOnSaleListRet(ReviewSubmitLetterReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> reviewSubmitLetterOnSaleListRet = mApi.reviewSubmitLetterOnSaleListRet(createRequestBody(data));
        return reviewSubmitLetterOnSaleListRet;
    }

    /**
     * //资产卖家保理确认接口 提交让渡函  复用ReviewOfferSubmitLetterOnSaleListRes
     *
     * @POST(Constants.NETPATH.REVIEWFACTORINGSUBMITLETTER) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSubmitLetterOnSaleListRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSubmitLetterOnSaleListRet(ReviewSubmitLetterReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSubmitLetterOnSaleListRet = mApi.reviewFactoringSubmitLetterOnSaleListRet(createRequestBody(data));
        return reviewFactoringSubmitLetterOnSaleListRet;
    }


    /**
     * //复核  福费廷发布接口 复用MarketForfaitingOfferReq
     *
     * @POST(Constants.NETPATH.REVIEWFORFAITINGIUSS) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewForfaitingIussRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitLetterOnSaleListRes> reviewForfaitingIussRet(MarketForfaitingOfferReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> reviewForfaitingIussRet = mApi.reviewForfaitingIussRet(createRequestBody(data));
        return reviewForfaitingIussRet;
    }


    /**
     * //我的报价 福费廷复核提交接口
     *
     * @POST(Constants.NETPATH.REVIEWOFFERFORFAITINGCOMMITPATH) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewOfferForfaitingCommitRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitLetterOnSaleListRes> reviewOfferForfaitingCommitRet(MarketForfaitingOfferReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> reviewOfferForfaitingCommitRet = mApi.reviewOfferForfaitingCommitRet(createRequestBody(data));
        return reviewOfferForfaitingCommitRet;
    }

    /**
     * //待售资产 福费廷提交按钮列表接口
     *
     * @POST(Constants.NETPATH.ASSERTCOMMITPATH) Call<ReviewOfferSubmitLetterOnSaleListRes> handleForfaitingCommitRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitLetterOnSaleListRes> handleForfaitingCommitRet(MarketForfaitingOfferReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> handleForfaitingCommitRet = mApi.handleForfaitingCommitRet(createRequestBody(data));
        return handleForfaitingCommitRet;
    }

    /**
     * //待售资产 保理经办提交按钮列表接口
     *
     * @POST(Constants.NETPATH.FACTORINGASSERTCOMMITPATH) Call<ReviewOfferSubmitLetterOnSaleListRes> handleFactoringCommitRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitLetterOnSaleListRes> handleFactoringCommitRet(MarketForfaitingOfferReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> handleFactoringCommitRet = mApi.handleFactoringCommitRet(createRequestBody(data));
        return handleFactoringCommitRet;
    }

    /**
     * //待售资产 保理复核发布接口
     *
     * @POST(Constants.NETPATH.REVIEWASSERTBAOLIPATH) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringCommitCommitRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringCommitCommitRet(MarketForfaitingOfferReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringCommitCommitRet = mApi.reviewFactoringCommitCommitRet(createRequestBody(data));
        return reviewFactoringCommitCommitRet;
    }


    /**
     * //复核  福费廷 卖家复核驳回接口 复用MarketForfaitingOfferReq
     *
     * @POST(Constants.NETPATH.REVIEWFORFAITINGCANCEL) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringRejectRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringRejectRet(MarketForfaitingOfferReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringRejectRet = mApi.reviewFactoringRejectRet(createRequestBody(data));
        return reviewFactoringRejectRet;
    }


    /**
     * //消息 客户服务列表接口
     *
     * @POST(Constants.NETPATH.CUSTOMERMESSAGELIST) Call<ReviewOfferSubmitLetterOnSaleListRes> customerMessageListRet(@Body RequestBody requestBody);
     */
    public Call<MessageListRes> customerMessageListRet(MessageListReq data) {
        Call<MessageListRes> customerMessageListRet = mApi.customerMessageListRet(createRequestBody(data));
        return customerMessageListRet;
    }


    /**
     * //消息 客户服务列表详情接口
     *
     * @POST(Constants.NETPATH.CUSTOMERMESSAGELISTDETAIL) Call<ReviewOfferSubmitLetterOnSaleListRes> customerMessageListDetailRet(@Body RequestBody requestBody);
     */
    public Call<MessageListDetailRes> customerMessageListDetailRet(MessageListReq data) {
        Call<MessageListDetailRes> customerMessageListDetailRet = mApi.customerMessageListDetailRet(createRequestBody(data));
        return customerMessageListDetailRet;
    }


    /**
     * //消息 业务消息列表接口
     *
     * @POST(Constants.NETPATH.BUSSINESSEMESSAGELIST) Call<ReviewOfferSubmitLetterOnSaleListRes> bussinessMessageListRet(@Body RequestBody requestBody);
     */
    public Call<BusinessMessage> bussinessMessageListRet(MessageListReq data) {
        Call<BusinessMessage> bussinessMessageListRet = mApi.bussinessMessageListRet(createRequestBody(data));
        return bussinessMessageListRet;
    }


    /**
     * //消息 业务消息详情接口
     *
     * @POST(Constants.NETPATH.BUSSINESSMESSAGELISTDETAIL) Call<ReviewOfferSubmitLetterOnSaleListRes> bussinessMessageListDetailRet(@Body RequestBody requestBody);
     */
    public Call<MessageListDetailRes> bussinessMessageListDetailRet(MessageListReq data) {
        Call<MessageListDetailRes> bussinessMessageListDetailRet = mApi.bussinessMessageListDetailRet(createRequestBody(data));
        return bussinessMessageListDetailRet;
    }


    /**
     * //消息 系统公告消息列表接口
     *
     * @POST(Constants.NETPATH.SYSTEMEMESSAGELIST) Call<MessageListRes> systemMessageListRet(@Body RequestBody requestBody);
     */
    public Call<MessageListRes> systemMessageListRet(MessageListReq data) {
        Call<MessageListRes> systemMessageListRet = mApi.systemMessageListRet(createRequestBody(data));
        return systemMessageListRet;
    }


    /**
     * //消息 系统公告消息详情接口
     *
     * @POST(Constants.NETPATH.SYSTEMMESSAGELISTDETAIL) Call<ReviewOfferSubmitLetterOnSaleListRes> systemMessageListDetailRet(@Body RequestBody requestBody);
     */
    public Call<MessageListDetailRes> systemMessageListDetailRet(MessageListReq data) {
        Call<MessageListDetailRes> systemMessageListDetailRet = mApi.systemMessageListDetailRet(createRequestBody(data));
        return systemMessageListDetailRet;
    }


    /**
     * //消息 消息状态更改为已读接口
     *
     * @POST(Constants.NETPATH.UPDATEMESSAGELIST) Call<ReviewOfferSubmitLetterOnSaleListRes> updateMessageListRet(@Body RequestBody requestBody);
     */
    public Call<MessageListRes> updateMessageListRet(HobbyDeleteReq data) {
        Call<MessageListRes> updateMessageListRet = mApi.updateMessageListRet(createRequestBody(data));
        return updateMessageListRet;
    }


    /**
     * //消息 收藏接口
     *
     * @POST(Constants.NETPATH.COMMONMESSAGELISTDETAIL) Call<ReviewOfferSubmitLetterOnSaleListRes> commonMessageListRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitLetterOnSaleListRes> commonMessageListRet(MessageListReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> commonMessageListRet = mApi.commonMessageListRet(createRequestBody(data));
        return commonMessageListRet;
    }


    /**
     * //消息 取消收藏接口
     *
     * @POST(Constants.NETPATH.UNCOMMONMESSAGELISTDETAIL) Call<ReviewOfferSubmitLetterOnSaleListRes> unCommonMessageListRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitLetterOnSaleListRes> unCommonMessageListRet(MessageListReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> unCommonMessageListRet = mApi.unCommonMessageListRet(createRequestBody(data));
        return unCommonMessageListRet;
    }


    /**
     * //区块链资产查询接口
     *
     * @POST(Constants.NETPATH.BLOCKCHAINBUSSINESS) Call<BlockChainRes> blockChainBussinessRet(@Body RequestBody requestBody);
     */
    public Call<BlockChainRes> blockChainBussinessRet(BlockChainReq data) {
        Call<BlockChainRes> blockChainBussinessRet = mApi.blockChainBussinessRet(createRequestBody(data));
        return blockChainBussinessRet;
    }


    /**
     * //区块链机构查询接口
     *
     * @POST(Constants.NETPATH.BLOCKCHAINCOMPANY) Call<BlockChainRes> blockChainCompanyRet(@Body RequestBody requestBody);
     */
    public Call<BlockChainRes> blockChainCompanyRet(BlockChainReq data) {
        Call<BlockChainRes> blockChainCompanyRet = mApi.blockChainCompanyRet(createRequestBody(data));
        return blockChainCompanyRet;
    }


    /**
     * //区块链柜员查询接口
     *
     * @POST(Constants.NETPATH.BLOCKCHAINTELLER) Call<BlockChainRes> blockChainTellerRet(@Body RequestBody requestBody);
     */
    public Call<BlockChainRes> blockChainTellerRet(BlockChainReq data) {
        Call<BlockChainRes> blockChainTellerRet = mApi.blockChainTellerRet(createRequestBody(data));
        return blockChainTellerRet;
    }

    /**
     * //复核  福费廷 卖家复核取消接口 返回复用BlockChainRes 请求复用MessageListReq
     *
     * @POST(Constants.NETPATH.REVIEWFORFAITINGCANCELSELL) Call<BlockChainRes> reviewForfaitingCancelSellRet(@Body RequestBody requestBody);
     */
    public Call<BlockChainRes> reviewForfaitingCancelSellRet(MessageListReq data) {
        Call<BlockChainRes> reviewForfaitingCancelSellRet = mApi.reviewForfaitingCancelSellRet(createRequestBody(data));
        return reviewForfaitingCancelSellRet;
    }


    /**
     * //复核  保理 卖家复核取消接口 返回复用BlockChainRes
     *
     * @POST(Constants.NETPATH.REVIEWFACTORINGCANCELSELL) Call<BlockChainRes> reviewFactoringCancelSellRet(@Body RequestBody requestBody);
     */
    public Call<BlockChainRes> reviewFactoringCancelSellRet(MessageListReq data) {
        Call<BlockChainRes> reviewFactoringCancelSellRet = mApi.reviewFactoringCancelSellRet(createRequestBody(data));
        return reviewFactoringCancelSellRet;
    }


    /**
     * //复核  福费廷 买家复核取消接口 返回复用BlockChainRes  请求复用MessageListReq
     *
     * @POST(Constants.NETPATH.REVIEWFORFAITINGCANCELBUY) Call<BlockChainRes> reviewForfaitingCancelBuyRet(@Body RequestBody requestBody);
     */
    public Call<BlockChainRes> reviewForfaitingCancelBuyRet(MessageListReq data) {
        Call<BlockChainRes> reviewForfaitingCancelBuyRet = mApi.reviewForfaitingCancelBuyRet(createRequestBody(data));
        return reviewForfaitingCancelBuyRet;
    }


    /**
     * //复核 保理 买家复核取消接口 返回复用BlockChainRes
     *
     * @POST(Constants.NETPATH.REVIEWFACTORINGCANCELBUY) Call<BlockChainRes> reviewFactoringCancelBuyRet(@Body RequestBody requestBody);
     */
    public Call<BlockChainRes> reviewFactoringCancelBuyRet(MessageListReq data) {
        Call<BlockChainRes> reviewFactoringCancelBuyRet = mApi.reviewFactoringCancelBuyRet(createRequestBody(data));
        return reviewFactoringCancelBuyRet;
    }


    /**
     * //复核  福费廷 买家复核确认接口 复用 ReviewOfferSubmitLetterOnSaleListRes 请求复用MessageListReq
     *
     * @POST(Constants.NETPATH.REVIEWFORFAITINGSURE) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewForfaitingSureRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitLetterOnSaleListRes> reviewForfaitingSureRet(MessageListReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> reviewForfaitingSureRet = mApi.reviewForfaitingSureRet(createRequestBody(data));
        return reviewForfaitingSureRet;
    }

    /**
     * 我的偏好———福费廷删除
     *
     * @param data
     * @return
     */
    public Call<HobbyDeleteRes> hobbyFufeitingDeleteRet(HobbyDeleteReq data) {
        Call<HobbyDeleteRes> hobbyRet = mApi.hobbyRet(createRequestBody(data));
        return hobbyRet;
    }

    /**
     * 我的偏好———保理删除
     *
     * @param data
     * @return
     */
    public Call<HobbyDeleteRes> hobbyBaoliDeleteRet(HobbyDeleteReq data) {
        Call<HobbyDeleteRes> hobbyRet = mApi.hobbybaoliRet(createRequestBody(data));
        return hobbyRet;
    }

    /**
     * 消息红点数
     *
     * @param data
     * @return
     */
    public Call<MsgCountRes> msgNodeRedRet(HobbyDeleteReq data) {
        Call<MsgCountRes> msgnodeRet = mApi.newnoderedRet(createRequestBody(data));
        return msgnodeRet;
    }


    /**
     * //复核  保理 买家复核确认接口 复用 ReviewOfferSubmitLetterOnSaleListRes
     *
     * @POST(Constants.NETPATH.REVIEWFACTORINGSURE) Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSureRet(@Body RequestBody requestBody);
     */
    public Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSureRet(MessageListReq data) {
        Call<ReviewOfferSubmitLetterOnSaleListRes> reviewFactoringSureRet = mApi.reviewFactoringSureRet(createRequestBody(data));
        return reviewFactoringSureRet;
    }

    /**
     * //查看要约函  复用MessageListReq id字段
     *
     * @POST(Constants.NETPATH.CHECKLETTER) Call<ReviewOfferSubmitLetterOnSaleListRes> checkLetterRet(@Body RequestBody requestBody);
     */
    public Call<CheckLetterRes> checkLetterRet(MessageListReq data) {
        Call<CheckLetterRes> checkLetterRet = mApi.checkLetterRet(createRequestBody(data));
        return checkLetterRet;
    }

    /**
     * //福费廷资产再次发布接口
     *
     * @POST(Constants.NETPATH.FORFAITINGREPORT) Call<CheckLetterRes> forfaitingReportRet(@Body RequestBody requestBody);
     */
    public Call<CheckLetterRes> forfaitingReportRet(ForfaitingRportReq data) {
        Call<CheckLetterRes> forfaitingReportRet = mApi.forfaitingReportRet(createRequestBody(data));
        return forfaitingReportRet;
    }


    /**
     * //福费廷偏好查询详情接口 复用ForfaitingRportReq id
     *
     * @POST(Constants.NETPATH.FORFAITINGPREFERENCEDETAIL) Call<ForfaitingPreferenceDetail> forfaitingPrefenceDetailRet(@Body RequestBody requestBody);
     */
    public Call<ForfaitingPreferenceDetail> forfaitingPrefenceDetailRet(ForfaitingRportReq data) {
        Call<ForfaitingPreferenceDetail> forfaitingPrefenceDetailRet = mApi.forfaitingPrefenceDetailRet(createRequestBody(data));
        return forfaitingPrefenceDetailRet;
    }


    /**
     * //保理偏好查询详情接口 复用ForfaitingRportReq id
     *
     * @POST(Constants.NETPATH.FACTORINGPREFERENCEDETAIL) Call<FactoringPreferenceDetail> factoringPrefenceDetailRet(@Body RequestBody requestBody);
     */
    public Call<FactoringPreferenceDetail> factoringPrefenceDetailRet(ForfaitingRportReq data) {
        Call<FactoringPreferenceDetail> factoringPrefenceDetailRet = mApi.factoringPrefenceDetailRet(createRequestBody(data));
        return factoringPrefenceDetailRet;
    }


    /**
     * //福费廷偏好更新接口
     *
     * @POST(Constants.NETPATH.FORFAITINGPREFERENCEUPDATE) Call<CheckLetterRes> forfaitingPrefenceUpdateRet(@Body RequestBody requestBody);
     */
    public Call<CheckLetterRes> forfaitingPrefenceUpdateRet(IussingForfaitingEntryReq data) {
        Call<CheckLetterRes> forfaitingPrefenceUpdateRet = mApi.forfaitingPrefenceUpdateRet(createRequestBody(data));
        return forfaitingPrefenceUpdateRet;
    }

    /**
     * //保理偏好更新接口
     *
     * @POST(Constants.NETPATH.FACTORINGPREFERENCEUPDATE) Call<CheckLetterRes> factoringPrefenceUpdateRet(@Body RequestBody requestBody);
     */
    public Call<CheckLetterRes> factoringPrefenceUpdateRet(IussingFactoringEntryReq data) {
        Call<CheckLetterRes> factoringPrefenceUpdateRet = mApi.factoringPrefenceUpdateRet(createRequestBody(data));
        return factoringPrefenceUpdateRet;
    }

    /**
     * //下载邀约函接口
     *
     * @POST(Constants.NETPATH.DOWNLOADCHECKLETTER) Call<CheckLetterRes> downCheckLetterRet(@Body RequestBody requestBody);
     */
    public Call<DownCheckLetterRes> downCheckLetterRet(IussingFactoringEntryReq data) {
        Call<DownCheckLetterRes> downCheckLetterRet = mApi.downCheckLetterRet(createRequestBody(data));
        return downCheckLetterRet;
    }

    /**
     * //检查权限接口
     *
     * @POST(Constants.NETPATH.CHECKCONTROR) Call<DownCheckLetterRes> checkControlRet(@Body RequestBody requestBody);
     */
    public Call<CheckControlRes> checkControlRet(IussingFactoringEntryReq data) {
        Call<CheckControlRes> checkControlRet = mApi.checkControlRet(createRequestBody(data));
        return checkControlRet;
    }

    /**
     * //根据报价查询报价流水信息（前五条）接口  复用ForfaitingRportReq id
     *
     * @POST(Constants.NETPATH.MYQUOTEPRISELIST) Call<CheckControlRes> myQuotePriseListRet(@Body RequestBody requestBody);
     */
    public Call<MyQuotePriseListRes> myQuotePriseListRet(ForfaitingRportReq data) {
        Call<MyQuotePriseListRes> myQuotePriseListRet = mApi.myQuotePriseListRet(createRequestBody(data));
        return myQuotePriseListRet;
    }

    /**
     * 查询让渡函接口
     *
     * @POST(Constants.NETPATH.CHECKTRANSFERLETTER) Call<CheckTransferLetterRes> checkTransferLetterRet(@Body RequestBody requestBody);
     */
    public Call<CheckTransferLetterRes> checkTransferLetterRet(ReviewCheckTransferReq data) {
        Call<CheckTransferLetterRes> checkTransferLetterRet = mApi.checkTransferLetterRet(createRequestBody(data));
        return checkTransferLetterRet;
    }

    /**
     * 复核人员查询已失效资产接口
     *
     * @POST(Constants.NETPATH.CHECKEXPIREDASSETS) Call<CheckTransferLetterRes> checkExpiredAssetsRet(@Body RequestBody requestBody);
     */
    public Call<CheckTransferLetterRes> checkExpiredAssetsRet(ReviewCheckTransferReq data) {
        Call<CheckTransferLetterRes> checkExpiredAssetsRet = mApi.checkExpiredAssetsRet(createRequestBody(data));
        return checkExpiredAssetsRet;
    }

    /**
     * 重新发布已失效资产福费廷信息接口
     *@POST(Constants.NETPATH.UPDATEEXPIREDASSETSFORFAITING)
     *     Call<CheckTransferLetterRes> updateExpiredAssetsForfaitingRet(@Body RequestBody requestBody);
     */
    public Call<CheckTransferLetterRes> updateExpiredAssetsForfaitingRet(RepublishExpiredAssetsReq data) {
        Call<CheckTransferLetterRes> updateExpiredAssetsForfaitingRet = mApi.updateExpiredAssetsForfaitingRet(createRequestBody(data));
        return updateExpiredAssetsForfaitingRet;
    }

    /**
     * 重新发布已失效资产保理信息接口
     *@POST(Constants.NETPATH.UPDATEEXPIREDASSETSFACTORING)
     *     Call<CheckTransferLetterRes> updateExpiredAssetsFactoringRet(@Body RequestBody requestBody);
     */
    public Call<CheckTransferLetterRes> updateExpiredAssetsFactoringRet(RepublishExpiredAssetsReq data) {
        Call<CheckTransferLetterRes> updateExpiredAssetsFactoringRet = mApi.updateExpiredAssetsFactoringRet(createRequestBody(data));
        return updateExpiredAssetsFactoringRet;
    }


    /**
     * app更新接口
     *
     * @POST(Constants.NETPATH.APPUPDATE) Call<CheckUpdateAppRes> checkAppUpdateRet(@Body RequestBody requestBody);
     */
    public Call<CheckUpdateAppRes> checkAppUpdateRet(CheckUpdateAppReq data) {
        Call<CheckUpdateAppRes> checkAppUpdateRet = mApi.checkAppUpdateRet(createRequestBody(data));
        return checkAppUpdateRet;
    }

    /**
     * 上传文件
     *
     * @param data
     * @return
     */
    public Call<UploadFileRes> uploadFileRet(UploadFileReq data, File file) {
        Call<UploadFileRes> uploadFileRet = mApi.uploadFileRet(Constants.API_ROOT1 + "" + Constants.NETPATH.UPLOADFILE, RequestBody.create(MediaType.parse("multipart/form-data"), "11"), RequestBody.create(MediaType.parse("multipart/form-data"), "11"), createMultipartBody(file));
        return uploadFileRet;
    }

    /**
     * 上传文件
     *
     * @param data
     * @return
     */
    public Call<UploadFileRes> uploadFileParamsRet(UploadFileReq data, File file) {
        Call<UploadFileRes> uploadFileParamsRet = mApi.uploadFileParamsRet(Constants.API_ROOT1 + "" + Constants.NETPATH.UPLOADFILE, createDesBody(), createMultipartBody(file));
        return uploadFileParamsRet;
    }

    /**
     * 上传多文件
     *
     * @param data
     * @return
     */
    public Call<UploadFileRes> uploadMoreFileParamsRet(UploadFileReq data, List<File> file) {
        Call<UploadFileRes> uploadFileParamsRet = mApi.uploadMoreFileParamsRet(Constants.API_ROOT1 + "" + Constants.NETPATH.UPLOADMOREFILE, createDesBody(), createMultipartBody(file));
        return uploadFileParamsRet;
    }

    /**
     * 上传文件OCR
     *
     * @param data
     * @return
     */
    public Call<UploadFileRes> uploadOCRFileParamsRet(UploadFileReq data, File file) {
        Call<UploadFileRes> uploadOCRFileParamsRet = mApi.uploadOCRFileParamsRet(Constants.API_ROOT1 + "" + Constants.NETPATH.OCRUPLOADFILE, createDesBody(), createMultipartBody(file));
        return uploadOCRFileParamsRet;
    }

    /**
     * 下载文件
     *
     * @param Url
     * @return
     * @GET Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);
     */
    public Call<ResponseBody> downloadFileWithDynamicUrlSync(String Url) {
        Call<ResponseBody> downloadFileWithDynamicUrlSync = mApi.downloadFileWithDynamicUrlSync(Url);
        return downloadFileWithDynamicUrlSync;
    }


    /**
     * // 下载用户手册
     *
     * @POST(Constants.NETPATH.USERMANUAL) Call<ResponseBody> userManualRet(@Body RequestBody requestBody);
     */
    public Call<ResponseBody> userManualRet(UserManualReq data) {
        Call<ResponseBody> userManualRet = mApi.userManualRet(createRequestBody(data));
        return userManualRet;
    }
}
