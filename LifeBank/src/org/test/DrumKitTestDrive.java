package org.test;


class  DrumKit {
	boolean topHat = false;
	boolean snare = false;
	
	void playSnare() {
		System.out.println("bang bang ba-bang");
	}
	
	void playTopHat() {
		System.out.println("ding ding da-ding");
	}
}

class  DrumKitTestDrive {
	public static void main (String[] arg) {
		DrumKit d = new DrumKit();
		
		d.snare = true;
		d.topHat = true;
		if(d.snare == true && d.topHat == true) {
			d.playSnare();
			d.playTopHat();
		}
	}
}