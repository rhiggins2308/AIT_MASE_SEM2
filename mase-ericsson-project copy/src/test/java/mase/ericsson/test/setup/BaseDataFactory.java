package mase.ericsson.test.setup;

import mase.ericsson.entities.BaseData;

public class BaseDataFactory {
    
    public static BaseData createSampleBaseData() {
        return new BaseData(new Integer(123), null, new Integer(77), new Integer(77), new Integer(77), new Integer(77),
                new Integer(77), new Integer(77), new Integer(77), "abc", new Long(77), new Long(77), new Long(77), new Long(77));
    }

}
