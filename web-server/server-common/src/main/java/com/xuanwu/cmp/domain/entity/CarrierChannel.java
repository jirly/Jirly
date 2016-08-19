package com.xuanwu.cmp.domain.entity;

import com.xuanwu.cmp.domain.AbstractEntity;
import com.xuanwu.cmp.utils.XmlUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * CarrierChannel——通道基本信息实体类
 * @author <a href="jiangziyuan@wxchina.com">ZiYuan.Jiang</a>
 * @date 2016-8-11
 * @version 1.0.0
 */
public class CarrierChannel extends AbstractEntity {
        private int id;//
        private int regionCarrierId;// 所属地区运营商
        private RegionCarrier regionCarrier;
        private String carrier = "";// 所属运营商
        private int carrierId;//所属运营商Id
        private String region = "";// 所属地区
        private String hostName = "";// 运营商IP地址
        private int port;// 运营商IP端口
        private boolean isSignal;// 短信是否包含签名
        private String identity = "";// 通道识别号
        private String signature = "";// 运营商企业签名
        private String msgType = "";// 可发送信息类型
        private boolean mms;// 是否支持彩信
        private boolean sms;// 是否支持短信
        private boolean longSms;// 是否支持长短信
        private boolean wappush;// 是否支持WAP PUSH
        private boolean voiceCode;
        private boolean stateReport;// 是否支持状态报告
        private boolean massCommit;// 是否支持批量提交
        private String protoVersion = "";// 通信协议版本号
        private int type;// 通道类型(0:物理通道,1:虚拟通道.虚拟通道与物理通道的绑定关系是一对一的，即一个物理通道与一个虚拟通道只能绑定一次)
        private int maxLength;// 单个短信的最大字节长度
        private int mmsMaxLength;// 单个彩信的最大字节长度,以KB为单位
        private int extendLength;// 短信切分时所需要的字节空间
        private String mmsSupportType = "";// 通道支持的文件类型，也就是扩展名，以英文“|”进行分割，如jpg|gif|midi|bmp|amr|txt
        private String account = "";// 账号
        private String password = "";// 密码
        private String name = "";// 通道名
        private String corpId = "";// 运营商分配给 SP的企业代码
        private boolean isEraseSignal;// 是否禁用通道签名，即以前的是否可以抹去签名
        private boolean mo;// 是否支持上行
        private int moWaitTime;// 上行分段等待处理时间上限
        private Date sendStartTime;// 通道的可发送起始时间
        private Date sendEndTime;// 通道的可发送中止时间
        private String channelNum = "";// 通道号
        private boolean isWhiteChannel;// 是否支持白名单通道
        private boolean isExtend;// 是否可扩展
        private int extendNumLength;// 通道的可扩展长度
        private String remark = "";// 备注
        private String parameters = "";// 参数,xml格式
        private int state;// 通道状态(1:启用, 0:停用)
        private boolean isLongSignal;// 长短信是否包含签名
        private boolean isRemove;// 标识是否删除
        private String channelShortNum = "";// 通道短号:若短号为空,发送给运营商要带上“通道号”参数;短号不为空,则发送给运营商只需带上“短号”参数
        private String corpCode = "";// 集团代码(只允许输入数字跟英文字符 )只有通道类型是MAS,方可编辑
        private String cropName = "";// 集团名称,通道是ADC类型方可编辑
        private String cropProductNum = "";// 集团产品号码,通道是ADC类型方可编辑
        private String bussinessCode = "";// 业务代码
        private String bussiness_code;//业务编码	,通道是ADC类型方可编辑
        private String bussinessName = "";// 业务名称,通道是ADC类型方可编辑
        private int channelType;// MAS:0, ADC:1, DIY:2, ADC子通道:3, 其它:4
        private String channelTypeName;
        private Integer parentId;// 父通道(ADC子通道的时候才会有数据)
        private boolean auditingFlag;// 是否需要彩信审核(0:不审核,1:审核)
        private List<Carrier> canSendCarrier;// 可发送运营商
        private String canSendCarriers = "";
        private List<Region> noRegion;// 区域限制发送
        private String noRegions = "";
        private String sendSpeed = "";// 发送速度
        private String massComitLength = "";// 批量提交总数
        private String paras = "";// 参数
        private String mmsSupportTypeSelected = "";// 已选择的类型
        private String canSendCarrierSelected = "";// 已选择的可发送运营商
        private String noRegionSelected = "";// 已选择的不可发送区域
        private double price;//单价
        private String number;//号码
        private int channelId;//通道ID
        private int provinceId;//所属省Id
        private int cityId;//所属市ID
        private String cancarrierIds;
        private String resourceType; //资源类型

