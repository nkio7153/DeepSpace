package com.depthspace.member.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.depthspace.member.model.jdbc.MemJDBCDAO;

public class Memtest {
	public static void main(String[] args) {
		MemJDBCDAO m = new MemJDBCDAO();

//		System.out.println("所有的會員列表：");
//		List<MemVO> all = m.getAll();
//		for (MemVO vo : all) {
//			System.out.println("memId:" + vo.getMemId());
//           System.out.println("memAcc:" + vo.getMemAcc());
//           System.out.println("memPwd:" + vo.getMemPwd());
//           System.out.println("memName:" + vo.getMemName());
//           System.out.println("memIdentity:" + vo.getMemIdentity());
//           System.out.println("memBth:" + vo.getMemBth());
//           System.out.println("memSex:" + vo.getMemSex());
//           System.out.println("memEmail:" + vo.getMemEmail());
//          System.out.println("memTel:" + vo.getMemTel());
//          System.out.println("memAdd:" + vo.getMemAdd());
//          System.out.println("accStatus:" + vo.getAccStatus());
//          System.out.println("memPoint:" + vo.getMemPoint());
//          System.out.println("memPoint:" + vo.getMemPoint());
//           System.out.println("===================================");
//       }
//			System.out.println("已取得全部資料");

			MemVO memvo = new MemVO();
			
			memvo.setMemAcc("user005");
			memvo.setMemPwd("lisa");
			memvo.setMemName("余八");
			memvo.setMemIdentity("D221987634");
			LocalDate localDate = LocalDate.of(1995, 3, 28);
			Date date = Date.valueOf(localDate);
			memvo.setMemBth(date);
			memvo.setMemSex((byte)1);
			memvo.setMemEmail("user005@email.com");
			memvo.setMemTel(956789012);
			memvo.setAccStatus((byte) 2);
			memvo.setMemPoint(1300);
			m.update(memvo);
			System.out.println("更新成功");
		}
	}

