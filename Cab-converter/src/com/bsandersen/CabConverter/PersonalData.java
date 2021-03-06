package com.bsandersen.CabConverter;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * This object provides a portion of the UI relating to the display, retrieveal,
 * and saving of personal data. This object is a Singleton; there can be only one.
 * 
 * @author B. Scott Andersen (NE1RD)
 */

/*
 * CabConverter by B. Scott Andersen (NE1RD) is licensed under a 
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 */
public class PersonalData extends JScrollPane {
	/**
	 * Fulfill the needs of our parent
	 */
	private static final long serialVersionUID = 1L;
	
	private static PersonalData me = null;
	
	/**
	 * Obtain a reference to this singleton through this method.
	 * @return The reference to this object
	 */
	public static PersonalData getInstance() {
		return me;
	}
	
	/*
	 * These are the labels the correspond to the array of text fields
	 * generated in the constructor.
	 */
	
	
	/**
	 * String key for our call sign. Used by both preferences save and XML expansion.
	 */
	public static final String key_MyCallsign = "MyCallsign";
	
	/**
	 * String key for our name. Used by both preferences save and XML expansion.
	 */
	public static final String key_Name = "Name";
	
	/**
	 * String key for our address (line 1). Used by both preferences save and XML expansion.
	 */
	public static final String key_Address1 = "Address1";
	
	/**
	 * String key for our address (line 2). Used by both preferences save and XML expansion.
	 */
	public static final String key_Address2 = "Address2";
	
	/**
	 * String key for our city. Used by both preferences save and XML expansion.
	 */
	public static final String key_City = "City"; 
	
	/**
	 * String key for our state. Used by both preferences save and XML expansion.
	 */
	public static final String key_State = "State";
	
	/**
	 * String key for our ZIP code or postal code. Used by both preferences save and XML expansion.
	 */
	public static final String key_Postal = "PostalCode";
	
	/**
	 * String key for our country. Used by both preferences save and XML expansion.
	 */
	public static final String key_Country = "Country";
	
	/**
	 * String key for our email address. Used by both preferences save and XML expansion.
	 */
	public static final String key_Email = "Email";
	
	/**
	 * String key for our IOTA designator. Used by both preferences save and XML expansion.
	 */
	public static final String key_IOTA = "IOTA";
	
	/**
	 * String key for our island name. Used by both preferences save and XML expansion.
	 */
	public static final String key_Island = "Island";
	
	/**
	 * String key for our ARRL section. Used by both preferences save and XML expansion.
	 */
	public static final String key_ARRLsection = "ARRLsection";
	
	/**
	 * String key for our CQ zone. Used by both preferences save and XML expansion.
	 */
	public static final String key_Zone = "Zone";
	
	/**
	 * String key for our contest club name. Used by both preferences save and XML expansion.
	 */
	public static final String key_Club = "Club";
	
	/*
	 * These are used as an index into the array of text fields created below.
	 */
	private enum DataIndex {
		MYCALLSIGN, NAME, ADDRESS1, ADDRESS2, CITY, PROVINCE, POSTALCODE, COUNTRY,
		EMAIL, IOTADESGINATOR, ISLANDNAME, ARRLSECTION, CQZONE, CLUB,
		LAST
	}
	
	/*
	 * These are the labels the correspond to the array of text fields
	 * generated in the constructor.
	 */
	private static String labels[] = {
		"Callsign", "Name", "Address", "Address", "City", 
		"State/Province", "Postal Code", "Country",
		"EMAIL", 
		"IOTA designation", "Island Name", 
		"ARRL Section", "CQ Zone", 
		"Club"
	};
	
	/*
	 * These are the keyword/keys that can be used in macros inside an XML file.
	 */
	private static String keys[] = {
		key_MyCallsign,
		key_Name,
		key_Address1,
		key_Address2,
		key_City,
		key_State,
		key_Postal,
		key_Country,
		key_Email,
		key_IOTA,
		key_Island,
		key_ARRLsection,
		key_Zone,
		key_Club
	};
	
