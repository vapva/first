package beans;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;

public class WaterParam {
	private long ID;
	private Date pDate;
	private String ControlSerialNumber;
	private double ParameterValue;
	
	public WaterParam(long i, Date pDate, String controlSerialNumber,
			Double double1) {
		super();
		ID = i;
		this.pDate = pDate;
		ControlSerialNumber = controlSerialNumber;
		ParameterValue = double1;
	}
	
	public long getID() {
		return ID;
	}

	public void setID(long iD2) {
		ID = iD2;
	}
	public Date getpDate() {
		return pDate;
	}
	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}
	public String getControlSerialNumber() {
		return ControlSerialNumber;
	}
	public void setControlSerialNumber(String controlSerialNumber) {
		ControlSerialNumber = controlSerialNumber;
	}
	public Double getParameterValue() {
		return ParameterValue;
	}
	public void setParameterValue(Double parameterValue) {
		ParameterValue = parameterValue;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WaterParam [ID=");
		builder.append(ID);
		builder.append(", ");
		if (pDate != null) {
			builder.append("pDate=");
			builder.append(pDate);
			builder.append(", ");
		}
		if (ControlSerialNumber != null) {
			builder.append("ControlSerialNumber=");
			builder.append(ControlSerialNumber);
			builder.append(", ");
		}
		builder.append("ParameterValue=");
		builder.append(ParameterValue);
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((ControlSerialNumber == null) ? 0 : ControlSerialNumber
						.hashCode());
		result = prime * result + (int) (ID ^ (ID >>> 32));
		long temp;
		temp = Double.doubleToLongBits(ParameterValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((pDate == null) ? 0 : pDate.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		DateFormat df=DateFormat.getDateInstance();
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof WaterParam)) return false;
		WaterParam other = (WaterParam) obj;
		if (ControlSerialNumber == null) {
			if (other.ControlSerialNumber != null) return false;
		} else if (!ControlSerialNumber.equals(other.ControlSerialNumber))
			return false;
		if (ID != other.ID) return false;
		if (Double.doubleToLongBits(ParameterValue) != Double
				.doubleToLongBits(other.ParameterValue)) return false;
		if (pDate == null) {
			if (other.pDate != null) return false;
		} else if (!(df.format(pDate).equals(
				df.format(other.pDate))))return false;
		return true;
	}


	
}
