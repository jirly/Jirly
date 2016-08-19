define(["app"], function(app) {
    var injectParams = ['$scope', '$location', 'repoService', 'utilService'];
    var infoAddCtrl = function($scope, $location, repo, util) {
        var channelAddConf = {
            url: 'channelmgmt/info/add', //基本URL，必填
            showName: '新增' //提示的名称，一般为模块名，必填
        };

        $scope.app = { enablers: [] };

        /** 检索栏参数*/
        $scope.channelAddParams = {};

        /** 初始化公共下拉框  begin*/
        $scope.whether = [
            {"id" : 0 , "name" : "是"},
            {"id" : 1 , "name" : "否"}
        ];
        /** 初始化公共下拉框  end*/

        /** “所属地区”下拉框联动功能  begin*/
        repo.getByUrl("channelmgmt/info/regions").then(function(data) {
            $scope.provices = data.data;
            $scope.channelAddParams.provice = data.data[0].id;
            findCity();
        });
        ;
        $scope.findCityByProvinceId = function () {
            findCity();
        }

        function findCity() {
            var provinceId = $scope.channelAddParams.provice;
            repo.getByUrl("channelmgmt/info/regions/initCity/"+provinceId).then(function(data) {
                $scope.citys = data.data;
                $scope.channelAddParams.city = data.data[0].id;
            });
        }
        /** “所属地区”下拉框联动功能  end*/

        /** “通道类型”下拉框联动  begin*/
        //初始化“通道类型”下拉框 以及 “ADC主通道”和“ADC子通道”的div隐藏/显示属性——isShowADCMain、isShowADCSub
        $scope.channelTypes = [
            {"id" : 0 , "name" : "MAS"},
            {"id" : 1 , "name" : "ADC主通道"},
            {"id" : 2 , "name" : "DIY"},
            {"id" : 3 , "name" : "ADC子通道"},
            {"id" : 4 , "name" : "其它"}
        ];
        $scope.channelAddParams.channelType = $scope.channelTypes[0].id;

        $scope.isShowADCMain = false;
        $scope.isShowADCSub = false;
        $scope.showADC = function () {
            var channelType = $scope.channelAddParams.channelType;
            if(channelType == 1){
                $scope.isShowADCMain = true;
                $scope.isShowADCSub = false;
            }else if(channelType == 3){
                $scope.isShowADCMain = true;
                $scope.isShowADCSub = true;
            }else{
                $scope.isShowADCMain = false;
                $scope.isShowADCSub = false;
            }
        }
        /** “通道类型”下拉框联动  end*/

        /** “所属运营商”下拉框初始化  begin*/
        $scope.carriers = [
            {"id" : 1 , "name" : "移动"},
            {"id" : 2 , "name" : "联通"},
            {"id" : 3 , "name" : "电信小灵通"},
            {"id" : 4 , "name" : "电信CDMA"}
        ];
        $scope.channelAddParams.carrier = $scope.carriers[0].id;
        /** “所属运营商”下拉框初始化  end*/

        /** “是否白名单通道”下拉框初始化  begin*/
        $scope.channelAddParams.isWhiteChannel = $scope.whether[1].id;
        /** “是否白名单通道”下拉框初始化  end*/

        /** “可发送信息类型”初始化  begin*/
        $scope.channelAddParams.sms = false;
        $scope.channelAddParams.longSms = false;
        $scope.channelAddParams.mms = false;
        $scope.isSMSSelected = false;
        $scope.isDisabledMmsMaxLength = true;//“可发送信息类型”只要没有选择“彩信”，都是不能被编辑
        $scope.isDisEditSignature = true;//“通道签名”只要没有选择“彩信”才能被编辑
        $scope.isDisabledIsSignal = true;//“通道签名——普通短信”只要没有选择“彩信”才能被编辑
        $scope.isDisabledIsLongSignal = true;//“通道签名——长短信”只要没有选择“彩信”才能被编辑
        $scope.selectSMS = function () {
            if ($scope.channelAddParams.sms == true) {
                $scope.channelAddParams.mmsMaxLength = '';
                $scope.dpMmsSupportTypeData =  [];//“彩信通道支持格式”只有选择“彩信”时才能加载下拉框数据
                $scope.isDisEditSignature = false;//“通道签名”只要没有选择“彩信”才能被编辑
                $scope.isDisabledMmsMaxLength = true;
                if ($scope.channelAddParams.mms == true) {
                    $scope.channelAddParams.mms = false;
                }
                if ($scope.channelAddParams.longSms == false){
                    $scope.isDisEditSignature = false;
                }
            } else if ($scope.channelAddParams.sms == false){
                $scope.isDisEditSignature = true;
                $scope.channelAddParams.signature = '';//当“可发送信息类型”的“普通短信”没有被选择时，“通道签名”要清空；
                $scope.checkedIsSignal = false;//通道签名——普通短信
                $scope.checkedIsLongSignal = false;//通道签名——长短信
            }
        }
        $scope.selectLongSMS = function () {
            if ($scope.channelAddParams.longSms == true){
                $scope.isDisEditSignature = false;//“通道签名”只要没有选择“彩信”才能被编辑
                $scope.dpMmsSupportTypeData =  [];
                $scope.isDisabledMmsMaxLength = true;
                $scope.isSMSSelected = true;
                $scope.isDisabledSMS = true;
                $scope.checkedIsSignal = true;//通道签名——普通短信
                $scope.checkedIsLongSignal = true;//通道签名——长短信
                $scope.channelAddParams.mmsMaxLength = '';
                if ($scope.channelAddParams.mms == true){
                    $scope.channelAddParams.mms = false;
                }
                if ($scope.channelAddParams.signature.length == 0){
                    $scope.checkedIsSignal = false;//通道签名——普通短信
                    $scope.checkedIsLongSignal = false;//通道签名——长短信
                }
            } else if ($scope.channelAddParams.longSms == false) {
                $scope.isSMSSelected = false;
                $scope.channelAddParams.sms = false;
                $scope.isDisabledSMS = false;
                $scope.channelAddParams.mms = false;
                $scope.isDisEditSignature = true;
                $scope.channelAddParams.signature = '';//当“可发送信息类型”的“长短信”没有被选择时，“通道签名”要清空；
                $scope.checkedIsSignal = false;//通道签名——普通短信
                $scope.checkedIsLongSignal = false;//通道签名——长短信
            }
        }
        $scope.selectMMS = function () {
            if ($scope.channelAddParams.mms == true) {
                $scope.isDisEditSignature = true;//“通道签名”只要没有选择“彩信”才能被编辑
                $scope.channelAddParams.signature = '';//当“可发送信息类型”的“彩信”被选择时，“通道签名”要清空；
                $scope.isDisabledMmsMaxLength = false;
                $scope.isDisabledSMS = false;
                $scope.isSMSSelected = false;
                $scope.channelAddParams.sms = false;
                $scope.channelAddParams.longSms = false;
                $scope.checkedIsSignal = false;//通道签名——普通短信
                $scope.checkedIsLongSignal = false;//通道签名——长短信
                $scope.channelAddParams.mmsMaxLength = '';
                initDpMmsSupportType();
            } else if ($scope.channelAddParams.mms == false ){
                $scope.dpMmsSupportTypeData =  [];
                $scope.isDisabledMmsMaxLength = true;
                $scope.isDisEditSignature = true;
            }
        }
        function initDpMmsSupportType() {
            $scope.dpMmsSupportTypeData = [
                {id: 'jpg', label: "jpg"},
                {id: 'jpeg', label: "jpeg"},
                {id: 'gif', label: "gif"},
                {id: 'bmp', label: "bmp"},
                {id: 'amr', label: "amr"},
                {id: 'mid', label: "mid"}];
        }
        /** “可发送信息类型”初始化  end*/

        /** “通道签名”初始化  begin*/
        $scope.channelAddParams.signature = '';
        $scope.editSignature = function () {
            $scope.checkedIsSignal = false;//通道签名——普通短信
            $scope.checkedIsLongSignal = false;//通道签名——长短信
            var sms = $scope.isSMSSelected;
            var longSms = $scope.channelAddParams.longSms;
            if ($scope.channelAddParams.signature.length > 0){
                $scope.checkedIsSignal = true;
                if (sms == true && longSms == true ){
                    $scope.checkedIsLongSignal = true;
                } else if (sms == true && longSms == false){
                    $scope.checkedIsLongSignal = false;
                }
            } else if ($scope.channelAddParams.signature.length == 0){
                $scope.checkedIsSignal = false;
                $scope.checkedIsLongSignal = false;
            }
        }
        /** “通道签名”初始化  end*/
        
        /** “彩信通道支持格式”初始化  begin*/
        $scope.channelAddParams.dpMmsSupportType = [];
        $scope.dpMmsSupportTypeData = [];
        $scope.dpMmsSupportTypeSettings = {
            externalIdProp: '',
            selectionLimit: 5,
            smartButtonMaxItems: 3,
            smartButtonTextConverter: function(itemText) {
                return itemText;
            }
        };
        $scope.dpMmsSupportTypeTexts = {buttonDefaultText:'请选择',uncheckAll:'取消选择'};
        /** “彩信通道支持格式”初始化  end*/

        /** “是否支持状态报告”下拉框初始化  begin*/
        $scope.channelAddParams.stateReport = $scope.whether[0].id;
        /** “是否支持状态报告”下拉框初始化  end*/

        /** “是否支持上行”下拉框初始化  begin*/
        $scope.channelAddParams.mo = $scope.whether[0].id;
        /** “是否支持上行”下拉框初始化  end*/

        /** “通道协议”下拉框初始化  begin*/
        $scope.protoVersions = [
            {"id" : 0 , "name" : "CMPP2.0（移动）"},
            {"id" : 1 , "name" : "SGIP1.2（联通）"},
            {"id" : 2 , "name" : "SMGP3.0（电信）"},
            {"id" : 3 , "name" : "CMPP3.0（移动）"},
            {"id" : 4 , "name" : "其他协议"}
        ];
        $scope.channelAddParams.protoVersion = $scope.protoVersions[0].id;
        /** “通道协议”下拉框初始化  end*/

        /** “是否支持群发”下拉框初始化  begin*/
        $scope.channelAddParams.massCommit = $scope.whether[1].id;
        $scope.isEditMass = true;
        $scope.allowEditMass = function () {
            var type = $scope.channelAddParams.massCommit;
            if(type == 0){
                $scope.isEditMass = false;
            }
        }
        /** “是否支持群发”下拉框初始化  end*/

        /** “可发送运营商”下拉多选框  begin*/
        $scope.channelAddParams.dpcanSendCarrier = [];
        $scope.dpcanSendCarrierData = [
            {id: 'mobileCarrier', label: "移动"},
            {id: 'unicomCarrier', label: "联通"},
            {id: 'telecomXLTCarrier', label: "电信小灵通"},
            {id: 'telecomCDMACarrier', label: "电信CDMA"}];
        $scope.dpcanSendCarrierSettings = {
            externalIdProp: '',
            selectionLimit: 4,
            smartButtonMaxItems: 3,
            smartButtonTextConverter: function(itemText) {
                return itemText;
            }
        };
        $scope.dpcanSendCarrierTexts = {buttonDefaultText:'请选择',uncheckAll:'取消选择'};
        /** “可发送运营商”下拉多选框  end*/

        /** “禁止发送区域”下拉多选框  begin*/
        $scope.channelAddParams.denoregion = [];
        repo.getByUrl("channelmgmt/info/regions").then(function(data) {
            $scope.denoregionData = data.data;
        });
        $scope.denoregionSettings = {
            externalIdProp: '',
            selectionLimit: 10,
            smartButtonMaxItems: 3,
            smartButtonTextConverter: function(itemText) {
                return itemText;
            }
        };
        $scope.denoregionTexts = {buttonDefaultText:'请选择',uncheckAll:'取消选择'};
        /** “禁止发送区域”下拉多选框  end*/

        /** “参数-键”、“参数-值”下拉框初始化  begin*/
        $scope.channelParameters = [
            {"id" : 0 , "name" : "计费用户类型"},
            {"id" : 1 , "name" : "被计费用户的号码"},
            {"id" : 2 , "name" : "资费类型"},
            {"id" : 3 , "name" : "资费代码"}
        ];
        $scope.channelAddParams.channelParameter = $scope.channelParameters[0].id;
        $scope.channelAddParams.txtParameters = "";
        $scope.cleanTxtParameters = function () {
            $scope.channelAddParams.txtParameters = "";
        }
        $scope.channelAddParams.content = "";
        /** “参数-键”、“参数-值”下拉框初始化  end*/

        /** “添加参数”按钮 begin*/
        $scope.lxbParamList = [];
        $scope.paramAdd = function () {
            var idx = $scope.channelAddParams.channelParameter;
            var paramName = $scope.channelParameters[idx].name;
            var paramValue = $scope.channelAddParams.txtParameters;
            var tempContent = paramName + ":" + paramValue;
            var contentList = $scope.lxbParamList;
            var isRepeatParam = checkContent(paramName,contentList);
            if(isRepeatParam == false){
                $scope.lxbParamList.push({lxbParamList:tempContent, done:false});
            }else if(isRepeatParam == true){
                alert("参数[" + paramName + "]已经添加，请添加另外的参数。");
            }
        }

        function checkContent(paramName,contentList) {
            //检查“添加参数”是否已被添加过
            var flag = false;
            for(i = 0; i < contentList.length; i++ ){
                var tempParam = contentList[i].lxbParamList.split(":")[0];
                if(tempParam == paramName){
                    return true;
                }
            }
            return flag;
        }
        /** “添加参数”按钮 end*/

        /** “删除参数”按钮 begin*/
        $scope.paramDel = function () {
            var oldList = $scope.lxbParamList;
            $scope.lxbParamList = [];
            angular.forEach(oldList, function(x) {
                if (!x.done) $scope.lxbParamList.push(x);
            });
        }
        /** “删除参数”按钮 end*/

        $scope.updateSelection = function($event, typeIdx) {
            var checkbox = $event.target;
            var checked = checkbox.checked;

            var idx = $scope.app.enablers.indexOf(typeIdx);
            if (checked && idx == -1) { //选中
                $scope.app.enablers.push(typeIdx);
            }
            if (!checked && idx != -1) { //取消选中
                $scope.app.enablers.splice(idx, 1);
            }
            $scope.app.enablers.sort(function(a, b) {
                return a - b;
            });
        }

        $scope.isSelected = function(typeIdx) {
            return $scope.app.enablers.indexOf(typeIdx) >= 0;
        }

        $scope.submitForm = function() {
            //校验 $scope.channelAddParams
            /*if ($scope.app.enablers.length == 0) {
             toastr.warning("请至少选择一个支持能力！");
             return;
             }*/

            debugger
            repo.add(channelAddConf, $scope.channelAddParams).then(function(data) {
                $location.path(channelAddConf.url);
            });
        };
    };

    infoAddCtrl.$inject = injectParams;
    app.register.controller('infoAddCtrl', infoAddCtrl);
});