        private Date createTime;// 创建时间

        private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public String getResourceType() {
            return resourceType;
        }

        public void setResourceType(String resourceType) {
            this.resourceType = resourceType;
        }

        public String getBussiness_code() {
            return bussiness_code;
        }

        public void setBussiness_code(String bussiness_code) {
            this.bussiness_code = bussiness_code;
        }


        public static final String UndefineChargeType = "未定义计费类型";

        public static final String CHARGE_TYPE_NAME = "charge_type";
        public static final String  NEED_USER_SIGN_NAME = "IsNeedUserSign";

/*        public String getChargeTypeDescript() {
            ChargeType chargetype = ChargeType.getChargeType(getChargeType());
            if (null == chargetype) {
                return UndefineChargeType;
            }
            return chargetype.getDescript();
        }*/

public enum ChargeType {
    NoCharge(1, "不计费"), AcceptCharge(2, "接收方计费");
    private int type;
    private String descript;

    public String getDescript() {
        return descript;
    }

    public int getType() {
        return type;
    }

    public static ChargeType getChargeType(int type) {
        switch (type) {
            case 1:
                return NoCharge;
            case 2:
                return AcceptCharge;
            default:
                return null;
        }
    }

    ChargeType(int type, String descript) {
        this.type = type;
        this.descript = descript;
    }
}

public enum ChannelType {
    MAS(0),ADC(1),DIY(2),ADC_SUB(3),OTHER(4);
    private int type;

    public int getType() {
        return type;
    }

    public static ChannelType getChannelType(int type) {
        switch (type) {
            case 0:
                return MAS;
            case 1:
                return ADC;
            case 2:
                return DIY;
            case 3:
                return ADC_SUB;
            case 4:
                return OTHER;
            default:
                return null;
        }
    }

