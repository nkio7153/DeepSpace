package com.depthspace.ticketorders.service;

import com.depthspace.member.model.MemVO;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import static com.depthspace.utils.Config.MYGMAIL;
import static com.depthspace.utils.Config.MYGMAIL_PASSWORD;

public class MailService {

    // 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
    public void sendOrderMail(String to, MemVO memVo, TicketOrdersVO toVo) {

        try {
            // 設定使用SSL連線至 Gmail smtp Server
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
            // ●1) 登入你的Gmail的:
            // ●2) 點選【管理你的 Google 帳戶】
            // ●3) 點選左側的【安全性】

            // ●4) 完成【兩步驟驗證】的所有要求如下:
            //     ●4-1) (請自行依照步驟要求操作之.....)

            // ●5) 完成【應用程式密碼】的所有要求如下:
            //     ●5-1) 下拉式選單【選取應用程式】--> 選取【郵件】
            //     ●5-2) 下拉式選單【選取裝置】--> 選取【Windows 電腦】
            //     ●5-3) 最後按【產生】密碼

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(MYGMAIL, MYGMAIL_PASSWORD);
                }
            });
            //創建一個MimeMessage
            Message message = new MimeMessage(session);

            //設置發件人
            message.setFrom(new InternetAddress(MYGMAIL));

            //設置收件人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // 設定信中的主旨
            message.setSubject("訂單成立通知");

            // 設定信中的內容
//            message.setText(messageText);

            // 創建一個Multipart對象，用於添加HTML內容
            Multipart multipart = new MimeMultipart();

            // 創建一個MimeBodyPart對象，並設置HTML內容
            MimeBodyPart htmlPart = new MimeBodyPart();

            String htmlContent = "<html>"
                    + "<head>"
                    + "<title>訂單已成功成立</title>"
                    + "</head>"
                    + "<body>"
                    + "<h2>訂單已成功成立</h2>"
                    + "<p>親愛的"+memVo.getMemName()+"，</p>"
                    + "<p>感謝您選擇我們的服務！我們很高興通知您，您的訂單已經成功成立。</p>"
                    + "<h3>訂單詳細資訊：</h3>"
                    + "<ul>"
                    + "<li><strong>訂單編號：</strong> "+toVo.getOrderId()+"</li>"
                    + "<li><strong>訂購日期：</strong> "+toVo.getOrderDate()+"</li>"
                    + "<li><strong>付款金額：</strong> $"+toVo.getAmountPaid()+"</li>"
                    + "</ul>"
//                    + "<h3>訂單明細：</h3>"
//                    + "<table border=\"1\">"
//                    + "<tr>"
//                    + "<th>商品名稱</th>"
//                    + "<th>數量</th>"
//                    + "<th>單價</th>"
//                    + "<th>小計</th>"
//                    + "</tr>"
//                    + "<tr>"
//                    + "<td>商品1</td>"
//                    + "<td>2</td>"
//                    + "<td>$50.00</td>"
//                    + "<td>$100.00</td>"
//                    + "</tr>"
//                    + "<tr>"
//                    + "<td>商品2</td>"
//                    + "<td>1</td>"
//                    + "<td>$30.00</td>"
//                    + "<td>$30.00</td>"
//                    + "</tr>"
//                    + "</table>"
                    + "<p>您可以透過<a href=\"http://localhost:8081/DepthSpace/\">官網首頁連結</a>至訂單管理查看詳細訂單信息。</p>"
                    + "<p>如果您有任何疑問或需要協助，請隨時與我們聯絡。我們感謝您的業務，並期待為您提供最優質的服務。</p>"
                    + "<p>再次感謝您的訂購！</p>"
                    + "<p>祝您有一個美好的一天！</p>"
                    + "<p>最誠摯的問候，<br>Depthspace</p>"
                    + "<img src='https://filedn.eu/l0aeIVKWqXgS9G0JKMwoFXJ/logo.png'>"
                    + "</body>"
                    + "</html>";

            htmlPart.setContent(htmlContent, "text/html; charset=utf-8");

            // 將HTML內容添加到Multipart中
            multipart.addBodyPart(htmlPart);

            // 設置整個郵件的內容
            message.setContent(multipart);

            // 發送郵件
            Transport.send(message);

            System.out.println("傳送成功!");
        } catch (MessagingException e) {
            System.out.println("傳送失敗!");
            e.printStackTrace();
        }
    }

}