	/*
	 * These values are used to set the width of the text fields generated dynamically below
	 */
	private int textWidths[] = {
			100, 200, 200, 200, 200, 
			100, 100, 100,
			200,
			70, 200,
			70, 50,
			200
	};
	
	/*
	 * This is an array of text fields that are presented in the UI to hold user preferences.
	 */
	private JTextField fields[];
	
	/**
	 * Constructor
	 * 
	 * This is the upper left area of the UI that has all of the personal
	 * information (that doesn't change much, if at all) such as name,
	 * callsign, etc. Labels and text fields are arrayed for entry.
	 * The PreferencesManager saves and restores these fields.
	 */
	PersonalData() {
		me = this;	// Enable the Singleton getInstance() method to work.
		
		int	yPosition = 6;
		int height = 30;
		int labelWidth = 110;
		int x1position = 6;
		int x2position = 120;
				
		fields = new JTextField[DataIndex.LAST.ordinal()];

		// We will use absolute positioning. So, no layout manager.
		setLayout(null);
		
		// Walk through the fields and place them one after another
		for (int i = 0; i < DataIndex.LAST.ordinal(); i++) {
			
			// Create and position the prompting label
			JLabel lab = new JLabel(labels[i]);
			lab.setBounds(x1position, yPosition, labelWidth, height);
		    add(lab);
		    
		    fields[i] = new JTextField();
		    fields[i].setBounds(x2position, yPosition, textWidths[i], height);
		    fields[i].setName(keys[i]);
		    add(fields[i]);
		    yPosition += height;
		}
		setSize(200,450);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
	}

	/**
	 * This method obtains the value in the UI for the given element name
	 * @param key The element name we are seeking (it will be a UI component)
	 * @return The string representing the value in the UI element, empty string if not found.
	 */
	public String getExpandedValue(String key) {
		String s = "";
		Component c = null;
		Component allComponents[] = getComponents();
		
		for (int i = 0; i < allComponents.length; i++) {
			c = allComponents[i];

			String name = c.getName();
			if ((name != null) && (name.compareTo(key) == 0)) {
				if (c instanceof JTextField) {
					JTextField t = (JTextField)c;
					s = new String(t.getText());
					break;
				} else if (c instanceof JComboBox) {
					JComboBox b = (JComboBox)c;
					s = new String((String)b.getSelectedItem());
					break;
				} else {
					// ERROR!
				}
			} // if name matches key
		} // for all components
		
		return s;
	}
	/*
	 * getters
	 */
	/**
	 * Getter
	 * @return our callsign
	 */
	public final String getMyCallSign() {
		return new String(fields[DataIndex.MYCALLSIGN.ordinal()].getText());
	}

	/** 
	 * Getter
	 * @return our full name
	 */
	public final String getName() {
		return new String(fields[DataIndex.NAME.ordinal()].getText());
	}
	
	/**
	 * Getter
	 * @return the first line of our address
	 */
	public final String getAddress1() {
		return new String(fields[DataIndex.ADDRESS1.ordinal()].getText());
	}
	
	/**
	 * Getter
	 * @return the second line of our address
	 */
	public final String getAddress2() {
		return new String(fields[DataIndex.ADDRESS2.ordinal()].getText());
	}
	
	/**
	 * Getter
	 * @return our city
	 */
	public final String getCity() {
		return new String(fields[DataIndex.CITY.ordinal()].getText());
	}
	
	/**
	 * Getter
	 * @return our state or province
	 */
	public final String getProvince() {
		return new String(fields[DataIndex.PROVINCE.ordinal()].getText());
	}
	
	/**
	 * Getter
	 * @return our zip or postal code
	 */
	public final String getPostalCode() {
		return new String(fields[DataIndex.POSTALCODE.ordinal()].getText());
	}
	
	/**
	 * Getter
	 * @return our country
	 */
	public final String getCountry() {
		return new String(fields[DataIndex.COUNTRY.ordinal()].getText());
	}
	
