package com.zt.pugongyingapi.utils;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsSendUtil {

    private static IAcsClient initClient() throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", Constant.APPKEY, Constant.APPSECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", Constant.PRODUCT_NAME, Constant.SMS_SEND_URL);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        return acsClient;
    }


    private static SendSmsRequest sendValiableMsg(String phone, String templateCode, String code) {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phone);
        request.setSignName(Constant.SIGN_NAME);
        request.setTemplateCode(templateCode);
        return request;
    }



    public static boolean sendMessage(String phone,String code,String templateCode) throws ClientException{
        if (null == templateCode)
            templateCode = Constant.VALITE_CODE_TEMPLATE_CODE;
        IAcsClient client = initClient();
        SendSmsRequest request = null;
        switch (templateCode) {
            case "SMS_172886259":
                request = sendValiableMsg(phone,code,templateCode);
                request.setTemplateParam("{\"code\":\""+code+"\"}");
                break;
        }
        SendSmsResponse response = client.getAcsResponse(request);
        if (response.getCode().equals("OK")){
            log.info("给{}发送短信成功，信息：{}",phone,response.getMessage());
            return true;
        }else {
            log.error("发送失败，错误码：{},信息：{}",response.getCode(),response.getMessage());
            return false;
        }


    }
}
