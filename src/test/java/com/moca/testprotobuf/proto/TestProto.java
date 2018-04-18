package com.moca.testprotobuf.proto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.moca.testprotobuf.proto.ProtoDemo.Student;
import com.moca.testprotobuf.proto.ProtoDemo.Student.PhoneNumber;

public class TestProto {
	
	
	public static void main(String[] args) {
		ProtoDemo.Student.Builder builder = ProtoDemo.Student.newBuilder();
		builder.setId(1);
		builder.setName("name1");
		builder.setEmail("xxx@qq.com");
		builder.setSex(ProtoDemo.Student.Sex.MAN);
		ProtoDemo.Student.PhoneNumber.Builder phb = ProtoDemo.Student.PhoneNumber.newBuilder();
		phb.setNumber("110");
		phb.setType(ProtoDemo.Student.PhoneType.HOME);
		PhoneNumber pn = phb.build();
		builder.addPhone(pn);
		
		PhoneNumber pn2 = phb.build();
		builder.addPhone(pn2);
		
		Student stu = builder.build();
		
		byte[] stuByte = stu.toByteArray();
		
		
		try {
			Student stu2 = ProtoDemo.Student.parseFrom(stuByte);
			
			FileOutputStream fo = new FileOutputStream("C:\\Users\\moca\\test.proto");
			stu2.writeTo(fo);
			System.out.println(stu2.toString());
			
			Student stu3 = Student.parseFrom(new FileInputStream("C:\\\\Users\\\\moca\\\\test.proto"));
//			System.out.println(stu3.getName());
//			System.out.println(stu3.getEmail());
//			System.out.println(stu3.getId());
//			System.out.println(stu3.getPhoneCount());
//			System.out.println(stu3.getPhoneList());
			List<PhoneNumber> phoneList = stu3.getPhoneList();
			phoneList.stream().forEach(phone -> System.out.println(phone.getNumber()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