	/**
	 * Getter
	 * @return our email address
	 */
	public final String getEmail() {
		return new String(fields[DataIndex.EMAIL.ordinal()].getText());
	}
	
	/**
	 * Getter
	 * @return our IOTA island designator in the form XX-nnn
	 */
	public final String getIotaDesignator() {
		return new String(fields[DataIndex.IOTADESGINATOR.ordinal()].getText());
	}
	
	/**
	 * Getter
	 * @return The RSGB recognized island name associated with the designator
	 */
	public final String getIslandName() {
		return new String(fields[DataIndex.ISLANDNAME.ordinal()].getText());
	}
	
	/**
	 * Getter
	 * @return our ARRL section
	 */
	public final String getArrlSection() {
		return new String(fields[DataIndex.ARRLSECTION.ordinal()].getText());
	}
	
	/**
	 * Getter
	 * @return our CQ zone
	 */
	public final String getCqZone() {
		return new String(fields[DataIndex.CQZONE.ordinal()].getText());
	}
	
	/**
	 * Getter
	 * @return the contesting club we belong to
	 */
	public final String getClub() {
		return new String(fields[DataIndex.CLUB.ordinal()].getText());
	}	
	
	/*
	 * Setters
	 * 
	 */
	/**
	 * Setter
	 * @param s our callsign to save
	 */
	public void setCallSign(String s) {
		fields[DataIndex.MYCALLSIGN.ordinal()].setText(new String(s));
	}
	
	/**
	 * Setter
	 * @param s our name to save
	 */
	public void setName(String s) {
		fields[DataIndex.NAME.ordinal()].setText(new String(s));
	}
	
	/**
	 * Setter
	 * @param s the first line of our address to save
	 */
	public void setAddress1(String s) {
		fields[DataIndex.ADDRESS1.ordinal()].setText(new String(s));
	}
	
	/**
	 * Setter
	 * @param s the second line of our address to save
	 */
	public void setAddress2(String s) {
		fields[DataIndex.ADDRESS2.ordinal()].setText(new String(s));
	}
	
	/**
	 * Setter
	 * @param s our city to save
	 */
	public void setCity(String s) {
		fields[DataIndex.CITY.ordinal()].setText(new String(s));
	}
	
	/**
	 * Setter
	 * @param s our state or province to save
	 */
	public void setProvince(String s) {
		fields[DataIndex.PROVINCE.ordinal()].setText(new String(s));
	}
	
	/**
	 * Setter
	 * @param s our ZIP or postal code to save
	 */
	public void setPostalCode(String s) {
		fields[DataIndex.POSTALCODE.ordinal()].setText(new String(s));
	}
	
	/**
	 * Setter
	 * @param s our country to save
	 */
	public void setCountry(String s) {
		fields[DataIndex.COUNTRY.ordinal()].setText(new String(s));
	}
	
	/**
	 * Setter
	 * @param s our email address to save
	 */
	public void setEmail(String s) {
		fields[DataIndex.EMAIL.ordinal()].setText(new String(s));
	}
	
	/**
	 * Setter
	 * @param s our IOTA designator to save
	 */
	public void setIotaDesignator(String s) {
		fields[DataIndex.IOTADESGINATOR.ordinal()].setText(new String(s));
	}
	
	/**
	 * Setter
	 * @param s our island name associated with the designator to save
	 */
	public void setIslandName(String s) {
		fields[DataIndex.ISLANDNAME.ordinal()].setText(new String(s));
	}
	
	/**
	 * Setter
	 * @param s our ARRL section to save
	 */
	public void setArrlSection(String s) {
		fields[DataIndex.ARRLSECTION.ordinal()].setText(new String(s));
	}
	
	/**
	 * Setter
	 * @param s our CQ zone to save
	 */
	public void getCqZone(String s) {
		fields[DataIndex.CQZONE.ordinal()].setText(new String(s));
	}
	
	/**
	 * Setter
	 * @param s our contesting radio club to save
	 */
	public void getClub(String s) {
		fields[DataIndex.CLUB.ordinal()].setText(new String(s));
	}	
}