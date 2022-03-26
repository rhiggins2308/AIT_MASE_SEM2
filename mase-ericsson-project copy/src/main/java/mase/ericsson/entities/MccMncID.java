package mase.ericsson.entities;

import java.io.Serializable;

public class MccMncID implements Serializable {

	private static final long serialVersionUID = 4850018332983462376L;
	
	public Integer mcc;
    public Integer mnc;

    public MccMncID() {
	}
    
	public MccMncID(Integer mcc, Integer mnc) {
		this.mcc = mcc;
		this.mnc = mnc;	
	}
		
	public Integer getMcc() {
		return mcc;
	}

	public Integer getMnc() {
		return mnc;
	}

	@Override
	public boolean equals(Object obj) {
    	
		if (this == obj) return true;
		
		if (obj == null || obj.getClass() != this.getClass()) return false;
		
		MccMncID checkPK = (MccMncID) obj;
		return (checkPK.mcc.intValue() == this.mcc.intValue() && checkPK.mnc.intValue() == this.mnc.intValue());
    }
    
    @Override
    public int hashCode() {
    	return (37 * (37 * (mcc^mcc)) + (mnc ^ mnc))%(2^31-1);
    }
}
