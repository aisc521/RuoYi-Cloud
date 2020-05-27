package com.ruoyi.common.core.utils.mail;



import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


/**
 * 发送邮件
 * create by  崔帅
 * 20200506
 */
public class SendFileMail {
    /**
     * @描述：发送邮件方法
     * @作者：cuishuai
     * @param smtpCom            smtpCom（smtp.163.com）
     * @param SendEmailAddress   发送者邮箱地址 18210301859@163.com
     * @param reciveEmailAddress 接受者邮箱地址
     * @param Theme              邮件主题：
     * @param Content            邮件内容：
     * @param fileAddress        附件地址
     * @param EnclosureName      附件名称
     */
    public  void SendMail(String smtpCom, final String SendEmailAddress, String reciveEmailAddress, String userName, String password,
                          String Theme, String Content, List<String> fileAddress, String EnclosureName) throws MessagingException, javax.mail.MessagingException {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        //创建一个配置文件保存并读取信息
        System.setProperty("mail.mime.splitlongparameters","false");
        Properties properties = new Properties();
        //设置邮件服务器的地址
        properties.setProperty("mail.smtp.host", smtpCom); // 指定的smtp服务器
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.transport.protocol", "smtp");//设置发送邮件使用的协议
        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        //=================================准备工作完毕
        //1.创建一个session会话对象；
        //创建Session对象,session对象表示整个邮件的环境信息
        Session session = Session.getInstance(properties);
        //可以通过session开启Dubug模式，查看所有的过程
        session.setDebug(true);
        //2.获取连接对象，通过session对象获得Transport，需要捕获或者抛出异常；
        Transport tp = session.getTransport();
        //3.连接服务器,需要抛出异常；
        tp.connect(smtpCom,SendEmailAddress,"shuang521");
        //4.连接上之后我们需要发送邮件；
        MimeMessage mimeMessage = TextMail(session,SendEmailAddress,reciveEmailAddress,Theme,Content,fileAddress,EnclosureName);
        //5.发送邮件
        tp.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        //6.关闭连接
        tp.close();
    }
    public  MimeMessage TextMail(Session session,String SendEmailAddress,String reciveEmailAddress,String theme,String Content,
                                 List<String> fileAddress,String EnclosureName) throws MessagingException, javax.mail.MessagingException {
        //消息的固定信息
        MimeMessage mimeMessage = new MimeMessage(session);
        //mimeMessage.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
        //邮件发送人
        mimeMessage.setFrom(new InternetAddress(SendEmailAddress));
        //邮件接收人，可以同时发送给很多人，我们这里只发给自己；
        InternetAddress[] sendTo = InternetAddress.parse(reciveEmailAddress);
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO, sendTo);
        mimeMessage.setSubject(theme); //邮件主题
  /*
        编写邮件内容
        1.图片
        2.附件
        3.文本
         */
        //文本
        MimeMultipart allFile =new MimeMultipart();
        MimeBodyPart body2 = new MimeBodyPart();
        body2.setContent(Content,"text/html;charset=utf-8");
        //附件
        if(fileAddress != null){
            for (int i = 0; i < fileAddress.size(); i++) {
                File file = new File(fileAddress.get(i));
                FileDataSource fds = new FileDataSource(file);
                BodyPart fileBodyPart = new MimeBodyPart();
                fileBodyPart.setDataHandler(new DataHandler(fds));
                try {
                    fileBodyPart.setFileName(MimeUtility.encodeText(file.getName()));//如果附件有中文通过转换没有问题了
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                allFile.addBodyPart(fileBodyPart); //附件
            }
        }

        //拼装邮件正文内容
        MimeMultipart multipart1 = new MimeMultipart();
        multipart1.addBodyPart(body2);
        multipart1.setSubType("related"); //1.文本和图片内嵌成功！
        //multipart1.addBodyPart(fileBodyPart);

        MimeBodyPart contentText =  new MimeBodyPart();
        contentText.setContent(multipart1);
        //拼接附件


        allFile.addBodyPart(contentText);//正文
        allFile.setSubType("mixed"); //正文和附件都存在邮件中，所有类型设置为mixed；
        //放到Message消息中
        mimeMessage.setContent(allFile);
        mimeMessage.saveChanges();//保存修改
        return mimeMessage;

    }
    public static InternetAddress[] Address(String str) {
        //多个接收账号
        InternetAddress[] address = null;
        try {
            List list = new ArrayList();//不能使用string类型的类型，这样只能发送一个收件人
            String[] median = str.split(",");//对输入的多个邮件进行逗号分割
            for (int i = 0; i < median.length; i++) {
                list.add(new InternetAddress(median[i]));
            }
            address = (InternetAddress[]) list.toArray(new InternetAddress[list.size()]);

        } catch (AddressException e) {
            e.printStackTrace();
        }
        return address;
    }
}
