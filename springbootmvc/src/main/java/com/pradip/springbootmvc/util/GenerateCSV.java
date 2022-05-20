package com.pradip.springbootmvc.util;

import java.io.FileWriter;
import com.pradip.springbootmvc.model.Address;
import com.pradip.springbootmvc.model.User;
import com.pradip.springbootmvc.service.UserService;


public class GenerateCSV {

	public boolean printCSV(UserService us) {
		String filename = "csvUsersReports.csv";
		
		try
		{
		FileWriter fw = new FileWriter(filename,true);
		fw.append("ID,Name,Mobile Number,Email,Hobby,Gender,Birthdate,Add ID,Address,City,State,Pincode\n");
		
		for(User csvUser:us.getAllUsers()) {
			System.out.println("=> "+csvUser);
			fw.append("\n"+csvUser.getId()+','+csvUser.getName()+','+csvUser.getMobile()+','+csvUser.getEmail()+','+csvUser.getHobby()+','+csvUser.getGender()+','+csvUser.getBirthdate()+',');
			for(Address addModelObj:csvUser.getAddress()) {
				fw.append(String.valueOf(addModelObj.getId())+','+addModelObj.getGeneral().replace(',', ' ')+','+addModelObj.getCity()+','+addModelObj.getState()+','+addModelObj.getPincode()+','+"\n,,,,,,,");
			}
		}
		fw.flush();
		fw.close();
		
		} 
		catch(RuntimeException c) {
			System.out.println("csv exp : "+c);
			return false;
		}
		catch (Exception e) {
			System.out.println("csv exp : "+e);
			return false;
		}
		return true;
	}
		
}