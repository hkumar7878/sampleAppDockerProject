package com.backbase.experiment;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.backbase.utilLibrary.MonitoringMail;
import com.backbase.utilLibrary.TestConfig;

public class TestLocalAddress {

	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {
		
		MonitoringMail mail = new MonitoringMail();
		
		System.out.println(InetAddress.getLocalHost().getHostAddress());
		String messageBody="http://" + InetAddress.getLocalHost().getHostAddress()
				+":8080/job/SeleniumDockerProject1/Extent_20Report/";
		System.out.println(messageBody);
		
		mail.sendMail(TestConfig.server, TestConfig.from,TestConfig.to, TestConfig.subject, messageBody);

	}

}
