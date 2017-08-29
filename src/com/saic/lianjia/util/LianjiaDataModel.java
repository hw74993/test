/**
 * 
 */
package com.saic.lianjia.util;

import java.util.List;

/**
 * @author tansheng-7
 *
 */
public class LianjiaDataModel {
	private List<LJData> dataList;

	public class LJData {
		private String currentType;
		private String dataId;
		private String dealAvgPrice;
		private String latitude;
		private String longitude;
		private String saleAvgPrice;
		private String saleTotal;
		private String showName;
		private String sort;
		private String type;

		public String getCurrentType() {
			return currentType;
		}

		public void setCurrentType(String currentType) {
			this.currentType = currentType;
		}

		public String getDataId() {
			return dataId;
		}

		public void setDataId(String dataId) {
			this.dataId = dataId;
		}

		public String getDealAvgPrice() {
			return dealAvgPrice;
		}

		public void setDealAvgPrice(String dealAvgPrice) {
			this.dealAvgPrice = dealAvgPrice;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		public String getSaleAvgPrice() {
			return saleAvgPrice;
		}

		public void setSaleAvgPrice(String saleAvgPrice) {
			this.saleAvgPrice = saleAvgPrice;
		}

		public String getSaleTotal() {
			return saleTotal;
		}

		public void setSaleTotal(String saleTotal) {
			this.saleTotal = saleTotal;
		}

		public String getShowName() {
			return showName;
		}

		public void setShowName(String showName) {
			this.showName = showName;
		}

		public String getSort() {
			return sort;
		}

		public void setSort(String sort) {
			this.sort = sort;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}

	public List<LJData> getDataList() {
		return dataList;
	}

	public void setDataList(List<LJData> dataList) {
		this.dataList = dataList;
	}

}
