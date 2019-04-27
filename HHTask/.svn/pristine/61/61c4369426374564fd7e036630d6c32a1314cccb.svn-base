package com.task.util.license;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;

/**
 * LicenseManagerÈİÆ÷Àà
 * @author melina
 */
public class LicenseManagerHolder {
	
	private static LicenseManager licenseManager;
 
	public static LicenseManager getLicenseManager(LicenseParam licenseParams) {
    	if (licenseManager == null) {
    		licenseManager = new LicenseManager(licenseParams);
    	}
    	return licenseManager;
    }
}