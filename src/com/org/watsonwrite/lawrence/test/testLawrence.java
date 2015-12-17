package com.org.watsonwrite.lawrence.test;

import java.io.IOException;
import com.org.watsonwrite.lawrence.Lawrence;

public class testLawrence {

	public static void main(String[] args) throws IOException {
		Lawrence lawrence = new Lawrence();
		
		System.out.println(lawrence.getSyllable("hyphenation")); // 3
		System.out.println(lawrence.getSyllable("computer")); // 4

	}

}
