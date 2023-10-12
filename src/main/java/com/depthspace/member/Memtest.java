package com.depthspace.member;

import java.util.List;
import java.util.Scanner;

public class Memtest {
	public static void main(String[] args) {
		MemJDBCDAO m = new MemJDBCDAO();

		System.out.println("所有的會員列表：");
		List<MemVO> all = m.getAll();
		for (MemVO vo : all) {
			System.out.println("memId:" + vo.getMemId());
           System.out.println("memAcc:" + vo.getMemAcc());
           System.out.println("memPwd:" + vo.getMemPwd());
           System.out.println("memName:" + vo.getMemName());
           System.out.println("memIdentity:" + vo.getMemIdentity());
           System.out.println("memBth:" + vo.getMemBth());
           System.out.println("memSex:" + vo.getMemSex());
           System.out.println("memEmail:" + vo.getMemEmail());
          System.out.println("memTel:" + vo.getMemTel());
          System.out.println("memAdd:" + vo.getMemAdd());
          System.out.println("accStatus:" + vo.getAccStatus());
          System.out.println("memPoint:" + vo.getMemPoint());
           System.out.println("===================================");
       }
			System.out.println("已取得全部資料");

		}
	}

