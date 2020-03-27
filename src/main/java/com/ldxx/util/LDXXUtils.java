package com.ldxx.util;

import java.io.File;
import java.util.UUID;

public class LDXXUtils {
	public static String getUUID32() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String getUUID12() {
        return UUID.randomUUID().toString().replace("-", "").substring(0,12);
	}
}