    ChannelType(int type) {
        this.type = type;
    }
}

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }


    public int getRegionCarrierId() {
        return regionCarrierId;
    }

    public void setRegionCarrierId(int regionCarrierId) {
        this.regionCarrierId = regionCarrierId;
    }

    /**
     * @return carrier
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * @param carrier
     *            要设置的 carrier
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
    public int getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(int carrierId) {
        this.carrierId = carrierId;
    }
    /**
     * @return region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region
     *            要设置的 region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return regionCarrier
     */
    public RegionCarrier getRegionCarrier() {
        return regionCarrier;
    }

    /**
     * @param regionCarrier
     *            要设置的 regionCarrier
     */
    public void setRegionCarrier(RegionCarrier regionCarrier) {
        this.regionCarrier = regionCarrier;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isSignal() {
        return isSignal;
    }

    public boolean isIsSignal() {
        return isSignal;
    }

    public void setIsSignal(boolean isSignal) {
        this.isSignal = isSignal;
    }

    /**
     * @return msgType
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * @param msgType
     *            要设置的 msgType
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public boolean isMms() {
        return mms;
    }

    public void setMms(boolean mms) {
        this.mms = mms;
    }

    public boolean isSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }

    public boolean isLongSms() {
        return longSms;
    }

    public void setLongSms(boolean longSms) {
        this.longSms = longSms;
    }

    public boolean isWappush() {
        return wappush;
    }

    public void setWappush(boolean wappush) {
        this.wappush = wappush;
    }

    public boolean isVoiceCode() {
        return voiceCode;
    }

    public void setVoiceCode(boolean voiceCode) {
        this.voiceCode = voiceCode;
    }

    public boolean isStateReport() {
        return stateReport;
    }

    public void setStateReport(boolean stateReport) {
        this.stateReport = stateReport;
    }

    public boolean isMassCommit() {
        return massCommit;
    }

    public void setMassCommit(boolean massCommit) {
        this.massCommit = massCommit;
    }

    public String getProtoVersion() {
        return protoVersion;
    }

    public void setProtoVersion(String protoVersion) {
        this.protoVersion = protoVersion;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMmsMaxLength() {
        return mmsMaxLength;
    }

    public void setMmsMaxLength(int mmsMaxLength) {
        this.mmsMaxLength = mmsMaxLength;
    }

    public int getExtendLength() {
        return extendLength;
    }

    public void setExtendLength(int extendLength) {
        this.extendLength = extendLength;
    }

    public String getMmsSupportType() {
        return mmsSupportType;
    }

    public void setMmsSupportType(String mmsSupportType) {
        this.mmsSupportType = mmsSupportType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public boolean isEraseSignal() {
        return isEraseSignal;
    }

    public boolean isIsEraseSignal() {
        return isEraseSignal;
    }

    public void setIsEraseSignal(boolean isEraseSignal) {
        this.isEraseSignal = isEraseSignal;
    }

    public boolean isMo() {
        return mo;
    }

    public void setMo(boolean mo) {
        this.mo = mo;
    }

    public int getMoWaitTime() {
        return moWaitTime;
    }

    public void setMoWaitTime(int moWaitTime) {
        this.moWaitTime = moWaitTime;
    }

    public Date getSendStartTime() {
        return sendStartTime;
    }

    public void setSendStartTime(Date sendStartTime) {
        this.sendStartTime = sendStartTime;
    }

    public Date getSendEndTime() {
        return sendEndTime;
    }

    public void setSendEndTime(Date sendEndTime) {
        this.sendEndTime = sendEndTime;
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }

    public boolean isWhiteChannel() {
        return isWhiteChannel;
    }

    public boolean isIsWhiteChannel() {
        return isWhiteChannel;
    }

    public void setIsWhiteChannel(boolean isWhiteChannel) {
        this.isWhiteChannel = isWhiteChannel;
    }

    public boolean isExtend() {
        return isExtend;
    }

    public boolean isIsExtend() {
        return isExtend;
    }

    public void setExtend(boolean isExtend) {
        this.isExtend = isExtend;
    }

    public int getExtendNumLength() {
        return extendNumLength;
    }

    public void setExtendNumLength(int extendNumLength) {
        this.extendNumLength = extendNumLength;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isLongSignal() {
        return isLongSignal;
    }

    public boolean isIsLongSignal() {
        return isLongSignal;
    }

    public void setIsLongSignal(boolean isLongSignal) {
        this.isLongSignal = isLongSignal;
    }

    public boolean isRemove() {
        return isRemove;
    }

    public boolean isIsRemove() {
        return isRemove;
    }

    public void setRemove(boolean isRemove) {
        this.isRemove = isRemove;
    }

    public String getChannelShortNum() {
        return channelShortNum;
    }

    public void setChannelShortNum(String channelShortNum) {
        this.channelShortNum = channelShortNum;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropProductNum() {
        return cropProductNum;
    }

    public void setCropProductNum(String cropProductNum) {
        this.cropProductNum = cropProductNum;
    }

    public String getBussinessCode() {
        return bussinessCode;
    }

    public void setBussinessCode(String bussinessCode) {
        this.bussinessCode = bussinessCode;
    }

    public String getBussinessName() {
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        switch(channelType){
            case 0:channelTypeName= "MAS";break;
            case 1:channelTypeName= "ADC主通道";break;
            case 2:channelTypeName= "DIY";break;
            case 3:channelTypeName= "ADC子通道";break;
            case 4:channelTypeName= "其它";break;
            default:channelTypeName="";
        }
        this.channelType = channelType;
    }

    public String getChannelTypeName() {
        return channelTypeName;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean isAuditingFlag() {
        return auditingFlag;
    }

    public void setAuditingFlag(boolean auditingFlag) {
        this.auditingFlag = auditingFlag;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    /**
     * @return canSendCarrier
     */
    public List<Carrier> getCanSendCarrier() {
        return canSendCarrier;
    }

    /**
     * @param canSendCarrier
     *            要设置的 canSendCarrier
     */
    public void setCanSendCarrier(List<Carrier> canSendCarrier) {
        this.canSendCarrier = canSendCarrier;
    }

    /**
     * @param canSendCarriers
     *            要设置的 canSendCarriers
     */
    public void setCanSendCarriers(String canSendCarriers) {
        this.canSendCarriers = canSendCarriers;
    }

    /**
     * @return canSendCarriers
     */
    public String getCanSendCarriers() {
        return canSendCarriers;
    }

    /**
     * @param noRegion
     *            要设置的 noRegion
     */
    public void setNoRegion(List<Region> noRegion) {
        this.noRegion = noRegion;
    }

    /**
     * @return noRegion
     */
    public List<Region> getNoRegion() {
        return noRegion;
    }

    /**
     * @return noRegions
     */
    public String getNoRegions() {
        return noRegions;
    }

    /**
     * @param noRegions
     *            要设置的 noRegions
     */
    public void setNoRegions(String noRegions) {
        this.noRegions = noRegions;
    }

    /**
     * @return sendSpeed
     */
    public String getSendSpeed() {
        return sendSpeed;
    }

    /**
     * @param sendSpeed
     *            要设置的 sendSpeed
     */
    public void setSendSpeed(String sendSpeed) {
        this.sendSpeed = sendSpeed;
    }

    /**
     * @param massComitLength
     *            要设置的 massComitLength
     */
    public void setMassComitLength(String massComitLength) {
        this.massComitLength = massComitLength;
    }

    /**
     * @return massComitLength
     */
    public String getMassComitLength() {
        return massComitLength;
    }

    /**
     * @param paras
     *            要设置的 paras
     */
    public void setParas(String paras) {
        this.paras = paras;
    }

    /**
     * @return paras
     */
    public String getParas() {
        return paras;
    }

    /**
     * @param mmsSupportTypeSelected
     *            要设置的 mmsSupportTypeSelected
     */
    public void setMmsSupportTypeSelected(String mmsSupportTypeSelected) {
        this.mmsSupportTypeSelected = mmsSupportTypeSelected;
    }

    /**
     * @return mmsSupportTypeSelected
     */
    public String getMmsSupportTypeSelected() {
        return mmsSupportTypeSelected;
    }

    /**
     * @param canSendCarrierSelected
     *            要设置的 canSendCarrierSelected
     */
    public void setCanSendCarrierSelected(String canSendCarrierSelected) {
        this.canSendCarrierSelected = canSendCarrierSelected;
    }

    /**
     * @return canSendCarrierSelected
     */
    public String getCanSendCarrierSelected() {
        return canSendCarrierSelected;
    }

    /**
     * @param noRegionSelected
     *            要设置的 noRegionSelected
     */
    public void setNoRegionSelected(String noRegionSelected) {
        this.noRegionSelected = noRegionSelected;
    }

    /**
     * @return noRegionSelected
     */
    public String getNoRegionSelected() {
        return noRegionSelected;
    }

    /**
     * @return chargeType
     */
/*    public int getChargeType() {
        Map<String, Object> parars = (Map<String, Object>) XmlUtil
                .fromXML(this.parameters);
        int chargetype = null != parars.get(CHARGE_TYPE_NAME) ? (Integer) parars
                .get(CHARGE_TYPE_NAME) : 0;
        return chargetype;
    }*/

    public String getCancarrierIds() {
        return cancarrierIds;
    }

    public void setCancarrierIds(String cancarrierIds) {
        this.cancarrierIds = cancarrierIds;
    }

    public String toJSONSimple() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"id\":").append(id);
        sb.append(",\"name\":\"").append(name).append("\"");
        sb.append("}");
        return sb.toString();
    }

}
