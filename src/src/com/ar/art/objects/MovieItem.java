package com.ar.art.objects;

import java.sql.Date;

public class MovieItem extends Item {

	
	private String formats;
	private String actors;
	private String lang;
	private String subtitles;
	private String region;
	private String aspect;
	private int discN;
	private Date release;
	private int runtime;
	private String ASIN;

	public MovieItem(int id, int sales, String name, int categ, int scateg, double price2, String imgUrl) {
		super(id, sales, name, categ, scateg, price2, imgUrl);
	}
	

	
	public void addInfo(String actors,String formats, String lang, String subtitles, String region, String aspect, int discN, Date release, int runtime, String aSIN2){
		this.actors=actors;
		this.formats=formats;
		this.lang=lang;
		this.subtitles=subtitles;
		this.region=region;
		this.aspect=aspect;
		this.discN=discN;
		this.release=release;
		this.runtime=runtime;
		this.ASIN=aSIN2;
		
	}

	@Override //FOR TESTING ONLY, CHANGE TO A GOOD ONE
	public String getInfo() {
		return "Actors: "+actors+"Formats: "+formats+"Language: "+lang;
	}
	public String getFormats() {
		return formats;
	}

	public String getActors() {
		return actors;
	}

	public String getLang() {
		return lang;
	}

	public String getSubtitles() {
		return subtitles;
	}

	public String getRegion() {
		return region;
	}

	public String getAspect() {
		return aspect;
	}

	public int getDiscN() {
		return discN;
	}

	public Date getRelease() {
		return release;
	}

	public int getRuntime() {
		return runtime;
	}

	public String getASIN() {
		return ASIN;
	}



}
